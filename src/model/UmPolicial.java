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

    public UmPolicial(String nome_policia, String cpf_policia, String matricula, String email, String patente, boolean em_servico, boolean autenticado) {
        this.nome_policia = nome_policia;
        this.cpf_policia = cpf_policia;
        this.matricula = matricula;
        this.email = email;
        this.patente = patente;
        this.em_servico = em_servico;
        this.autenticado = autenticado;
        this.historicos = new ArrayList<>();
    }
}