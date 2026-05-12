package model;

public class UmaClassificacao {
    private String tipo_classificacao;

    public UmaClassificacao(String tipo_classificacao){
        this.tipo_classificacao = tipo_classificacao;
    }

    public String getTipoClassificacao() {
        return tipo_classificacao;
    }
    public void setTipoClassificacao(String tipo_classificacao) {
        this.tipo_classificacao = tipo_classificacao;
    }

    public void classifica(UmaClassificacao novaClassificacao) {
        if (novaClassificacao == null || novaClassificacao.getTipoClassificacao() == null
                || novaClassificacao.getTipoClassificacao().isBlank()) {
            System.err.println("Erro: classificação inválida.");
            return;
        }
        this.tipo_classificacao = novaClassificacao.getTipoClassificacao();
        System.out.println("Classificação atualizada para: " + this.tipo_classificacao);
    }
     public void classifica(String novoTipo) {
        if (novoTipo == null || novoTipo.isBlank()) {
            System.err.println("Erro: tipo de classificação não pode ser vazio.");
            return;
        }
        this.tipo_classificacao = novoTipo;
        System.out.println("Classificação atualizada para: " + this.tipo_classificacao);
    }
 
    @Override
    public String toString() {
        return "Classificação: " + tipo_classificacao;
    }
}
