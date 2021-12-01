import java.sql.*;

public class Main {
    public static void main (String[] args) throws SQLException{
        //process control class
        Controller ctr=null;

        //sql setup
        String jdbURL= "jdbc:mysql://localhost:3306/javatest";
        String username= "EXAMPLE";
        String password="EXAMPLE";
        Conta acc= null;

        //table setup
        Connection con= DriverManager.getConnection(jdbURL, username, password);
        Statement state= con.createStatement();
        String sql= "create table if not exists accounts(u_id int auto_increment primary key , accNum varchar(10) unique,  balance decimal(11, 2))";
        state.execute(sql);

        //Log-In/Sign-up window
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

        //Account DashBoard
        AccountManager man= new AccountManager(acc.getNumero(), ctr);

        if(!man.isActive())System.out.println("Process ended");
    }
}
