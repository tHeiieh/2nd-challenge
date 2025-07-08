import java.util.*;

abstract class Book {
    protected String isbn;
    protected String title;
    protected int year;
    protected double price;
    protected String author;

    public Book(String isbn, String title, int year, double price, String author) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.price = price;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public abstract void buy(int quantity, String email, String address);
    public abstract boolean isAvailable(int quantity);
}

class PaperBook extends Book {
    private int stock;

    public PaperBook(String isbn, String title, int year, double price, String author, int stock) {
        super(isbn, title, year, price, author);
        this.stock = stock;
    }

    @Override
    public void buy(int quantity, String email, String address) {
        if (stock < quantity) {
            throw new RuntimeException("Quantum book store: Not enough stock for ISBN " + isbn);
        }
        stock -= quantity;
        double total = quantity * price;
        System.out.println("Quantum book store: Total paid = " + total);
        System.out.println("Quantum book store: Paper book shipped to " + address);
    }

    @Override
    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }
}

class EBook extends Book {
    private String fileType;

    public EBook(String isbn, String title, int year, double price, String author, String fileType) {
        super(isbn, title, year, price, author);
        this.fileType = fileType;
    }

    @Override
    public void buy(int quantity, String email, String address) {
        double total = quantity * price;
        System.out.println("Quantum book store: Total paid = " + total);
        System.out.println("Quantum book store: EBook sent to " + email);
    }

    @Override
    public boolean isAvailable(int quantity) {
        return true;
    }
}

class ShowcaseBook extends Book {
    public ShowcaseBook(String isbn, String title, int year, double price, String author) {
        super(isbn, title, year, price, author);
    }

    @Override
    public void buy(int quantity, String email, String address) {
        throw new RuntimeException("Quantum book store: Showcase books are not for sale");
    }

    @Override
    public boolean isAvailable(int quantity) {
        return false;
    }
}

class QuantumBookstore {
    private Map<String, Book> inventory = new HashMap<>();

    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
    }

    public Book removeOutdated(int maxYears) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Iterator<Map.Entry<String, Book>> iterator = inventory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            if (currentYear - entry.getValue().getYear() > maxYears) {
                iterator.remove();
                return entry.getValue();
            }
        }
        return null;
    }

    public void buyBook(String isbn, int quantity, String email, String address) {
        if (!inventory.containsKey(isbn)) {
            throw new RuntimeException("Quantum book store: Book not found");
        }
        Book book = inventory.get(isbn);
        if (!book.isAvailable(quantity)) {
            throw new RuntimeException("Quantum book store: Not enough quantity");
        }
        book.buy(quantity, email, address);
    }
}

public class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        QuantumBookstore store = new QuantumBookstore();

        PaperBook pb = new PaperBook("111", "Java Basics", 2015, 120.5, "Alice", 10);
        EBook eb = new EBook("222", "Python Advanced", 2020, 75.0, "Bob", "PDF");
        ShowcaseBook sb = new ShowcaseBook("333", "Secret Book", 2010, 0.0, "Dr. X");

        store.addBook(pb);
        store.addBook(eb);
        store.addBook(sb);

        store.buyBook("111", 2, "Taha@gmail.com", "123 Cairo St");
        store.buyBook("222", 1, "blackbeauty@gmail.com", "");

        try {
            store.buyBook("333", 1, "alt@gmail.com", "");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        Book outdated = store.removeOutdated(10);
        if (outdated != null) {
            System.out.println("Quantum book store: Removed outdated book: " + outdated.title);
        }
    }
}
