package model;

import java.util.ArrayList;
import java.util.List;

public class UmUsuario {
    private static int contadorId = 1;
    private String nome;
    private String cpf;
    private int id_usuario;
    private String email;
    private String data_Nasc;
    private String senha;
    private String telefone;
    private List<UmEndereco> enderecos;
    private boolean autenticado;
    private List<Antecedentes> antecedentes; 
    private List<UmAtendimento> atendimentos;
    private List<UmaDenuncia> denuncias;
    private List<UmBoletim> boletims_usuario;

    public UmUsuario(String nome, String cpf, String email, String data_Nasc, String senha, String telefone) {
        this.id_usuario = contadorId++; // ID auto-incrementado
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_Nasc = data_Nasc;
        this.senha = senha;
        this.telefone = telefone;
        this.autenticado = false; // Usuário começa deslogado por padrão
 
        this.enderecos = new ArrayList<>();
        this.antecedentes = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.boletims_usuario = new ArrayList<>();
    }

    // Método para autenticar o usuário
    public boolean autenticar(String senhaDigitada, String emailDigitado) {
        if (this.senha.equals(senhaDigitada) && this.email.equalsIgnoreCase(emailDigitado)) {
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }

    // Agendar atendimento (Usa a variável atendimentos)
    public void agendarAtendimento(String data, String horario, String descricao) {
        if (autenticado) {
            UmAtendimento novo = new UmAtendimento(atendimentos.size() + 1, data, horario, descricao, this);
            atendimentos.add(novo);
            System.out.println("Atendimento agendado para " + this.nome + " com sucesso!");
        } else {
            System.err.println("Erro! Você precisa estar logado para agendar atendimentos.");
        }
    }

    // Realizar denúncia 
    public void realizarDenuncia(String descricao, String data, boolean denunciaAnonima) {
        int novoId = denuncias.size() + 1;
        // Se estiver autenticado, pode ser anônima ou não. Se não estiver, forçamos anônima.
        boolean statusAnonimo = !autenticado || denunciaAnonima;
        
        UmaDenuncia novaDenuncia = new UmaDenuncia(novoId, descricao, data, statusAnonimo);
        denuncias.add(novaDenuncia);

        System.out.println("Denúncia (" + (statusAnonimo ? "Anônima" : "Identificada") + ") realizada!");
    }

    // Realizar boletim 
    public void realizarBoletim(String descricao, String local, String categoria) {
        if (autenticado) {
            int novoIdBoletim = boletims_usuario.size() + 1;
            UmBoletim novoBoletim = new UmBoletim(novoIdBoletim, descricao, local, categoria);
            boletims_usuario.add(novoBoletim);
            System.out.println("Boletim de Ocorrência registrado com sucesso!");
        } else {
            System.err.println("Erro! Login obrigatório para registrar Boletim.");
        }
    }

    // Adicionar endereço 
    public void adicionarEndereco(String rua, String numero, String cidade, String estado, String cep) {
        UmEndereco novoEndereco = new UmEndereco(rua, numero, cidade, estado, cep);
        enderecos.add(novoEndereco);
        System.out.println("Endereço cadastrado no perfil de " + this.nome);
    }

    // Consultar Antecedentes 
    public void emitirCertidaoAntecedentes() {
    System.out.println("Certidão de Antecedentes de: " + this.nome + " (CPF: " + this.cpf + ")");
    
    if (antecedentes.isEmpty()) {
        System.out.println("Nada consta nos registros criminais.");
    } else {
        for (Antecedentes a : antecedentes) {
            System.out.println("ID Registro: " + a.getIdAntecedentes());
            System.out.println("Quantidade: " + a.getQuantidadeAntecedentes());
            System.out.println("Última Ocorrência: " + a.getDataUltimaOcorrencia());
            System.out.println("Observações: " + a.getObservacoesAntecedentes());
            System.out.println("------------------------------------");
        }
    }
}

    // Verificação de Status 
    public void verificarStatusAtendimento(int idAtendimento) {
        for (UmAtendimento a : atendimentos) {
            if (a.getId() == idAtendimento) {
                System.out.println("Status Atendimento #" + idAtendimento + ": " + a.getStatus());
                return;
            }
        }
        System.err.println("Atendimento não encontrado.");
    }

    public void verificarStatusBoletim(int idBoletim) {
        for (UmBoletim b : boletims_usuario) {
            if (b.getId() == idBoletim) {
                System.out.println("Status Boletim #" + idBoletim + ": " + b.getStatus());
                return;
            }
        }
        System.err.println("Boletim não encontrado.");
    }

    public void verificarStatusDenuncia(int idDenuncia) {
        for (UmaDenuncia d : denuncias) {
            if (d.getId() == idDenuncia) {
                System.out.println("Status Denúncia #" + idDenuncia + ": " + d.getStatus());
                return;
            }
        }
        System.err.println("Denúncia não encontrada.");
    }

    // getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getdata_Nasc() { return data_Nasc; }
    public int getId_usuario() { return id_usuario; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public boolean isAutenticado() { return autenticado; }
}