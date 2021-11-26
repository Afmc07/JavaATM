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

//Log-in and Sign-Up window
    public InputWindow(Statement state) {
        setContentPane(MainPanel);
        setTitle("Log-In");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        logInButton.addActionListener(e -> {

            //new account flag check: if true user will be directed to account creation process
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

                //verifying new account number isn't taken.
                sql = "select * from accounts where accNum=" + acc;
                try {
                    rst = state.executeQuery(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    if(rst.next()){
                        JOptionPane.showMessageDialog(this, "This number is already taken.");
                    }
                    else{
                        //adding new account to database
                        accs= new Conta(acc);
                        sql= "insert into accounts(accNum, balance) values ("+accs.getNumero()+","+accs.getSaldo()+")";
                        try {
                            state.execute(sql);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //New Account toggle
        NewAccButton.addActionListener(e -> {
            newACCF= true;
            LabelMain.setText("Add new Account");
            NewAccButton.setVisible(false);
            logInButton.setText("Sign up");
        });
    }

    public Conta getConta(){
        if (accs==null){
            return new Conta("-1", 0);
        }
        return accs;
    }
}
