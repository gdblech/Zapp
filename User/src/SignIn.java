import javax.swing.*;
import java.awt.event.*;

public class SignIn extends JDialog {
    private JPanel contentPane;
    private JButton buttonSignIn;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel userNameLabel;
    private JLabel passwordLabel;

    public SignIn() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSignIn);

        buttonSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSIgnIn();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSIgnIn() {

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SignIn dialog = new SignIn();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
