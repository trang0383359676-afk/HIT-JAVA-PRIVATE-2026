
import model.Product;
import model.GeneralProduct;
import model.Book;
import model.Cake;
import model.Customer;
import model.Order;

import java.util.Scanner;

public class HitShop {

    // Nhap so nguyen an toan
    public static int nhapInt(Scanner sc, String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    // Nhap so thuc an toan
    public static double nhapDouble(Scanner sc, String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl(sc);

        int choice;

        do {
            System.out.println("\n===== HITSHOP - QUAN LY DON HANG =====");
            System.out.println("1. Them don hang");
            System.out.println("2. Xem danh sach don hang");
            System.out.println("3. Sua don hang theo ID");
            System.out.println("4. Xoa don hang theo ID");
            System.out.println("5. Kiem tra san pham co dat khong");
            System.out.println("6. Thanh toan don hang");
            System.out.println("0. Thoat");

            choice = nhapInt(sc, "Nhap lua chon: ");

            switch (choice) {

                case 1: {
                    System.out.println("\n===== NHAP THONG TIN KHACH HANG =====");

                    System.out.print("Nhap ma khach hang: ");
                    String customerId = sc.nextLine();

                    System.out.print("Nhap ten khach hang: ");
                    String customerName = sc.nextLine();

                    System.out.print("Nhap so dien thoai: ");
                    String phone = sc.nextLine();

                    Customer customer = new Customer(
                            customerId, customerName, phone
                    );

                    System.out.println("\n===== CHON LOAI SAN PHAM =====");
                    System.out.println("1. GeneralProduct");
                    System.out.println("2. Book");
                    System.out.println("3. Cake");

                    int type = nhapInt(sc, "Chon loai: ");

                    if (type < 1 || type > 3) {
                        System.out.println("Loai san pham khong hop le!");
                        break;
                    }

                    System.out.print("Nhap ID san pham: ");
                    String productId = sc.nextLine();

                    // Kiem tra ID san pham da ton tai chua
                    if (productService.findById(productId) != null) {
                        System.out.println("ID san pham da ton tai!");
                        break;
                    }

                    System.out.print("Nhap ten san pham: ");
                    String productName = sc.nextLine();

                    double price = nhapDouble(sc, "Nhap gia: ");
                    int stock = nhapInt(sc, "Nhap ton kho: ");

                    if (price < 0 || stock < 0) {
                        System.out.println("Gia va ton kho khong hop le!");
                        break;
                    }

                    Product product = null;

                    if (type == 1) {
                        product = new GeneralProduct(
                                productId, productName, price, stock
                        );
                    } else if (type == 2) {
                        System.out.print("Nhap ten tac gia: ");
                        String author = sc.nextLine();

                        product = new Book(
                                productId, productName,
                                price, stock, author
                        );
                    } else {
                        System.out.print("Nhap thuong hieu banh: ");
                        String brand = sc.nextLine();

                        product = new Cake(
                                productId, productName,
                                price, stock, brand
                        );
                    }

                    int quantity = nhapInt(sc, "Nhap so luong mua: ");

                    if (quantity <= 0 || quantity > stock) {
                        System.out.println("So luong khong hop le!");
                        break;
                    }

                    System.out.print("Nhap ma don hang: ");
                    String orderId = sc.nextLine();

                    productService.addProduct(product);

                    Order order = new Order(
                            orderId, customer, product, quantity
                    );

                    orderService.addOrder(order);

                    System.out.println("Them don hang thanh cong!");
                    break;
                }

                case 2: {
                    System.out.println("\n===== DANH SACH DON HANG =====");
                    orderService.printOrders();
                    break;
                }

                case 3: {
                    System.out.print("Nhap ID don hang can sua: ");
                    String id = sc.nextLine();

                    orderService.updateOrder(id);
                    break;
                }

                case 4: {
                    System.out.print("Nhap ID don hang can xoa: ");
                    String id = sc.nextLine();

                    orderService.deleteOrder(id);
                    break;
                }

                case 5: {
                    System.out.print("Nhap ID san pham: ");
                    String id = sc.nextLine();

                    Product product = productService.findById(id);

                    if (product == null) {
                        System.out.println("Khong tim thay san pham!");
                    } else if (product.isExpensive()) {
                        System.out.println("San pham dat!");
                    } else {
                        System.out.println("San pham khong dat!");
                    }

                    break;
                }

                case 6: {
                    System.out.print("Nhap ID don hang: ");
                    String orderId = sc.nextLine();

                    System.out.println("\n===== PHUONG THUC THANH TOAN =====");
                    System.out.println("1. MoMo");
                    System.out.println("2. HITBank");

                    int paymentChoice = nhapInt(sc, "Chon phuong thuc: ");

                    PaymentService paymentService;

                    if (paymentChoice == 1) {
                        paymentService = new MoMoPaymentService();
                    } else if (paymentChoice == 2) {
                        paymentService = new BankPaymentService();
                    } else {
                        System.out.println("Phuong thuc khong hop le!");
                        break;
                    }

                    paymentService.pay(orderId);
                    break;
                }

                case 0: {
                    System.out.println("Da thoat chuong trinh!");
                    break;
                }

                default: {
                    System.out.println("Lua chon khong hop le!");
                }
            }

        } while (choice != 0);

        sc.close();
    }
}
