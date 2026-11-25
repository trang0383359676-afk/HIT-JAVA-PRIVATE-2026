
package model;

import java.util.Objects;

public abstract class Product {

    private String id;
    private String name;
    private double price;
    private int stock;

    // Constructor
    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Abstract method
    public abstract boolean isExpensive();

    // toString()
    @Override
    public String toString() {
        return "ID: " + id +
                ", Ten: " + name +
                ", Gia: " + price +
                ", Ton kho: " + stock;
    }

    // equals()
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product other = (Product) obj;

        return Objects.equals(this.id, other.id);
    }

    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
