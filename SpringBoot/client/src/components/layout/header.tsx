import { Search, Bell, User } from "lucide-react";
import { useState } from "react";
import { Input } from "@/components/ui/input";

interface HeaderProps {
  currentSection: string;
}

const sectionTitles: Record<string, { title: string; breadcrumb: string }> = {
  dashboard: { title: "Dashboard", breadcrumb: "Trang chủ / Dashboard" },
  books: { title: "Quản lý Sách", breadcrumb: "Trang chủ / Quản lý Sách" },
  readers: { title: "Quản lý Độc giả", breadcrumb: "Trang chủ / Quản lý Độc giả" },
  borrowing: { title: "Mượn/Trả Sách", breadcrumb: "Trang chủ / Mượn/Trả Sách" },
  reports: { title: "Báo cáo & Thống kê", breadcrumb: "Trang chủ / Báo cáo & Thống kê" },
  settings: { title: "Cài đặt", breadcrumb: "Trang chủ / Cài đặt" },
};

export default function Header({ currentSection }: HeaderProps) {
  const [searchQuery, setSearchQuery] = useState("");
  
  const sectionInfo = sectionTitles[currentSection] || sectionTitles.dashboard;

  return (
    <header className="bg-white shadow-sm border-b border-slate-200 px-6 py-4">
      <div className="flex items-center justify-between">
        <div className="flex items-center space-x-4">
          <h2 className="text-2xl font-semibold text-slate-800">
            {sectionInfo.title}
          </h2>
          <div className="hidden sm:block">
            <nav className="flex space-x-4" aria-label="Breadcrumb">
              <span className="text-slate-500 text-sm">
                {sectionInfo.breadcrumb}
              </span>
            </nav>
          </div>
        </div>

        <div className="flex items-center space-x-4">
          {/* Search */}
          <div className="hidden md:flex relative">
            <div className="relative">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-slate-400 w-4 h-4" />
              <Input
                type="text"
                placeholder="Tìm kiếm sách, độc giả..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="pl-10 w-80"
              />
            </div>
          </div>

          {/* Notifications */}
          <div className="relative">
            <button className="relative p-2 text-slate-600 hover:text-primary transition-colors duration-200">
              <Bell className="w-5 h-5" />
              <span className="absolute top-0 right-0 w-2 h-2 bg-red-500 rounded-full"></span>
            </button>
          </div>

          {/* User Menu */}
          <div className="flex items-center space-x-3">
            <div className="w-8 h-8 bg-primary rounded-full flex items-center justify-center">
              <User className="w-4 h-4 text-white" />
            </div>
            <span className="hidden md:block text-sm font-medium text-slate-700">
              Thủ thư
            </span>
          </div>
        </div>
      </div>
    </header>
  );
}
