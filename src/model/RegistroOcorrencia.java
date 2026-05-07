package model;

public class RegistroOcorrencia {

    private String descricao_ocorrencia;
    private String data_ocorrencia;
    private String local_ocorrencia;
    private UmPolicial policial;
    private UmaDenuncia denuncia;
    private UmBoletim boletim;

    public RegistroOcorrencia(String descricao_ocorrencia, String data_ocorrencia, String local_ocorrencia, UmPolicial policial, UmaDenuncia denuncia, UmBoletim boletim){

    this.descricao_ocorrencia = descricao_ocorrencia;
    this.data_ocorrencia = data_ocorrencia;
    this.local_ocorrencia = local_ocorrencia;
    this.policial = policial;
    this.denuncia = denuncia;
    this.boletim = boletim;
}

    public UmPolicial getPolicial(){
        return policial;
    }

    @Override
    public String toString() {
        String info = "Ocorrência: " + descricao_ocorrencia +
                      "\nData: " + data_ocorrencia +
                      "\nLocal: " + local_ocorrencia +
                      "\nPolicial: " + policial.getNome();

        if (denuncia != null){
            info += "\nLigada à denúncia.";
        }
        if (boletim != null) {
            info += "\nLigada ao boletim.";
        }
        return info;
    }
}

