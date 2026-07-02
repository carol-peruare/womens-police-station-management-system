package persistencia;

import java.util.ArrayList;
import java.util.List;

import model.UmUsuario;

public class UsuarioDAO {
    private Banco banco;

    public UsuarioDAO() {
        this.banco = new Banco();
    }

    public int salvar(UmUsuario usuario) {
        String sqlPessoa = "INSERT INTO umaPessoa (cpf, nome, email, dataNasc, senha, telefone, autenticado, endereco_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> paramsPessoa = new ArrayList<>();
        paramsPessoa.add(usuario.getCpf());
        paramsPessoa.add(usuario.getNome());
        paramsPessoa.add(usuario.getEmail());
        
        try {
            paramsPessoa.add(java.sql.Date.valueOf(usuario.getdata_Nasc()));
        } catch (Exception e) {
            paramsPessoa.add(null);
        }
        
        paramsPessoa.add(usuario.getSenha());
        paramsPessoa.add(usuario.getTelefone());
        paramsPessoa.add(usuario.isAutenticado());
        
        if (usuario.getEnderecoId() > 0) {
            paramsPessoa.add(usuario.getEnderecoId());
        } else {
            paramsPessoa.add(null);
        }

        String sqlUsuario = "INSERT INTO umUsuario (cpf_pessoa) VALUES (?)";
        List<Object> paramsUsuario = new ArrayList<>();
        paramsUsuario.add(usuario.getCpf());

        int idUsuarioGerado = 0;

        try {
            banco.conectar();
            
            banco.executarPreparedStatement(sqlPessoa, paramsPessoa);
            
            idUsuarioGerado = banco.executarPreparedStatementRetornandoId(sqlUsuario, paramsUsuario);
            
            System.out.println("Usuário cadastrado com sucesso! ID do Usuário: " + idUsuarioGerado);
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            e.printStackTrace();
        } finally {
            banco.fechar();
        }

        return idUsuarioGerado;
    }
}
