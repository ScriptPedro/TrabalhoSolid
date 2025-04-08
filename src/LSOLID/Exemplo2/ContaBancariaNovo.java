package LSOLID.Exemplo2;


public abstract class ContaBancariaNovo {
    protected double saldo;


    public void depositarValor(double valor) {
        saldo += valor;
    }


    public void sacar(double valor) {
    }


    public double getSaldo() {
        return saldo;
    }
// não é necessário um setter (setSaldo) porque o saldo está sendo alterado diretamente dentro da própria classe através dos métodos depositarValor e sacar
}

