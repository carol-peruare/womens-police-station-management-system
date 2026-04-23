package model;

import java.util.ArrayList;
import java.util.List;

public class Antecedentes {
    private int quantidade_antecedentes;
    private boolean possuiAntecedentes;
    private int id_antecedentes;
    private String dataUltimaOcorrencia;
    private String observacoes_antecedentes;
    private List<UmBoletim> boletim_antecedentes;
    private List<UmaDenuncia> denuncia_antecedentes;
    private List<UmaClassificacao> classificacao_antecedentes; //passagens
    private List<UmUsuario> usuario_antecedentes;

    public Antecedentes(int quantidade_antecedentes, boolean possuiAntecedentes, int id_antecedentes, String dataUltimaOcorrencia, String observacoes_antecedentes){
        this.quantidade_antecedentes = quantidade_antecedentes;
        this.possuiAntecedentes = possuiAntecedentes;
        this.id_antecedentes = id_antecedentes;
        this.dataUltimaOcorrencia = dataUltimaOcorrencia;
        this.observacoes_antecedentes = observacoes_antecedentes;
        this.boletim_antecedentes = new ArrayList<>();
        this.denuncia_antecedentes = new ArrayList<>();
        this.classificacao_antecedentes = new ArrayList<>();
        this.usuario_antecedentes = new ArrayList<>();
    }
}
