public interface DataAccess {
    void connect();

    void saveProduct(ProductModel product);

    ProductModel loadProduct(int productID);

    void saveOrder(Order order);

    Order loadOrder(int orderID);

    User loadUser(String username);
}
