import java.util.ArrayList;

public class Admin {
    private String password = "admin123";

    public boolean login(String pass) {
        return password.equals(pass);
    }

    public void addProduct(ArrayList<Product> products, Product p) {
        for(Product prod : products) {
            if(prod.getName().equalsIgnoreCase(p.getName())) {
                prod.setStock(prod.getStock() + p.getStock());
                System.out.println("Existing product stock updated.");
                return;
            }
        }
        products.add(p);
        System.out.println("New product added successfully.");
    }

    public void viewProducts(ArrayList<Product> products) {
        if(products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("--- Products ---");
        for(Product p : products) {
            System.out.println(p);
        }
    }

    public void viewOrders(ArrayList<Order> orders) {
        if(orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        System.out.println("--- Orders ---");
        for(Order o : orders) {
            System.out.println(o);
        }
    }
}
