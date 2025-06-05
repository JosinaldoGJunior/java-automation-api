package models;

public class AccountPayload {
    private String nome;

    public AccountPayload() {}

    public AccountPayload(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}