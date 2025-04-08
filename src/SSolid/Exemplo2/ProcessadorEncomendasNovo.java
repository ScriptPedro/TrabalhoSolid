package SSolid.Exemplo2;

public class ProcessadorEncomendasNovo {

    private final LeitorEncomenda leitor = new LeitorEncomenda();
    private final CalculadoraEncomenda calculadora = new CalculadoraEncomenda();
    private final ArquivoEncomenda repositorio = new ArquivoEncomenda();

    public void processar() {
        try {
            Encomenda encomenda = leitor.ler();
            double valorFrete = calculadora.calcular(encomenda.getPeso());

            System.out.println("Valor do frete calculado: " + valorFrete);
            repositorio.salvar(encomenda.getId(), valorFrete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
