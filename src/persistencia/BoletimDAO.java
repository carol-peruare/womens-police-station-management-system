package persistencia;

import model.UmBoletim;
import java.util.ArrayList;
import java.util.List;

public class BoletimDAO {
    
    private Banco banco;

    public BoletimDAO() {
        this.banco = new Banco();
    }
    
    // Passamos o boletim e os IDs das entidades vinculadas a ele no banco
    public int salvar(UmBoletim boletim, int idStatus, int idClassificacao, int idEndereco, int idUsuario, String matriculaPolicial) {
        
        String sql = "INSERT INTO umBoletim (descricao_boletim, data_boletim, evidencias_boletim, ativo, status_id, classificacao_id, endereco_id, usuario_id, matricula_policial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        List<Object> parametros = new ArrayList<>();
        parametros.add(boletim.getDescricao());
        
        // Converte a String data_boletim para java.sql.Date
        try {
            parametros.add(java.sql.Date.valueOf(boletim.getData()));
        } catch (Exception e) {
            parametros.add(new java.sql.Date(System.currentTimeMillis())); // Fallback para data atual se falhar
        }
        
        parametros.add(boletim.getEvidencias());
        parametros.add(boletim.isAtivo());
        
        // Adicionando as chaves estrangeiras (Foreign Keys)
        parametros.add(idStatus > 0 ? idStatus : null);
        parametros.add(idClassificacao > 0 ? idClassificacao : null);
        parametros.add(idEndereco > 0 ? idEndereco : null);
        parametros.add(idUsuario > 0 ? idUsuario : null);
        parametros.add(matriculaPolicial != null && !matriculaPolicial.trim().isEmpty() ? matriculaPolicial : null);

        int idBoletimGerado = 0;

        try {
            banco.conectar();
            idBoletimGerado = banco.executarPreparedStatementRetornandoId(sql, parametros);
            System.out.println("Boletim de Ocorrência registrado no banco de dados com sucesso! ID: " + idBoletimGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar boletim de ocorrência: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idBoletimGerado;
    }
}