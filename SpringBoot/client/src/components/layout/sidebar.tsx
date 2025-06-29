import { Link, useLocation } from "wouter";
import { useState } from "react";
import { cn } from "@/lib/utils";
import { 
  Book, 
  Users, 
  ArrowRightLeft, 
  BarChart3, 
  Settings, 
  ChevronRight,
  Library
} from "lucide-react";

interface SidebarProps {
  currentSection: string;
  onSectionChange: (section: string) => void;
}

interface SubMenuItem {
  id: string;
  label: string;
  path: string;
}

interface MenuItem {
  id: string;
  label: string;
  icon: React.ReactNode;
  path: string;
  subItems?: SubMenuItem[];
}

const menuItems: MenuItem[] = [
  {
    id: "dashboard",
    label: "Dashboard",
    icon: <BarChart3 className="w-5 h-5" />,
    path: "/dashboard",
  },
  {
    id: "books",
    label: "Quản lý Sách",
    icon: <Book className="w-5 h-5" />,
    path: "/books",
    subItems: [
      { id: "books-list", label: "Danh sách Sách", path: "/books" },
      { id: "books-add", label: "Thêm Sách mới", path: "/books?action=add" },
      { id: "books-categories", label: "Thể loại Sách", path: "/books?tab=categories" },
    ],
  },
  {
    id: "readers",
    label: "Quản lý Độc giả",
    icon: <Users className="w-5 h-5" />,
    path: "/readers",
    subItems: [
      { id: "readers-list", label: "Danh sách Độc giả", path: "/readers" },
      { id: "readers-add", label: "Thêm Độc giả", path: "/readers?action=add" },
      { id: "readers-search", label: "Tìm kiếm Độc giả", path: "/readers?tab=search" },
    ],
  },
  {
    id: "borrowing",
    label: "Mượn/Trả Sách",
    icon: <ArrowRightLeft className="w-5 h-5" />,
    path: "/borrowing",
    subItems: [
      { id: "borrowing-new", label: "Cho mượn Sách", path: "/borrowing?tab=borrow" },
      { id: "borrowing-return", label: "Trả Sách", path: "/borrowing?tab=return" },
      { id: "borrowing-overdue", label: "Sách quá hạn", path: "/borrowing?tab=overdue" },
      { id: "borrowing-history", label: "Lịch sử mượn/trả", path: "/borrowing?tab=history" },
    ],
  },
  {
    id: "settings",
    label: "Cài đặt",
    icon: <Settings className="w-5 h-5" />,
    path: "/settings",
  },
];

export default function Sidebar({ currentSection, onSectionChange }: SidebarProps) {
  const [location] = useLocation();
  const [expandedItems, setExpandedItems] = useState<Set<string>>(new Set());

  const toggleExpanded = (itemId: string) => {
    const newExpanded = new Set(expandedItems);
    if (newExpanded.has(itemId)) {
      newExpanded.delete(itemId);
    } else {
      newExpanded.add(itemId);
    }
    setExpandedItems(newExpanded);
  };

  const isActive = (path: string) => {
    if (path === "/dashboard" && (location === "/" || location === "/dashboard")) {
      return true;
    }
    return location.startsWith(path) && path !== "/";
  };

  return (
    <div className="w-64 bg-white shadow-lg border-r border-slate-200">
      {/* Logo */}
      <div className="p-6">
        <div className="flex items-center space-x-3">
          <div className="w-10 h-10 bg-primary rounded-lg flex items-center justify-center">
            <Library className="text-white text-lg" />
          </div>
          <div>
            <h1 className="text-xl font-bold text-slate-800">LibraryMS</h1>
            <p className="text-xs text-slate-500">Quản lý Thư viện</p>
          </div>
        </div>
      </div>

      {/* Navigation */}
      <nav className="px-4 pb-4">
        <div className="space-y-2">
          {menuItems.map((item) => (
            <div key={item.id} className="sidebar-category">
              <Link href={item.path}>
                <div
                  className={cn(
                    "sidebar-item rounded-lg p-3 cursor-pointer transition-all duration-200 flex items-center justify-between",
                    isActive(item.path)
                      ? "active bg-primary text-white"
                      : "text-slate-700 hover:bg-slate-100"
                  )}
                  onClick={() => {
                    onSectionChange(item.id);
                    if (item.subItems) {
                      toggleExpanded(item.id);
                    }
                  }}
                >
                  <div className="flex items-center space-x-3">
                    {item.icon}
                    <span className="font-medium">{item.label}</span>
                  </div>
                  {item.subItems && (
                    <ChevronRight
                      className={cn(
                        "w-4 h-4 transition-transform duration-200",
                        expandedItems.has(item.id) ? "rotate-90" : ""
                      )}
                    />
                  )}
                </div>
              </Link>

              {/* Submenu */}
              {item.subItems && expandedItems.has(item.id) && (
                <div className="ml-8 space-y-1 mt-2">
                  {item.subItems.map((subItem) => (
                    <Link key={subItem.id} href={subItem.path}>
                      <div className="text-sm text-slate-600 hover:text-primary cursor-pointer p-2 rounded transition-colors duration-200">
                        {subItem.label}
                      </div>
                    </Link>
                  ))}
                </div>
              )}
            </div>
          ))}
        </div>
      </nav>
    </div>
  );
}
