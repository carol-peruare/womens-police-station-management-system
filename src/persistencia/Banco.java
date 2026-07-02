package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Banco {
    private Connection conexao;
    private String url = "jdbc:mysql://localhost:3308/poo_delegacia";
    private String user ="root";
    private String password = "maressa123";

    public void conectar(){
        try{
            conexao = DriverManager.getConnection(url, user, password); 
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void executarSQL(String sql){
        try{
            conexao.createStatement().execute(sql);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void fechar(){
        try{
            conexao.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void executarPreparedStatement(String sql, List<Object> parametros){
        try{
            PreparedStatement pstm = conexao.prepareStatement(sql);
            for(int i = 0; i < parametros.size(); i++){
                Object obj = parametros.get(i);
                pstm.setObject(i + 1, obj);
            }
            pstm.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int executarPreparedStatementRetornandoId(String sql, List<Object> parametros) {
    try {
        PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        for (int i = 0; i < parametros.size(); i++) {
            Object obj = parametros.get(i);
            pstm.setObject(i + 1, obj);
        }
        
        pstm.execute();
        
        // Recupera o ID gerado
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
            int idGerado = rs.getInt(1);
            pstm.close();
            return idGerado;
        }
        
        pstm.close();
        return 0;
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}

    public ResultSet 
    executarConsultar(String sql){
        try{
            return conexao.createStatement().executeQuery(sql);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
