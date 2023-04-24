import javax.swing.*;

public class MainApp {

    private static MainApp instance;
    public static MainApp getInstance() {
        if (instance == null) {
            instance = new MainApp();
        }
        return instance;
    }

    private User currentUser = null;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Create the Product View and Controller here!

    private ProductView productView = new ProductView();

    private CheckoutScreen checkoutScreen = new CheckoutScreen();

    //private CheckoutScreen checkoutScreen = new CheckoutScreen();

    public ProductView getProductView() {
        return productView;
    }
    public LoginScreen getLoginScreen() { return loginScreen;}

    public CheckoutScreen getCheckoutScreen() {
        return checkoutScreen;
    }

    public LoginScreen loginScreen = new LoginScreen();


    public LoginController loginController; // = new LoginController(loginScreen, dataAdapter);

    private ProductController productController;

    public ProductController getProductController() {
        return productController;
    }

    private CheckoutController checkoutController;

    public CheckoutController getCheckoutController() {
        return checkoutController;
    }

    // Create the Product View and Controller here!
    public static void main(String[] args) {
        /* Test Data Access
        DataAccess dao = StoreManager.getInstance().getDataAccess();

        dao.connect();

        ProductModel prod = dao.loadProduct(1); // Apple;
        if (prod != null)
            System.out.println("Product with ID = " + prod.productID + " name = " + prod.name + " price = " + prod.price + " quantity = " + prod.quantity);

        prod.productID = 100;
        prod.name = "Samsung TV";
        prod.price = 399.99;
        prod.quantity = 1000;

        dao.saveProduct(prod);

        prod = dao.loadProduct(100); // Samsung!!!
        if (prod != null)
            System.out.println("Product with ID = " + prod.productID + " name = " + prod.name + " price = " + prod.price + " quantity = " + prod.quantity);


         */
//        StoreManager.getInstance().getProductView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StoreManager.getInstance().getLoginScreen().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StoreManager.getInstance().getLoginScreen().setVisible(true); // Show the LoginScreen!
    }
}
