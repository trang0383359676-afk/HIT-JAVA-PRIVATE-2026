
import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void printProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sach san pham rong!");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }
}
