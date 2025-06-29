import type { Express } from "express";
import { createServer, type Server } from "http";
import { storage } from "./storage";
import { insertBookSchema, insertReaderSchema, insertBorrowingSchema } from "@shared/schema";
import { z } from "zod";

export async function registerRoutes(app: Express): Promise<Server> {
  
  // Books routes
  app.get("/api/books", async (req, res) => {
    try {
      const { search, category } = req.query;
      let books;
      
      if (search || category) {
        books = await storage.searchBooks(
          search as string || "", 
          category as string
        );
      } else {
        books = await storage.getBooks();
      }
      
      res.json(books);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch books" });
    }
  });

  app.get("/api/books/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const book = await storage.getBook(id);
      
      if (!book) {
        return res.status(404).json({ message: "Book not found" });
      }
      
      res.json(book);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch book" });
    }
  });

  app.post("/api/books", async (req, res) => {
    try {
      const bookData = insertBookSchema.parse(req.body);
      const book = await storage.createBook(bookData);
      res.status(201).json(book);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid book data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to create book" });
    }
  });

  app.put("/api/books/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const updateData = insertBookSchema.partial().parse(req.body);
      const book = await storage.updateBook(id, updateData);
      
      if (!book) {
        return res.status(404).json({ message: "Book not found" });
      }
      
      res.json(book);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid book data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to update book" });
    }
  });

  app.delete("/api/books/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const deleted = await storage.deleteBook(id);
      
      if (!deleted) {
        return res.status(404).json({ message: "Book not found" });
      }
      
      res.json({ message: "Book deleted successfully" });
    } catch (error) {
      res.status(500).json({ message: "Failed to delete book" });
    }
  });

  // Readers routes
  app.get("/api/readers", async (req, res) => {
    try {
      const { search } = req.query;
      let readers;
      
      if (search) {
        readers = await storage.searchReaders(search as string);
      } else {
        readers = await storage.getReaders();
      }
      
      res.json(readers);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch readers" });
    }
  });

  app.get("/api/readers/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const reader = await storage.getReader(id);
      
      if (!reader) {
        return res.status(404).json({ message: "Reader not found" });
      }
      
      res.json(reader);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch reader" });
    }
  });

  app.post("/api/readers", async (req, res) => {
    try {
      const readerData = insertReaderSchema.parse(req.body);
      const reader = await storage.createReader(readerData);
      res.status(201).json(reader);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid reader data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to create reader" });
    }
  });

  app.put("/api/readers/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const updateData = insertReaderSchema.partial().parse(req.body);
      const reader = await storage.updateReader(id, updateData);
      
      if (!reader) {
        return res.status(404).json({ message: "Reader not found" });
      }
      
      res.json(reader);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid reader data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to update reader" });
    }
  });

  app.delete("/api/readers/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const deleted = await storage.deleteReader(id);
      
      if (!deleted) {
        return res.status(404).json({ message: "Reader not found" });
      }
      
      res.json({ message: "Reader deleted successfully" });
    } catch (error) {
      res.status(500).json({ message: "Failed to delete reader" });
    }
  });

  // Borrowings routes
  app.get("/api/borrowings", async (req, res) => {
    try {
      const { type, readerId, bookId } = req.query;
      let borrowings;
      
      if (type === "active") {
        borrowings = await storage.getActiveBorrowings();
      } else if (type === "overdue") {
        borrowings = await storage.getOverdueBorrowings();
      } else if (readerId) {
        borrowings = await storage.getBorrowingsByReader(parseInt(readerId as string));
      } else if (bookId) {
        borrowings = await storage.getBorrowingsByBook(parseInt(bookId as string));
      } else {
        borrowings = await storage.getBorrowings();
      }
      
      res.json(borrowings);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch borrowings" });
    }
  });

  app.get("/api/borrowings/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const borrowing = await storage.getBorrowing(id);
      
      if (!borrowing) {
        return res.status(404).json({ message: "Borrowing not found" });
      }
      
      res.json(borrowing);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch borrowing" });
    }
  });

  app.post("/api/borrowings", async (req, res) => {
    try {
      const borrowingData = insertBorrowingSchema.parse({
        ...req.body,
        borrowDate: new Date(req.body.borrowDate),
        dueDate: new Date(req.body.dueDate),
        returnDate: req.body.returnDate ? new Date(req.body.returnDate) : undefined
      });
      
      // Check if book is available
      const book = await storage.getBook(borrowingData.bookId);
      if (!book) {
        return res.status(404).json({ message: "Book not found" });
      }
      
      if (book.availableQuantity <= 0) {
        return res.status(400).json({ message: "Book is not available" });
      }
      
      // Check if reader exists
      const reader = await storage.getReader(borrowingData.readerId);
      if (!reader) {
        return res.status(404).json({ message: "Reader not found" });
      }
      
      const borrowing = await storage.createBorrowing(borrowingData);
      res.status(201).json(borrowing);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid borrowing data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to create borrowing" });
    }
  });

  app.put("/api/borrowings/:id", async (req, res) => {
    try {
      const id = parseInt(req.params.id);
      const updateData = insertBorrowingSchema.partial().parse({
        ...req.body,
        borrowDate: req.body.borrowDate ? new Date(req.body.borrowDate) : undefined,
        dueDate: req.body.dueDate ? new Date(req.body.dueDate) : undefined,
        returnDate: req.body.returnDate ? new Date(req.body.returnDate) : undefined
      });
      
      const borrowing = await storage.updateBorrowing(id, updateData);
      
      if (!borrowing) {
        return res.status(404).json({ message: "Borrowing not found" });
      }
      
      res.json(borrowing);
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ message: "Invalid borrowing data", errors: error.errors });
      }
      res.status(500).json({ message: "Failed to update borrowing" });
    }
  });

  // Statistics route
  app.get("/api/stats", async (req, res) => {
    try {
      const stats = await storage.getDashboardStats();
      res.json(stats);
    } catch (error) {
      res.status(500).json({ message: "Failed to fetch statistics" });
    }
  });

  const httpServer = createServer(app);
  return httpServer;
}
