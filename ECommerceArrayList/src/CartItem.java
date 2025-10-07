public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void incrementQuantity() { quantity++; }
    public double getTotalPrice() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = ₹" + getTotalPrice();
    }
}
