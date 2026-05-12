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
    private List<String> evidencias;
    private boolean ativa;

    public UmaDenuncia(int id_denuncia, String descricao_denuncia, String data_denuncia, Boolean anonima_denuncia){

        this.id_denuncia = id_denuncia;
        this.descricao_denuncia = descricao_denuncia;
        this.data_denuncia = data_denuncia;
        this.anonima_denuncia = anonima_denuncia;
        this.classificacao_denuncia = new ArrayList<>();
        this.enderecos_denuncia = new ArrayList<>();
        this.status_denuncia = new ArrayList<>();
        this.evidencias = new ArrayList<>();

        this.ativa = true;
    }

    public String getStatus() {
    if (status_denuncia.isEmpty()) {
        return "Pendente"; 
    }
    return status_denuncia.get(status_denuncia.size() - 1).toString();
    }

    public int getId() {
        return id_denuncia; 
    }

    public String getDescricao() {
        return descricao_denuncia; 
    }

    public String getData() {
        return data_denuncia; 
    }

    
    public List<UmaClassificacao> getClassificacaoDenuncia() {
        return classificacao_denuncia; 
    }
    
    public void RegistrarDenuncia(){

        System.out.println("Denúncia registrada com sucesso!");
        System.out.println("ID: " + id_denuncia);
        System.out.println("Descrição: " + descricao_denuncia);
        System.out.println("Data: " + data_denuncia);
        if(anonima_denuncia){
            System.out.println("Denúncia anônima.");
        }
    }
    // atualizar status da denúncia
    public void AtualizarStatus(UmStatus novoStatus){

        status_denuncia.add(novoStatus);
        System.out.println("Status atualizado com sucesso!");
    }
    // visualizar detalhes da denúncia
    public void Visualizar(){

        System.out.println("===== DENÚNCIA =====");
        System.out.println("ID: " + id_denuncia);
        System.out.println("Descrição: " + descricao_denuncia);
        System.out.println("Data: " + data_denuncia);
        System.out.println("Anônima: " + anonima_denuncia);
        System.out.println("\nENDEREÇOS:");

        for(UmEndereco endereco : enderecos_denuncia){
            endereco.mostrarEndereco();
        }
        System.out.println("\nEVIDÊNCIAS:");

        for(String evidencia : evidencias){
            System.out.println("- " + evidencia);
        }
        System.out.println("\nSTATUS:");

        for(UmStatus status : status_denuncia){
            System.out.println(status);
        }
        System.out.println("\nAtiva: " + ativa);
    }

    // adicionar evidencia à denúncia
    public void AdicionarEvidencia(String evidencia){
        evidencias.add(evidencia);
        System.out.println("Evidência adicionada com sucesso!");
    }

    // cancelar denúncia
    public void Cancelar(){
        ativa = false;
        System.out.println("Denúncia cancelada.");
    }
}