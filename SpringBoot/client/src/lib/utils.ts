import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export function formatDate(date: string | Date): string {
  const d = new Date(date);
  return d.toLocaleDateString("vi-VN", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });
}

export function formatDateTime(date: string | Date): string {
  const d = new Date(date);
  return d.toLocaleString("vi-VN", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  });
}

export function calculateDaysUntilDue(dueDate: string | Date): number {
  const due = new Date(dueDate);
  const now = new Date();
  const diffTime = due.getTime() - now.getTime();
  return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
}

export function isOverdue(dueDate: string | Date): boolean {
  return calculateDaysUntilDue(dueDate) < 0;
}

export function getInitials(name: string): string {
  return name
    .split(" ")
    .map(part => part.charAt(0).toUpperCase())
    .slice(0, 2)
    .join("");
}

export function getCategoryColor(category: string): string {
  const colors: Record<string, string> = {
    technology: "category-technology",
    literature: "category-literature",
    science: "category-science",
    history: "category-history",
    business: "category-business",
  };
  return colors[category] || "bg-gray-100 text-gray-800";
}

export function getStatusColor(status: string): string {
  const colors: Record<string, string> = {
    available: "status-available",
    borrowed: "status-borrowed",
    overdue: "status-overdue",
    returned: "status-returned",
  };
  return colors[status] || "bg-gray-100 text-gray-800";
}

export function getStatusText(status: string): string {
  const texts: Record<string, string> = {
    available: "Có sẵn",
    borrowed: "Đang mượn",
    overdue: "Quá hạn",
    returned: "Đã trả",
  };
  return texts[status] || status;
}

export function getCategoryText(category: string): string {
  const texts: Record<string, string> = {
    technology: "Công nghệ",
    literature: "Văn học",
    science: "Khoa học",
    history: "Lịch sử",
    business: "Kinh doanh",
  };
  return texts[category] || category;
}

export function validateEmail(email: string): boolean {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

export function validatePhone(phone: string): boolean {
  const phoneRegex = /^[0-9]{10,11}$/;
  return phoneRegex.test(phone.replace(/\s/g, ""));
}

export function debounce<T extends (...args: any[]) => any>(
  func: T,
  wait: number
): (...args: Parameters<T>) => void {
  let timeout: NodeJS.Timeout;
  return (...args: Parameters<T>) => {
    clearTimeout(timeout);
    timeout = setTimeout(() => func(...args), wait);
  };
}

