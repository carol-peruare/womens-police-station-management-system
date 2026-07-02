package persistencia;

import model.Antecedentes;
import java.util.ArrayList;
import java.util.List;

public class AntecedentesDAO {
    
    private Banco banco;

    public AntecedentesDAO() {
        this.banco = new Banco();
    }

    public int salvar(Antecedentes antecedentes, int idUsuario) {
        String sql = "INSERT INTO umAntecedente (quantidade_antecedentes, possui_antecedentes, data_ultima_ocorrencia, observacoes_antecedentes, usuario_id) VALUES (?, ?, ?, ?, ?)";

        List<Object> parametros = new ArrayList<>();
        parametros.add(antecedentes.getQuantidadeAntecedentes());
        parametros.add(antecedentes.isPossuiAntecedentes());
       
        try {
            parametros.add(java.sql.Date.valueOf(antecedentes.getDataUltimaOcorrencia()));
        } catch (Exception e) {
            parametros.add(new java.sql.Date(System.currentTimeMillis())); 
        }
        
        parametros.add(antecedentes.getObservacoesAntecedentes());
        parametros.add(idUsuario > 0 ? idUsuario : null); 

        int idAntecedenteGerado = 0;

        try {
            banco.conectar();
            idAntecedenteGerado = banco.executarPreparedStatementRetornandoId(sql, parametros);
            System.out.println("Registro de antecedentes salvo com sucesso! ID: " + idAntecedenteGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar antecedentes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idAntecedenteGerado;
    }
}