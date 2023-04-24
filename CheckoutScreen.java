import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckoutScreen extends JFrame {

    private JButton btnAdd = new JButton("Add a new item");
    private JButton btnPay = new JButton("Finish and Pay");

    private JButton btnExit = new JButton("Exit");

    private DefaultTableModel items = new DefaultTableModel(); // store information for the table!

    private JTable tblItems = new JTable(items); // null, new String[]{"ProductID", "Product Name", "Price", "Quantity", "Cost"});
    private JLabel labTotal = new JLabel("Total: ");
    public JTextField txtOrderID = new JTextField(30);
    public JTextField txtDate = new JTextField(30);
    public JTextField txtCustomerName = new JTextField(30);
    public JTextField txtTotalCost = new JTextField(30);
    public JTextField txtTotalTax = new JTextField(30);

    public CheckoutScreen() {

        this.setTitle("Checkout");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(800, 800);


        items.addColumn("Product ID");
        items.addColumn("Name");
        items.addColumn("Price");
        items.addColumn("Quantity");
        items.addColumn("Cost");

        JPanel panelOrder = new JPanel();
        panelOrder.setBackground(Color.white);
        panelOrder.setPreferredSize(new Dimension(800, 400));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        tblItems.setBounds(0, 0, 400, 350);
        panelOrder.add(tblItems.getTableHeader());
        panelOrder.add(tblItems);
        panelOrder.add(labTotal);
        tblItems.setFillsViewportHeight(true);
        this.getContentPane().add(panelOrder);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(400, 100));
        panelButton.add(btnAdd);
        panelButton.setBackground(Color.white);
        btnAdd.setBackground(Color.blue);
        btnAdd.setForeground(Color.white);
        btnAdd.setOpaque(true);
        btnAdd.setBorderPainted(false);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelButton.add(btnPay);
        btnPay.setBackground(Color.blue);
        btnPay.setForeground(Color.white);
        btnPay.setOpaque(true);
        btnPay.setBorderPainted(false);
        btnPay.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.getContentPane().add(panelButton);

        JPanel line1 = new JPanel();
        line1.setBackground(Color.white);
        line1.add(new JLabel("Order ID: "));
        line1.add(txtOrderID);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.setBackground(Color.white);
        line2.add(new JLabel("Order Date: "));
        line2.add(txtDate);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.setBackground(Color.white);
        line3.add(new JLabel("Customer Name: "));
        line3.add(txtCustomerName);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.setBackground(Color.white);
        line4.add(new JLabel("Total Cost: "));
        line4.add(txtTotalCost);
        this.getContentPane().add(line4);

        JPanel line5 = new JPanel();
        line5.setBackground(Color.white);
        line5.add(new JLabel("Total Tax: "));
        line5.add(txtTotalTax);
        this.getContentPane().add(line5);

        JPanel panelExit = new JPanel();
        panelExit.add(btnExit);
        panelExit.setBackground(Color.white);
        btnExit.setBackground(Color.blue);
        btnExit.setForeground(Color.white);
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));

        this.getContentPane().add(panelExit);

    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnPay() {
        return btnPay;
    }

    public JButton getBtnExit() { return btnExit; }

    public JLabel getLabTotal() {
        return labTotal;
    }

    public void addRow(Object[] row) {
        items.addRow(row);              // add a row to list of item!
        //    items.fireTableDataChanged();
    }
}