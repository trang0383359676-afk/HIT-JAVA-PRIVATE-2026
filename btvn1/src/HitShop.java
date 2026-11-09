import model.Product;
import model.Customer;
import model.CartItem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HitShop {

    private static final Customer currentCustomer =
            new Customer("C01", "Linh Nguyễn", "0900000001");

    private static final List<CartItem> cart = new ArrayList<>();

    private static final Product p1 =
            new Product("P01", "Áo thun", 150000, 20);

    private static final Product p2 =
            new Product("P02", "Bình nước", 50000, 10);

    static void addItem(Product product, int quantity) {
        CartItem item = new CartItem(product, quantity);
        cart.add(item);
    }

    static void removeItem(int index) {
        cart.remove(index);
    }

    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer);

        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            Product product = item.getProduct();

            System.out.println(
                    (i + 1) + ". "
                            + product.getId() + " | "
                            + product.getName() + " | "
                            + product.getPrice() + " | SL: "
                            + item.getQuantity() + " | Thành tiền: "
                            + item.getTotal()
            );
        }
    }

    static double calculateTotal() {
        double total = 0;

        for (CartItem item : cart) {
            total += item.getTotal();
        }

        return total;
    }

    static void printMenu() {
        System.out.println("\n===== HIT SHOP =====");
        System.out.println("1. Thêm mặt hàng");
        System.out.println("2. Xóa mặt hàng");
        System.out.println("3. Xem giỏ hàng");
        System.out.println("4. Xem tổng tiền");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            printMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sách sản phẩm:");
                    System.out.println(p1.getId() + " - " + p1.getName());
                    System.out.println(p2.getId() + " - " + p2.getName());

                    System.out.print("Nhập mã sản phẩm: ");
                    String id = sc.next();

                    Product product = null;

                    if (id.equalsIgnoreCase("P01")) {
                        product = p1;
                    } else if (id.equalsIgnoreCase("P02")) {
                        product = p2;
                    }

                    if (product == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                        break;
                    }

                    System.out.print("Nhập số lượng: ");
                    int quantity = sc.nextInt();

                    if (quantity <= 0) {
                        System.out.println(
                                "Số lượng phải lớn hơn 0."
                        );
                        break;
                    }

                    addItem(product, quantity);
                    System.out.println("Thêm mặt hàng thành công.");
                    break;

                case 2:
                    if (cart.isEmpty()) {
                        System.out.println("Giỏ hàng đang trống.");
                        break;
                    }

                    printItems();

                    System.out.print("Nhập số thứ tự cần xóa: ");
                    int displayNumber = sc.nextInt();

                    int index = displayNumber - 1;

                    if (index < 0 || index >= cart.size()) {
                        System.out.println("Không tìm thấy mặt hàng.");
                    } else {
                        removeItem(index);
                        System.out.println("Xóa mặt hàng thành công.");
                    }
                    break;

                case 3:
                    printItems();
                    break;

                case 4:
                    System.out.println(
                            "Tổng tiền: " + calculateTotal()
                    );
                    break;

                case 0:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);

        sc.close();
    }
}