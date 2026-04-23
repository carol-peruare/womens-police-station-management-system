package model;

import java.util.ArrayList;
import java.util.List;

public class UmaDenuncia {
    private List<UmaClassificacao> classificacao_denuncia;
    private String descricao_denuncia; 
    private String data_denuncia;
    private List<UmEndereco> enderecos_denuncia;
    private boolean anonima_denuncia;
    private int id_denuncia;
    private List<UmStatus> status_denuncia;

    public UmaDenuncia(int id_denuncia, String descricao_denuncia, String data_denuncia, Boolean anonima_denuncia){
        this.id_denuncia = id_denuncia;
        this.descricao_denuncia = descricao_denuncia;
        this.data_denuncia = data_denuncia;
        this.anonima_denuncia = anonima_denuncia;
        this.classificacao_denuncia = new ArrayList<>();
        this.enderecos_denuncia = new ArrayList<>();
        this.status_denuncia = new ArrayList<>();
    }
}
