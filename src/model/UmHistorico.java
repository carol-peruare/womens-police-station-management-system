package model;

import java.util.ArrayList;
import java.util.List;

public class UmHistorico {
    private String id;
    private List<UmPolicial> policiais;
    private List<UmaDenuncia> lista_denuncias_atendidas;
    private String data_ultima_atividade;
    private List<String> lista_operacoes;
    private List<RegistroOcorrencia> lista_ocorrencias_atendidas;

    public UmHistorico(String id, String data_ultima_atividade) {
        this.id = id;
        this.data_ultima_atividade = data_ultima_atividade;
        this.policiais = new ArrayList<>();
        this.lista_denuncias_atendidas = new ArrayList<>();
        this.lista_operacoes = new ArrayList<>();
        this.lista_ocorrencias_atendidas = new ArrayList<>();
    }
    //registrar atendimento de ocorrência
    public void RegistroDeAtendimentoDeOcorrencia(String descricao_ocorrencia, UmPolicial policial, String data_ocorrencia, String local_ocorrencia, UmaDenuncia denuncia, UmBoletim boletim){
    
        RegistroOcorrencia registro = new RegistroOcorrencia( descricao_ocorrencia, data_ocorrencia, local_ocorrencia, policial, denuncia, boletim);
        lista_ocorrencias_atendidas.add(registro);

        if (!policiais.contains(policial)) {
            policiais.add(policial);
        }

        data_ultima_atividade = data_ocorrencia;
        System.out.println("Ocorrência registrada com sucesso!");
    }
    //registrar atendimento de boletim
    public void registrarAtendimentoBoletim( UmBoletim boletim, UmPolicial policial, String data_atendimento){
        String registro = "Boletim atendido por: " + policial.getNome() + "\nData: " + data_atendimento;

        lista_operacoes.add(registro);
        data_ultima_atividade = data_atendimento;
        System.out.println("Boletim atendido e registrado com sucesso!");
    }
    //lista o histórico de atendimentos de um policial
    public void listarHistorico(UmPolicial policial){
    boolean encontrou = false;
    for (RegistroOcorrencia registro : lista_ocorrencias_atendidas){
        if(registro.getPolicial().equals(policial)){
            System.out.println(registro);
            System.out.println("-------------------");
            encontrou = true;
        }
    }
    if(!encontrou){
        System.out.println("Nenhum histórico encontrado para esse policial.");
    }
}
}