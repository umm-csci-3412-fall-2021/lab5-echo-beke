package echoserver;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            // Start listening on the specified port
            ServerSocket sock = new ServerSocket(portNumber);
            System.out.println("Running!");

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket client = sock.accept();
                System.out.println("Got a request!");

                // Construct a writer so we can write to the socket, thereby
                // sending something back to the client.
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

                // Send the current date back tothe client.
                writer.println(new java.util.Date().toString());

                // Close the client socket since we're done.
                client.close();
            }
            // *Very* minimal error handling.
        } catch (Exception ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}