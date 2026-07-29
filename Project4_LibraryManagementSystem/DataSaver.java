import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import Class.*;
public class DataSaver {

    private ArrayList<Book> bookList;
    private ArrayList<Member> memberList;
    private ArrayList<Loan>  loanList;
    private LibraryManager libraryManager;
    public DataSaver(ArrayList<Book> bookList, ArrayList<Member> memberList, ArrayList<Loan> loanList, LibraryManager manager){
        this.bookList = bookList;
        this.memberList = memberList;
        this.loanList = loanList;
        this.libraryManager = manager;
    }
    public void save(){
        String path = "saved_data.txt";
        try(FileWriter fw = new FileWriter(path)){
            fw.write("==BOOKS==\n");
            for(Book book : bookList){
                fw.write(book.toSave()+ "\n");
            }
            fw.write("==MEMBERS==\n");
            for(Member member : memberList){
                fw.write(member.toSave() + "\n");
            }
            fw.write("==LOANS==\n");
            for(Loan loan : loanList){
                fw.write(loan.toSave()+ "\n");
            }
        }
        catch(IOException error){
            System.out.println("Error writing to save file");
        }
    }
    public void load(){
        String path = "saved_data.txt";
        try(BufferedReader fd = new BufferedReader(new FileReader(path))){
            String section = "";
            String line;
            bookList = new ArrayList<Book>();
            memberList = new ArrayList<Member>();
            loanList = new ArrayList<Loan>();
            libraryManager.setBookList(bookList);
            libraryManager.setMemberList(memberList);
            libraryManager.setLoanList(loanList);
            while((line = fd.readLine()) != null){
                if(line.contains("==BOOKS==")){
                    section = "BOOKS";
                    continue;

                }
                if(line.contains("==MEMBERS==")){
                    section = "MEMBERS";
                    continue;

                }
                if(line.contains("==LOANS==")){
                    section = "LOANS";
                    continue;

                }
                switch(section){
                    case "BOOKS" ->{
                        String bookName;
                        String bookAuthor;
                        String bookISBN;
                        int releaseYear;
                        String[] read_data = line.split(",");
                        bookName = read_data[0];
                        bookAuthor = read_data[1];
                        bookISBN = read_data[2];
                        releaseYear = Integer.parseInt(read_data[3]);
                        bookList.add(new Book(bookName, bookAuthor, bookISBN, releaseYear));
                    }
                    case "MEMBERS" ->{
                        String memberName;
                        int memberAge;
                        LocalDate joinDate;
                        String[] read_data = line.split(",");
                        memberName = read_data[0];
                        memberAge = Integer.parseInt(read_data[1]);
                        joinDate = LocalDate.parse(read_data[2]);
                        memberList.add(new Member(memberName, memberAge, joinDate));
                    }
                    case "LOANS" ->{
                        Member loanMember;
                        Book loanBook;
                        LocalDate borrowDate;
                        String[] read_data = line.split(",");
                        loanMember = libraryManager.searchMember(read_data[0]);
                        loanBook = libraryManager.searchBookISBN(read_data[1]);
                        borrowDate = LocalDate.parse(read_data[2]);
                        loanBook.setAvailable(false);
                        Loan newLoan = new Loan(loanBook, loanMember, borrowDate);
                        loanMember.addBorrowBook(newLoan);
                        loanList.add(newLoan);
                    }
                }
            }
        }
        catch(IOException error){
            System.out.println("Something went wrong");
        }
    }
    public ArrayList<Member> getMemberList(){
        return memberList;
    }
    public ArrayList<Book> getBookList(){
        return bookList;
    }

    public ArrayList<Loan> getLoanList() {
        return loanList;
    }
}
