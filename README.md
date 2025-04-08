ALTERAÇÕES NAS PRÁTICAS DO SOLID:


Substituição de Liskov:

Código anterior:
package LSOLID.Exemplo2;


public class ContaBancaria {
    protected double saldo;


    public void depositar(double valor) {
        saldo += valor;
    }


    public void sacar(double valor) {
        saldo -= valor;
    }


    public double getSaldo() {
        return saldo;
    }
}



package ISOLID.Exemplo2;


public class Carro implements Veiculo {
    @Override
    public void dirigir() {
        System.out.println("Carro está dirigindo na estrada...");
    }


    @Override
    public void voar() {
        throw new UnsupportedOperationException("Carro não voa!");
    }


    @Override
    public void navegar() {
        throw new UnsupportedOperationException("Carro não navega!");
    }
}




Código atual:

Classe Mãe:
package LSOLID.Exemplo2;


public class ContaBancaria {
    protected double saldo;


    public void depositarValor(double valor) {
        saldo += valor;
    }


    public abstract void sacar(double valor) {
    }


    public double getSaldo() {
        return saldo;
    }
// não é necessário um setter (setSaldo) porque o saldo está sendo alterado diretamente dentro da própria classe através dos métodos depositarValor e sacar
}






Classe filha:
package LSOLID.Exemplo2;


public class ContaPoupanca extends ContaBancaria {


    @Override
    public void sacar(double valor) {
       if (valor > saldo){
        throw new UnsopportedOperationExcepetion("Saldo insuficiente na conta Poupança")
       }
       saldo -= valor
    }
}





MAIN:
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


       
        contaBancaria.depositarValor(1000);
        contaPoupanca.depositarValor(2000);


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





O que foi alterado:
Classe ContaBancaria: Tornamos a classe ContaBancaria abstrata e o método sacar foi alterado para ser abstrato. Isso força as classes filhas a implementarem o comportamento do saque de forma personalizada.


Classe ContaPoupanca: Em vez de lançar uma exceção no sacar, agora podemos implementar um comportamento de saque específico para a Conta Poupança. Aqui, você pode personalizar as regras de saque (como saques somente após um período ou com saldo mínimo), mantendo a substituição válida.

Benefícios dessa refatoração:
Princípio de Substituição de Liskov: Agora, a ContaPoupanca pode ser usada no lugar da ContaBancaria sem quebrar o comportamento esperado. O método sacar é implementado de maneira que faz sentido para a conta poupança, sem violar o contrato da classe base.


Flexibilidade: Agora, a classe base ContaBancaria permite que as subclasses definam seus próprios comportamentos para o saque, mantendo o código mais modular e flexível.



PRINCÍPIO SEGREGAÇÃO DA INTERFACE:

Código antigo:

package ISOLID.Exemplo2;


public class Carro implements Veiculo {
    @Override
    public void dirigir() {
        System.out.println("Carro está dirigindo na estrada...");
    }


    @Override
    public void voar() {
        throw new UnsupportedOperationException("Carro não voa!");
    }


    @Override
    public void navegar() {
        throw new UnsupportedOperationException("Carro não navega!");
    }
}




package ISOLID.Exemplo2;


public interface Veiculo {
    void dirigir();
    void voar();
    void navegar();
}








O princípio da Segregação da Interface (Interface Segregation Principle, ISP) sugere que uma interface deve ter apenas os métodos que são relevantes para os clientes que a implementam. Ou seja, se uma classe não utiliza um método de uma interface, ela não deve ser obrigada a implementá-lo.
No seu código, a interface Veiculo define métodos como dirigir(), voar() e navegar(), mas nem todos os veículos vão precisar de todos esses comportamentos. O Carro não precisa dos métodos voar() e navegar(), por exemplo, e isso está violando o princípio ISP, pois a classe Carro é forçada a implementar métodos que ela não usa.
Para corrigir isso, o necessário é dividir a interface Veiculo em várias interfaces menores e mais específicas. Veja como ficaria o código após a refatoração:
—---------------------------------------------------------------------------------------------------------------
Código atual:

Criação da interface Veículo
public interface Veiculo {
   void dirigir();
}


Criação da interface de veículos aéreos 
public interface Aereo {
   void voar();
}


Criação da interface de veículos aquáticos
public interface Aquatico {
   void navegar();
}


Criação da classe Carro
public class Carro implements Veiculo {
   @Override
   public void dirigir() {
       System.out.println("Carro está dirigindo na estrada...");
   }
}


Como isso melhora o código?
Segregação de Interfaces: Cada tipo de veículo implementa apenas os métodos que fazem sentido para ele. O carro não precisa implementar métodos para voar ou navegar, enquanto o avião e o barco implementam apenas os métodos relevantes.


Código mais limpo e flexível: Agora, os clientes que usam essas interfaces podem escolher apenas os métodos que realmente precisam, sem ter que lidar com métodos que não fazem sentido. Isso também facilita a manutenção e extensibilidade do código, pois novos tipos de veículos (como helicópteros, barcos, etc.) podem ser adicionados sem impactar negativamente o restante do sistema.

O Princípio da Segregação de Interfaces foi aplicado dividindo a interface Veiculo em interfaces menores e mais específicas (Veiculo, Aereo, Aquatico).


Isso garante que cada classe de veículo implemente apenas os métodos que são relevantes para ela, sem ser forçada a implementar métodos que não fazem sentido.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

1️⃣ Sistema de Pagamento - Violação do Princípio Open/Closed (OCP)
Problema Identificado:
O código original da classe SistemaPagamento violava o Princípio Aberto/Fechado (OCP), pois era necessário modificar a lógica interna da classe sempre que um novo método de pagamento era adicionado, utilizando estruturas condicionais (if/else). Isso tornava o sistema difícil de escalar e de manter.

Solução Aplicada:
Refatoramos o código utilizando polimorfismo e abstração. Criamos uma interface MetodoPagamento, e cada método de pagamento passou a ser uma classe que implementa essa interface (PagamentoCartao, PagamentoPix, PagamentoBoleto, etc).

Benefícios da Refatoração:

O código agora está aberto para extensão, permitindo adicionar novos métodos de pagamento facilmente.

O código está fechado para modificação, pois SistemaPagamento não precisa mais ser alterada.

Respeito ao OCP, mantendo o código mais limpo e modular.

Resumo da Nova Estrutura:

MetodoPagamento: Interface base

PagamentoCartao, PagamentoPix, PagamentoBoleto: Implementações específicas

SistemaPagamento: Orquestrador que recebe qualquer MetodoPagamento

Main: Demonstração de uso via polimorfismo

2️⃣ Processador de Encomendas - Violação do Princípio da Responsabilidade Única (SRP)
Problema Identificado:
A classe ProcessadorEncomendas realizava múltiplas responsabilidades:

Entrada de dados via terminal

Cálculo de frete

Escrita em arquivo

Essa violação ao Princípio da Responsabilidade Única (SRP) dificultava a manutenção, testabilidade e reutilização do código.

Solução Aplicada:
Dividimos as responsabilidades em classes distintas, cada uma com uma única função bem definida:

LeitorEncomenda: Responsável por coletar os dados do usuário

CalculadoraFrete: Realiza o cálculo do frete

ArquivoEncomendaRepository: Persiste os dados em arquivo

Encomenda: Entidade com id e peso

ProcessadorEncomendas: Classe orquestradora que integra tudo

Benefícios da Refatoração:

Cada classe agora possui apenas uma responsabilidade (SRP respeitado)

Código mais modular e reutilizável

Facilidade para testes unitários em cada componente

Melhor legibilidade e organização do código

Resumo da Nova Estrutura:

Separação clara entre entrada de dados, lógica de negócio e persistência

Fácil adição de novos tipos de persistência (ex: banco de dados) ou fontes de entrada (ex: API)
