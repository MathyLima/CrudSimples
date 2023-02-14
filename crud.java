package Crud;

import java.util.Scanner;

public class crud {
    private crudNo cabeca;
    private int tamanho;

    public crud() {
        cabeca = null;
        tamanho = 0;
    }

    public boolean vazia() {
        if (tamanho == 0)
            return true;
        else
            return false;
    }

    public int tamanho() {
        if (vazia())
            return 0;
        else {
            crudNo aux = cabeca;
            int cont = 0;
            while (aux != null) {
                aux = aux.getProximo();
                cont++;
            }
            return cont;
        }
    }

    public boolean insereInicio(String nome, int idade) {

        // cria um novo no
        crudNo novoNo = new crudNo();

        // seta os valores
        novoNo.setNome(nome);
        novoNo.setIdade(idade);

        // apos setar os valores, precisamos setar o proximo no como a cabeça
        novoNo.setProximo(cabeca);

        // apos isso, devemos tornar esse novo no, como a cabeça da lista
        cabeca = novoNo;

        // como adicionamos, devemos aumentar o tamanho da lista
        tamanho++;

        // como tudo deu certo, retorna true
        return true;

    }

    public boolean insereMeio(String nome, int idade, int posicao) {
        // cria um novo no
        crudNo novoNo = new crudNo();
        // seta os valores
        novoNo.setNome(nome);
        novoNo.setIdade(idade);

        // criamos um no auxiliar e setamos ele como a cabeça
        crudNo aux = cabeca;
        // a partir do no auxiliar, percorremos a lista
        for (int i = 0; i < posicao - 1; i++) {
            // ira percorrer a lista até a posicao -1
            aux = aux.getProximo();
        }
        // como meu auxiliar está 1 posicao atras da que eu quero adicionar, primeiro
        // setamos o proximo do novo no como sendo o atual proximo do auxiliar
        novoNo.setProximo(aux.getProximo());
        // apos isso, setamos que o proximo do meu auxiliar é o meu novo no
        aux.setProximo(novoNo);

        tamanho++;

        return true;

    }

    public boolean addFinal(String nome, int idade) {
        // criamos um novo no
        crudNo novoNo = new crudNo();
        // setando valores para meu novo no
        novoNo.setNome(nome);
        novoNo.setIdade(idade);
        // criamos um no aux para percorrer a lista e para a posicao 0, setamos que ele
        // é a cabeça
        crudNo aux = cabeca;
        // percorrendo a lista até o ultimo elemento
        for (int i = 0; i < tamanho - 1; i++) {
            aux = aux.getProximo();
        }
        // como o auxiliar está na ultima prosicao, setamos que o proximo dele sera o
        // nosso novo no

        aux.setProximo(novoNo);
        novoNo.setProximo(null);

        tamanho++;
        return true;
    }

    public boolean addElemento(String nome, int idade, int posicao) {
        // como nos metodos já estão sendo criados os novos nos, não precisamos criar um
        // novo nesse metodo, ja que ele é so de controle

        if (vazia() && posicao > 1) {
            return false;// lista vazia mas posicao invalida
        }

        if ((posicao < 0) || (posicao > tamanho + 1)) {
            return false; // posicao invalida
        }

        // insercao inicio da lista
        if (posicao == 0) {
            return insereInicio(nome, idade);
        } else if ((posicao > 0) && (posicao < tamanho)) {
            return insereMeio(nome, idade, posicao);
        } else {
            return addFinal(nome, idade);
        }
    }

    public String removeInicio() {
        // setamos um no aux para representar nosso primeiro elemento
        crudNo aux = cabeca;

        // criamos uma variável para receber uma informação principal do no que será
        // removido
        String nomePessoa = aux.getNome();

        // como queremos remover da cabeça, so temos que setar o proximo da cabeça como
        // sendo o proximo do no auxiliar, assim "removendo" o ponteiro que aponta para
        // o no que queremos remover, fazendo com que se perca na memória, entretando o
        // java garbage collector cuidará disso apos setarmos o no auxiliar como null

        cabeca = aux.getProximo();

        // indicando para o java garbage collector que o aux deve ser "limpo"
        aux = null;

        tamanho--;
        return nomePessoa;
    }

    public String removeMeio(int posicao) {
        // criando um no auxiliar e setando-o como a cabeça
        crudNo antecessor = cabeca;

        // criando um novo no auxiliar
        crudNo atual;

        // percorrendo a lista ate a posicao indicada -1

        for (int i = 0; i < posicao - 1; i++) {
            antecessor = antecessor.getProximo();
        }
        // meu segundo auxiliar receberá o proximo do meu primeiro auxiliar,se meu aux
        // está na posicao -1 (3), meu auxiliar será a 4
        atual = antecessor.getProximo();

        // criando a variavel para armazenar o nome
        String nomePessoa = atual.getNome();

        // com isso, o meu segundo auxiliar já foi removido da lista
        antecessor.setProximo(atual.getProximo());

        // sugerindo ao GC para retirada da memoria
        atual = null;
        tamanho--;
        return nomePessoa;

    }

    public String removeFinal() {
        // cria um no auxiliar e setamos como a cabeça
        crudNo antecessor = cabeca;
        // percorremos a lista ate o penultimo elemento
        for (int i = 0; i < tamanho - 1; i++) {
            antecessor = antecessor.getProximo();
        }
        String nomePessoa = antecessor.getNome();

        antecessor.setProximo(null);

        tamanho--;
        return nomePessoa;
    }

    public String remove(int posicao) {

        if (vazia())
            return null;

        if ((posicao < 0) || (posicao > tamanho)) {
            return null;
        }

        if (posicao == 0) {
            return removeInicio();
        } else if ((posicao > 1) && (posicao < tamanho)) {
            return removeMeio(posicao);
        } else {
            return removeFinal();
        }

    }

    public void atualiza(int idade, int posicao) {
        // criando no auxiliar
        crudNo aux = cabeca;

        if (vazia()) {
            System.out.println("Ninguem cadastrado, favor cadastrar primeiro");
        }

        if ((posicao < 0) || (posicao > tamanho))
            System.out.println("Posicao inválida");

        // percorre a lista até a posicao do elemento que recebera a atualização
        for (int i = 0; i < posicao; i++) {
            aux = aux.getProximo();
        }

        aux.setIdade(idade);
    }

    public void exibe() {
        crudNo aux = cabeca;

        if (vazia()) {
            System.out.println("Ninguém cadastrado, favor cadastrar cliente");
        }

        for (int i = 0; i < tamanho; i++) {
            System.out.println("Cliente[" + i + "]");
            System.out.println(aux.getNome());
            System.out.println(aux.getIdade());
            aux = aux.getProximo();
        }

    }

    public static void main(String[] args) {
        crud novoCrud = new crud();
        boolean rodando = true;
        String nome;
        int idade;
        Scanner entrada = new Scanner(System.in);

        int entradaInicial;

        while (rodando) {
            System.out.println("CRUD SIMPLES");
            System.out.println("CRIAR  [1]");
            System.out.println("REMOVER[2]");
            System.out.println("UPDATE [3]");
            System.out.println("LISTAR [4]");
            System.out.println("SAIR   [0]");
            entradaInicial = entrada.nextInt();
            entrada.nextLine();

            if (entradaInicial == 0) {
                rodando = false;
            }

            if (entradaInicial == 1) {

                System.out.println("Por favor digite o nome e idade");
                nome = entrada.nextLine();

                idade = entrada.nextInt();
                entrada.nextLine();

                if (novoCrud.vazia()) {
                    novoCrud.insereInicio(nome, idade);

                } else {
                    int posicao;
                    System.out.println("Numero de elementos:" + novoCrud.tamanho);
                    System.out.println("Por favor digite a posicao desejada");
                    posicao = entrada.nextInt();
                    novoCrud.addElemento(nome, idade, posicao);
                    System.out.println(nome);
                }
            }

            if (entradaInicial == 2) {
                if (novoCrud.vazia()) {
                    System.out.println("Nenhum cliente cadastrado, favor cadastre primeiramente");
                } else if (novoCrud.tamanho == 1) {
                    System.out.println(novoCrud.removeInicio());

                } else {
                    System.out.println("Por favor digite a posição do cliente a ser removido");
                    novoCrud.exibe();
                    int posicao = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println(novoCrud.remove(posicao));
                }
            }
            if (entradaInicial == 3) {
                System.out.println("Por favor, digite a posicao do cliente");
                novoCrud.exibe();
                int posicao = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Por favor, digite a nova idade");
                idade = entrada.nextInt();
                entrada.nextLine();
                novoCrud.atualiza(idade, posicao);
            }

            if (entradaInicial == 4) {
                novoCrud.exibe();
            }
        }
    }
}
