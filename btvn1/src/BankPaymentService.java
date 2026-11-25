
public class BankPaymentService implements PaymentService {

    @Override
    public void pay(String orderId) {
        System.out.println(
                "Don hang " + orderId
                        + " cua ban da duoc thanh toan thanh cong qua HITBank"
        );
    }
}
