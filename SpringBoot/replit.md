# Library Management System

## Overview

This is a modern library management web application built with React frontend and Express.js backend. The system allows librarians to manage books, readers, and book borrowing/returning operations through a clean, responsive interface.

## System Architecture

### Frontend Architecture
- **Framework**: React 18 with TypeScript
- **Styling**: Tailwind CSS with shadcn/ui component library
- **State Management**: TanStack Query (React Query) for server state
- **Routing**: Wouter for client-side routing
- **Build Tool**: Vite for fast development and optimized builds

### Backend Architecture
- **Runtime**: Node.js with Express.js framework
- **Database**: PostgreSQL with Drizzle ORM
- **Development**: In-memory storage implementation for testing/development
- **API Design**: RESTful API structure

### UI/UX Design
- Modern, clean interface using shadcn/ui components
- Responsive design with mobile-first approach
- Vietnamese language interface
- Dark/light theme support through CSS variables

## Key Components

### Data Models
- **Books**: Title, author, ISBN, category, publisher, publication year, quantity management
- **Readers**: Personal information, contact details, registration status
- **Borrowings**: Loan tracking with due dates, return status, and condition monitoring

### Core Features
1. **Book Management**: Add, edit, delete, and search books with category filtering
2. **Reader Management**: Maintain reader database with contact information
3. **Borrowing System**: Track book loans, due dates, and returns
4. **Dashboard**: Overview statistics and recent activity monitoring
5. **Search & Filtering**: Advanced search capabilities across all entities

### Storage Layer
- **Development**: In-memory storage with full CRUD operations
- **Production Ready**: PostgreSQL database with Drizzle ORM migrations
- **Session Management**: PostgreSQL session store integration

## Data Flow

1. **Client Requests**: React components make API calls through TanStack Query
2. **API Layer**: Express.js routes handle HTTP requests and validation
3. **Business Logic**: Service layer processes data operations
4. **Storage Layer**: Drizzle ORM manages database interactions
5. **Response**: JSON data returned to client with error handling

### Query Management
- TanStack Query handles caching, background updates, and optimistic updates
- Automatic refetching on window focus and network reconnection
- Mutation management for create, update, and delete operations

## External Dependencies

### Frontend Dependencies
- **@radix-ui**: Accessible UI primitives for complex components
- **@tanstack/react-query**: Server state management and caching
- **wouter**: Lightweight routing solution
- **class-variance-authority**: Utility for component variant management
- **date-fns**: Date manipulation and formatting

### Backend Dependencies
- **drizzle-orm**: Type-safe SQL query builder and ORM
- **@neondatabase/serverless**: Serverless PostgreSQL driver
- **express**: Web application framework
- **zod**: Schema validation for API inputs

### Development Tools
- **Vite**: Build tool with hot module replacement
- **TypeScript**: Type safety across the entire stack
- **Tailwind CSS**: Utility-first CSS framework
- **ESBuild**: Fast JavaScript bundler for production

## Deployment Strategy

### Development Environment
- Vite development server with hot module replacement
- In-memory storage for rapid prototyping
- Error overlay integration for debugging

### Production Build
- Frontend: Vite builds optimized static assets
- Backend: ESBuild bundles server code for Node.js
- Database: PostgreSQL with Drizzle migrations
- Environment: Supports deployment to cloud platforms (Replit, Vercel, etc.)

### Database Migration
- Drizzle Kit handles schema migrations
- PostgreSQL connection via environment variables
- Automatic schema synchronization

## Changelog
```
Changelog:
- June 28, 2025. Initial setup
```

## User Preferences
```
Preferred communication style: Simple, everyday language.
```