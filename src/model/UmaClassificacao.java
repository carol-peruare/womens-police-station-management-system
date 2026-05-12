package model;

public class UmaClassificacao {


    public enum OpcaoViolencia {
        FEMINICIDIO("Feminicídio"),
        AGRESSAO("Agressão"),
        VIOLENCIA_DOMESTICA("Violência Doméstica"),
        SEQUESTRO("Sequestro");

        private final String descricao;

        OpcaoViolencia(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // A variável agora não é mais uma String solta, ela é do tipo do nosso Enum
    private OpcaoViolencia tipo_classificacao;

    // O construtor OBRIGA quem está criando a classe a escolher uma opção do dropdown
    public UmaClassificacao(OpcaoViolencia tipo_classificacao) {
        this.tipo_classificacao = tipo_classificacao;
    }


    // Retorna o texto formatado (ex: "Violência Doméstica")
    public String getTipoClassificacao() {
        return tipo_classificacao.getDescricao();
    }

    // Retorna a opção técnica do Enum (ex: VIOLENCIA_DOMESTICA)
    public OpcaoViolencia getOpcaoClassificacao() {
        return tipo_classificacao;
    }

    //setters
    public void setTipoClassificacao(OpcaoViolencia tipo_classificacao) {
        this.tipo_classificacao = tipo_classificacao;
    }


    public void classifica(UmaClassificacao novaClassificacao) {
        if (novaClassificacao != null) {
            this.tipo_classificacao = novaClassificacao.getOpcaoClassificacao();
            System.out.println("Classificação atualizada para: " + getTipoClassificacao());
        } else {
            System.err.println("Erro: classificação inválida.");
        }
    }

    public void classifica(OpcaoViolencia novaOpcao) {
        if (novaOpcao != null) {
            this.tipo_classificacao = novaOpcao;
            System.out.println("Classificação atualizada para: " + getTipoClassificacao());
        } else {
            System.err.println("Erro: opção não selecionada.");
        }
    }

    @Override
    public String toString() {
        return "Classificação: " + getTipoClassificacao();
    }
}