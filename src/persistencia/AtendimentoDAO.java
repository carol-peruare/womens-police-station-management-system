package persistencia;

import model.UmAtendimento;
import java.util.ArrayList;
import java.util.List;


public class AtendimentoDAO {
    
    private Banco banco;

    public AtendimentoDAO() {
        this.banco = new Banco();
    }

    public int salvar(UmAtendimento atendimento) {
        String sql = "INSERT INTO umAtendimento (data, horario, descricao, status, usuario_id) VALUES (?, ?, ?, ?, ?)";
        
        List<Object> parametros = new ArrayList<>();
        parametros.add(atendimento.getData());
        parametros.add(atendimento.getHorario());
        parametros.add(atendimento.getDescricao());
        parametros.add(atendimento.getStatus());
        parametros.add(atendimento.getUsuario().getId()); 

        int idAtendimentoGerado = 0;

        try {
            banco.conectar();
            idAtendimentoGerado = banco.executarPreparedStatementRetornandoId(sql, parametros);
            System.out.println("Atendimento registrado no banco de dados com sucesso! ID: " + idAtendimentoGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar atendimento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idAtendimentoGerado;
    }
}
