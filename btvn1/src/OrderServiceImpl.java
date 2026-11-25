
import model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService {

    private List<Order> orders = new ArrayList<>();
    private Scanner sc;

    public OrderServiceImpl(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void addOrder(Order order) {
        for (Order o : orders) {
            if (o.getId().equals(order.getId())) {
                System.out.println("Ma don hang da ton tai!");
                return;
            }
        }

        orders.add(order);
        System.out.println("Them don hang thanh cong!");
    }

    @Override
    public void printOrders() {
        if (orders.isEmpty()) {
            System.out.println("Danh sach don hang rong!");
            return;
        }

        for (Order o : orders) {
            System.out.println(o);
        }
    }

    @Override
    public void updateOrder(String id) {
        for (Order o : orders) {
            if (o.getId().equals(id)) {
                System.out.print("Nhap so luong moi: ");

                try {
                    int quantity = Integer.parseInt(sc.nextLine());

                    if (quantity <= 0 || quantity > o.getProduct().getStock()) {
                        System.out.println("So luong khong hop le!");
                        return;
                    }

                    o.setQuantity(quantity);
                    System.out.println("Cap nhat don hang thanh cong!");
                } catch (NumberFormatException e) {
                    System.out.println("So luong phai la so nguyen!");
                }

                return;
            }
        }

        System.out.println("Khong tim thay don hang!");
    }

    @Override
    public void deleteOrder(String id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                orders.remove(i);
                System.out.println("Xoa don hang thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay don hang!");
    }
}
