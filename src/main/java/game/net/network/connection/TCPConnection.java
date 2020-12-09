package game.net.network.connection;

import game.net.network.ConnectionListener;
import game.net.network.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPConnection extends AbstractConnection<Message, TCPConnection> implements Runnable, Sending<Message> {

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public TCPConnection(Socket socket, ConnectionListener<TCPConnection, Message> listener) {
        this.listener = listener;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        isAlive = true;
    }

    @Override
    public void send(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                if (in.available() != 0) {
                    listener.receiveContent((Message) in.readObject());
                }
                else {
                    Thread.sleep(200);
                }
            } catch(IOException | InterruptedException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
