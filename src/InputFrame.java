import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;

public class InputFrame extends JFrame{
    private JTextField InputBox;
    private JButton confirmButton;
    private JPanel MainPanel;
    private double am=-1;
    private double pre;
    private double post;
    private String sql;


    public InputFrame(String tit, Conta acc, Statement state){
        setContentPane(MainPanel);
        setTitle(tit);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        confirmButton.addActionListener(e ->{
            am= Double.parseDouble(InputBox.getText());
            if (tit.equals("Deposit")){
                acc.creditar(am);
                String t= "\""+acc.getNumero()+"\"";
                sql= "update accounts set balance= "+acc.getSaldo()+" where accNum="+t;
                try {
                    state.execute(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                pre= acc.getSaldo();
                acc.debitar(am);
                post= acc.getSaldo();

                if(pre==post || post<0){
                    acc.setSaldo(pre);
                    JOptionPane.showMessageDialog(this, "Transaction Failed: Insufficient Funds.");
                }
                else {
                    String t= "\""+acc.getNumero()+"\"";
                    sql= "update accounts set balance= "+acc.getSaldo()+" where accNum="+t;
                    try {
                        state.execute(sql);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            dispose();
        });
    }

    public double getAm(){
        return am;
    }
}
