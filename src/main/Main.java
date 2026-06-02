// Maressa Luiza dos Reis Machado - 202421901046
// Carolina Peruare Ferreira - 202412901003

/*  Fazer login como policial: 
        2022101
        senhaSegura

    Fazer login como cidadão: 
        fernanda@cidadao.gov.br
        amoMeuPet
*/

package main;

import javax.swing.*;
import model.Antecedentes;
import model.UmaClassificacao;
import model.UmBoletim;
import model.UmHistorico;
import model.UmPolicial;
import model.UmStatus;
import java.util.ArrayList;
import java.util.List;
import model.UmUsuario; 

public class Main {
    
    private static List<UmUsuario> bancoUsuarios = new ArrayList<>();
    private static List<UmPolicial> bancoPoliciais = new ArrayList<>();

    private static UmUsuario usuarioLogado = null;
    
    // Variáveis para salvar o boletim criado em tempo de execução
    private static UmBoletim boletimCadastrado = null;
    private static UmStatus statusBoletim = null;
    private static UmaClassificacao classificacaoBoletim = null;
    private static UmHistorico historicoGeral = new UmHistorico(java.time.LocalDate.now().toString());

    // Inicializa o sistema com os usuários cadastrados
    public static void inicializarSistema() {
       UmPolicial policial = new UmPolicial(
            "Flores Machado dos Santos", // nome
            "999.888.777-66",           // cpf
            "flores@policia.gov.br",     // email
            "15/08/1985",                // data_Nasc
            "senhaSegura",               // senha
            "65998775341",               // telefone
            "2022101",                   // matricula
            "Sargenta",                  // patente
            false,                       // em_servico
            false                        // autenticado
        );
        bancoPoliciais.add(policial);

        UmUsuario usuario = new UmUsuario(
            "Fernanda Maria Ferreira",   // nome
            "999.888.777-66",            // cpf
            "fernanda@cidadao.gov.br",   // email
            "24/02/2001",                // data_Nasc
            "amoMeuPet",                 // senha
            "65998775341"                // telefone
        );
        bancoUsuarios.add(usuario);
    }

    public static void main(String[] args) {
        
        inicializarSistema();
        
        JTextField campoLogin = new JTextField();
        JPasswordField campoSenha = new JPasswordField();

        Object[] camposLogin = {
            "Login (E-mail para Cidadão ou Matrícula para Policial):", campoLogin,
            "Senha:", campoSenha
        };

        int opcaoLogin = JOptionPane.showConfirmDialog(
            null, 
            camposLogin, 
            "Login do Sistema de Ocorrências", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (opcaoLogin == JOptionPane.OK_OPTION) {
            String loginDigitado = campoLogin.getText();
            String senhaDigitada = new String(campoSenha.getPassword());
            boolean autenticado = false;

            for (UmUsuario u : bancoUsuarios) {
                if (u.getEmail().equals(loginDigitado) && u.getSenha().equals(senhaDigitada)) {
                    usuarioLogado = u; // Salva quem está usando o sistema
                    autenticado = true;
                    JOptionPane.showMessageDialog(null, "Bem-vinda, " + u.getNome() + "! Acesso liberado.");
                    chamarMenuCidadao();
                    break;
                }
            }

            if (!autenticado) {
                for (UmPolicial p : bancoPoliciais) {
                    if (p.getMatricula().equals(loginDigitado) && p.getSenha().equals(senhaDigitada)) {
                        autenticado = true;
                        JOptionPane.showMessageDialog(null, "Acesso Oficial detectado: " + p.getNome() + "\n(Redirecionando para a área do Policial...)");
<<<<<<< HEAD
                        // chamarMeuPolicial() ou algo assim  
=======
                        chamarMenuPolicial();
>>>>>>> 6dbe51c09755b8f141b5ac7cc5ba153ba109265a
                        break;
                    }
                }
            }

            // Se não achou em nenhuma das listas, erro
            if (!autenticado) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Erro: E-mail/Matrícula ou senha incorretos, ou usuário não cadastrado.", 
                    "Falha no Login", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void chamarMenuPolicial(){

        String [] botoesMenuPolicial = {
            "Consultar Boletins", 
            "Atualizar Status de Ocorrência", 
            "Bater Ponto",
            "Verificar Ficha de Antecedentes",
            "Meu Histórico",
            "Sair"
        };

        int op = JOptionPane.showOptionDialog(
            null,
            "=== PAINEL DO POLICIAL ===\nUsuário logado: " + bancoPoliciais.get(0).getNome() + "\n\nSelecione a ação desejada abaixo:",
            "Menu Principal",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            botoesMenuPolicial,
            botoesMenuPolicial[0] 
        );

        switch(op) {
            case 0:
                consultarBoletim();
                break;
            case 1:
                atualizarOcorrencia();
                break;
            case 2:
                baterPonto();
                break;
            case 3:
                verificarFichaAntecedentes();
                break;
            case 4:
                verificarHistoricoPolicial();
                break;
            case 5:
            case -1: // Caso o usuário feche a janela no "X"
                JOptionPane.showMessageDialog(null, "Sessão encerrada com sucesso.");
                break;
        }
    }

    public static void consultarBoletim(){
        if (boletimCadastrado == null){
            JOptionPane.showMessageDialog(
                null, 
                "Nenhum boletim registrado nesta sessão.\nPor favor, peça para o cidadão registrar uma ocorrência primeiro.", 
                "Nenhum Registro Encontrado", 
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            String relatorio = "=== CONSULTA DE BOLETIM ===\n\n" +
                               "Buscando registros vinculados ao usuário: " + usuarioLogado.getNome() + "\n\n" +
                               "• Classificação Atribuída: " + classificacaoBoletim.getTipoClassificacao() + "\n" +
                               "• Status do Processo: " + statusBoletim.getTipo() + "\n\n" +
                               "Detalhamento do Sistema: O caso foi registrado e aguarda análise.";
            
            JOptionPane.showMessageDialog(null, relatorio, "Acompanhamento de Ocorrência", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n-> [POO VALIDAÇÃO] Policial consultou o boletim registrado:");
            statusBoletim.identificarStatus();
        }
        chamarMenuPolicial(); 
    }

    public static void atualizarOcorrencia(){
        if (boletimCadastrado == null || statusBoletim == null) {
            JOptionPane.showMessageDialog(
                null, 
                "Nenhum boletim registrado nesta sessão para atualizar.\nPor favor, peça para o cidadão registrar uma ocorrência primeiro.", 
                "Nenhum Registro Encontrado", 
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            String[] opcoesStatus = {"Em Análise", "Em Andamento", "Concluído"};
            String novoStatus = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o novo status para o boletim de número: " + boletimCadastrado.getId(),
                "Atualização de Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesStatus,
                opcoesStatus[0]
            );

            if (novoStatus != null) {
            statusBoletim = new UmStatus(novoStatus.toLowerCase());
            boletimCadastrado.atualizarStatus(statusBoletim);

            // Registra essa atualização no histórico do policial logado
            UmPolicial policialLogado = bancoPoliciais.get(0);
            historicoGeral.registrarAtendimentoOcorrencia(
                "Atualização de status para: " + novoStatus, 
                policialLogado, 
                java.time.LocalDate.now().toString(), 
                "Delegacia Digital", 
                boletimCadastrado
            );

            JOptionPane.showMessageDialog(null, "Status atualizado e registrado no seu histórico!");
        }
    }
        chamarMenuPolicial(); 
    }

    public static void baterPonto(){
        JOptionPane.showMessageDialog(
            null, 
            "Ponto registrado com sucesso para: " + bancoPoliciais.get(0).getNome() + "\nHorário: " + java.time.LocalTime.now().withNano(0), 
            "Registro de Ponto", 
            JOptionPane.INFORMATION_MESSAGE
        );
        chamarMenuPolicial(); 
    }

    public static void verificarFichaAntecedentes(){
        chamarMenuPolicial();
    }

    public static void verificarHistoricoPolicial(){
        UmPolicial policialLogado = bancoPoliciais.get(0);
        historicoGeral.listarHistorico(policialLogado);
    
        JOptionPane.showMessageDialog(null, 
            "O histórico detalhado de " + policialLogado.getNome() + 
            " foi gerado no console do sistema (System.out).", 
            "Histórico do Policial", 
            JOptionPane.INFORMATION_MESSAGE);
        
        chamarMenuPolicial();
    }

    public static void chamarMenuCidadao() {
        String[] botoesMenu = {
            "Registrar Boletim", 
            "Verificar Status", 
            "Meus Antecedentes", 
            "Sair"
        };

        int escolha = JOptionPane.showOptionDialog(
            null,
            "=== PAINEL DO CIDADÃO ===\nUsuário logado: " + usuarioLogado.getNome() + "\n\nSelecione a ação desejada abaixo:",
            "Menu Principal",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            botoesMenu,
            botoesMenu[0] 
        );

        switch (escolha) {
            case 0:
                registrarBoletim();
                break;
            case 1:
                verificarStatusBoletim();
                break;
            case 2:
                verificarAntecedentesCidadao();
                break;
            case 3:
            case -1: // Caso o usuário feche a janela no "X"
                JOptionPane.showMessageDialog(null, "Sessão encerrada com sucesso.");
                break;
        }
    }

    public static void registrarBoletim() {
        JTextField campoData = new JTextField();
        JTextField campoRelato = new JTextField();
        JTextField campoEvidencias = new JTextField();
        
        String[] opcoesClassificacao = {
            "Feminicídio", 
            "Agressão", 
            "Violência Doméstica", 
            "Sequestro"
        };
        JComboBox<String> comboClassificacao = new JComboBox<>(opcoesClassificacao);

        Object[] formularioBoletim = {
            "Data do Ocorrido (DD/MM/AAAA):", campoData,
            "Relato detalhado do fato:", campoRelato,
            "Evidências iniciais (se houver):", campoEvidencias,
            "Classificação da Ocorrência:", comboClassificacao
        };

        int resultado = JOptionPane.showConfirmDialog(
            null, 
            formularioBoletim, 
            "Formulário de Boletim de Ocorrência", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String data = campoData.getText();
            String relato = campoRelato.getText();
            String evidencias = campoEvidencias.getText();
            String escolhaClasse = (String) comboClassificacao.getSelectedItem();

            UmaClassificacao.OpcaoViolencia opcaoEnum = UmaClassificacao.OpcaoViolencia.AGRESSAO; // Padrão
            
            if (escolhaClasse != null) {
                switch (escolhaClasse) {
                    case "Feminicídio":
                        opcaoEnum = UmaClassificacao.OpcaoViolencia.FEMINICIDIO;
                        break;
                    case "Agressão":
                        opcaoEnum = UmaClassificacao.OpcaoViolencia.AGRESSAO;
                        break;
                    case "Violência Doméstica":
                        opcaoEnum = UmaClassificacao.OpcaoViolencia.VIOLENCIA_DOMESTICA;
                        break;
                    case "Sequestro":
                        opcaoEnum = UmaClassificacao.OpcaoViolencia.SEQUESTRO;
                        break;
                }
            }

            classificacaoBoletim = new UmaClassificacao(opcaoEnum);

            int idAleatorio = (int) (Math.random() * 1000); 
            boletimCadastrado = new UmBoletim(idAleatorio, relato, data, evidencias);
            boletimCadastrado.adicionarClassificacao(classificacaoBoletim);

            statusBoletim = new UmStatus("registrado");
            boletimCadastrado.atualizarStatus(statusBoletim);

            JOptionPane.showMessageDialog(
                null, 
                "=== BOLETIM REGISTRADO COM SUCESSO ===\n\n" +
                "Número do BO: " + idAleatorio + "\n" +
                "Classificação: " + classificacaoBoletim.getTipoClassificacao() + "\n" + 
                "Status Atual: " + statusBoletim.getTipo() + "\n\n" +
                "O caso foi registrado e aguarda análise.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            System.out.println("\n-> [POO VALIDAÇÃO] Novo Boletim e Classificação vinculados:");
            System.out.println(classificacaoBoletim.toString());
            statusBoletim.identificarStatus();
        }

        chamarMenuCidadao(); 
    }

    public static void verificarStatusBoletim() {
        if (boletimCadastrado == null || statusBoletim == null) {
            JOptionPane.showMessageDialog(
                null, 
                "Você não possui nenhum boletim cadastrado nesta sessão.\nPor favor, registre uma ocorrência primeiro.", 
                "Nenhum Registro Encontrado", 
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            String relatorio = "=== CONSULTA DE BOLETIM ===\n\n" +
                               "Buscando registros vinculados ao usuário: " + usuarioLogado.getNome() + "\n\n" +
                               "• Classificação Atribuída: " + classificacaoBoletim.getTipoClassificacao() + "\n" +
                               "• Status do Processo: " + statusBoletim.getTipo() + "\n\n" +
                               "Detalhamento do Sistema: O caso foi registrado e aguarda análise.";
            
            JOptionPane.showMessageDialog(null, relatorio, "Acompanhamento de Ocorrência", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n-> [POO VALIDAÇÃO] Status consultado pelo usuário:");
            statusBoletim.identificarStatus();
        }
        chamarMenuCidadao(); 
    }

    public static void verificarAntecedentesCidadao() {
        Antecedentes ficha = new Antecedentes(0, false, 777, "Nenhuma", "Cidadão ficha limpa");
        
        String relatorio = "=== MINHA CERTIDÃO DE ANTECEDENTES ===\n\n" +
                           "Consulta realizada no Banco de Dados Criminal para: " + usuarioLogado.getNome() + "\n" +
                           "CPF: " + usuarioLogado.getCpf() + "\n\n" + // Puxando do usuário real logado!
                           "• Status da Ficha: NADA CONSTA\n" +
                           "• Histórico: Cidadão ficha limpa\n" +
                           "• Registros criminais ativos: 0\n\n" +
                           "Sua situação cadastral perante a justiça está REGULAR.";

        JOptionPane.showMessageDialog(null, relatorio, "Certidão Eletrônica", JOptionPane.INFORMATION_MESSAGE);
        
        System.out.println("\n-> [POO VALIDAÇÃO] Usuário puxou a própria certidão de antecedentes:");
        ficha.verificarAntecedentes();
        
        chamarMenuCidadao(); 
    }
}