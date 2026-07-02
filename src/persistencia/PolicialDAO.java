package persistencia;

import model.UmPolicial;
import java.util.ArrayList;
import java.util.List;

public class PolicialDAO {
    private Banco banco;

    public PolicialDAO() {
        this.banco = new Banco();
    }

    public void salvar(UmPolicial policial) {
        String sqlPessoa = "INSERT INTO umaPessoa (cpf, nome, email, dataNasc, senha, telefone, autenticado, endereco_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> paramsPessoa = new ArrayList<>();
        paramsPessoa.add(policial.getCpf());
        paramsPessoa.add(policial.getNome());
        paramsPessoa.add(policial.getEmail());
        
        try {
            paramsPessoa.add(java.sql.Date.valueOf(policial.getdata_Nasc()));
        } catch (Exception e) {
            paramsPessoa.add(null);
        }
        
        paramsPessoa.add(policial.getSenha());
        paramsPessoa.add(policial.getTelefone());
        paramsPessoa.add(policial.isAutenticado());
        
        if (policial.getEnderecoId() > 0) {
            paramsPessoa.add(policial.getEnderecoId()); 
        } else {
            paramsPessoa.add(null); 
        }

        String sqlPolicial = "INSERT INTO umPolicial (matricula, patente, emServico, cpf_pessoa) VALUES (?, ?, ?, ?)";
        List<Object> paramsPolicial = new ArrayList<>();
        paramsPolicial.add(policial.getMatricula());
        paramsPolicial.add(policial.getPatente());
        paramsPolicial.add(policial.isEmServico());
        paramsPolicial.add(policial.getCpf());

        try {
            banco.conectar();
            banco.executarPreparedStatement(sqlPessoa, paramsPessoa);
            banco.executarPreparedStatement(sqlPolicial, paramsPolicial);
            System.out.println("Policial e seus dados de Pessoa salvos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar policial: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }
    }
}

