import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class LibraryServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(5000); // Start RMI registry
            LibraryImpl library = new LibraryImpl();
            Naming.rebind("Library", library); // Bind the server to "Library"
            System.out.println("Library Server is running...");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
