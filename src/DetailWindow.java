import javax.swing.*;

public class DetailWindow extends JFrame {

    private JPanel MainPanel;
    private JLabel AccountLbl;
    private JLabel BalanceLbl;

    public DetailWindow(String num, double am){
        setContentPane(MainPanel);
        setTitle("Details");
        AccountLbl.setText("Account: #"+num);
        BalanceLbl.setText("Balance: $"+am);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
