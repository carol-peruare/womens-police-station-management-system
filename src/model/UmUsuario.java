package model;

import java.util.ArrayList;
import java.util.List;

public class UmUsuario extends UmaPessoa{
    private List<Antecedentes> antecedentes; 
    private List<UmAtendimento> atendimentos;
    private List<UmBoletim> boletims_usuario;

    public UmUsuario(String nome, String cpf, String email, String data_Nasc, String senha, String telefone) {
        super(nome, cpf, email, data_Nasc, senha, telefone, email); // Usa o CPF como código identificador para o usuário

        this.antecedentes = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.boletims_usuario = new ArrayList<>();
    }

    // Agendar atendimento (Usa a variável atendimentos) TEM QUE COLOCAR ONLINE OU PRESENCIAL
    public void agendarAtendimento(String data, String horario, String descricao) {
        if (autenticado) {
            UmAtendimento novo = new UmAtendimento(atendimentos.size() + 1, data, horario, descricao, this);
            atendimentos.add(novo);
            System.out.println("Atendimento agendado para " + this.nome + " com sucesso!");
        } else {
            System.err.println("Erro! Você precisa estar logado para agendar atendimentos.");
        }
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

}