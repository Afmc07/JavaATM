import java.sql.*;

public class Main {
    public static void main (String[] args) throws SQLException{
        Controller ctr=null;
        //sql
        String jdbURL= "jdbc:mysql://localhost:3306/javatest"; //177.127.7.193
        String username= "root";
        String password="Jrp-25-Afm07";
        Conta acc= null;

        Connection con= DriverManager.getConnection(jdbURL, username, password);
        Statement state= con.createStatement();
        String sql= "create table if not exists accounts(u_id int auto_increment primary key , accNum varchar(10) unique,  balance decimal(11, 2))";
        state.execute(sql);

        InputWindow Input= new InputWindow(state);

        do {
            Conta a= Input.getConta();

            if(!"-1".equals(a.getNumero())) {
                acc = Input.getConta();
                ctr = new Controller(acc, state);
            }
        }
        while (Input.isActive() || acc==null);

        System.out.println("Account Opened");
        acc.showDetails();

        AccountManager man= new AccountManager(acc.getNumero(), ctr);

        if(!man.isActive())System.out.println("Process ended");
    }
}
