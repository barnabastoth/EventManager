package application.model.authentication;

import javax.validation.constraints.Size;

public class NewMessage {
    private String sender;
    private String receiver;
    @Size(min = 4, max = 20, message = "Az üzenetnek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
