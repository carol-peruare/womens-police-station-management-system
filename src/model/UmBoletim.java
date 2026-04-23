package model;

import java.util.ArrayList;
import java.util.List;

public class UmBoletim {
    private List<UmaClassificacao> classificacao_boletim;
    private int id_boletim;
    private String descricao_boletim; 
    private String data_boletim;
    private List<UmEndereco> enderecos;
    private List<UmUsuario> usuarios;
    private List<UmStatus> status;
    private List<UmPolicial> policialResponsavel;
    private String evidencias_boletim;

    public UmBoletim(int id_boletim, String descricao_boletim, String data_boletim, String evidencias_boletim){
        this.id_boletim = id_boletim;
        this.descricao_boletim = descricao_boletim;
        this.data_boletim = data_boletim;
        this.evidencias_boletim = evidencias_boletim;
        this.enderecos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.status = new ArrayList<>();
        this.policialResponsavel = new ArrayList<>();
        this.classificacao_boletim = new ArrayList<>();
    }
}
