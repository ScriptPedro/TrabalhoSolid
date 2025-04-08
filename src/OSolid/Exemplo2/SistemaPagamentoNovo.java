package OSolid.Exemplo2;

public class SistemaPagamentoNovo {
    public void realizarPagamento(double valor, MetodoPagamento metodoPagamento) {
        metodoPagamento.pagar(valor);
    }
}

