import { useState, useEffect } from "react";
import { useMutation } from "@tanstack/react-query";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Switch } from "@/components/ui/switch";
import { useToast } from "@/hooks/use-toast";
import { apiRequest } from "@/lib/queryClient";
import { validateEmail, validatePhone } from "@/lib/utils";
import type { Reader, InsertReader } from "@shared/schema";

interface ReaderModalProps {
  isOpen: boolean;
  onClose: () => void;
  reader?: Reader | null;
  onSuccess: () => void;
}

export default function ReaderModal({ isOpen, onClose, reader, onSuccess }: ReaderModalProps) {
  const [formData, setFormData] = useState<InsertReader>({
    name: "",
    email: "",
    phone: "",
    address: "",
    isActive: true,
  });

  const [errors, setErrors] = useState<Partial<Record<keyof InsertReader, string>>>({});

  const { toast } = useToast();

  useEffect(() => {
    if (reader) {
      setFormData({
        name: reader.name,
        email: reader.email,
        phone: reader.phone || "",
        address: reader.address || "",
        isActive: reader.isActive,
      });
    } else {
      setFormData({
        name: "",
        email: "",
        phone: "",
        address: "",
        isActive: true,
      });
    }
    setErrors({});
  }, [reader, isOpen]);

  const validateForm = (): boolean => {
    const newErrors: Partial<Record<keyof InsertReader, string>> = {};

    if (!formData.name.trim()) {
      newErrors.name = "Tên độc giả là bắt buộc";
    }

    if (!formData.email.trim()) {
      newErrors.email = "Email là bắt buộc";
    } else if (!validateEmail(formData.email)) {
      newErrors.email = "Email không hợp lệ";
    }

    if (formData.phone && !validatePhone(formData.phone)) {
      newErrors.phone = "Số điện thoại không hợp lệ (10-11 chữ số)";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const mutation = useMutation({
    mutationFn: async (data: InsertReader) => {
      if (reader) {
        return await apiRequest("PUT", `/api/readers/${reader.id}`, data);
      } else {
        return await apiRequest("POST", "/api/readers", data);
      }
    },
    onSuccess: () => {
      onSuccess();
      onClose();
      toast({
        title: "Thành công",
        description: reader ? "Đã cập nhật độc giả thành công" : "Đã thêm độc giả mới thành công",
      });
    },
    onError: (error: any) => {
      toast({
        title: "Lỗi",
        description: error?.message || "Không thể lưu thông tin độc giả",
        variant: "destructive",
      });
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!validateForm()) {
      toast({
        title: "Lỗi",
        description: "Vui lòng kiểm tra lại thông tin đã nhập",
        variant: "destructive",
      });
      return;
    }

    mutation.mutate(formData);
  };

  const handleInputChange = (field: keyof InsertReader, value: any) => {
    setFormData(prev => ({ ...prev, [field]: value }));
    
    // Clear error when user starts typing
    if (errors[field]) {
      setErrors(prev => ({ ...prev, [field]: undefined }));
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="max-w-2xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>
            {reader ? "Chỉnh sửa độc giả" : "Thêm độc giả mới"}
          </DialogTitle>
        </DialogHeader>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="form-field">
              <Label className="form-label">Họ và tên *</Label>
              <Input
                value={formData.name}
                onChange={(e) => handleInputChange("name", e.target.value)}
                placeholder="Nhập họ và tên..."
                className={`form-input ${errors.name ? 'border-red-500' : ''}`}
                required
              />
              {errors.name && (
                <p className="text-sm text-red-600 mt-1">{errors.name}</p>
              )}
            </div>
            <div className="form-field">
              <Label className="form-label">Email *</Label>
              <Input
                type="email"
                value={formData.email}
                onChange={(e) => handleInputChange("email", e.target.value)}
                placeholder="Nhập địa chỉ email..."
                className={`form-input ${errors.email ? 'border-red-500' : ''}`}
                required
              />
              {errors.email && (
                <p className="text-sm text-red-600 mt-1">{errors.email}</p>
              )}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="form-field">
              <Label className="form-label">Số điện thoại</Label>
              <Input
                type="tel"
                value={formData.phone}
                onChange={(e) => handleInputChange("phone", e.target.value)}
                placeholder="Nhập số điện thoại..."
                className={`form-input ${errors.phone ? 'border-red-500' : ''}`}
              />
              {errors.phone && (
                <p className="text-sm text-red-600 mt-1">{errors.phone}</p>
              )}
            </div>
            <div className="form-field">
              <Label className="form-label">Trạng thái</Label>
              <div className="flex items-center space-x-3 mt-2">
                <Switch
                  checked={formData.isActive}
                  onCheckedChange={(checked) => handleInputChange("isActive", checked)}
                />
                <Label className="text-sm font-normal">
                  {formData.isActive ? "Hoạt động" : "Không hoạt động"}
                </Label>
              </div>
            </div>
          </div>

          <div className="form-field">
            <Label className="form-label">Địa chỉ</Label>
            <Textarea
              value={formData.address}
              onChange={(e) => handleInputChange("address", e.target.value)}
              placeholder="Nhập địa chỉ..."
              rows={3}
              className="form-textarea"
            />
          </div>

          <div className="flex space-x-4 pt-4">
            <Button
              type="submit"
              className="flex-1 btn-primary"
              disabled={mutation.isPending}
            >
              {mutation.isPending ? "Đang lưu..." : (reader ? "Cập nhật độc giả" : "Thêm độc giả")}
            </Button>
            <Button
              type="button"
              variant="outline"
              className="flex-1"
              onClick={onClose}
              disabled={mutation.isPending}
            >
              Hủy
            </Button>
          </div>
        </form>
      </DialogContent>
    </Dialog>
  );
}
