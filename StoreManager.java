public class StoreManager {

    private static StoreManager instance;

    private RemoteDataAdapter dao;

    private ProductView productView = null;

    public ProductView getProductView() {
        return productView;
    }

    private ProductController productController = null;

    private LoginScreen loginScreen = null;

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public LoginController loginController = null;

    private CheckoutScreen checkoutScreen = null;

    public CheckoutScreen getCheckoutScreen() {
        return checkoutScreen;
    }

    public CheckoutController checkoutController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();
        loginScreen = new LoginScreen();
        loginController = new LoginController(loginScreen, dao);
        productView = new ProductView();
        productController = new ProductController(productView, dao);
        checkoutScreen = new CheckoutScreen();
        checkoutController = new CheckoutController(checkoutScreen, dao);

    }






}
