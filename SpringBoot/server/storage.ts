import { 
  books, readers, borrowings, 
  type Book, type Reader, type Borrowing, type BorrowingWithDetails,
  type InsertBook, type InsertReader, type InsertBorrowing,
  type DashboardStats
} from "@shared/schema";

export interface IStorage {
  // Books
  getBooks(): Promise<Book[]>;
  getBook(id: number): Promise<Book | undefined>;
  createBook(book: InsertBook): Promise<Book>;
  updateBook(id: number, book: Partial<InsertBook>): Promise<Book | undefined>;
  deleteBook(id: number): Promise<boolean>;
  searchBooks(query: string, category?: string): Promise<Book[]>;

  // Readers
  getReaders(): Promise<Reader[]>;
  getReader(id: number): Promise<Reader | undefined>;
  createReader(reader: InsertReader): Promise<Reader>;
  updateReader(id: number, reader: Partial<InsertReader>): Promise<Reader | undefined>;
  deleteReader(id: number): Promise<boolean>;
  searchReaders(query: string): Promise<Reader[]>;

  // Borrowings
  getBorrowings(): Promise<BorrowingWithDetails[]>;
  getBorrowing(id: number): Promise<BorrowingWithDetails | undefined>;
  createBorrowing(borrowing: InsertBorrowing): Promise<Borrowing>;
  updateBorrowing(id: number, borrowing: Partial<InsertBorrowing>): Promise<Borrowing | undefined>;
  getActiveBorrowings(): Promise<BorrowingWithDetails[]>;
  getOverdueBorrowings(): Promise<BorrowingWithDetails[]>;
  getBorrowingsByReader(readerId: number): Promise<BorrowingWithDetails[]>;
  getBorrowingsByBook(bookId: number): Promise<BorrowingWithDetails[]>;

  // Statistics
  getDashboardStats(): Promise<DashboardStats>;

  // User management
  getUser(id: number): Promise<any | undefined>;
  getUserByUsername(username: string): Promise<any | undefined>;
  createUser(user: any): Promise<any>;
}

export class MemStorage implements IStorage {
  private books: Map<number, Book>;
  private readers: Map<number, Reader>;
  private borrowings: Map<number, Borrowing>;
  private currentBookId: number;
  private currentReaderId: number;
  private currentBorrowingId: number;

  constructor() {
    this.books = new Map();
    this.readers = new Map();
    this.borrowings = new Map();
    this.currentBookId = 1;
    this.currentReaderId = 1;
    this.currentBorrowingId = 1;

    // Initialize with some sample data
    this.initializeSampleData();
  }

  private initializeSampleData() {
    // Sample books
    const sampleBooks: InsertBook[] = [
      {
        title: "Lập trình Java cơ bản",
        author: "Nguyễn Văn A",
        isbn: "978-0123456789",
        category: "technology",
        publishYear: 2023,
        publisher: "NXB Giáo dục",
        description: "Giáo trình lập trình Java cho người mới bắt đầu",
        quantity: 5
      },
      {
        title: "Cơ sở dữ liệu",
        author: "Trần Thị B",
        isbn: "978-0123456790",
        category: "technology",
        publishYear: 2022,
        publisher: "NXB Đại học Quốc gia",
        description: "Kiến thức cơ bản về cơ sở dữ liệu",
        quantity: 3
      },
      {
        title: "Văn học Việt Nam",
        author: "Lê Văn C",
        isbn: "978-0123456791",
        category: "literature",
        publishYear: 2021,
        publisher: "NXB Văn học",
        description: "Tổng quan văn học Việt Nam qua các thời kỳ",
        quantity: 4
      }
    ];

    sampleBooks.forEach(book => this.createBook(book));

    // Sample readers
    const sampleReaders: InsertReader[] = [
      {
        name: "Nguyễn Văn Nam",
        email: "nam@email.com",
        phone: "0123456789",
        address: "Hà Nội",
        isActive: true
      },
      {
        name: "Trần Thị Lan",
        email: "lan@email.com",
        phone: "0987654321",
        address: "Hồ Chí Minh",
        isActive: true
      }
    ];

    sampleReaders.forEach(reader => this.createReader(reader));

    // Sample borrowings
    const now = new Date();
    const dueDate = new Date(now.getTime() + 14 * 24 * 60 * 60 * 1000); // 14 days from now
    
    this.createBorrowing({
      bookId: 1,
      readerId: 1,
      borrowDate: now,
      dueDate: dueDate,
      status: "borrowed"
    });
  }

  // Books
  async getBooks(): Promise<Book[]> {
    return Array.from(this.books.values());
  }

  async getBook(id: number): Promise<Book | undefined> {
    return this.books.get(id);
  }

  async createBook(insertBook: InsertBook): Promise<Book> {
    const id = this.currentBookId++;
    const book: Book = {
      id,
      title: insertBook.title,
      author: insertBook.author,
      isbn: insertBook.isbn || null,
      category: insertBook.category,
      publishYear: insertBook.publishYear || null,
      publisher: insertBook.publisher || null,
      description: insertBook.description || null,
      quantity: insertBook.quantity || 1,
      availableQuantity: insertBook.quantity || 1,
      createdAt: new Date()
    };
    this.books.set(id, book);
    return book;
  }

  async updateBook(id: number, updateData: Partial<InsertBook>): Promise<Book | undefined> {
    const existing = this.books.get(id);
    if (!existing) return undefined;

    const updated: Book = { ...existing, ...updateData };
    this.books.set(id, updated);
    return updated;
  }

  async deleteBook(id: number): Promise<boolean> {
    return this.books.delete(id);
  }

  async searchBooks(query: string, category?: string): Promise<Book[]> {
    const allBooks = Array.from(this.books.values());
    const lowerQuery = query.toLowerCase();
    
    return allBooks.filter(book => {
      const matchesQuery = !query || 
        book.title.toLowerCase().includes(lowerQuery) ||
        book.author.toLowerCase().includes(lowerQuery) ||
        book.isbn?.toLowerCase().includes(lowerQuery);
      
      const matchesCategory = !category || book.category === category;
      
      return matchesQuery && matchesCategory;
    });
  }

  // Readers
  async getReaders(): Promise<Reader[]> {
    return Array.from(this.readers.values());
  }

  async getReader(id: number): Promise<Reader | undefined> {
    return this.readers.get(id);
  }

  async createReader(insertReader: InsertReader): Promise<Reader> {
    const id = this.currentReaderId++;
    const reader: Reader = {
      id,
      name: insertReader.name,
      email: insertReader.email,
      phone: insertReader.phone || null,
      address: insertReader.address || null,
      registrationDate: new Date(),
      isActive: insertReader.isActive ?? true
    };
    this.readers.set(id, reader);
    return reader;
  }

  async updateReader(id: number, updateData: Partial<InsertReader>): Promise<Reader | undefined> {
    const existing = this.readers.get(id);
    if (!existing) return undefined;

    const updated: Reader = { ...existing, ...updateData };
    this.readers.set(id, updated);
    return updated;
  }

  async deleteReader(id: number): Promise<boolean> {
    return this.readers.delete(id);
  }

  async searchReaders(query: string): Promise<Reader[]> {
    const allReaders = Array.from(this.readers.values());
    const lowerQuery = query.toLowerCase();
    
    return allReaders.filter(reader => 
      reader.name.toLowerCase().includes(lowerQuery) ||
      reader.email.toLowerCase().includes(lowerQuery)
    );
  }

  // Borrowings
  async getBorrowings(): Promise<BorrowingWithDetails[]> {
    const allBorrowings = Array.from(this.borrowings.values());
    return this.enrichBorrowings(allBorrowings);
  }

  async getBorrowing(id: number): Promise<BorrowingWithDetails | undefined> {
    const borrowing = this.borrowings.get(id);
    if (!borrowing) return undefined;
    
    const enriched = await this.enrichBorrowings([borrowing]);
    return enriched[0];
  }

  async createBorrowing(insertBorrowing: InsertBorrowing): Promise<Borrowing> {
    const id = this.currentBorrowingId++;
    const borrowing: Borrowing = {
      id,
      bookId: insertBorrowing.bookId,
      readerId: insertBorrowing.readerId,
      borrowDate: insertBorrowing.borrowDate,
      dueDate: insertBorrowing.dueDate,
      returnDate: insertBorrowing.returnDate || null,
      status: insertBorrowing.status || "borrowed",
      condition: insertBorrowing.condition || null,
      createdAt: new Date()
    };
    
    // Update book availability
    const book = this.books.get(insertBorrowing.bookId);
    if (book && book.availableQuantity > 0) {
      book.availableQuantity--;
      this.books.set(book.id, book);
    }
    
    this.borrowings.set(id, borrowing);
    return borrowing;
  }

  async updateBorrowing(id: number, updateData: Partial<InsertBorrowing>): Promise<Borrowing | undefined> {
    const existing = this.borrowings.get(id);
    if (!existing) return undefined;

    // If returning book, update availability
    if (updateData.returnDate && !existing.returnDate) {
      const book = this.books.get(existing.bookId);
      if (book) {
        book.availableQuantity++;
        this.books.set(book.id, book);
      }
    }

    const updated: Borrowing = { ...existing, ...updateData };
    this.borrowings.set(id, updated);
    return updated;
  }

  async getActiveBorrowings(): Promise<BorrowingWithDetails[]> {
    const allBorrowings = Array.from(this.borrowings.values());
    const active = allBorrowings.filter(b => b.status === "borrowed" && !b.returnDate);
    return this.enrichBorrowings(active);
  }

  async getOverdueBorrowings(): Promise<BorrowingWithDetails[]> {
    const allBorrowings = Array.from(this.borrowings.values());
    const now = new Date();
    const overdue = allBorrowings.filter(b => 
      b.status === "borrowed" && 
      !b.returnDate && 
      new Date(b.dueDate) < now
    );
    return this.enrichBorrowings(overdue);
  }

  async getBorrowingsByReader(readerId: number): Promise<BorrowingWithDetails[]> {
    const allBorrowings = Array.from(this.borrowings.values());
    const readerBorrowings = allBorrowings.filter(b => b.readerId === readerId);
    return this.enrichBorrowings(readerBorrowings);
  }

  async getBorrowingsByBook(bookId: number): Promise<BorrowingWithDetails[]> {
    const allBorrowings = Array.from(this.borrowings.values());
    const bookBorrowings = allBorrowings.filter(b => b.bookId === bookId);
    return this.enrichBorrowings(bookBorrowings);
  }

  private async enrichBorrowings(borrowings: Borrowing[]): Promise<BorrowingWithDetails[]> {
    return borrowings.map(borrowing => {
      const book = this.books.get(borrowing.bookId);
      const reader = this.readers.get(borrowing.readerId);
      
      return {
        ...borrowing,
        bookTitle: book?.title || "Unknown Book",
        bookAuthor: book?.author || "Unknown Author",
        readerName: reader?.name || "Unknown Reader",
        readerEmail: reader?.email || "Unknown Email"
      };
    });
  }

  // Statistics
  async getDashboardStats(): Promise<DashboardStats> {
    const allBooks = Array.from(this.books.values());
    const allReaders = Array.from(this.readers.values());
    const allBorrowings = Array.from(this.borrowings.values());
    const now = new Date();

    const totalBooks = allBooks.reduce((sum, book) => sum + book.quantity, 0);
    const availableBooks = allBooks.reduce((sum, book) => sum + book.availableQuantity, 0);
    const borrowedBooks = totalBooks - availableBooks;

    const totalReaders = allReaders.length;
    const activeReaders = allReaders.filter(r => r.isActive).length;

    const totalBorrowings = allBorrowings.length;
    const currentBorrowings = allBorrowings.filter(b => b.status === "borrowed" && !b.returnDate).length;
    const overdueBooks = allBorrowings.filter(b => 
      b.status === "borrowed" && 
      !b.returnDate && 
      new Date(b.dueDate) < now
    ).length;

    return {
      books: {
        totalBooks,
        availableBooks,
        borrowedBooks
      },
      readers: {
        totalReaders,
        activeReaders
      },
      borrowings: {
        totalBorrowings,
        currentBorrowings,
        overdueBooks
      }
    };
  }

  // User management (required by interface)
  async getUser(id: number): Promise<any | undefined> {
    return undefined;
  }

  async getUserByUsername(username: string): Promise<any | undefined> {
    return undefined;
  }

  async createUser(user: any): Promise<any> {
    return user;
  }
}

export const storage = new MemStorage();
