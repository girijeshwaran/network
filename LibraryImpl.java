import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LibraryImpl extends UnicastRemoteObject implements Library {
    private List<String> books;

    protected LibraryImpl() throws RemoteException {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(String bookName, String author) throws RemoteException {
        books.add(bookName + " by " + author);
        System.out.println("Book added: " + bookName + " by " + author);
    }

    @Override
    public String searchBook(String bookName) throws RemoteException {
        for (String book : books) {
            if (book.contains(bookName)) {
                return "Found: " + book;
            }
        }
        return "Book not found";
    }

    @Override
    public List<String> viewAllBooks() throws RemoteException {
        return books;
    }
}
