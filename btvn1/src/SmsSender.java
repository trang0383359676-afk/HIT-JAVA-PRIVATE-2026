public class SmsSender extends BaseMessageSender implements MessageSender{
    public SmsSender(String senderName) {
        super(senderName);
    }

    @Override
    public String send(String message){
        return "Đã gửi SMS: " + message;
    }
}
