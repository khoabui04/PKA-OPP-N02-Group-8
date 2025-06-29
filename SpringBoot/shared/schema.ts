import { pgTable, text, serial, integer, boolean, timestamp } from "drizzle-orm/pg-core";
import { createInsertSchema } from "drizzle-zod";
import { z } from "zod";

export const books = pgTable("books", {
  id: serial("id").primaryKey(),
  title: text("title").notNull(),
  author: text("author").notNull(),
  isbn: text("isbn"),
  category: text("category").notNull(),
  publishYear: integer("publish_year"),
  publisher: text("publisher"),
  description: text("description"),
  quantity: integer("quantity").notNull().default(1),
  availableQuantity: integer("available_quantity").notNull().default(1),
  createdAt: timestamp("created_at").defaultNow(),
});

export const readers = pgTable("readers", {
  id: serial("id").primaryKey(),
  name: text("name").notNull(),
  email: text("email").notNull().unique(),
  phone: text("phone"),
  address: text("address"),
  registrationDate: timestamp("registration_date").defaultNow(),
  isActive: boolean("is_active").notNull().default(true),
});

export const borrowings = pgTable("borrowings", {
  id: serial("id").primaryKey(),
  bookId: integer("book_id").notNull(),
  readerId: integer("reader_id").notNull(),
  borrowDate: timestamp("borrow_date").notNull(),
  dueDate: timestamp("due_date").notNull(),
  returnDate: timestamp("return_date"),
  status: text("status").notNull().default("borrowed"), // borrowed, returned, overdue
  condition: text("condition"), // good, fair, damaged
  createdAt: timestamp("created_at").defaultNow(),
});

export const insertBookSchema = createInsertSchema(books).omit({
  id: true,
  availableQuantity: true,
  createdAt: true,
});

export const insertReaderSchema = createInsertSchema(readers).omit({
  id: true,
  registrationDate: true,
});

export const insertBorrowingSchema = createInsertSchema(borrowings).omit({
  id: true,
  createdAt: true,
});

export type InsertBook = z.infer<typeof insertBookSchema>;
export type Book = typeof books.$inferSelect;
export type InsertReader = z.infer<typeof insertReaderSchema>;
export type Reader = typeof readers.$inferSelect;
export type InsertBorrowing = z.infer<typeof insertBorrowingSchema>;
export type Borrowing = typeof borrowings.$inferSelect;

// Extended types for UI
export type BorrowingWithDetails = Borrowing & {
  bookTitle: string;
  bookAuthor: string;
  readerName: string;
  readerEmail: string;
};

export type BookStats = {
  totalBooks: number;
  availableBooks: number;
  borrowedBooks: number;
};

export type ReaderStats = {
  totalReaders: number;
  activeReaders: number;
};

export type BorrowingStats = {
  totalBorrowings: number;
  currentBorrowings: number;
  overdueBooks: number;
};

export type DashboardStats = {
  books: BookStats;
  readers: ReaderStats;
  borrowings: BorrowingStats;
};
