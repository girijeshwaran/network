import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Library extends Remote {
    void addBook(String bookName, String author) throws RemoteException;
    String searchBook(String bookName) throws RemoteException;
    List<String> viewAllBooks() throws RemoteException;
}
