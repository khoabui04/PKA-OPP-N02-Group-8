import { useState } from "react";
import { useQuery, useMutation } from "@tanstack/react-query";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Badge } from "@/components/ui/badge";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Skeleton } from "@/components/ui/skeleton";
import { useToast } from "@/hooks/use-toast";
import { queryClient } from "@/lib/queryClient";
import { apiRequest } from "@/lib/queryClient";
import { formatDate, isOverdue, calculateDaysUntilDue, getStatusColor, getStatusText } from "@/lib/utils";
import { BookOpen, Undo, AlertTriangle, History } from "lucide-react";
import type { BorrowingWithDetails, Book, Reader } from "@shared/schema";

export default function Borrowing() {
  const [borrowForm, setBorrowForm] = useState({
    readerId: "",
    bookId: "",
    borrowDate: new Date().toISOString().split('T')[0],
    dueDate: "",
  });
  const [returnForm, setReturnForm] = useState({
    borrowingId: "",
    condition: "good",
  });

  const { toast } = useToast();

  // Calculate default due date (14 days from borrow date)
  const calculateDueDate = (borrowDate: string) => {
    const date = new Date(borrowDate);
    date.setDate(date.getDate() + 14);
    return date.toISOString().split('T')[0];
  };

  // Update due date when borrow date changes
  const handleBorrowDateChange = (date: string) => {
    setBorrowForm(prev => ({
      ...prev,
      borrowDate: date,
      dueDate: calculateDueDate(date)
    }));
  };

  const { data: borrowings, isLoading: borrowingsLoading } = useQuery<BorrowingWithDetails[]>({
    queryKey: ["/api/borrowings"],
  });

  const { data: activeBorrowings } = useQuery<BorrowingWithDetails[]>({
    queryKey: ["/api/borrowings", { type: "active" }],
  });

  const { data: overdueBorrowings } = useQuery<BorrowingWithDetails[]>({
    queryKey: ["/api/borrowings", { type: "overdue" }],
  });

  const { data: books } = useQuery<Book[]>({
    queryKey: ["/api/books"],
  });

  const { data: readers } = useQuery<Reader[]>({
    queryKey: ["/api/readers"],
  });

  const createBorrowingMutation = useMutation({
    mutationFn: async (data: any) => {
      return await apiRequest("POST", "/api/borrowings", data);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["/api/borrowings"] });
      queryClient.invalidateQueries({ queryKey: ["/api/books"] });
      queryClient.invalidateQueries({ queryKey: ["/api/stats"] });
      toast({
        title: "Thành công",
        description: "Đã cho mượn sách thành công",
      });
      setBorrowForm({
        readerId: "",
        bookId: "",
        borrowDate: new Date().toISOString().split('T')[0],
        dueDate: "",
      });
    },
    onError: (error: any) => {
      toast({
        title: "Lỗi",
        description: error?.message || "Không thể cho mượn sách",
        variant: "destructive",
      });
    },
  });

  const returnBookMutation = useMutation({
    mutationFn: async (borrowingId: number) => {
      return await apiRequest("PUT", `/api/borrowings/${borrowingId}`, {
        returnDate: new Date().toISOString(),
        status: "returned",
        condition: returnForm.condition,
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["/api/borrowings"] });
      queryClient.invalidateQueries({ queryKey: ["/api/books"] });
      queryClient.invalidateQueries({ queryKey: ["/api/stats"] });
      toast({
        title: "Thành công",
        description: "Đã trả sách thành công",
      });
      setReturnForm({
        borrowingId: "",
        condition: "good",
      });
    },
    onError: (error: any) => {
      toast({
        title: "Lỗi",
        description: error?.message || "Không thể trả sách",
        variant: "destructive",
      });
    },
  });

  const handleBorrowSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!borrowForm.readerId || !borrowForm.bookId) {
      toast({
        title: "Lỗi",
        description: "Vui lòng chọn độc giả và sách",
        variant: "destructive",
      });
      return;
    }

    createBorrowingMutation.mutate({
      readerId: parseInt(borrowForm.readerId),
      bookId: parseInt(borrowForm.bookId),
      borrowDate: borrowForm.borrowDate,
      dueDate: borrowForm.dueDate,
      status: "borrowed",
    });
  };

  const handleReturnSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!returnForm.borrowingId) {
      toast({
        title: "Lỗi",
        description: "Vui lòng chọn sách cần trả",
        variant: "destructive",
      });
      return;
    }

    returnBookMutation.mutate(parseInt(returnForm.borrowingId));
  };

  const availableBooks = books?.filter(book => book.availableQuantity > 0) || [];
  const activeBooksForReturn = activeBorrowings || [];

  // Initialize due date on first render
  if (!borrowForm.dueDate && borrowForm.borrowDate) {
    setBorrowForm(prev => ({ ...prev, dueDate: calculateDueDate(prev.borrowDate) }));
  }

  return (
    <div className="space-y-6">
      <Tabs defaultValue="borrow" className="w-full">
        <TabsList className="grid w-full grid-cols-4">
          <TabsTrigger value="borrow" className="flex items-center space-x-2">
            <BookOpen className="w-4 h-4" />
            <span>Cho mượn</span>
          </TabsTrigger>
          <TabsTrigger value="return" className="flex items-center space-x-2">
            <Undo className="w-4 h-4" />
            <span>Trả sách</span>
          </TabsTrigger>
          <TabsTrigger value="overdue" className="flex items-center space-x-2">
            <AlertTriangle className="w-4 h-4" />
            <span>Quá hạn</span>
          </TabsTrigger>
          <TabsTrigger value="history" className="flex items-center space-x-2">
            <History className="w-4 h-4" />
            <span>Lịch sử</span>
          </TabsTrigger>
        </TabsList>

        <TabsContent value="borrow" className="space-y-6">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
            {/* Borrow Form */}
            <Card>
              <CardHeader>
                <CardTitle>Cho mượn sách</CardTitle>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleBorrowSubmit} className="space-y-4">
                  <div className="form-field">
                    <Label className="form-label">Độc giả *</Label>
                    <Select 
                      value={borrowForm.readerId} 
                      onValueChange={(value) => setBorrowForm(prev => ({ ...prev, readerId: value }))}
                    >
                      <SelectTrigger>
                        <SelectValue placeholder="Chọn độc giả..." />
                      </SelectTrigger>
                      <SelectContent>
                        {readers?.map((reader) => (
                          <SelectItem key={reader.id} value={reader.id.toString()}>
                            {reader.name} - {reader.email}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="form-field">
                    <Label className="form-label">Sách *</Label>
                    <Select 
                      value={borrowForm.bookId} 
                      onValueChange={(value) => setBorrowForm(prev => ({ ...prev, bookId: value }))}
                    >
                      <SelectTrigger>
                        <SelectValue placeholder="Chọn sách..." />
                      </SelectTrigger>
                      <SelectContent>
                        {availableBooks.map((book) => (
                          <SelectItem key={book.id} value={book.id.toString()}>
                            {book.title} - {book.author} (Còn: {book.availableQuantity})
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="form-field">
                    <Label className="form-label">Ngày mượn *</Label>
                    <Input
                      type="date"
                      value={borrowForm.borrowDate}
                      onChange={(e) => handleBorrowDateChange(e.target.value)}
                      className="form-input"
                    />
                  </div>

                  <div className="form-field">
                    <Label className="form-label">Ngày hẹn trả *</Label>
                    <Input
                      type="date"
                      value={borrowForm.dueDate}
                      onChange={(e) => setBorrowForm(prev => ({ ...prev, dueDate: e.target.value }))}
                      className="form-input"
                    />
                  </div>

                  <Button 
                    type="submit" 
                    className="w-full btn-primary"
                    disabled={createBorrowingMutation.isPending}
                  >
                    {createBorrowingMutation.isPending ? "Đang xử lý..." : "Xác nhận cho mượn"}
                  </Button>
                </form>
              </CardContent>
            </Card>

            {/* Statistics */}
            <Card>
              <CardHeader>
                <CardTitle>Thống kê mượn/trả</CardTitle>
              </CardHeader>
              <CardContent className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="bg-blue-50 p-4 rounded-lg">
                    <p className="text-sm text-blue-600 font-medium">Đang mượn</p>
                    <p className="text-2xl font-bold text-blue-800">
                      {activeBorrowings?.length || 0}
                    </p>
                  </div>
                  <div className="bg-red-50 p-4 rounded-lg">
                    <p className="text-sm text-red-600 font-medium">Quá hạn</p>
                    <p className="text-2xl font-bold text-red-800">
                      {overdueBorrowings?.length || 0}
                    </p>
                  </div>
                </div>
                
                <div className="space-y-2">
                  <h4 className="font-medium">Sách có sẵn</h4>
                  <div className="max-h-48 overflow-y-auto space-y-1">
                    {availableBooks.slice(0, 5).map((book) => (
                      <div key={book.id} className="flex justify-between text-sm">
                        <span className="truncate">{book.title}</span>
                        <span className="text-slate-500">{book.availableQuantity}</span>
                      </div>
                    ))}
                    {availableBooks.length > 5 && (
                      <p className="text-xs text-slate-500">
                        ...và {availableBooks.length - 5} sách khác
                      </p>
                    )}
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>
        </TabsContent>

        <TabsContent value="return" className="space-y-6">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
            {/* Return Form */}
            <Card>
              <CardHeader>
                <CardTitle>Trả sách</CardTitle>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleReturnSubmit} className="space-y-4">
                  <div className="form-field">
                    <Label className="form-label">Sách cần trả *</Label>
                    <Select 
                      value={returnForm.borrowingId} 
                      onValueChange={(value) => setReturnForm(prev => ({ ...prev, borrowingId: value }))}
                    >
                      <SelectTrigger>
                        <SelectValue placeholder="Chọn sách cần trả..." />
                      </SelectTrigger>
                      <SelectContent>
                        {activeBooksForReturn.map((borrowing) => (
                          <SelectItem key={borrowing.id} value={borrowing.id.toString()}>
                            {borrowing.bookTitle} - {borrowing.readerName}
                            {isOverdue(borrowing.dueDate) && " (QUÁ HẠN)"}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  {returnForm.borrowingId && (
                    <div className="bg-slate-50 rounded-lg p-4 space-y-2">
                      {(() => {
                        const selectedBorrowing = activeBooksForReturn.find(
                          b => b.id.toString() === returnForm.borrowingId
                        );
                        if (!selectedBorrowing) return null;
                        
                        return (
                          <>
                            <div className="flex justify-between text-sm">
                              <span className="text-slate-600">Tên sách:</span>
                              <span className="font-medium">{selectedBorrowing.bookTitle}</span>
                            </div>
                            <div className="flex justify-between text-sm">
                              <span className="text-slate-600">Độc giả:</span>
                              <span className="font-medium">{selectedBorrowing.readerName}</span>
                            </div>
                            <div className="flex justify-between text-sm">
                              <span className="text-slate-600">Ngày mượn:</span>
                              <span className="font-medium">{formatDate(selectedBorrowing.borrowDate)}</span>
                            </div>
                            <div className="flex justify-between text-sm">
                              <span className="text-slate-600">Hạn trả:</span>
                              <span className={`font-medium ${isOverdue(selectedBorrowing.dueDate) ? 'text-red-600' : ''}`}>
                                {formatDate(selectedBorrowing.dueDate)}
                                {isOverdue(selectedBorrowing.dueDate) && " (QUÁ HẠN)"}
                              </span>
                            </div>
                          </>
                        );
                      })()}
                    </div>
                  )}

                  <div className="form-field">
                    <Label className="form-label">Tình trạng sách khi trả</Label>
                    <Select 
                      value={returnForm.condition} 
                      onValueChange={(value) => setReturnForm(prev => ({ ...prev, condition: value }))}
                    >
                      <SelectTrigger>
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectItem value="good">Tốt</SelectItem>
                        <SelectItem value="fair">Khá</SelectItem>
                        <SelectItem value="damaged">Hư hỏng nhẹ</SelectItem>
                        <SelectItem value="severely_damaged">Hư hỏng nặng</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>

                  <Button 
                    type="submit" 
                    className="w-full btn-success"
                    disabled={returnBookMutation.isPending}
                  >
                    {returnBookMutation.isPending ? "Đang xử lý..." : "Xác nhận trả sách"}
                  </Button>
                </form>
              </CardContent>
            </Card>

            {/* Active Borrowings Summary */}
            <Card>
              <CardHeader>
                <CardTitle>Sách đang mượn gần đây</CardTitle>
              </CardHeader>
              <CardContent>
                <div className="space-y-3 max-h-96 overflow-y-auto">
                  {activeBooksForReturn?.slice(0, 10).map((borrowing) => (
                    <div key={borrowing.id} className="border rounded-lg p-3">
                      <div className="font-medium text-sm">{borrowing.bookTitle}</div>
                      <div className="text-xs text-slate-600">{borrowing.readerName}</div>
                      <div className="flex justify-between items-center mt-2">
                        <span className="text-xs text-slate-500">
                          Hạn: {formatDate(borrowing.dueDate)}
                        </span>
                        <Badge className={getStatusColor(isOverdue(borrowing.dueDate) ? "overdue" : "borrowed")}>
                          {isOverdue(borrowing.dueDate) ? "Quá hạn" : `Còn ${calculateDaysUntilDue(borrowing.dueDate)} ngày`}
                        </Badge>
                      </div>
                    </div>
                  )) || (
                    <div className="text-center text-slate-500 py-8">
                      Không có sách nào đang được mượn
                    </div>
                  )}
                </div>
              </CardContent>
            </Card>
          </div>
        </TabsContent>

        <TabsContent value="overdue" className="space-y-6">
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center space-x-2">
                <AlertTriangle className="w-5 h-5 text-red-600" />
                <span>Sách quá hạn</span>
              </CardTitle>
            </CardHeader>
            <CardContent>
              {overdueBorrowings && overdueBorrowings.length > 0 ? (
                <div className="overflow-x-auto">
                  <table className="w-full">
                    <thead className="bg-slate-50 border-b border-slate-200">
                      <tr>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Sách</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Độc giả</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Ngày mượn</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Hạn trả</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Quá hạn</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Thao tác</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-200">
                      {overdueBorrowings.map((borrowing) => (
                        <tr key={borrowing.id} className="table-hover">
                          <td className="py-3 px-4">
                            <div className="font-medium">{borrowing.bookTitle}</div>
                            <div className="text-sm text-slate-500">{borrowing.bookAuthor}</div>
                          </td>
                          <td className="py-3 px-4">
                            <div>{borrowing.readerName}</div>
                            <div className="text-sm text-slate-500">{borrowing.readerEmail}</div>
                          </td>
                          <td className="py-3 px-4">{formatDate(borrowing.borrowDate)}</td>
                          <td className="py-3 px-4 text-red-600">{formatDate(borrowing.dueDate)}</td>
                          <td className="py-3 px-4">
                            <Badge className="status-overdue">
                              {Math.abs(calculateDaysUntilDue(borrowing.dueDate))} ngày
                            </Badge>
                          </td>
                          <td className="py-3 px-4">
                            <Button
                              size="sm"
                              className="btn-success"
                              onClick={() => {
                                setReturnForm(prev => ({ ...prev, borrowingId: borrowing.id.toString() }));
                                // You could also automatically switch to the return tab
                              }}
                            >
                              <Undo className="w-3 h-3 mr-1" />
                              Trả sách
                            </Button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              ) : (
                <div className="text-center py-12 text-slate-500">
                  <AlertTriangle className="w-12 h-12 mx-auto mb-4 text-green-500" />
                  <p>Tuyệt vời! Không có sách nào quá hạn</p>
                </div>
              )}
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="history" className="space-y-6">
          <Card>
            <CardHeader>
              <CardTitle>Lịch sử mượn/trả</CardTitle>
            </CardHeader>
            <CardContent>
              {borrowingsLoading ? (
                <div className="space-y-3">
                  {Array.from({ length: 5 }).map((_, index) => (
                    <div key={index} className="flex space-x-4">
                      <Skeleton className="h-4 w-48" />
                      <Skeleton className="h-4 w-32" />
                      <Skeleton className="h-4 w-24" />
                      <Skeleton className="h-4 w-20" />
                    </div>
                  ))}
                </div>
              ) : borrowings && borrowings.length > 0 ? (
                <div className="overflow-x-auto">
                  <table className="w-full">
                    <thead className="bg-slate-50 border-b border-slate-200">
                      <tr>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Mã mượn</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Sách</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Độc giả</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Ngày mượn</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Hạn trả</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Ngày trả</th>
                        <th className="text-left py-3 px-4 font-medium text-slate-700">Trạng thái</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-200">
                      {borrowings.map((borrowing) => (
                        <tr key={borrowing.id} className="table-hover">
                          <td className="py-3 px-4">BR{borrowing.id.toString().padStart(3, '0')}</td>
                          <td className="py-3 px-4">
                            <div className="font-medium">{borrowing.bookTitle}</div>
                            <div className="text-sm text-slate-500">{borrowing.bookAuthor}</div>
                          </td>
                          <td className="py-3 px-4">
                            <div>{borrowing.readerName}</div>
                            <div className="text-sm text-slate-500">{borrowing.readerEmail}</div>
                          </td>
                          <td className="py-3 px-4">{formatDate(borrowing.borrowDate)}</td>
                          <td className="py-3 px-4">{formatDate(borrowing.dueDate)}</td>
                          <td className="py-3 px-4">
                            {borrowing.returnDate ? formatDate(borrowing.returnDate) : "-"}
                          </td>
                          <td className="py-3 px-4">
                            <Badge className={getStatusColor(borrowing.status)}>
                              {getStatusText(borrowing.status)}
                            </Badge>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              ) : (
                <div className="text-center py-12 text-slate-500">
                  <History className="w-12 h-12 mx-auto mb-4" />
                  <p>Chưa có lịch sử mượn/trả nào</p>
                </div>
              )}
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  );
}
