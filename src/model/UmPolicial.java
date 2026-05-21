package model;

import java.util.ArrayList;
import java.util.List;

public class UmPolicial extends UmaPessoa{

    private String matricula, patente;
    private boolean em_servico;
    private List<UmHistorico> historicos;

    public UmPolicial(String nome, String cpf, String email, String data_Nasc, String senha, String telefone,String matricula,String patente, boolean em_servico, boolean autenticado){
        super(nome, cpf, email, data_Nasc, senha, telefone);

        this.matricula = matricula;
        this.patente = patente;
        this.em_servico = em_servico;
        this.historicos = new ArrayList<>();
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public String getPatente() { return patente; }
    public boolean isEmServico() { return em_servico; }
    public boolean isAutenticado() { return autenticado; }
    public List<UmHistorico> getHistoricos() { return historicos; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setPatente(String patente) { this.patente = patente; }
    public void setEmServico(boolean em_servico) { this.em_servico = em_servico; }

    public void baterPonto() {
        if (!autenticado) {
            System.err.println("Erro: autentique-se antes de bater ponto.");
            return;
        }
        em_servico = !em_servico;
        if (em_servico) {
            System.out.println("Ponto registrado: " + nome + " ENTROU em serviço.");
        } else {
            System.out.println("Ponto registrado: " + nome + " SAIU de serviço.");
        }
    }
   
    public void atenderBoletim(UmBoletim boletim, String dataAtendimento) {
        if (!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if (!em_servico) {
            System.err.println("Erro: policial não está em serviço.");
            return;
        }
        if (boletim == null) {
            System.err.println("Erro: boletim inválido.");
            return;
        }

        boletim.adicionarPolicial(this);

        boletim.atualizarStatus(new UmStatus("Em atendimento"));

        if (!historicos.isEmpty()) {
            historicos.get(0).registrarAtendimentoBoletim(boletim, this, dataAtendimento);
        }

        System.out.println("Boletim " + boletim.getId() + " atendido por " + nome
                + " em " + dataAtendimento);
    }

    public void verHistorico() {
        if (!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if (historicos.isEmpty()) {
            System.out.println("Nenhum histórico registrado para " + nome);
            return;
        }
        System.out.println("===== HISTÓRICO DE " + nome.toUpperCase() + " =====");
        for (UmHistorico historico : historicos) {
            historico.listarHistorico(this);
        }
    }
    
    public void realizarAtendimento(String descricaoAtendimento, String dataAtendimento, UmBoletim boletim) {
        if(!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if(!em_servico) {
            System.err.println("Erro: policial não está em serviço.");
            return;
        }
        System.out.println("Realizando atendimento: " + descricaoAtendimento);

        if (!historicos.isEmpty()) {
            historicos.get(0).registrarAtendimentoOcorrencia(descricaoAtendimento, this, dataAtendimento, "Local do atendimento", boletim);
            }
        System.out.println("Atendimento realizado por " + nome + " em " + dataAtendimento);
    }

    
    public void adicionarHistorico(UmHistorico historico) {
        if (historico == null) {
            System.err.println("Erro: histórico inválido.");
            return;
        }

        historicos.add(historico);
        System.out.println("Histórico vinculado ao policial " + nome);
    }

    // Sobrescreve o método de autenticação para usar matrícula em vez de email
    @Override 
    public boolean autenticar(String senhaDigitada, String matriculaDigitada) {
        if(this.senha.equals(senhaDigitada) && this.matricula.equals(matriculaDigitada)){
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }
}