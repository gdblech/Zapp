import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserUI {
    private JButton signInButton;
    private JButton searchButton;
    private JButton signUpButton;
    private JPanel start;
    private JScrollPane providers;


    public UserUI() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.print("you clicked sign in");
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Zapp: Find Electricians Fast");
        frame.setContentPane(new UserUI().start);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
