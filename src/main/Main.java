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
            "Flores Machado dos Santos",
            "999.888.777-66",
            "flores@policia.gov.br",
            "15/08/1985",
            "senhaSegura",
            "65998775341",
            "2022101",
            "Sargenta",
            false,
            false
        );

        UmUsuario usuario = new UmUsuario(
            "Fernanda Maria Ferreira",
            "fernanda@cidadao.gov.br",
            "999.888.777-66",
            "24/02/2001",
            "amoMeuPet",
            "65998775341"
        );

        JTextField campoLogin = new JTextField();
        JTextField campoSenha = new JTextField();

        Object[] campos = {
            "E-mail ou Matrícula:", campoLogin,
            "Senha:", campoSenha
        };

        int opcao = JOptionPane.showConfirmDialog(
            null,
            campos,
            "Login do Sistema",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            String identificador = campoLogin.getText();
            String senha = campoSenha.getText();

            if (identificador.contains("@")) {
                if(usuario.autenticar(senha, identificador)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo, " + usuario.getNome() + "!");
                    System.out.println("Portal do Cidadão");
                    System.out.println("Seja bem-vindo!");
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Falha no login! Verifique suas credenciais.");
                }
            } 
            else {
                if(policial.autenticar(senha, identificador)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo, " + policial.getNome() + "!");
                    System.out.println("Portal do Policial");
                    System.out.println("Seja bem-vindo!");
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Falha no login! Verifique suas credenciais.");
                }
            }
        }
    }
}


    

