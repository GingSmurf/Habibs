import java.util.ArrayList;

abstract class Animal {
    private String nome;
    private int idade;

    public Animal(String nome, int idade){
        this.nome = nome;
        this. idade = idade;
    }

    abstract void emitirSom();

    public void exibirInformacoes(){
        System.out.println("Nome do animal: " + nome);
        System.out.println("Idade do Animal: " + idade);
    }
}

interface Comportamento {
    void mover(); // Método abstrato (não tem corpo)
}

class Cachorro extends Animal implements Comportamento {
    private String raca;

    // Construtor
    public Cachorro(String name, int idade, String raca) {
        super(name, idade); // Chama o construtor da cl animal
        this.raca = raca;
    }

    // Implementação do método emitirSom() (herdado de Animal)
    @Override
    public void emitirSom(){
        System.out.println("Au Au");
    }

    // Implementação do método mover() (herdado de Comportamento)
    @Override
    public void mover(){
        System.out.println("O cachorro está correndo!");
    }

    // Sobrescreve o método exibirInformacoes() (herdado de Animal)
    @Override
    public void exibirInformacoes(){
        super.exibirInformacoes(); // Chama o método da classe Animal
        System.out.println("Raça do cachorro: " + raca);
    }
}

// Classe Gato
class Gato extends Animal implements Comportamento {
    private boolean temPelagemDeLobo;

    // Construtor
    public Gato(String name, int idade, boolean temPelagemDeLobo) {
        super(name, idade); // Chama o construtor da cl Animal
        this.temPelagemDeLobo = temPelagemDeLobo;
    }

    // Implementação do método emitirSom() (herdado de Animal)
    @Override
    public void emitirSom() {
        System.out.println("Miau!");
    }

    // Implementação do método mover() (herdado de Comportamento)
    @Override
    public void mover(){
        System.out.println("O gato está pulando!");
    }

    // Sobrescreve o método exibirInformacoes() (herdado de Animal)
    @Override
    public void exibirInformacoes(){
        super.exibirInformacoes();

        if (temPelagemDeLobo == true) {
            System.out.println("Pelagem de lobo: sim 🐺");
        }
        else {
            System.out.println("Pelagem de lobo: não 🐑");
        }

    }
}

class Passaro extends Animal implements Comportamento {
    private String corDasPenas;

    // Construtor
    public Passaro(String name, int idade, String corDasPenas) {
        super(name, idade);
        this.corDasPenas = corDasPenas;
    }

    // Implementação do método emitirSom() (herdado de Animal)
    @Override
    public void emitirSom() {
        System.out.println("Piu Piu!");
    }

    // Implementação do método mover() (herdado de Comportamento)
    @Override
    public void mover(){
        System.out.println("O pássaro está voando!");
    }

    // Sobrescreve o método exibirInformacoes() (herdado de Animal)
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Cor das penas: " + corDasPenas);
    }
}

public class Animais {
    public static void main(String[] args) {
        // Criando uma lista de animais
        ArrayList<Animal> animais = new ArrayList<>();

        // Adicionando animais à lista
        animais.add(new Cachorro("Rex", 5, "Puddle com chettos"));
        animais.add(new Gato("Mia", 3, true));
        animais.add(new Passaro("Pipiu", 2, "Azul"));

        // Percorrendo a lista e chamando os métodos
        for (Animal animal : animais) {
            animal.exibirInformacoes(); // Exibe informações do animal
            animal.emitirSom(); // Faz o animal emitir som
            ((Comportamento) animal).mover(); // Faz o animal se mover
            System.out.println(); // Pula uma linha para separar os animais
        }
        
    }
}