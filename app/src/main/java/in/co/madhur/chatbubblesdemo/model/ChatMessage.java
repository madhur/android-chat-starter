package in.co.madhur.chatbubblesdemo.model;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatMessage {

    private String messageText;
    private UserType userType;
    private Status messageStatus;

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    private long messageTime;

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setMessageStatus(Status messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageText() {

        return messageText;
    }

    public UserType getUserType() {
        return userType;
    }

    public Status getMessageStatus() {
        return messageStatus;
    }
}
