package persistencia;

import model.UmStatus;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
     private Banco banco;

    public StatusDAO() {
        this.banco = new Banco();
    }

        public void salvar(UmStatus status) {
        String sqlStatus = "INSERT INTO umStatus (tipo_status) VALUES (?)";
        List<Object> paramsStatus = new ArrayList<>();
        paramsStatus.add(status.getTipo());

        try {
            banco.conectar();
            banco.executarPreparedStatement(sqlStatus, paramsStatus);
            System.out.println("Status salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }
    }
}
