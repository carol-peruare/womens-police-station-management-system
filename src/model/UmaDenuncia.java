package model;

import java.util.List;

public class UmaDenuncia {
    private List<UmaClassificacao> classificacao_denuncia;
    private String descricao_denuncia; 
    private String data_denuncia;
    private List<UmEndereco> enderecos_denuncia;
    private boolean anonima;
    private int id_denuncia;
    private List<UmStatus> status_denuncia;
}
