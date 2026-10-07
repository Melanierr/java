package Class;

public class Book {
    private String name;
    private String author;
    private String isbn;
    private int releaseYear;
    public static int numberOfBooks = 0;
    private boolean isAvailable = true;
    public Book(String name, String author, String isbn, int releaseYear) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.releaseYear = releaseYear;
        numberOfBooks++;
    }

    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getISBN() {
        return isbn;
    }
    @Override
    public String toString() {
        return String.format("Name: %s\nAuthor: %s\nBook Number: %s", this.getName(), this.getAuthor(), this.getISBN());
    }
    public String toSave(){
        return this.getName() + "," + getAuthor() + "," + getISBN() + "," + releaseYear + "," + isAvailable;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean state) {
        isAvailable = state;
    }
}
