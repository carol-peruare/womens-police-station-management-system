package persistencia;

import model.UmEndereco;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private Banco banco;

    public EnderecoDAO() {
        this.banco = new Banco();
    }

    public int salvar(UmEndereco endereco) {
        String sqlEndereco = "INSERT INTO umEndereco (rua, cep, bairro, numero, complemento) VALUES (?, ?, ?, ?, ?)";
        
        List<Object> paramsEndereco = new ArrayList<>();
        paramsEndereco.add(endereco.getRua());
        paramsEndereco.add(endereco.getCep());
        paramsEndereco.add(endereco.getBairro());
        paramsEndereco.add(endereco.getNumero());
        paramsEndereco.add(endereco.getComplemento());

        int idGerado = 0;

        try {
            banco.conectar();
            idGerado = banco.executarPreparedStatementRetornandoId(sqlEndereco, paramsEndereco);
            System.out.println("Endereço salvo com sucesso! ID: " + idGerado);
        } catch (Exception e) {
            System.err.println("Erro ao salvar endereço: " + e.getMessage());
        } finally {
            banco.fechar();
        }

        return idGerado; 
    }
}