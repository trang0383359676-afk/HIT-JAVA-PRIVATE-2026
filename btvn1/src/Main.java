public class Main {
    public static void notifyUser(MessageSender sender, String message){
        System.out.println(sender.send(message));
    }
    public static void main(String[] args) {
        notifyUser(new EmailSender(),"Đơn hàng đã được tạo");
        notifyUser(new SmsSender(),"Đơn hàng đã được tạo");
        notifyUser(new AppSender(),"Đơn hàng đã được tạo");
    }
}