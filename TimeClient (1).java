import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        String host = "localhost"; // Server address
        int port = 8088;           // Server port

        try (Socket socket = new Socket(host, port)) {
            // Get input stream to receive data from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Read and print the response from the server
            String serverTime = in.readLine();
            System.out.println("Received from server: " + serverTime);
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}

