import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;

public class LoginScreen extends JFrame {
    private JTextField txtUserName = new JTextField(10);
    private JTextField txtPassword = new JPasswordField(10);
    private JTextField txtUserID  = new JTextField(10);
    private JButton    btnLogin    = new JButton("Login");
    private JLabel txtIntro = new JLabel("Welcome to Tyler's Store!");
    private JButton btnExit = new JButton("Exit");

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JTextField getTxtUserName(){ return txtUserName; }
    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public LoginScreen() {

        this.setSize(400, 250);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panelIntro = new JPanel();
        panelIntro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIntro.add(txtIntro);
        txtIntro.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtIntro.setForeground(Color.white);
        panelIntro.setBackground(Color.blue);

        this.getContentPane().add(panelIntro);

        JPanel panelUserName = new JPanel();
        panelUserName.add(new JLabel("Username:"));
        panelUserName.add(txtUserName);

        this.getContentPane().add(panelUserName);
        panelUserName.setBackground(Color.white);

        JPanel panelPassword = new JPanel();
        panelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPassword.add(new JLabel("Password:"));
        panelPassword.add(txtPassword);

        this.getContentPane().add(panelPassword);

        panelPassword.setBackground(Color.white);


        JPanel panelLogin = new JPanel();
        panelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(btnLogin);
        panelLogin.setBackground(Color.white);
        btnLogin.setBackground(Color.blue);
        btnLogin.setForeground(Color.white);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));

        this.getContentPane().add(panelLogin);

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
}