public class EmailSender extends BaseMessageSender implements MessageSender{
    public EmailSender(String senderName) {
        super(senderName);
    }

    @Override
    public String send(String message){
        return "Đã gửi email: "+message;
    }
}

