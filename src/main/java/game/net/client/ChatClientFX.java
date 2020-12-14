package game.net.client;

import game.net.network.connection.Sender;
import game.net.network.message.ChatMessage;

public class ChatClientFX implements Sender<ChatMessage> {

    // TODO: 12/14/2020 добавить считывание из поля и отправка сообщения по нажатию
    private final GameTCPClient tcpClient;

    public ChatClientFX(GameTCPClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public void send(ChatMessage message) {
        tcpClient.send(message);
    }
}
