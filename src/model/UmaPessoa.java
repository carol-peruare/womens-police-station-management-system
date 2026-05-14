package model;

import java.util.ArrayList;
import java.util.List;

public class UmaPessoa {
    protected static int contadorId = 1;
    protected String nome, cpf, email, data_Nasc, senha, telefone;
    protected int id_pessoa;
    protected List<UmEndereco> enderecos;
    protected boolean autenticado;

    public UmaPessoa(String nome, String cpf, String email, String data_Nasc, String senha, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.id_pessoa = contadorId++;
        this.email = email;
        this.data_Nasc = data_Nasc;
        this.senha = senha;
        this.telefone = telefone;
        this.autenticado = false; // Usuário/Policial começa deslogado por padrão
        this.enderecos = new ArrayList<>();
    }

    // getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getdata_Nasc() { return data_Nasc; }
    public int getId() { return id_pessoa; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public boolean isAutenticado() { return autenticado; }

    // Método para autenticar o usuário
    public boolean autenticar(String senhaDigitada, String emailDigitado) {
        if (this.senha.equals(senhaDigitada) && this.email.equalsIgnoreCase(emailDigitado)) {
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }
}
