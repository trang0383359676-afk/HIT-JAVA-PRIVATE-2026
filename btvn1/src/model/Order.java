
package model;

public class Order {
    private String id;
    private Customer customer;
    private Product product;
    private int quantity;

    public Order(String id, Customer customer, Product product, int quantity) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Ma don hang: " + id
                + " | Khach hang: " + customer
                + " | San pham: " + product
                + " | So luong: " + quantity
                + " | Thanh tien: " + calculateTotal();
    }
}
