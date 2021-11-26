import java.sql.Statement;

public class Controller {
    Conta acc;
    InputFrame input;
    DetailWindow dts;
    Statement state;

    Controller(Conta c, Statement s){
        this.acc=c;
        this.state=s;
    }

    public void process(int a){
        switch (a){
            case 1->deposit();
            case 2->withdraw();
            case 3->details();
        }
    }

    private void deposit(){
        input = new InputFrame("Deposit", acc, state);
    }

    private void withdraw(){
        input = new InputFrame("Withdrawal", acc, state);
    }

    private void details(){
        dts = new DetailWindow(acc.getNumero(), acc.getSaldo());
    }
}
