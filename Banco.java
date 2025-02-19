import java.util.ArrayList;

// Classe Abstrata ContaBancaria
abstract class ContaBancaria {
    protected String numeroConta;
    protected double saldo;

    // Construtor
    public ContaBancaria(String numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    // Métodos abstratos
    abstract void depositar(double valor);
    abstract void sacar(double valor) throws SaldoInsuficienteException;


    // Método concreto
    public void exibirSaldo() {
        System.out.println("número da conta: " + numeroConta);
        System.out.println("Saldo da conta: " + saldo);
    }

}

// Interface Rentavel
interface Rentavel {
    void aplicarRendimento();
}


// Classe ContaCorrente
class ContaCorrente extends ContaBancaria {
    private double limiteChequeEspecial;

    // Construtor
    public ContaCorrente(String numeroConta, double saldo, double limiteChequeEspecial) {
        super(numeroConta, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Implementação do método depositar
    @Override
    public void depositar(double valor) {
        this.saldo += valor; // acho que eu nao preciso colocar o this. ,, verificar dps
        System.out.println("Depósito de " + valor + " realizado. Novo Saldo: " + saldo);
    }

    // Implementação do método sacar
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo + limiteChequeEspecial) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque. Compre ações do habibs");
        }
        saldo -= valor;
        System.out.println("Saque de " + valor + " realizado. Novo saldo: " + saldo);
    }

    // Sobrescreve o método exibirSaldo
    @Override
    public void exibirSaldo() {
        super.exibirSaldo();
        System.out.println("Limite do cheque Especial: " + limiteChequeEspecial);
    }

}

// Classe ContaPoupanca
class ContaPoupaca extends ContaBancaria implements Rentavel {
    private double taxaRendimento;

    // Construtor
    public ContaPoupaca(String numeroConta, double saldo, double taxaRendimento) {
        super(numeroConta, saldo);
        this.taxaRendimento = taxaRendimento;
    }

    // Implementação do método depositar
    @Override 
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado. Novo saldo: " + saldo);
    }

    // Implementação do método sacar
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque. va comer na pizza forte");
        }
        saldo -= valor;
        System.out.println("Saque de " + valor + " realizado. Novo saldo: " + saldo);
    }

    // Implementação do método aplicarRendimento
    @Override
    public void aplicarRendimento() {
        double rendimento = saldo * (taxaRendimento / 100);
        saldo += rendimento;
        System.out.println("Rendimento aplicado. Novo saldo " + saldo);
    }

    //Sobrescreve o método exibirSaldo
    @Override
    public void exibirSaldo() {
        super.exibirSaldo();
        System.out.println("Taxa de rendimento: " + taxaRendimento);
    }
}

// Classe ContaInvestimento
class ContaInvestimento extends ContaBancaria implements Rentavel {
    private String tipoInvestimento;

    //  Construtor
    public ContaInvestimento(String numeroConta, double saldo, String tipoInvestimento) {
        super(numeroConta, saldo);
        this.tipoInvestimento = tipoInvestimento;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado. Novo saldo: " + saldo);
    }

    // Implementação do método sacar
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        }
        saldo -= valor;
        System.out.println("Saque de " + valor + " realizado. Novo saldo: " + saldo);
    }

    // Implementação do método aplicarRendimento
    @Override
    public void aplicarRendimento() {
        double rendimento = 0;
        if (tipoInvestimento.equalsIgnoreCase("Pizza Forte")) {
            rendimento = saldo * 0.05; // 5% de rendimento
        } else if (tipoInvestimento.equalsIgnoreCase("Habibs")) {
            rendimento = saldo * 0.03; // 3% de rendimento
        }
        saldo += rendimento;
        System.out.println("Rendimento aplicado. Novo saldo: " + saldo);
    }

    // Sobrescreve o método exibirSaldo
    @Override
    public void exibirSaldo() {
        super.exibirSaldo();
        System.out.println("Tipo de investimento: " + tipoInvestimento);
    }

}

// Classe de Exceção SaldoInsuficienteException
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}


// Main

public class Banco {
    public static void main(String[] args) {
        // Criando uma lista de contas bancárias
        ArrayList<ContaBancaria> contas = new ArrayList<>();

        // Adicionando contas à lista
        contas.add(new ContaCorrente("12345", 1000.0, 500.0));
        contas.add(new ContaPoupaca("67890", 2000.0, 0.5));
        contas.add(new ContaInvestimento("54321", 5000.0, "Habibs"));
        contas.add(new ContaInvestimento("54321", 5000.0, "Pizza Forte"));

        // Percorrendo a lista e realizando operações
        for (ContaBancaria conta : contas) {
            try {
                conta.exibirSaldo();
                conta.depositar(200.0);
                conta.sacar(300.0);
                if (conta instanceof Rentavel) {
                    ((Rentavel) conta).aplicarRendimento();
                }
                System.out.println(); // Pula uma linha
            } catch (SaldoInsuficienteException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}