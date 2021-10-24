package echoserver;

import java.io.*;
import java.net.Socket;
import java.rmi.ConnectException;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        int i;

        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, portNumber);

            // enter data using InputStreams
            InputStream keyboardReader = System.in;
            OutputStream output = System.out;

            // socket streams
            InputStream streamFromSocket = socket.getInputStream();
            OutputStream streamToSocket = socket.getOutputStream();

            while((i = keyboardReader.read()) != -1)
            {
                streamToSocket.write(i);
                streamToSocket.flush();

                i = streamFromSocket.read();
                output.write(i);
                output.flush();;
            }

            socket.shutdownOutput();

            // Close the socket when we're done reading from it
            socket.close();

            // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}