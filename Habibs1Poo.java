import java.util.ArrayList;


// Classe Abstrata Funcionario
abstract class Funcionario {
    private String nome;
    private int matricula;
    protected double salarioBase;

    // Construtor
    public Funcionario(String nome, int matricula, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getMatricula(){
        return matricula;
    }

    public void setMatricula(int matricula){
        this.matricula = matricula;
    }

    public double getSalarioBase(){
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase){
        this.salarioBase = salarioBase;
    }

    // Método abstrato para calcular o salário
    public abstract double calcularSalario();
    // Método concreto para exibir dados
    public void exibirDados(){
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Base do funcionário: " + salarioBase);
    }
}

// Classe Gerente (herda de Funcionario)
class Gerente extends Funcionario {
    private double bonus;

    // Construtor
    public Gerente(String nome, int matricula, double salarioBase, double bonus){
        super(nome, matricula, salarioBase); // Chama o construtor da cl pai
        this.bonus = bonus;
    }

    // Implementação do método abstrato
    @Override
    public double calcularSalario() {
        return salarioBase + bonus; // Salário total = salário base + bônus
    }

    // 
    @Override
    public void exibirDados() {
        super.exibirDados(); // chama o método da classe pai
        System.out.println("Bônus " + bonus);
        System.out.println("Salário total : " + calcularSalario());
    }

}

// Classe Desenvolvedor (herda de Funcionario)
class Desenvolvedor extends Funcionario {
    private int horasExtras;

    // Construtor
    public Desenvolvedor(String nome, int matricula, double salarioBase, int horasExtras){
        super(nome, matricula, salarioBase); // Chama o construtor da cl
        this.horasExtras = horasExtras;
    }

    // Implementação do método abstrato
    @Override
    public double calcularSalario(){
        return salarioBase + (horasExtras * 50); // Salário total
    }

    // Sobrescreve o método exibirDados para incluir as horas extras
    @Override
    public void exibirDados(){
        super.exibirDados(); // Chama o método da classe pai
        System.out.println("Horas extras: " + horasExtras);
        System.out.println("Salário total: " + calcularSalario());
    }
}

// Classe Main para testar o sistema
public class Habibs1Poo{
    public static void main(String[] args) {
        // Criando uma lista de funcionários
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        // Adicionando um gerente e um desenvolvedor à lista
        funcionarios.add(new Gerente("Carlos Pobre", 101, 5000.0, 2000.0));
        funcionarios.add(new Desenvolvedor("Maria Souza", 102, 4000.0, 10));

        // Percorrendo a lista e chamando os métodos de cada funcionário
        for (Funcionario pessoas : funcionarios) {
            pessoas.exibirDados(); // Exebe os dados do funcionário
            System.out.println(); // Pula uma linha
        }

        
    }
}