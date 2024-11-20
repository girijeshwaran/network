import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class LibraryClient {
    public static void main(String[] args) {
        try {
            Library library = (Library) Naming.lookup("rmi://localhost/Library"); // Connect to the server
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Library Management ---");
                System.out.println("1. Add Book");
                System.out.println("2. Search Book");
                System.out.println("3. View All Books");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book name: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Enter author name: ");
                        String author = scanner.nextLine();
                        library.addBook(bookName, author);
                        break;

                    case 2:
                        System.out.print("Enter book name to search: ");
                        bookName = scanner.nextLine();
                        System.out.println(library.searchBook(bookName));
                        break;

                    case 3:
                        List<String> books = library.viewAllBooks();
                        System.out.println("Books in Library:");
                        for (String book : books) {
                            System.out.println(book);
                        }
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

