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

    public int getQuantidadeAntecedentes() {
        return quantidade_antecedentes;
    }
 
    public boolean isPossuiAntecedentes() {
        return possuiAntecedentes;
    }
 
    public int getIdAntecedentes() {
        return id_antecedentes;
    }
 
    public String getDataUltimaOcorrencia() {
        return dataUltimaOcorrencia;
    }
 
    public String getObservacoesAntecedentes() {
        return observacoes_antecedentes;
    }
 
    public List<UmBoletim> getBoletimAntecedentes() {
        return boletim_antecedentes;
    }
 
    public List<UmaDenuncia> getDenunciaAntecedentes() {
        return denuncia_antecedentes;
    }
 
    public List<UmaClassificacao> getClassificacaoAntecedentes() {
        return classificacao_antecedentes;
    }
 
    public List<UmUsuario> getUsuarioAntecedentes() {
        return usuario_antecedentes;
    }
  
    public void setQuantidadeAntecedentes(int quantidade_antecedentes) {
        this.quantidade_antecedentes = quantidade_antecedentes;
    }
 
    public void setPossuiAntecedentes(boolean possuiAntecedentes) {
        this.possuiAntecedentes = possuiAntecedentes;
    }
 
    public void setIdAntecedentes(int id_antecedentes) {
        this.id_antecedentes = id_antecedentes;
    }
 
    public void setDataUltimaOcorrencia(String dataUltimaOcorrencia) {
        this.dataUltimaOcorrencia = dataUltimaOcorrencia;
    }
 
    public void setObservacoesAntecedentes(String observacoes_antecedentes) {
        this.observacoes_antecedentes = observacoes_antecedentes;
    }

    public void listarOcorrencia() {
        System.out.println("===== ANTECEDENTES ID: " + id_antecedentes + " =====");
 
        System.out.println("\n--- Boletins de Ocorrência ---");
        if (boletim_antecedentes.isEmpty()) {
            System.out.println("Nenhum boletim registrado.");
        } else {
            for (UmBoletim boletim : boletim_antecedentes) {
                // Usa o getter de UmBoletim para exibir o id e a descrição
                System.out.println("Boletim ID: " + boletim.getId()
                        + " | Descrição: " + boletim.getDescricao()
                        + " | Data: " + boletim.getData());
            }
        }
 
        System.out.println("\n--- Denúncias ---");
        if (denuncia_antecedentes.isEmpty()) {
            System.out.println("Nenhuma denúncia registrada.");
        } else {
            for (UmaDenuncia denuncia : denuncia_antecedentes) {
                System.out.println("Denúncia ID: " + denuncia.getId()
                        + " | Descrição: " + denuncia.getDescricao()
                        + " | Data: " + denuncia.getData());
            }
        }
 
        System.out.println("\nTotal de ocorrências: " + quantidade_antecedentes);
        System.out.println("Última ocorrência: " + dataUltimaOcorrencia);
        System.out.println("Observações: " + observacoes_antecedentes);
    }
 
    public void adicionarOcorrencia(UmBoletim boletim, String dataOcorrencia) {
        if (boletim == null) {
            System.err.println("Erro: boletim inválido.");
            return;
        }
        boletim_antecedentes.add(boletim);
        quantidade_antecedentes++;
        possuiAntecedentes = true;
        dataUltimaOcorrencia = dataOcorrencia;
        System.out.println("Boletim adicionado aos antecedentes. Total: " + quantidade_antecedentes);
    }
 
    public void adicionarOcorrencia(UmaDenuncia denuncia, String dataOcorrencia) {
        if (denuncia == null) {
            System.err.println("Erro: denúncia inválida.");
            return;
        }
        denuncia_antecedentes.add(denuncia);
        quantidade_antecedentes++;
        possuiAntecedentes = true;
        dataUltimaOcorrencia = dataOcorrencia;
        System.out.println("Denúncia adicionada aos antecedentes. Total: " + quantidade_antecedentes);
    }
 
  
    public void verificarAntecedentes() {
        if (possuiAntecedentes && quantidade_antecedentes > 0) {
            System.out.println("! Atenção: este registro POSSUI antecedentes.");
            System.out.println("Quantidade de ocorrências: " + quantidade_antecedentes);
            System.out.println("Última ocorrência em: " + dataUltimaOcorrencia);
        } else {
            System.out.println("Este registro NÃO possui antecedentes.");
        }
    }
}
