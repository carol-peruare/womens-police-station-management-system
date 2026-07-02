package persistencia;

import model.RegistroOcorrencia;
import java.util.ArrayList;
import java.util.List;

public class RegistroOcorrenciaDAO {
    
    private Banco banco;

    public RegistroOcorrenciaDAO() {
        this.banco = new Banco();
    }

    public int salvar(RegistroOcorrencia registro) {
        String sql = "INSERT INTO registro_ocorrencia (descricao, data, local, matricula_policial, boletim_id) VALUES (?, ?, ?, ?, ?)";

        List<Object> parametros = new ArrayList<>();
        parametros.add(registro.getDescricao_ocorrencia());
        
        try {
            parametros.add(java.sql.Date.valueOf(registro.getData_ocorrencia()));
        } catch (Exception e) {
            parametros.add(new java.sql.Date(System.currentTimeMillis())); 
        }
        
        parametros.add(registro.getLocal_ocorrencia());
        
        if (registro.getPolicial() != null) {
            parametros.add(registro.getPolicial().getMatricula());
        } else {
            parametros.add(null);
        }
        
        if (registro.getBoletim() != null) {
            parametros.add(registro.getBoletim().getId());
        } else {
            parametros.add(null);
        }

        int idRegistroGerado = 0;

        try {
            banco.conectar();
            idRegistroGerado = banco.executarPreparedStatementRetornandoId(sql, parametros);
            System.out.println("Registro de ocorrência gravado no banco com sucesso! ID: " + idRegistroGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar registro de ocorrência: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idRegistroGerado;
    }
}