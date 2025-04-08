package LSOLID.Exemplo2;

public class ContaPoupancaNovo extends ContaBancariaNovo {

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            throw new UnsupportedOperationException("Saldo insuficiente na conta Poupança");
        }
        saldo -= valor;
    }
}


