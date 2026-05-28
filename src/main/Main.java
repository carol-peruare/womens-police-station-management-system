    // Maressa Luiza dos Reis Machado - 202421901046
    // Carolina Peruare Ferreira - 202412901003
package main;

import javax.swing.*;

import model.Antecedentes;
import model.UmaClassificacao;
import model.UmBoletim;
import model.UmPolicial;
import model.UmStatus;
import model.UmaPessoa;
import model.UmHistorico;
import model.RegistroOcorrencia;
import model.UmaClassificacao;
import model.UmAtendimento;
import model.UmEndereco;
import model.UmUsuario;

public class Main {
    public static void main(String[] args) {
        
        UmPolicial policial = new UmPolicial(
            "Sargenta Flores", 
            "999.888.777-66", 
            "flores@policia.gov.br",
             "15/08/1985", 
             "senhaSegura",
             "65998775341",
            "2022101", 
            "Sargenta",
            false,  // em_servico inicial
            false   // autenticado inicial
        );
        }

        System.out.println("Insira seu email ou matrícula:\n");
        JTextField campoLogin = new JTextField();    
        System.out.println("Insira sua senha:\n");
        JTextField campoSenha = new JTextField();


        Object[] campos = {
            "E-mail ou Matrícula:", campoLogin,
            "Senha:", campoSenha
        };

        int opcao = JOptionPane.showConfirmDialog(null, campos, "Login do Sistema", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            String identificador = campoLogin.getText();
            String senha = campoSenha.getText();
        }

        if(identificador.getCodigoIdentificador().contains("@")) {
            // Se tem '@', o sistema sabe automaticamente que é um Cidadão (E-mail)
            UmUsuario usuario = repository.findByEmail(identificador); 
            System.out.println("Portal do Cidadão\n");
            System.out.println("Seja bem-vindo, \n" + usuario.getNome() + "!");
        }
        else {
            // Caso contrário, é um Policial (Matrícula)
            UmPolicial policial = repository.findByMatricula(identificador);
            System.out.println("Portal do Policial\n");
            System.out.println("Seja bem-vindo, \n" + policial.getNome() + "!");
        }

}
    

