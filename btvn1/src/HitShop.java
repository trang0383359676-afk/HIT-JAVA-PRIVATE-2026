
import model.Product;
import model.Book;
import model.Cake;
import model.Customer;
import model.CartItem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HitShop {

    private static final Customer currentCustomer =
            new Customer("C01", "Linh Nguyễn", "0900000001");

    private static final List<CartItem> cart = new ArrayList<>();

    private static final List<Product> products = new ArrayList<>();

    private static final Product p1 =
            new Product("P01", "Áo thun", 150000, 20);

    private static final Product p2 =
            new Product("P02", "Bình nước", 50000, 10);

    static {
        products.add(p1);
        products.add(p2);
    }

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
        System.out.println("5. Kiểm tra sản phẩm có đắt không");
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
                    System.out.println("=== Chọn loại sản phẩm ===");
                    System.out.println("1. Product - Sản phẩm thông thường");
                    System.out.println("2. Book - Sách");
                    System.out.println("3. Cake - Bánh");
                    System.out.print("Chọn loại: ");

                    int type = sc.nextInt();

                    if (type < 1 || type > 3) {
                        System.out.println("Loại sản phẩm không hợp lệ.");
                        break;
                    }

                    sc.nextLine();

                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nhập tên sản phẩm: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập giá: ");
                    double price = sc.nextDouble();

                    System.out.print("Nhập tồn kho: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Product product = null;

                    if (type == 1) {
                        product = new Product(id, name, price, stock);
                    } else if (type == 2) {
                        System.out.print("Nhập tên tác giả: ");
                        String author = sc.nextLine();

                        product = new Book(
                                id, name, price, stock, author
                        );
                    } else if (type == 3) {
                        System.out.print("Nhập thương hiệu bánh: ");
                        String brand = sc.nextLine();

                        product = new Cake(
                                id, name, price, stock, brand
                        );
                    }

                    products.add(product);

                    System.out.print("Nhập số lượng mua: ");
                    int quantity = sc.nextInt();

                    if (quantity <= 0 || quantity > stock) {
                        System.out.println("Số lượng không hợp lệ.");
                        products.remove(product);
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

                case 5:
                    System.out.print("Nhập ID sản phẩm: ");
                    String searchId = sc.next();

                    Product foundProduct = null;

                    for (Product p : products) {
                        if (p.getId().equalsIgnoreCase(searchId)) {
                            foundProduct = p;
                            break;
                        }
                    }

                    if (foundProduct == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                    } else {
                        if (foundProduct.isExpensive()) {
                            System.out.println("Sản phẩm đắt.");
                        } else {
                            System.out.println("Sản phẩm không đắt.");
                        }
                    }
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
