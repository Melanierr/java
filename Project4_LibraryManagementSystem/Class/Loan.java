package Class;

import java.time.LocalDate;

public class Loan {
    private final Book borrowedBook;
    private final Member borrowedMember;
    private final LocalDate borrowDate;
    public Loan(Book borrowedBook, Member borrowedMember) {
        this.borrowedBook = borrowedBook;
        this.borrowedMember = borrowedMember;
        borrowDate = LocalDate.now();
    }
    public Loan(Book borrowedBook, Member borrowedMember, LocalDate date){
        this.borrowedBook = borrowedBook;
        this.borrowedMember = borrowedMember;
        borrowDate = date;
    }

    public String getCurrentBorrowedBook() {
        return borrowedBook.getISBN();
    }
    public String getCurrentBorrowedMember() {
        return borrowedMember.getName();
    }
    @Override
    public String toString() {
        return String.format("Borrower: %s\nBook ISBN: %s\nDate: %s", this.getCurrentBorrowedMember(), this.getCurrentBorrowedBook(), this.borrowDate);
    }
    public String toSave(){
        return getCurrentBorrowedMember() + "," +
                getCurrentBorrowedBook() + "," +
                borrowDate;
    }
}
