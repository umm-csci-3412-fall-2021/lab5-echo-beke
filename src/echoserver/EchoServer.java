package echoserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        int i;
        try {
            // Start listening on the specified port
            ServerSocket sock = new ServerSocket(portNumber);

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket client = sock.accept();
                System.out.println("Client connected");

                // socket streams
                InputStream streamFromSocket = client.getInputStream();
                OutputStream streamToSocket = client.getOutputStream();

                while((i = streamFromSocket.read()) != -1)
                {
                    streamToSocket.write(i);
                    streamToSocket.flush();

                    i = streamFromSocket.read();
                    streamToSocket.write(i);
                    streamToSocket.flush();;
                }

                client.shutdownOutput();

                // Close the client socket since we're done.
                client.close();
                System.out.println("Client disconnected.");
            }
            // *Very* minimal error handling.
        } catch (Exception ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}