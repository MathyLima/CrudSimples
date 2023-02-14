package Crud;

public class crudNo {
    private String nome;
    private int idade;
    private crudNo proximo;

    public crudNo() {
        setNome(null);
        setIdade(0);
        setProximo(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public crudNo getProximo() {
        return proximo;
    }

    public void setProximo(crudNo proximo) {
        this.proximo = proximo;
    }
}
