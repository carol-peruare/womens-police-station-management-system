package persistencia;

import model.UmaPessoa;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Banco banco;

    public PessoaDAO() {
        this.banco = new Banco();
    }

    public void salvar(UmaPessoa pessoa) {
        String sqlPessoa = "INSERT INTO umaPessoa (cpf, nome, email, data_Nasc, senha, telefone, autenticado, endereco_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        List<Object> paramsPessoa = new ArrayList<>();
        paramsPessoa.add(pessoa.getCpf());
        paramsPessoa.add(pessoa.getNome());
        paramsPessoa.add(pessoa.getEmail());
        paramsPessoa.add(pessoa.getdata_Nasc()); // Certifique-se de usar java.sql.Date no modelo
        paramsPessoa.add(pessoa.getSenha());
        paramsPessoa.add(pessoa.getTelefone());
        paramsPessoa.add(pessoa.isAutenticado());
        
        if (pessoa.getEnderecoId() > 0) {
            paramsPessoa.add(pessoa.getEnderecoId());
        } else {
            paramsPessoa.add(null);
        }

        try {
            banco.conectar();
            banco.executarPreparedStatement(sqlPessoa, paramsPessoa);
            System.out.println("Pessoa salva com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar pessoa: " + e.getMessage());
        } finally {
            banco.fechar();
        }
    }

}
