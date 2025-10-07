import java.util.ArrayList;

public class Order {
    private String username;
    private ArrayList<CartItem> items;
    private double total;

    public Order(String username, ArrayList<CartItem> items) {
        this.username = username;
        this.items = new ArrayList<>(items);
        this.total = 0;
        for(CartItem ci : items) {
            total += ci.getTotalPrice();
        }
    }

    @Override
    public String toString() {
        return "Order by " + username + " | Items: " + items.size() + " | Total: ₹" + total;
    }
}
