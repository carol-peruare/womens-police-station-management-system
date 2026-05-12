package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UmUsuario {
    private String nome;
    private String cpf;
    private int id_usuario;
    private String email;
    private String data_Nasc;
    private String senha;
    private String telefone;
    private List<UmEndereco> enderecos;
    private boolean autenticado;
    private List<Antecedentes> antecedente;
    private List<UmAtendimento> atendimentos;
    private List<UmaDenuncia> denuncias;
    private List<UmBoletim> boletims_usuario;

    public UmUsuario(String nome, String cpf, int id_usuario, String email, String data_Nasc, String senha, String telefone, boolean autenticado){
        this.nome = nome;
        this.cpf = cpf;
        this.id_usuario = id_usuario;
        this.email = email;
        this.data_Nasc = data_Nasc;
        this.senha = senha;
        this.telefone = telefone;
        this.autenticado = autenticado;
        this.enderecos = new ArrayList<>();
        this.antecedente = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.boletims_usuario = new ArrayList<>();
    }
    public String getNome() {
    return this.nome; 
}
    // autenticação do usuário
    public boolean autenticar(String senhaDigitada, String emailDigitado){
        if(senha.equals(senhaDigitada)&& email.equals(emailDigitado)){
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }
    // agendar atendimento
    public void agendarAtendimento(String data, String horario, String dercricao){
        if(autenticado){
            UmAtendimento novo = new UmAtendimento(1, data, horario, dercricao, this);
            atendimentos.add(novo);
            System.out.println("Atendimento agendado com sucesso!\n");
        }
        else{
            System.err.println("Erro! Você precisa estar logado.\n");
        }
    }
    // realizar denúncia
    public void realizarDenuncia(String descricao, String local, String categoria, Boolean denunciaAnonima){
        if(autenticado){
            UmaDenuncia novaDenuncia = new UmaDenuncia(descricao, local, categoria, denunciaAnonima = false);

            denuncias.add(novaDenuncia);

            System.out.println("Denúncia realizada com sucesso!");
        }
        else{
            UmaDenuncia novaDenuncia = new UmaDenuncia(descricao, local, categoria, denunciaAnonima = true);

            denuncias.add(novaDenuncia);

            System.out.println("Denúncia realizada com sucesso!");
        }
    }
    // realizar boletim
    public void realizarBoletim(String descricao, String local, String categoria){
        if(autenticado){
            UmBoletim novoBoletim = new UmBoletim(descricao, local, categoria);

            denuncias.add(novoBoletim);

            System.out.println("Boletim realizado com sucesso!");
        }
        else{
            System.err.println("Erro! Você precisa estar logado.\n");
        }
    }
    // adiconar endereço ao perfil do usuário
    public void adicionarEndereco(String rua, String numero, String cidade, String estado, String cep){
        UmEndereco novoEndereco = new UmEndereco(rua, numero, cidade, estado, cep);
        enderecos.add(novoEndereco);
        System.out.println("Endereço adicionado com sucesso!\n");
    }
    //verificar o status do atendimento
    public void verificarStatusAtendimento(int idAtendimento){
        for(UmAtendimento atendimento : atendimentos){
            if(atendimento.getId() == idAtendimento){
                System.out.println("Status do atendimento: " + atendimento.getStatus() + "\n");
                return;
            }
        }
        System.err.println("Atendimento não encontrado.\n");
    }
    // verifica o status do boletim
    public void verificarStatusBoletim(int idBoletim){
        for(UmBoletim boletim : boletims_usuario){
            if(boletim.getId() == idBoletim){
                System.out.println("Status do boletim: " + boletim.getStatus() + "\n");
                return;
            }
        }
        System.err.println("Boletim não encontrado.\n");
    }
    //verifica o status da denúncia
    public void verificarStatusDenuncia(int idDenuncia){
        for(UmDenuncia denuncia : denuncias){
            if(denuncia.getId() == idDenuncia){
                System.out.println("Status da denúncia: " + denuncia.getStatus() + "\n");
                return;
            }
        }
        System.err.println("Denúncia não encontrada.\n");
    }

}
