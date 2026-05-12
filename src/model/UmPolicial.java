package model;

import java.util.ArrayList;
import java.util.List;

public class UmPolicial {

    private String nome_policia;
    private String cpf_policia;
    private String matricula;
    private String email;
    private String patente;
    private boolean em_servico;
    private boolean autenticado;
    private List<UmHistorico> historicos;

    public UmPolicial(String nome_policia, String cpf_policia, String matricula, String email,
            String patente, boolean em_servico, boolean autenticado) {
        this.nome_policia = nome_policia;
        this.cpf_policia = cpf_policia;
        this.matricula = matricula;
        this.email = email;
        this.patente = patente;
        this.em_servico = em_servico;
        this.autenticado = autenticado;
        this.historicos = new ArrayList<>();
    }

    public String getNome() {
        return nome_policia;
    }

    public String getCpf() {
        return cpf_policia;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public String getPatente() {
        return patente;
    }

    public boolean isEmServico() {
        return em_servico;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public List<UmHistorico> getHistoricos() {
        return historicos;
    }

    public void setNome(String nome_policia) {
        this.nome_policia = nome_policia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setEmServico(boolean em_servico) {
        this.em_servico = em_servico;
    }


    public boolean autenticar(String emailDigitado, String senhaDigitada) {

        if (this.email.equals(emailDigitado) && senhaDigitada != null && !senhaDigitada.trim().isEmpty()) {
            this.autenticado = true;
            System.out.println("Policial " + nome_policia + " autenticado com sucesso.");
            return true;
        }
        this.autenticado = false;
        System.err.println("Erro de autenticação: credenciais inválidas.");
        return false;
    }

    public void baterPonto() {
        if (!autenticado) {
            System.err.println("Erro: autentique-se antes de bater ponto.");
            return;
        }
        em_servico = !em_servico;
        if (em_servico) {
            System.out.println("Ponto registrado: " + nome_policia + " ENTROU em serviço.");
        } else {
            System.out.println("Ponto registrado: " + nome_policia + " SAIU de serviço.");
        }
    }


    public void atenderDenuncia(UmaDenuncia denuncia, String dataAtendimento) {
        if (!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if (!em_servico) {
            System.err.println("Erro: policial não está em serviço.");
            return;
        }
        if (denuncia == null) {
            System.err.println("Erro: denúncia inválida.");
            return;
        }

        denuncia.AtualizarStatus(new UmStatus("Em atendimento"));

        // Registra no histórico do policial
        if (!historicos.isEmpty()) {
            historicos.get(0).registrarAtendimentoBoletim(null, this, dataAtendimento);
        }

        System.out.println("Denúncia atendida por " + nome_policia + " em " + dataAtendimento);
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

        System.out.println("Boletim " + boletim.getId() + " atendido por " + nome_policia
                + " em " + dataAtendimento);
    }

   
    public void verHistorico() {
        if (!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if (historicos.isEmpty()) {
            System.out.println("Nenhum histórico registrado para " + nome_policia);
            return;
        }
        System.out.println("===== HISTÓRICO DE " + nome_policia.toUpperCase() + " =====");
        for (UmHistorico historico : historicos) {
            historico.listarHistorico(this);
        }
    }

    
    public void realizarAtendimento(String descricaoAtendimento, String dataAtendimento,
            UmaDenuncia denuncia, UmBoletim boletim) {
        if (!autenticado) {
            System.err.println("Erro: policial não autenticado.");
            return;
        }
        if (!em_servico) {
            System.err.println("Erro: policial não está em serviço.");
            return;
        }

        System.out.println("Realizando atendimento: " + descricaoAtendimento);

        if (!historicos.isEmpty()) {
    
    historicos.get(0).registrarAtendimentoOcorrencia(descricaoAtendimento, this, dataAtendimento, "Local do atendimento", denuncia, boletim
    );
}

        System.out.println("Atendimento realizado por " + nome_policia
                + " em " + dataAtendimento);
    }

    
    public void adicionarHistorico(UmHistorico historico) {
        if (historico == null) {
            System.err.println("Erro: histórico inválido.");
            return;
        }
        historicos.add(historico);
        System.out.println("Histórico vinculado ao policial " + nome_policia);
    }
}