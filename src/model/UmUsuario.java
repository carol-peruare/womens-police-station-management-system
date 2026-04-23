package model;

import java.util.ArrayList;
import java.util.List;

public class UmUsuario {
    private String nome;
    private String cpf;
    private int id;
    private String email;
    private String data_Nasc;
    private String senha;
    private String telefone;
    private List<UmEndereco> endereco;
    private boolean autenticado;
    private List<Antecedentes> antecedente;

    public UmUsuario(String nome, String cpf, int id, String email, String data_Nasc, String senha, String telefone, boolean autenticado){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.email = email;
        this.data_Nasc = data_Nasc;
        this.senha = senha;
        this.telefone = telefone;
        this.autenticado = autenticado;
        this.endereco = new ArrayList<>();
        this.antecedente = new ArrayList<>();
    }
}
