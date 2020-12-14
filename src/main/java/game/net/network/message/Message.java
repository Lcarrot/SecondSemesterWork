package game.net.network.message;

import java.io.Serializable;

public interface Message extends Serializable {

    Object getMessage();

    <T extends Serializable> void setMessage(T message);
}
