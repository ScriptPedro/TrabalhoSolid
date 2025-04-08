package OSolid.Exemplo2;

public class Main {
    public static void main(String[] args) {
        SistemaPagamento sistema = new SistemaPagamento();

        sistema.realizarPagamento(100.0, new PagamentoCartao());
        sistema.realizarPagamento(50.0, new PagamentoPix());
        sistema.realizarPagamento(80.0, new PagamentoBoleto());
    }
}

