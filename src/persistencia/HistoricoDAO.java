package persistencia;

import model.UmHistorico;
import model.UmPolicial;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {

    private Banco banco;

    public HistoricoDAO() {
        this.banco = new Banco();
    }

    // Passamos o histórico e o policial específico ao qual esse registro de atividade pertence
    public int salvar(UmHistorico historico, UmPolicial policial) {
        
        String sqlHistorico = "INSERT INTO umHistorico (lista_ocorrencias, ultima_atividade, lista_operacoes, matricula_policial_historico) VALUES (?, ?, ?, ?)";

        // Converter a lista de operações em uma única String para caber no VARCHAR do banco
        String operacoesTexto = String.join(" | ", historico.getListaOperacoes());
        
        // Você pode fazer uma lógica parecida para mapear as ocorrências se necessário,
        // por enquanto vamos mandar um texto padrão ou vazio caso esteja criando o histórico inicial
        String ocorrenciasTexto = "Lista de ocorrências vinculadas"; 

        List<Object> paramsHistorico = new ArrayList<>();
        paramsHistorico.add(ocorrenciasTexto);
        
        // Tratamento da Data de última atividade
        try {
            // Se a sua string vier com barras (Ex: "dd/MM/yyyy"), é preciso converter para o formato ISO ("yyyy-MM-dd") do MySQL
            String dataFormatada = historico.getDataUltimaAtividade();
            if (dataFormatada.contains("/") && dataFormatada.length() >= 10) {
                String[] partes = dataFormatada.split(" ")[0].split("/");
                dataFormatada = partes[2] + "-" + partes[1] + "-" + partes[0];
            }
            paramsHistorico.add(java.sql.Date.valueOf(dataFormatada));
        } catch (Exception e) {
            paramsHistorico.add(new java.sql.Date(System.currentTimeMillis())); // Fallback para a data atual se falhar
        }
        
        paramsHistorico.add(operacoesTexto);
        paramsHistorico.add(policial.getMatricula()); // Garante o vínculo da FK com a tabela umPolicial

        int idGerado = 0;

        try {
            banco.conectar();
            idGerado = banco.executarPreparedStatementRetornandoId(sqlHistorico, paramsHistorico);
            System.out.println("Histórico registrado com sucesso no banco de dados! ID: " + idGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idGerado;
    }
}