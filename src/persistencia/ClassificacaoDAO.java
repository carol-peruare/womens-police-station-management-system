package persistencia;

import model.UmaClassificacao;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO {
    private Banco banco;

    public ClassificacaoDAO() {
        this.banco = new Banco();
    }

    public int salvar(UmaClassificacao classificacao) {
        String sql = "INSERT INTO umaClassificacao (tipo_classificacao) VALUES (?)";
        
        List<Object> parametros = new ArrayList<>();
        // Salvamos a string correspondente à descrição do enum (ex: "Feminicídio")
        parametros.add(classificacao.getTipoClassificacao());

        int idGerado = 0;

        try {
            banco.conectar();
            // Reutiliza o método que busca a chave primária auto incrementada
            idGerado = banco.executarPreparedStatementRetornandoId(sql, parametros);
            System.out.println("Classificação [" + classificacao.getTipoClassificacao() + "] salva com sucesso! ID: " + idGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar classificação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idGerado;
    }
}
