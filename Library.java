public class Library 
{
    private String bookName;
    private String author;
    private int pages;
    private String publisher;
    private String genre;

    // Constructor
    public Library(String bookName, String author, int pages, String publisher, String genre)
    {
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.genre = genre;
    }

    // Get Methods
    public String getBookName() {
        return bookName;
    }
    
    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getPublisher() {
        return publisher;
    }
    public String getgenre() {
        return genre;

    }
    
    // Set Methods
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Output
    public String toString()
    {
        // System.out.printf(	"%-40s %-20s %-10s %-40s %-20s%n", "Name:", "Author:", "Pages:", "Publisher:", "Genre:");
        return String.format("%-40s %-20s %-10s %-40s %-25s", bookName, author, pages, publisher, genre);
    }

    public String String()  
    {
        System.out.println("");
        String description = "Book Description: " + bookName;
        description += " by " + author + "\n";
        description += "With " + pages + " pages"+ "\n";
        description += "Published by: " + publisher + "\n";
        description += "Genre: " + genre;
        System.out.println("");
        description += "\nThank you for borrowing "+bookName + "!";

        return description;
   }
}