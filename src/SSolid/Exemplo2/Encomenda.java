package SSolid.Exemplo2;

public class Encomenda {
    private final String id;
    private final double peso;

    public Encomenda(String id, double peso) {
        this.id = id;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }
}

