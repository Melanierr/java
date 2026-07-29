package Class;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    private String name;
    private LocalDate dateJoin;
    private int age;
    static int memberCount = 0;
    private ArrayList<Loan> loansBook = new ArrayList<>();
    private static final int maxLoanBooks = 5;
    public Member(String name, int age) {
            this.name = name;
            this.dateJoin = LocalDate.now();
            this.age = age;
        memberCount++;
    }
    public Member(String name, int age, LocalDate dateJoin) {
        this.name = name;
        this.dateJoin = dateJoin;
        this.age = age;
        memberCount++;
    }

    public String getName() {
        return name;
    }
    public LocalDate getDateJoin() {
        return dateJoin;
    }
    public int getCurrentLoanBooks(){
        return loansBook.size();
    }
    @Override
    public String toString() {
        return "Member { "+ "name:" + getName() + ", Date join:" + getDateJoin() + ", Age: " + age + ", Loans: "+ loansBook.size() + '}';
    }
    public String toSave(){
        return getName() + "," + age + ","+ getDateJoin();
    }
    public void addBorrowBook(Loan loan){
        if(getCurrentLoanBooks() >= maxLoanBooks){
            System.out.println("Maximum books loaned.");
            return;
        }
        loansBook.add(loan);
    }
    public void deleteLoanBook(String bookISBN){
        loansBook.removeIf(scanBook -> scanBook.getCurrentBorrowedBook().equals(bookISBN));
        System.out.println("Has removed loan successfully. ");
    }
}
