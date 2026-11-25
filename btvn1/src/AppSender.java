public class AppSender extends BaseMessageSender implements MessageSender{
    public AppSender(String senderName) {
        super(senderName);
    }

    @Override
    public String send(String message) {
        return "Đã gửi thông báo app: " + message;
    }
}
