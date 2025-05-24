package manager;

import model.Borrower;
import java.util.*;

public class BorrowerManager {
    public List<Borrower> borrowers = new ArrayList<>();

    public void createBorrower(Borrower b) {
        borrowers.add(b);
    }

    public Borrower readBorrower(String id) {
        return borrowers.stream().filter(b -> b.borrowerID.equals(id)).findFirst().orElse(null);
    }

    public void updateBorrower(String id, String newContact) {
        Borrower b = readBorrower(id);
        if (b != null) b.contactInfo = newContact;
    }

    public void deleteBorrower(String id) {
        borrowers.removeIf(b -> b.borrowerID.equals(id));
    }
}
