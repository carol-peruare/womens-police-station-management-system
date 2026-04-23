package model;

import java.util.ArrayList;
import java.util.List;

public class UmHistorico {
    private String id;
    private List<UmPolicial> policiais;
    private List<String> lista_denuncias_atendidas;
    private List<String> lista_ocorrencias_atendidas;
    private String data_ultima_atividade;
    private List<String> lista_operacoes;

    public UmHistorico(String id, String data_ultima_atividade) {
        this.id = id;
        this.data_ultima_atividade = data_ultima_atividade;
        this.policiais = new ArrayList<>();
        this.lista_denuncias_atendidas = new ArrayList<>();
        this.lista_ocorrencias_atendidas = new ArrayList<>();
        this.lista_operacoes = new ArrayList<>();
    }
}