import java.text.DecimalFormat;

public class Conta {
    private String numero;
    private double saldo = 100.0;
    private DecimalFormat df= new DecimalFormat("0.00");

    public Conta(String numero){
        this.numero = numero;

    }

    public Conta(String numero, double saldo){
        this.numero= numero;
        this.saldo= saldo;
    }

    public void creditar (double valor){
        saldo += valor;
        df.format(saldo);
    }

    public void debitar (double valor){
        saldo -= valor;
        df.format(saldo);
    }

    public void setSaldo(double d){
        saldo= d;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void showDetails(){
        System.out.println("Account " + getNumero() + " || Balance: "+ getSaldo());
    }
}
