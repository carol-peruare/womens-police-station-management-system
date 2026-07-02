// Maressa Luiza dos Reis Machado - 202421901046
// Carolina Peruare Ferreira - 202412901003

/* Fazer login como policiall:
        2022101
        senhaSegura

    Fazer login como cidadão:
        fernanda@cidadao.gov.br
        amoMeuPet
*/

package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Antecedentes;
import model.UmaClassificacao;
import model.UmBoletim;
import model.UmHistorico;
import model.UmPolicial;
import model.UmStatus;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import model.UmUsuario;

public class Main {

    // =========================================================================
    //  CONSTANTES DE ESTILO
    // =========================================================================

    private static final Color COR_FUNDO        = Color.WHITE; // Fundo branco solicitado
    private static final Color COR_BOTAO        = new Color(190,  90, 140);
    private static final Color COR_BOTAO_HOVER  = new Color(160,  60, 110);
    private static final Color COR_BORDA        = new Color(180, 100, 140);
    private static final Color COR_TEXTO_BRANCO = Color.WHITE;
    private static final Font  FONTE_TITULO     = new Font("Segoe UI", Font.BOLD,  18);
    private static final Font  FONTE_LABEL      = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font  FONTE_CAMPO      = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font  FONTE_BOTAO      = new Font("Segoe UI", Font.BOLD,  14);
    
    // Define o tamanho padronizado para todas as janelas do sistema
    private static final Dimension TAMANHO_JANELA = new Dimension(500, 600);
    
    private static final DateTimeFormatter FMT_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

            
    // =========================================================================
    //  BANCO DE DADOS EM MEMÓRIA
    // =========================================================================

    private static final List<UmUsuario>  bancoUsuarios  = new ArrayList<>();
    private static final List<UmPolicial> bancoPoliciais = new ArrayList<>();

    // =========================================================================
    //  ESTADO DA SESSÃO
    // =========================================================================

    private static UmUsuario  usuarioLogado  = null;
    private static UmPolicial policialLogado = null;

    private static UmBoletim      boletimCadastrado    = null;
    private static UmStatus        statusBoletim        = null;
    private static UmaClassificacao classificacaoBoletim = null;
    private static UmHistorico historicoGeral =
            new UmHistorico(LocalDate.now().toString());
    private static int entradaOuSaida = 0; // 0 = entrada, 1 = saída

    // =========================================================================
    //  INICIALIZAÇÃO
    // =========================================================================

    public static void inicializarSistema() {
        UmPolicial policial = new UmPolicial(
            "Flores Machado dos Santos",
            "12345678901",
            "flores@policia.gov.br",
            "15/08/1985",
            "senhaSegura",
            "65998775341",
            "2022101",
            "Sargenta",
            false,
            false
        );
        bancoPoliciais.add(policial);

        UmUsuario usuario = new UmUsuario(
            "Fernanda Maria Ferreira",
            "99988877766",
            "fernanda@cidadao.gov.br",
            "24/02/2001",
            "amoMeuPet",
            "65998775341"
        );
        bancoUsuarios.add(usuario);
    }

    public static void main(String[] args) {
        aplicarTemaGlobal();
        inicializarSistema();
        telaInicial();
    }

    // =========================================================================
    //  TEMA VISUAL GLOBAL
    // =========================================================================

    private static void aplicarTemaGlobal() {
        UIManager.put("OptionPane.background",       COR_FUNDO);
        UIManager.put("Panel.background",            COR_FUNDO);
        UIManager.put("Button.background",           COR_BOTAO);
        UIManager.put("Button.foreground",           COR_TEXTO_BRANCO);
        UIManager.put("Button.font",                 FONTE_BOTAO);
        UIManager.put("Label.font",                  FONTE_LABEL);
        UIManager.put("TextField.font",              FONTE_CAMPO);
        UIManager.put("PasswordField.font",          FONTE_CAMPO);
        UIManager.put("ComboBox.font",               FONTE_CAMPO);
        UIManager.put("TextArea.font",               FONTE_CAMPO);
        UIManager.put("OptionPane.messageFont",      FONTE_LABEL);
        UIManager.put("OptionPane.buttonFont",       FONTE_BOTAO);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    // =========================================================================
    //  HELPERS DE UI
    // =========================================================================

    /** Carrega e redimensiona a logo para o Header. */
    private static ImageIcon carregarLogo() {
        java.net.URL url = Main.class.getResource("/logo.png");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            // Redimensiona para caber bonito no header
            Image img = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        System.out.println("AVISO: 'logo.png' não encontrado dentro de 'src'.");
        return null;
    }

    /** Carrega a imagem original para ser usada como ícone da janela */
    private static Image carregarIconeJanela() {
        java.net.URL url = Main.class.getResource("/logo.png");
        if (url != null) {
            return new ImageIcon(url).getImage();
        }
        return null;
    }

    /** Cria o Header padrão que aparecerá em todas as telas. */
    private static JPanel criarCabecalho() {
        JPanel painelHeader = new JPanel(new BorderLayout(15, 0));
        painelHeader.setBackground(COR_FUNDO);
        painelHeader.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, COR_BORDA), // Linha sutil embaixo
            new EmptyBorder(10, 20, 10, 20)
        ));

        ImageIcon logo = carregarLogo();
        if (logo != null) {
            painelHeader.add(new JLabel(logo), BorderLayout.WEST);
        }

        JLabel lblTitulo = new JLabel("Fork's Women Police Departament");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_BOTAO); // Título na cor do tema
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        painelHeader.add(lblTitulo, BorderLayout.CENTER);

        return painelHeader;
    }

    private static JTextField campoCriado() {
        JTextField c = new JTextField(22);
        c.setFont(FONTE_CAMPO);
        c.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1, true),
            BorderFactory.createEmptyBorder(6, 8, 6, 8) // Mais padding interno
        ));
        return c;
    }

    private static JPasswordField campaSenha() {
        JPasswordField c = new JPasswordField(22);
        c.setFont(FONTE_CAMPO);
        c.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1, true),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return c;
    }

    private static JLabel labelCriado(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FONTE_LABEL);
        return l;
    }

    /** Monta apenas a grade de campos. */
    private static JPanel criarPainelForm(Object... pares) {
        JPanel painel = new JPanel(new GridLayout(0, 1, 4, 8)); // Maior espaçamento vertical
        painel.setBackground(COR_FUNDO);
        
        for (Object item : pares) {
            if (item instanceof String) {
                painel.add(labelCriado((String) item));
            } else if (item instanceof Component) {
                painel.add((Component) item);
            }
        }
        return painel;
    }

    private static int exibirFormulario(String tituloJanela, JPanel campos) {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setPreferredSize(TAMANHO_JANELA); 

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(COR_FUNDO);
        
        // AQUI: Reduzimos as margens (Top: 5, Left: 40, Bottom: 5, Right: 40)
        centro.setBorder(new EmptyBorder(5, 40, 5, 40)); 
        centro.add(campos, BorderLayout.NORTH);

        painelPrincipal.add(centro, BorderLayout.CENTER);

        return JOptionPane.showConfirmDialog(
            null, painelPrincipal, tituloJanela, 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
    }

    /** Exibe o Menu Principal padronizado com tamanho fixo. */
    private static int exibirMenu(String titulo, String cabecalhoSecundario, String[] opcoes) {
        final int[] resultado = {-1};

        JFrame frame = new JFrame(titulo);

        Image icone = carregarIconeJanela();
        if (icone != null) {
            frame.setIconImage(icone);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(TAMANHO_JANELA); // Tamanho fixo para TODAS as janelas
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(COR_FUNDO);

        // Adiciona o Header Global
        frame.add(criarCabecalho(), BorderLayout.NORTH);

        // Conteúdo central
        JPanel conteudo = new JPanel(new BorderLayout(0, 20));
        conteudo.setBackground(COR_FUNDO);
        conteudo.setBorder(new EmptyBorder(20, 50, 40, 50));

        // Subtítulo do menu (ex: Painel do Cidadão)
        JLabel lblCabecalho = new JLabel("<html><center>" +
                cabecalhoSecundario.replace("\n", "<br>") + "</center></html>");
        lblCabecalho.setFont(FONTE_LABEL);
        lblCabecalho.setHorizontalAlignment(SwingConstants.CENTER);
        conteudo.add(lblCabecalho, BorderLayout.NORTH);

        // Botões bonitos centralizados
        JPanel painelBotoes = new JPanel(new GridLayout(opcoes.length, 1, 0, 12));
        painelBotoes.setBackground(COR_FUNDO);

        for (int i = 0; i < opcoes.length; i++) {
            final int idx = i;
            JButton btn = new JButton(opcoes[i]);
            btn.setFont(FONTE_BOTAO);
            btn.setBackground(COR_BOTAO);
            btn.setForeground(COR_TEXTO_BRANCO);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(300, 45)); // Botões mais grossos e bonitos
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(COR_BOTAO_HOVER); }
                public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(COR_BOTAO); }
            });

            btn.addActionListener(e -> {
                resultado[0] = idx;
                frame.dispose();
            });
            painelBotoes.add(btn);
        }

        // Centraliza a grid de botões
        JPanel wrapBotoes = new JPanel(new GridBagLayout());
        wrapBotoes.setBackground(COR_FUNDO);
        wrapBotoes.add(painelBotoes);
        
        conteudo.add(wrapBotoes, BorderLayout.CENTER);
        frame.add(conteudo, BorderLayout.CENTER);

        // Bloqueia até o frame fechar
        Object lock = new Object();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                synchronized (lock) { lock.notifyAll(); }
            }
        });
        
        frame.setVisible(true);
        synchronized (lock) {
            while (frame.isVisible()) {
                try { lock.wait(); } catch (InterruptedException ignored) {}
            }
        }

        return resultado[0];
    }

    private static void erro(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }
    private static void aviso(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
    }
    private static void sucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    private static void info(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // =========================================================================
    //  VALIDAÇÃO DE DATA
    // =========================================================================

    private static boolean isDataValida(String data) {
        if (data == null || data.isEmpty()) return false;
        try {
            LocalDate.parse(data, FMT_DATA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static LocalDate parseData(String data) {
        return LocalDate.parse(data, FMT_DATA);
    }

    // =========================================================================
    //  TELA INICIAL
    // =========================================================================

    public static void telaInicial() {
        String[] opcoes = {"Fazer Login", "Cadastrar-se", "Sair do Sistema"};

        // Agora a tela inicial também usa o Menu padronizado
        int escolha = exibirMenu(
            "Início", 
            "Bem-vindo ao Sistema de Ocorrências!\nSelecione uma opção abaixo:", 
            opcoes
        );

        switch (escolha) {
            case 0:  realizarLogin();    break;
            case 1:  realizarCadastro(); break;
            default:
                JOptionPane.showMessageDialog(null, "Encerrando o sistema. Até logo!");
                System.exit(0);
        }
    }

    // =========================================================================
    //  CADASTRO DE CIDADÃO
    // =========================================================================

    public static void realizarCadastro() {
        while (true) {
            JTextField campoNome     = campoCriado();
            JTextField campoCpf      = campoCriado();
            JTextField campoEmail    = campoCriado();
            JTextField campoDataNasc = campoCriado();
            JPasswordField campoSenha = campaSenha();
            JTextField campoTelefone = campoCriado();

            JPanel form = criarPainelForm(
                "Nome Completo:",                 campoNome,
                "CPF (apenas números):",          campoCpf,
                "E-mail:",                        campoEmail,
                "Data Nasc. (DD/MM/AAAA):",       campoDataNasc,
                "Senha:",                         campoSenha,
                "Telefone (apenas números):",     campoTelefone
            );

            // Usa o novo helper padronizado
            int resultado = exibirFormulario("Cadastro de Novo Cidadão", form);

            if (resultado != JOptionPane.OK_OPTION) break;

            String nome     = campoNome.getText().trim();
            String cpf      = campoCpf.getText().trim();
            String email    = campoEmail.getText().trim();
            String dataNasc = campoDataNasc.getText().trim();
            String senha    = new String(campoSenha.getPassword()).trim();
            String telefone = campoTelefone.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || dataNasc.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
                aviso("Todos os campos são obrigatórios!", "Campo Obrigatório");
                continue;
            }
            if (!email.contains("@") || !email.contains(".")) {
                erro("O e-mail informado é inválido!", "E-mail Inválido");
                continue;
            }
            if (!isDataValida(dataNasc) || parseData(dataNasc).isAfter(LocalDate.now())) {
                erro("A data de nascimento é inválida ou futura!", "Data Inválida");
                continue;
            }
            if (!cpf.matches("\\d{11}")) {
                erro("O CPF deve conter exatamente 11 dígitos numéricos!", "CPF Inválido");
                continue;
            }
            if (!telefone.matches("\\d+") || telefone.length() < 10 || telefone.length() > 11) {
                erro("Telefone inválido!", "Telefone Inválido");
                continue;
            }

            boolean duplicado = false;
            for (UmUsuario u : bancoUsuarios) {
                if (u.getEmail().equalsIgnoreCase(email) || u.getCpf().equals(cpf)) {
                    erro("Usuário já cadastrado com este E-mail ou CPF!", "Dados Duplicados");
                    duplicado = true;
                    break;
                }
            }
            if (duplicado) continue;

            bancoUsuarios.add(new UmUsuario(nome, cpf, email, dataNasc, senha, telefone));
            sucesso("Cadastro realizado com sucesso!\nVocê já pode fazer login.");
            break; 
        }
        telaInicial();
    }

    // =========================================================================
    //  LOGIN
    // =========================================================================

    public static void realizarLogin() {
        while (true) {
            JTextField campoLogin = campoCriado();
            JPasswordField campoSenha = campaSenha();

            JPanel form = criarPainelForm(
                "Login (E-mail para Cidadão ou Matrícula para Policial):", campoLogin,
                "Senha:", campoSenha
            );

            JPanel painel = new JPanel(new GridLayout(0, 1, 4, 4)); 
            painel.setBackground(COR_FUNDO); 

            // Usa o novo helper padronizado
            int opcao = exibirFormulario("Login — Sistema de Ocorrências", form);

            if (opcao != JOptionPane.OK_OPTION) {
                telaInicial();
                return;
            }

            String login = campoLogin.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();

            if (login.isEmpty() || senha.isEmpty()) {
                aviso("Os campos de login e senha não podem estar vazios!", "Atenção");
                continue;
            }

            boolean encontrado  = false;
            boolean senhaErrada = false;

            for (UmUsuario u : bancoUsuarios) {
                if (u.getEmail().equalsIgnoreCase(login)) {
                    encontrado = true;
                    if (u.getSenha().equals(senha)) {
                        usuarioLogado = u;
                        info("Bem-vinda, " + u.getNome() + "!\nAcesso liberado.", "Acesso Permitido");
                        chamarMenuCidadao();
                        return;
                    } else {
                        senhaErrada = true;
                    }
                    break;
                }
            }

            if (!encontrado || senhaErrada) {
                for (UmPolicial p : bancoPoliciais) {
                    if (p.getMatricula().equals(login)) {
                        encontrado = true;
                        if (p.getSenha().equals(senha)) {
                            policialLogado = p; 
                            info("Acesso Oficial: " + p.getNome() + "\nRedirecionando...", "Acesso Policial");
                            chamarMenuPolicial();
                            return;
                        } else {
                            senhaErrada = true;
                        }
                        break;
                    }
                }
            }

            if (!encontrado) erro("E-mail ou Matrícula não encontrados.", "Usuário Inexistente");
            else if (senhaErrada) erro("Senha incorreta.", "Falha de Autenticação");
        }
    }

    // =========================================================================
    //  MENU DO POLICIAL
    // =========================================================================

    public static void chamarMenuPolicial() {
        String[] opcoes = {
            "Consultar Boletins",
            "Atualizar Status de Ocorrência",
            "Bater Ponto",
            "Verificar Ficha de Antecedentes",
            "Meu Histórico",
            "Sair"
        };

        while (true) {
            int op = exibirMenu(
                "Painel do Policial",
                "=== PAINEL DO POLICIAL ===\nOficial: " + policialLogado.getNome(),
                opcoes
            );

            switch (op) {
                case 0:  consultarBoletim();           break;
                case 1:  atualizarOcorrencia();        break;
                case 2:  baterPonto();                 break;
                case 3:  verificarFichaAntecedentes(); break;
                case 4:  verificarHistoricoPolicial(); break;
                default:
                    JOptionPane.showMessageDialog(null, "Sessão encerrada com sucesso.");
                    policialLogado = null;
                    telaInicial();
                    return;
            }
        }
    }

    public static void consultarBoletim() {
        if (boletimCadastrado == null) {
            aviso("Nenhum boletim registrado nesta sessão.", "Nenhum Registro");
            return;
        }
        String relatorio = "=== CONSULTA DE BOLETIM ===\n\nConsultado pelo oficial: " + policialLogado.getNome() + "\n\n"
            + "• Classificação Atribuída: " + classificacaoBoletim.getTipoClassificacao() + "\n"
            + "• Status do Processo:      " + statusBoletim.getTipo() + "\n\nO caso foi registrado e aguarda análise.";
        info(relatorio, "Acompanhamento de Ocorrência");
    }

    public static void atualizarOcorrencia() {
        if (boletimCadastrado == null || statusBoletim == null) {
            aviso("Nenhum boletim registrado nesta sessão.", "Nenhum Registro");
            return;
        }
        String[] opcoesStatus = {"Em Análise", "Em Andamento", "Concluído"};
        String novoStatus = (String) JOptionPane.showInputDialog(null, "Selecione o novo status:", 
            "Atualização", JOptionPane.QUESTION_MESSAGE, null, opcoesStatus, opcoesStatus[0]);

        if (novoStatus != null) {
            statusBoletim = new UmStatus(novoStatus.toLowerCase());
            boletimCadastrado.atualizarStatus(statusBoletim);
            historicoGeral.registrarAtendimentoOcorrencia("Atualização de status: " + novoStatus, policialLogado,
                LocalDate.now().toString(), "Delegacia Digital", boletimCadastrado);
            sucesso("Status atualizado com sucesso!");
        }
    }

    public static void baterPonto() {
        String tipo = (entradaOuSaida == 0) ? "REGISTRO DE PONTO (ENTRADA)" : "REGISTRO DE PONTO (SAÍDA)";
        historicoGeral.registrarEventoSimples(policialLogado, tipo);
        entradaOuSaida = (entradaOuSaida == 0) ? 1 : 0;
        info("Ponto registrado com sucesso!", "Ponto Eletrônico");
    }

    public static void verificarFichaAntecedentes() {
        String cpfBusca = JOptionPane.showInputDialog(null, "Digite o CPF do cidadão (apenas números):", "Buscar Ficha", JOptionPane.QUESTION_MESSAGE);
        UmUsuario alvo = null;

        if (cpfBusca != null && !cpfBusca.isEmpty()) {
            for (UmUsuario u : bancoUsuarios) {
                if (u.getCpf().equals(cpfBusca)) {
                    alvo = u;
                    break;
                }
            }

            if (alvo != null) {
                UmPolicial policialLogado = bancoPoliciais.get(0);
                Antecedentes ficha = new Antecedentes(0, false, 101, "N/A", "Nada consta");
                
                // 1. Painel principal (fundo branco)
                JPanel painelPrincipal = new JPanel();
                painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
                painelPrincipal.setBackground(java.awt.Color.WHITE);
                painelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));

                // 2. Título
                JLabel lblTitulo = new JLabel("FICHA CRIMINAL");
                lblTitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
                lblTitulo.setForeground(new java.awt.Color(136, 14, 79)); 
                lblTitulo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

                JSeparator separador = new JSeparator();
                separador.setForeground(new java.awt.Color(244, 143, 177));
                separador.setMaximumSize(new java.awt.Dimension(400, 5));

                // 3. Dados do cidadão (alinhados à esquerda)
                JPanel painelDados = new JPanel();
                painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
                painelDados.setBackground(java.awt.Color.WHITE);
                painelDados.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                painelDados.setMaximumSize(new java.awt.Dimension(400, 80)); 

                JLabel lblSub = new JLabel("Dados do Cidadão:");
                lblSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                
                JLabel lblNome = new JLabel("Nome: " + alvo.getNome());
                lblNome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
                
                JLabel lblCpf = new JLabel("CPF: " + alvo.getCpf());
                lblCpf.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

                painelDados.add(lblSub);
                painelDados.add(Box.createVerticalStrut(5));
                painelDados.add(lblNome);
                painelDados.add(lblCpf);

                // 4. Caixa de Status (Muda de cor dependendo dos antecedentes)
                JPanel painelStatus = new JPanel();
                painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
                painelStatus.setBackground(new java.awt.Color(245, 245, 245));
                
                boolean possuiPassagens = ficha.isPossuiAntecedentes();
                // Verde se estiver limpo, Vermelho se tiver passagem
                java.awt.Color corBorda = possuiPassagens ? new java.awt.Color(211, 47, 47) : new java.awt.Color(76, 175, 80); 
                java.awt.Color corTexto = possuiPassagens ? new java.awt.Color(198, 40, 40) : new java.awt.Color(46, 125, 50);

                painelStatus.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 5, 0, 0, corBorda), 
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
                painelStatus.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                painelStatus.setMaximumSize(new java.awt.Dimension(400, 110));

                JLabel lblStatus = new JLabel("Status: " + (possuiPassagens ? "POSSUI PASSAGENS" : "NADA CONSTA"));
                lblStatus.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
                lblStatus.setForeground(corTexto);
                
                JLabel lblQtd = new JLabel("Quantidade de registros: " + ficha.getQuantidadeAntecedentes());
                lblQtd.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
                
                JLabel lblUltima = new JLabel("Última Ocorrência: " + ficha.getDataUltimaOcorrencia());
                lblUltima.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
                
                JLabel lblObs = new JLabel("Observações: " + ficha.getObservacoesAntecedentes());
                lblObs.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

                painelStatus.add(lblStatus);
                painelStatus.add(Box.createVerticalStrut(8));
                painelStatus.add(lblQtd);
                painelStatus.add(lblUltima);
                painelStatus.add(lblObs);

                // 5. Montagem do painel de conteúdo
                painelPrincipal.add(lblTitulo);
                painelPrincipal.add(Box.createVerticalStrut(15));
                painelPrincipal.add(separador);
                painelPrincipal.add(Box.createVerticalStrut(20));
                painelPrincipal.add(painelDados);
                painelPrincipal.add(Box.createVerticalStrut(20));
                painelPrincipal.add(painelStatus);
                painelPrincipal.add(Box.createVerticalGlue()); // Empurra o conteúdo para cima

                // 6. Fundo rosa (Tamanho Fixo 500x600 para manter o padrão)
                JPanel painelFundo = new JPanel(new java.awt.BorderLayout());
                painelFundo.setBackground(new java.awt.Color(248, 200, 220));
                painelFundo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                painelFundo.setPreferredSize(new java.awt.Dimension(500, 600)); 
                painelFundo.add(painelPrincipal, java.awt.BorderLayout.CENTER);

                // 7. Exibição da janela limpa (sem o "i" de informação)
                JOptionPane.showOptionDialog(
                    null, painelFundo, "Consulta de Ficha Criminal", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
                    null, new Object[]{"Voltar"}, "Voltar"
                );
                
                // Logs de validação
                ficha.listarOcorrencia(); 
                historicoGeral.registrarEventoSimples(policialLogado, "Consulta de Antecedentes: " + alvo.getNome());
            } else {
                JOptionPane.showMessageDialog(null, "CPF não encontrado no sistema.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        chamarMenuPolicial();
    }

    public static void verificarHistoricoPolicial() {
        UmPolicial policialLogado = bancoPoliciais.get(0);

        // 1. Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // 2. Título
        JLabel lblTitulo = new JLabel("HISTÓRICO DE ATIVIDADES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(136, 14, 79)); 
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(244, 143, 177));
        separador.setMaximumSize(new java.awt.Dimension(400, 5));

        // 3. Subtítulo com o nome do oficial
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBackground(Color.WHITE);
        painelDados.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDados.setMaximumSize(new java.awt.Dimension(400, 40)); 

        JLabel lblNome = new JLabel("Oficial: " + policialLogado.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelDados.add(lblNome);

        // 4. Monta o texto do histórico
        StringBuilder relatorioHistorial = new StringBuilder();
        boolean encontrouRegistros = false;

        for(String operacao : historicoGeral.getListaOperacoes()) {
            if (operacao.contains(policialLogado.getNome())) {
                relatorioHistorial.append("➔ ").append(operacao).append("\n\n");
                encontrouRegistros = true;
            }
        }

        if (!encontrouRegistros) {
            relatorioHistorial.append("Nenhuma atividade registrada para este oficial até o momento.");
        }

        // 5. Área de texto rolável para o histórico
        JTextArea areaTexto = new JTextArea(relatorioHistorial.toString());
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new java.awt.Dimension(400, 350));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // 6. Montagem
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(separador);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(painelDados);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(scrollPane);
        painelPrincipal.add(Box.createVerticalGlue());

        // 7. Fundo rosa (Tamanho Fixo 500x600)
        JPanel painelFundo = new JPanel(new java.awt.BorderLayout());
        painelFundo.setBackground(new Color(248, 200, 220));
        painelFundo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelFundo.setPreferredSize(new java.awt.Dimension(500, 600)); 
        painelFundo.add(painelPrincipal, java.awt.BorderLayout.CENTER);

        JOptionPane.showOptionDialog(
            null, painelFundo, "Relatório de Atividades", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
            null, new Object[]{"Voltar"}, "Voltar"
        );

        chamarMenuPolicial();
    }

    // =========================================================================
    //  MENU DO CIDADÃO
    // =========================================================================

   public static void chamarMenuCidadao() {
        // Emojis adicionados para dar uma cara mais moderna
        String[] opcoes = {
           "Registrar Boletim",
            "Verificar Status",
            "Meus Antecedentes",
            "Sair"
        };

        while (true) {
            // Criando uma mensagem de boas-vindas usando HTML para formatar melhor
            String mensagemBoasVindas = "<html><center>"
                    + "<b style='font-size:14px; color:#BE5A8C;'>Painel de Controle</b><br><br>"
                    + "Olá, <b>" + usuarioLogado.getNome() + "</b>!<br>"
                    + "O que você deseja fazer hoje?</center></html>";

            int escolha = exibirMenu(
                "Painel do Cidadão",
                mensagemBoasVindas, // Substituímos o texto antigo por essa variável
                opcoes
            );

            switch (escolha) {
                case 0:  registrarBoletim();             break;
                case 1:  verificarStatusBoletim();       break;
                case 2:  verificarAntecedentesCidadao(); break;
                default:
                    JOptionPane.showMessageDialog(null, "Sessão encerrada com sucesso.");
                    usuarioLogado = null;
                    telaInicial();
                    return;
            }
        }
    }

    public static void registrarBoletim() {
        while (true) {
            JTextField campoData       = campoCriado();
            JTextField campoRelato     = campoCriado();
            JTextField campoEvidencias = campoCriado();
            JComboBox<String> combo = new JComboBox<>(new String[]{"Feminicídio", "Agressão", "Violência Doméstica", "Sequestro"});
            combo.setFont(FONTE_CAMPO);

            JPanel form = criarPainelForm(
                "Data do Ocorrido (DD/MM/AAAA):",   campoData,
                "Relato detalhado do fato:",        campoRelato,
                "Evidências iniciais (se houver):", campoEvidencias,
                "Classificação da Ocorrência:",     combo
            );

            // Formulário padronizado
            if (exibirFormulario("Boletim de Ocorrência", form) != JOptionPane.OK_OPTION) return;

            String data = campoData.getText().trim();
            String relato = campoRelato.getText().trim();
            String evidencias = campoEvidencias.getText().trim();

            if (data.isEmpty() || relato.isEmpty()) {
                aviso("Data e relato são obrigatórios!", "Erro");
                continue;
            }
            if (!isDataValida(data) || parseData(data).isAfter(LocalDate.now())) {
                erro("Data inválida ou futura!", "Erro");
                continue;
            }

            UmaClassificacao.OpcaoViolencia op = UmaClassificacao.OpcaoViolencia.AGRESSAO;
            switch ((String) combo.getSelectedItem()) {
                case "Feminicídio": op = UmaClassificacao.OpcaoViolencia.FEMINICIDIO; break;
                case "Violência Doméstica": op = UmaClassificacao.OpcaoViolencia.VIOLENCIA_DOMESTICA; break;
                case "Sequestro": op = UmaClassificacao.OpcaoViolencia.SEQUESTRO; break;
            }

            classificacaoBoletim = new UmaClassificacao(op);
            int idAleatorio = (int) (Math.random() * 9000) + 1000;
            boletimCadastrado = new UmBoletim(idAleatorio, relato, data, evidencias);
            boletimCadastrado.adicionarClassificacao(classificacaoBoletim);
            statusBoletim = new UmStatus("registrado");
            boletimCadastrado.atualizarStatus(statusBoletim);

            sucesso("Boletim " + idAleatorio + " registrado com sucesso!");
            return;
        }
    }

   public static void verificarStatusBoletim() {
        if (boletimCadastrado == null || statusBoletim == null) {
            JOptionPane.showMessageDialog(
                null, 
                "Você não possui nenhum boletim cadastrado nesta sessão.\nPor favor, registre uma ocorrência primeiro.", 
                "Nenhum Registro", 
                JOptionPane.WARNING_MESSAGE
            );
            chamarMenuCidadao(); 
            return;
        }

        // 1. Painel principal (fundo branco)
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // 2. Título
        JLabel lblTitulo = new JLabel("STATUS DO BOLETIM");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(136, 14, 79)); 
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(244, 143, 177));
        separador.setMaximumSize(new java.awt.Dimension(400, 5));

        // 3. Detalhes (alinhados à esquerda)
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBackground(Color.WHITE);
        painelDados.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDados.setMaximumSize(new java.awt.Dimension(400, 80)); 

        JLabel lblSub = new JLabel("Detalhes da Ocorrência:");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblNome = new JLabel("Cidadão: " + usuarioLogado.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel lblClasse = new JLabel("Classificação: " + classificacaoBoletim.getTipoClassificacao());
        lblClasse.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        painelDados.add(lblSub);
        painelDados.add(Box.createVerticalStrut(5));
        painelDados.add(lblNome);
        painelDados.add(lblClasse);

        // 4. Caixa de Status (Fundo azul claro, borda azul mais forte)
        JPanel painelStatus = new JPanel();
        painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
        painelStatus.setBackground(new Color(227, 242, 253)); 
        painelStatus.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(33, 150, 243)), 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        painelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelStatus.setMaximumSize(new java.awt.Dimension(400, 80));

        JLabel lblStatus = new JLabel("Status Atual: " + statusBoletim.getTipo().toUpperCase());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(new Color(21, 101, 192)); 
        
        JLabel lblInfo = new JLabel("O caso foi registrado e aguarda análise.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        painelStatus.add(lblStatus);
        painelStatus.add(Box.createVerticalStrut(8));
        painelStatus.add(lblInfo);

        // 5. Montagem
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(separador);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelDados);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelStatus);
        painelPrincipal.add(Box.createVerticalGlue()); // Joga tudo pra cima

        // 6. Fundo rosa (Tamanho Fixo 500x600)
        JPanel painelFundo = new JPanel(new java.awt.BorderLayout());
        painelFundo.setBackground(new Color(248, 200, 220));
        painelFundo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelFundo.setPreferredSize(new java.awt.Dimension(500, 600)); 
        painelFundo.add(painelPrincipal, java.awt.BorderLayout.CENTER);

        JOptionPane.showOptionDialog(
            null, painelFundo, "Acompanhamento de Ocorrência", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
            null, new Object[]{"Voltar"}, "Voltar"
        );
        
        System.out.println("\n-> [POO VALIDAÇÃO] Status consultado pelo usuário:");
        statusBoletim.identificarStatus();
        
        chamarMenuCidadao(); 
    }

 public static void verificarAntecedentesCidadao() {
        Antecedentes ficha = new Antecedentes(0, false, 777, "Nenhuma", "Cidadão ficha limpa");

        // 1. Painel principal (fundo branco)
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // 2. Título
        JLabel lblTitulo = new JLabel("FICHA DE ANTECEDENTES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(136, 14, 79)); // Rosa escuro
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3. Linha divisória
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(244, 143, 177));
        separador.setMaximumSize(new java.awt.Dimension(400, 5));

        // 4. Dados do cidadão (alinhados à esquerda)
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));
        painelDados.setBackground(Color.WHITE);
        painelDados.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDados.setMaximumSize(new java.awt.Dimension(400, 80)); 

        JLabel lblSub = new JLabel("Dados do Cidadão:");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblNome = new JLabel("Nome: " + usuarioLogado.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel lblCpf = new JLabel("CPF: " + usuarioLogado.getCpf());
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        painelDados.add(lblSub);
        painelDados.add(Box.createVerticalStrut(5));
        painelDados.add(lblNome);
        painelDados.add(lblCpf);

        // 5. Caixa de Status (Fundo cinza claro, borda verde na esquerda)
        JPanel painelStatus = new JPanel();
        painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
        painelStatus.setBackground(new Color(245, 245, 245));
        painelStatus.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(76, 175, 80)), // Borda verde
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        painelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelStatus.setMaximumSize(new java.awt.Dimension(400, 90));

        JLabel lblStatus = new JLabel("Status: NADA CONSTA");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(new Color(46, 125, 50)); // Verde escuro
        
        JLabel lblHist = new JLabel("Histórico: Cidadão ficha limpa");
        lblHist.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel lblOcorrencias = new JLabel("Ocorrências ativas: 0");
        lblOcorrencias.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        painelStatus.add(lblStatus);
        painelStatus.add(Box.createVerticalStrut(8));
        painelStatus.add(lblHist);
        painelStatus.add(lblOcorrencias);

        // 6. Montando o painel principal
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(separador);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelDados);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelStatus);
        
        // Empurra todo o conteúdo para cima, impedindo que fique esticado no meio da janela
        painelPrincipal.add(Box.createVerticalGlue()); 

        JPanel painelFundo = new JPanel(new java.awt.BorderLayout());
        painelFundo.setBackground(new Color(248, 200, 220));
        painelFundo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        painelFundo.setPreferredSize(new java.awt.Dimension(500, 600)); 
        
        painelFundo.add(painelPrincipal, java.awt.BorderLayout.CENTER);

        // 8. Exibição da janela
        JOptionPane.showOptionDialog(
            null, 
            painelFundo, 
            "Consulta de Antecedentes", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            new Object[]{"Voltar"}, 
            "Voltar"
        );
        
        System.out.println("\n-> [POO VALIDAÇÃO] Usuário consultou a própria ficha de antecedentes:");
        ficha.verificarAntecedentes();
        
        chamarMenuCidadao(); 
    }
}