import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InputWindow extends JFrame{
    private JTextField AccountNameInput;
    private JButton logInButton;
    private JPanel MainPanel;
    private JButton NewAccButton;
    private JLabel LabelMain;
    private Conta accs=null;
    private String sql;
    private ResultSet rst;
    private Boolean newACCF=false;


    public InputWindow(Statement state) {
        setContentPane(MainPanel);
        setTitle("Log-In");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        logInButton.addActionListener(e -> {
            if(!newACCF) {
                String acc = "\"" + AccountNameInput.getText() + "\"";
                sql = "select * from accounts where accNum=" + acc;
                try {
                    rst = state.executeQuery(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                try {
                    if (rst.next()) {
                        String a = rst.getString("accNum");
                        double b = rst.getDouble("balance");
                        accs = new Conta(a, b);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "No account exists with that number");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                String acc= AccountNameInput.getText();
                accs= new Conta(acc);
                sql= "insert into accounts(accNum, balance) values ("+accs.getNumero()+","+accs.getSaldo()+")";
                try {
                    state.execute(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        NewAccButton.addActionListener(e -> {
            newACCF= true;
            LabelMain.setText("Add new Account");
        });
    }

    public Conta getConta(){
        if (accs==null){
            return new Conta("-1", 0);
        }
        return accs;
    }

    public Boolean getNewACCF(){
        return newACCF;
    }
}
