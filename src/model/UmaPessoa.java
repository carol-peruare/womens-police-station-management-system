package model;

public class UmaPessoa {
    protected static int contadorId = 1;
    protected String nome, cpf, email, data_Nasc, senha, telefone;
    protected int id_pessoa;
    protected String codigoIdentificador;
    protected int endereco_id;
    protected boolean autenticado;

    public UmaPessoa(String nome, String cpf, String email, String data_Nasc, String senha, String telefone, String codigoIdentificador) {
        this.nome = nome;
        this.cpf = cpf;
        this.id_pessoa = contadorId++;
        this.email = email;
        this.data_Nasc = data_Nasc;
        this.senha = senha;
        this.telefone = telefone;
        this.codigoIdentificador = codigoIdentificador;
        this.autenticado = false; // Usuário/Policial começa deslogado por padrão
        this.endereco_id = 0; // Inicialmente, a pessoa não tem um endereço associado
    }

    // getters
    public String getCpf() { return cpf; }
    public String getdata_Nasc() { return data_Nasc; }
    public int getId() { return id_pessoa; }
    public String getTelefone() { return telefone; }
    public String getCodigoIdentificador() { return codigoIdentificador; }
    public boolean isAutenticado() { return autenticado; }
    public int getEnderecoId() { return endereco_id; }

    public String getSenha() {
        return this.senha; 
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    // Método para autenticar o usuário
    public boolean autenticar(String senhaDigitada, String codigoIdentificadorDigitado) {
        if (this.senha.equals(senhaDigitada) && this.codigoIdentificador.equals(codigoIdentificadorDigitado)){
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }

}
