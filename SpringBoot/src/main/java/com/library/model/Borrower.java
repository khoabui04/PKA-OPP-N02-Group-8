package com.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "borrowers")
public class Borrower implements Serializable {
    @Id
    private String borrowerId;
    private String name;
    private String email;

    public Borrower() {}
    public Borrower(String borrowerId, String name, String email) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.email = email;
    }

    public String getBorrowerId() { return borrowerId; }
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Borrower{ID=" + borrowerId + ", Name=" + name + ", Email=" + email + "}";
    }
}