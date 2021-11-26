import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountManager extends JFrame{
    private JButton DepostitBtn;
    private JButton WithdrawalBtn;
    private JButton DetailsBtn;
    private JPanel MainPanel;
    private JLabel AccountNumLbl;
    private JButton logOutButton;

    public AccountManager(String accNum, Controller ctr) {
        setContentPane(MainPanel);
        setTitle("Account: "+accNum+" dashboard");
        AccountNumLbl.setText("Account Number: "+accNum);
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DepostitBtn.addActionListener(e -> {
            ctr.process(1);
        });
        WithdrawalBtn.addActionListener(e -> {
            ctr.process(2);
        });
        DetailsBtn.addActionListener(e -> {
            ctr.process(3);
        });
        logOutButton.addActionListener(e -> {
            dispose();
        });
    }
}
