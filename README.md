# Projeto de POO

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
                false,
                false
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

                    System.out.println("Portal do Cidadão");
                    System.out.println("Seja bem-vindo!");

                } else {

                    System.out.println("Portal do Policial");
                    System.out.println("Seja bem-vindo!");
                }
            }
        }
    }
