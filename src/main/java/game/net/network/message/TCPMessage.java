package game.net.network.message;

import java.io.Serializable;

public class TCPMessage implements Message {

    private Object value;
    @Override
    public Object getMessage() {
        return value;
    }

    @Override
    public <T extends Serializable> void setMessage(T message) {
        this.value = message;
    }
}
