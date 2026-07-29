import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Class.*;
public class LibraryManager {
    Scanner scanner;
    ISBNGenerator numberGen = new ISBNGenerator();
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<Loan> loanList = new ArrayList<>();

    public LibraryManager(Scanner scanner) {
        this.scanner = scanner;
    }
    public boolean isEmpty(String array){
        switch(array){
            case "book" -> {
                return bookList.isEmpty();
            }
            case "member" -> {
                return memberList.isEmpty();
            }
            case "loan" -> {
                return loanList.isEmpty();
            }
        }
        return false;
    }
    // member
    public void addMember(String name, int age){
        Member newMember = new Member(name, age);
        memberList.add(newMember);
    }
    public void deleteMember(String name){
        memberList.removeIf(member -> member.getName().equalsIgnoreCase(name));
    }
    public Member searchMember(String name){
        for(Member member : memberList){
            if(member.getName().equalsIgnoreCase(name)){
                return member;
            }
        }
        return null;
    }

    // book
    public Book searchBook(String name){
        name = name.toLowerCase().trim();
        for(Book book : bookList){
            if(book.getName().toLowerCase().equals(name)){
                return book;
            }
        }
        return null;
    }
    public Book searchBookISBN(String ISBN){
        ISBN = ISBN.trim();
        for(Book book : bookList){
            if(book.getISBN().equals(ISBN)){
                return book;
            }
        }
        return null;
    }
    public void addBook(String name, String author, int releaseYear){
        for(Book book : bookList){
            if(book.getName().toLowerCase().equals(name)){
                System.out.println("Book already exists!");
                return;
            }
        }
        Book newBook = new Book(name, author, numberGen.generateISBN(), releaseYear);
        bookList.add(newBook);
    }
    public void deleteBook(String isbn){
        bookList.removeIf(book -> book.getISBN().equalsIgnoreCase(isbn));
    }
    public void importBook(){
        System.out.print("Type a valid file path to import: ");
        String filePath = scanner.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] lineSplit = line.split(",");
                String name = lineSplit[0];
                String author = lineSplit[1];
                String isbn = lineSplit[2];
                int releaseYear = Integer.parseInt(lineSplit[3]);

                boolean hasBookOnShelf = false;
                for(Book book : bookList){
                    if(book.getName().equalsIgnoreCase(name) || book.getISBN().equalsIgnoreCase(isbn)){
                        hasBookOnShelf = true;
                        break;
                    }
                }
                if(!hasBookOnShelf){
                    Book newBookFromImport = new Book(name, author, isbn, releaseYear);
                    bookList.add(newBookFromImport);
                    System.out.println("Added " + name);
                }
                else{
                    System.out.printf("Book %s already exists!", name);
                }
            }
        }
        catch (IOException error){
            System.out.println("Something went wrong.");
        }
        catch (NumberFormatException error){
            System.out.println("Error year.");
        }
    }

    // loans
    public void viewLoanList(){
        for(Loan loan : loanList){
            System.out.println(loan);
        }
    }
    public void addLoan(Book loanBook, Member borrowedMember){
        if(borrowedMember.getCurrentLoanBooks() == 5){
            System.out.println("Max loan books reached.");
        }
        else {
            Loan newLoan = new Loan(loanBook, borrowedMember);
            loanBook.setAvailable(false);
            loanList.add(newLoan);
            borrowedMember.addBorrowBook(newLoan);
            System.out.printf("Current loan books: %d/%d\n", borrowedMember.getCurrentLoanBooks(), 5);

        }
    }
    public void deleteLoan(Member loanMember, Book book){
        for(Loan loan : loanList){
            if(loan.getCurrentBorrowedMember().equals(loanMember.getName()) && loan.getCurrentBorrowedBook().equals(book.getISBN())){
                loanMember.deleteLoanBook(book.getISBN());
                book.setAvailable(true);
                loanList.remove(loan);
                break;
            }
        }
    }
    public ArrayList<Loan> getLoanList(){
        return loanList;
    }
    public ArrayList<Book> getBookList(){
        return bookList;
    }
    public  ArrayList<Member> getMemberList(){
        return memberList;
    }
    public void setMemberList(ArrayList<Member> memberList){
        this.memberList = memberList;
    }
    public void setBookList(ArrayList<Book> bookList){
        this.bookList = bookList;
    }
    public void setLoanList(ArrayList<Loan> loanList){
        this.loanList = loanList;
    }
}

