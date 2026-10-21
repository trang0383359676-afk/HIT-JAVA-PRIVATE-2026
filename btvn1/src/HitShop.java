import java.util.Scanner;

public class HitShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên mặt hàng: ");
        String matHang = sc.nextLine();
        System.out.print("Nhập đơn giá: ");
        double donGia = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(sc.nextLine());
        System.out.println("Tên mặt hàng: " + matHang);
        System.out.println("Đơn giá: " + donGia);
        System.out.println("Số lượng: " + soLuong);
        System.out.println("Thành tiền: " + donGia*soLuong);
        final double VAT_RATE = 0.10;
        final double SHIPPING_FEE = 20000;
        double tongThanhToan = donGia*soLuong + VAT_RATE*donGia*soLuong + SHIPPING_FEE;
        System.out.println("Tổng thanh toán: " + tongThanhToan);
    }
}
