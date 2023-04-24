import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CheckoutController implements ActionListener {
    private CheckoutScreen view;
    DataAccess myDAO;
    private Order order = null;

    public CheckoutController(CheckoutScreen view, DataAccess dao) {
        myDAO = dao;
        this.view = view;

        view.getBtnAdd().addActionListener(this);
        view.getBtnPay().addActionListener(this);
        view.getBtnExit().addActionListener(this);

        order = new Order();
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAdd())
            addProduct();
        else
        if (e.getSource() == view.getBtnPay())
            saveOrder();

        else if (e.getSource() == view.getBtnExit()) {
            this.view.setVisible(false);
            StoreManager.getInstance().getLoginScreen().setVisible(true);
        }
    }

    private void saveOrder() {
        Order order = new Order();
        try {
            int orderID = Integer.parseInt(view.txtOrderID.getText());
            order.orderID = orderID;
            order.date = view.txtDate.getText();
            order.customerName = view.txtCustomerName.getText();
            order.totalCost = Double.parseDouble(view.txtTotalCost.getText());
            order.totalTax = Double.parseDouble(view.txtTotalTax.getText());
            myDAO.saveOrder(order);
            JOptionPane.showMessageDialog(null, "Order saved successfully!");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for numbers!");
            ex.printStackTrace();
        }
    }

    private void addProduct() {
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        ProductModel product = myDAO.loadProduct(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }

        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 0 || quantity > product.getQuantity()) {
            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
            return;
        }

        OrderLine line = new OrderLine();
        line.setOrderID(this.order.getOrderID());
        line.setProductID(product.getProductID());
        line.setQuantity(quantity);
        line.setCost(quantity * product.getPrice());

        product.setQuantity(product.getQuantity() - quantity); // update new quantity!!
        myDAO.saveProduct(product); // and store this product back right away!!!

        order.getLines().add(line);
        order.setTotalCost(order.getTotalCost() + line.getCost());

        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = product.getName();
        row[2] = product.getPrice();
        row[3] = line.getQuantity();
        row[4] = line.getCost();

        this.view.addRow(row);
        this.view.getLabTotal().setText("Total: $" + order.getTotalCost());
        this.view.invalidate();
    }

}