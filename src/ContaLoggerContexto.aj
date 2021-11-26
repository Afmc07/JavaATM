import org.aspectj.lang.annotation.Aspect;

@Aspect
public aspect ContaLoggerContexto {
//Log for deposits
    pointcut creditLog(Conta a, double b):
            call (* Conta.creditar(double)) &&
            target(a) &&
            args(b);

    before (Conta a, double b): creditLog(a, b){
        skip(0);
        System.out.println("Account: " + a.getNumero() + " || Balance: " + a.getSaldo());
        System.out.println("Processing credit of: " + b);
    }
    after (Conta a, double b): creditLog(a, b) {
        System.out.println("Credit successfully processed");
        System.out.println("Account: " + a.getNumero() + " || New Balance: " + a.getSaldo());
        skip(1);
    }
    
//Log for withdrawals
    pointcut debitLog(Conta a, double b):
            call (* Conta.debitar(double)) &&
            target(a) &&
            args(b);
//Balance check prior to proceeding.
    void around(Conta a, double b): debitLog(a, b){
        if(b>a.getSaldo()) {
            skip(0);
            System.out.println("Insufficient funds!");
        }
        else {
            proceed(a, b);
        }
    }

    before (Conta a, double b): debitLog(a, b){
        skip(0);
        System.out.println("Account: "+a.getNumero()+" || Balance: "+a.getSaldo());
        System.out.println("Processing debit of: "+b);
    }

    after (Conta a, double b): debitLog(a, b){
        if (b>a.getSaldo()){
            System.out.println("Transaction Failed.");
        }
        else{
            System.out.println("Debit successfully processed");
            System.out.println("Account: "+a.getNumero()+" || New Balance: "+a.getSaldo());
        }
        skip(1);

    }

//Process Log
    pointcut dataProc():
            call(void Controller.details());

    before (): dataProc(){
        skip(0);
        System.out.println("Loading details...");
        skip(1);
        System.out.println();
    }

    pointcut contProc():
            call(void Controller.process(*));

    before (): contProc(){
        skip(0);
        System.out.println("Processing response");
        skip(1);
    }

    private void skip(int a){
        if(a==0){
            System.out.println("------------------------");
            System.out.println();
        }
        else {
            System.out.println();
            System.out.println("------------------------");
        }
    }
}
