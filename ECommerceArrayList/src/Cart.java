import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void addProduct(Product p) {
        for(CartItem ci : items) {
            if(ci.getProduct().getId() == p.getId()) {
                ci.incrementQuantity();
                p.setStock(p.getStock() - 1);
                System.out.println("Existing product quantity updated in cart. Quantity: " + ci.getQuantity());
                return;
            }
        }
        items.add(new CartItem(p));
        p.setStock(p.getStock() - 1);
        System.out.println(p.getName() + " added to cart. Quantity: 1");
    }

    public void viewCart() {
        if(items.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("--- Your Cart ---");
        double total = 0;
        for(CartItem ci : items) {
            System.out.println(ci);
            total += ci.getTotalPrice();
        }
        System.out.println("Total: ₹" + total);
    }

    public ArrayList<CartItem> getItems() { return items; }
    public void clearCart() { items.clear(); }
}
