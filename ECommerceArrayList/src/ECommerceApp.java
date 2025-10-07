import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceApp {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    static User loggedInUser = null;

    public static void main(String[] args) {
        Admin admin = new Admin();
        products.add(new Product(1, "Laptop", 55000, 5));
        products.add(new Product(2, "Mobile", 20000, 10));
        products.add(new Product(3, "Headphones", 1500, 20));

        while(true) {
            System.out.println("\n=== E-Commerce App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1: register(); break;
                case 2: login(admin); break;
                case 3: adminMenu(admin); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void register() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        users.add(new User(uname, pass));
        System.out.println("Registration successful!");
    }

    static void login(Admin admin) {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        for(User u : users) {
            if(u.getUsername().equals(uname) && u.getPassword().equals(pass)) {
                loggedInUser = u;
                System.out.println("Login successful!");
                userMenu();
                return;
            }
        }
        System.out.println("Invalid credentials!");
    }

    static void userMenu() {
        Cart cart = new Cart();
        while(true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch(ch) {
                case 1: viewProducts(); break;
                case 2: addToCart(cart); break;
                case 3: cart.viewCart(); break;
                case 4: placeOrder(cart); break;
                case 5: loggedInUser = null; return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void viewProducts() {
        if(products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("--- Available Products ---");
        for(Product p : products) {
            System.out.println(p);
        }
    }

    static void addToCart(Cart cart) {
        viewProducts();
        System.out.print("Enter product ID to add: ");
        int id = sc.nextInt();
        sc.nextLine();
        Product p = null;
        for(Product prod : products) {
            if(prod.getId() == id) { p = prod; break; }
        }
        if(p != null) {
            cart.addProduct(p);
        } else {
            System.out.println("Invalid product ID.");
        }
    }

    static void placeOrder(Cart cart) {
        if(cart.getItems().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        orders.add(new Order(loggedInUser.getUsername(), cart.getItems()));
        System.out.println("Order placed successfully.");
        cart.clearCart();
    }

    static void adminMenu(Admin admin) {
        System.out.print("Enter admin password: ");
        String pass = sc.nextLine();
        if(!admin.login(pass)) {
            System.out.println("Access denied!");
            return;
        }
        while(true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. View Orders");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch(ch) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();
                    admin.addProduct(products, new Product(id, name, price, stock));
                    break;
                case 2: admin.viewProducts(products); break;
                case 3: admin.viewOrders(orders); break;
                case 4: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}
