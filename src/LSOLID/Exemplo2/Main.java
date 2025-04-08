package LSOLID.Exemplo2;


public class Main {
    public static void main(String[] args) {
        ContaBancaria contaBancaria = new ContaBancaria() {
            @Override
            public void sacar(double valor) {
                if (valor > saldo) {
                    throw new UnsupportedOperationException("Saldo insuficiente para saque na Conta Bancária.");
                }
                saldo -= valor;
            }
        };


        ContaBancaria contaPoupanca = new ContaPoupanca();


       
        contaBancaria.depositar(1000);
        contaPoupanca.depositar(2000);


        // Exibindo saldos antes dos saques
        System.out.println("Saldo Conta Bancária: " + contaBancaria.getSaldo());
        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());


        try {
            contaBancaria.sacar(500);
            contaPoupanca.sacar(300);
        } catch (Exception e) {
            System.out.println("Erro ao sacar: " + e.getMessage());
        }


        System.out.println("Saldo Conta Bancária após saque: " + contaBancaria.getSaldo());
        System.out.println("Saldo Conta Poupança após saque: " + contaPoupanca.getSaldo());


        try {
            contaBancaria.sacar(600);
        } catch (Exception e) {
            System.out.println("Erro ao sacar da Conta Bancária: " + e.getMessage());
        }


        try {
            contaPoupanca.sacar(2500);
        } catch (Exception e) {
            System.out.println("Erro ao sacar da Conta Poupança: " + e.getMessage());
        }
    }
}

