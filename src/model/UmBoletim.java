package model;

import java.util.ArrayList;
import java.util.List;

public class UmBoletim {
    private List<UmaClassificacao> classificacao_boletim;
    private int id_boletim;
    private String descricao_boletim; 
    private String data_boletim;
    private List<UmEndereco> enderecos_boletim;
    private List<UmUsuario> usuarios_boletim;
    private List<UmStatus> status_boletim;
    private List<UmPolicial> policialResponsavel_boletim;
    private String evidencias_boletim;

    public UmBoletim(int id_boletim, String descricao_boletim, String data_boletim, String evidencias_boletim){
        this.id_boletim = id_boletim;
        this.descricao_boletim = descricao_boletim;
        this.data_boletim = data_boletim;
        this.evidencias_boletim = evidencias_boletim;
        this.enderecos_boletim = new ArrayList<>();
        this.usuarios_boletim = new ArrayList<>();
        this.status_boletim = new ArrayList<>();
        this.policialResponsavel_boletim = new ArrayList<>();
        this.classificacao_boletim = new ArrayList<>();
    }
}
