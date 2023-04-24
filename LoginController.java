import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginScreen loginScreen;
    DataAccess myDAO;

    private User user = null;


    public LoginController(LoginScreen loginScreen, DataAccess dao) {
        this.loginScreen = loginScreen;
        myDAO = dao;
        this.loginScreen.getBtnLogin().addActionListener(this);
        this.loginScreen.getBtnExit().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreen.getBtnLogin()) {
            String username = loginScreen.getTxtUserName().getText().trim();
            String password = loginScreen.getTxtPassword().getText().trim();

            System.out.println("Login with username = " + username + " and password = " + password);
            User user = myDAO.loadUser(username);


            if (user == null) {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            } else if (user.isManager()) {
                MainApp.getInstance().setCurrentUser(user);
                this.loginScreen.setVisible(false);
                StoreManager.getInstance().getProductView().setVisible(true);
            } else {
                MainApp.getInstance().setCurrentUser(user);
                this.loginScreen.setVisible(false);
                StoreManager.getInstance().getCheckoutScreen().setVisible(true);
            }
        }
        else if (e.getSource() == loginScreen.getBtnExit()) {
            System.exit(0);
        }
    }

}