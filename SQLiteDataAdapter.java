import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data!!!
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public void saveOrder(Order order) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(order.orderID) == null) {           // this is a new order!
                stmt.execute("INSERT INTO 'Orders'(OrderID, OrderDate, Customer, TotalCost, TotalTax) VALUES ("
                        + order.orderID + ","
                        + '\'' + order.date + '\'' + ","
                        + '\'' + order.customerName + '\''
                        + order.totalCost + ","
                        + order.totalTax + ")"
                );

            }
            else {
                stmt.executeUpdate("UPDATE 'Orders' SET "
                        + "OrderID = " + order.orderID + ","
                        + "OrderDate = " + "'" + order.date + "'" + ","
                        + "Customer = " + "'" + order.customerName + "'" + ","
                        + "TotalCost = " + order.totalCost + ","
                        + "TotalTax = " + order.totalTax
                        + " WHERE OrderID = " + order.orderID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Order loadOrder(int orderId) {
        Order order = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM 'Orders' WHERE OrderID = " + orderId);
            if (rs.next()) {
                order = new Order();
                order.setOrderID(rs.getInt(1));
                order.setDate(rs.getString(2));
                order.setCustomerName(rs.getString(3));
                order.setTotalCost(rs.getDouble(4));
                order.setTotalTax(rs.getDouble(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;
    }

    @Override
    public User loadUser(String username) {
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE UserName = '" + username + "'");
            if (rs.next()) {
                user = new User();
                user.userID = rs.getInt(1);
                user.userName = rs.getString(2);
                user.password = rs.getString(3);
                user.displayName = rs.getString(4);
                user.isManager = rs.getBoolean(5);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

}
