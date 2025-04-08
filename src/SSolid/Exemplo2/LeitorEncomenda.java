package SSolid.Exemplo2;

import java.util.Scanner;

public class LeitorEncomenda {

    public Encomenda ler() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o ID da encomenda: ");
            String id = sc.nextLine();

            System.out.println("Digite o peso (em kg): ");
            double peso = sc.nextDouble();

            return new Encomenda(id, peso);
        }
    }
}

