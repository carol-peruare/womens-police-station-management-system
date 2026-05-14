package model;

public class RegistroOcorrencia {

    private String descricao_ocorrencia;
    private String data_ocorrencia;
    private String local_ocorrencia;
    private UmPolicial policial;
    private UmBoletim boletim;

    public RegistroOcorrencia(String descricao_ocorrencia, String data_ocorrencia, String local_ocorrencia, UmPolicial policial, UmBoletim boletim){

    this.descricao_ocorrencia = descricao_ocorrencia;
    this.data_ocorrencia = data_ocorrencia;
    this.local_ocorrencia = local_ocorrencia;
    this.policial = policial;
    this.boletim = boletim;
}
    // getters
    public UmPolicial getPolicial(){
        return policial;
    }

    public String getDescricao_ocorrencia() {
        return descricao_ocorrencia;
    }

    public String getData_ocorrencia() {
        return data_ocorrencia;
    }

    public String getLocal_ocorrencia() {
        return local_ocorrencia;
    }

    public UmBoletim getBoletim() {
        return boletim;
    }
    // método para exibir detalhes da ocorrência
    @Override
    public String toString() {
        String info = "Ocorrência: " + descricao_ocorrencia + "\nData: " + data_ocorrencia + "\nLocal: " + local_ocorrencia + "\nPolicial: " + policial.getNome();

        if (boletim != null) {
            info += "\nLigada ao boletim.";
        }
        return info;
    }
}

