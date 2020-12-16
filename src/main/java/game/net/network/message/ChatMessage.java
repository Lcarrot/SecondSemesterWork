package game.net.network.message;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage extends TCPMessage {

    private final String nickname;
    private Object message;
    private Date messageTime;

    public ChatMessage(String nickname) {
        this.nickname = nickname;
    }

    public Object getMessage() {
        return message;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public String getNickname() {
        return nickname;
    }

    public <T extends Serializable> void setMessage(T message, Date date) {
        this.message = message;
        this.messageTime = date;
    }
}
