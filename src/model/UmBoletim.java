package model;
 
import java.util.ArrayList;
import java.util.List;
 
public class UmBoletim {
 
    private List<UmaClassificacao> classificacao_boletim;
    private int id_boletim;
    private String descricao_boletim;
    private String data_boletim;
    private List<UmEndereco> enderecos_boletim;
    private List<UmUsuario> usuarios_boletim;
    private List<UmStatus> status_boletim;
    private List<UmPolicial> policialResponsavel_boletim;
    private String evidencias_boletim;
    private boolean ativo;
 
    public UmBoletim(int id_boletim, String descricao_boletim, String data_boletim, String evidencias_boletim) {
        this.id_boletim = id_boletim;
        this.descricao_boletim = descricao_boletim;
        this.data_boletim = data_boletim;
        this.evidencias_boletim = evidencias_boletim;
        this.enderecos_boletim = new ArrayList<>();
        this.usuarios_boletim = new ArrayList<>();
        this.status_boletim = new ArrayList<>();
        this.policialResponsavel_boletim = new ArrayList<>();
        this.classificacao_boletim = new ArrayList<>();
        this.ativo = true;
    }
    public int getId() {
        return id_boletim;
    }
 
    public String getDescricao() {
        return descricao_boletim;
    }
 
    public String getData() {
        return data_boletim;
    }
 
    public String getEvidencias() {
        return evidencias_boletim;
    }
 
    public boolean isAtivo() {
        return ativo;
    }
 
    public List<UmaClassificacao> getClassificacaoBoletim() {
        return classificacao_boletim;
    }
 
    public List<UmEndereco> getEnderecosBoletim() {
        return enderecos_boletim;
    }
 
    public List<UmUsuario> getUsuariosBoletim() {
        return usuarios_boletim;
    }
 
    public List<UmPolicial> getPolicialResponsavelBoletim() {
        return policialResponsavel_boletim;
    }
 
    public String getStatus() {
        if (status_boletim.isEmpty()) {
            return "Sem status definido";
        }
        return status_boletim.get(status_boletim.size() - 1).getTipo();
    }
  
    public void setDescricaoBoletim(String descricao_boletim) {
        this.descricao_boletim = descricao_boletim;
    }
 
    public void setDataBoletim(String data_boletim) {
        this.data_boletim = data_boletim;
    }
 
    public void setEvidenciasBoletim(String evidencias_boletim) {
        this.evidencias_boletim = evidencias_boletim;
    }
 
    public void registrarBoletim(UmUsuario usuario) {
        if (usuario == null) {
            System.err.println("Erro: usuário inválido para registro do boletim.");
            return;
        }
        usuarios_boletim.add(usuario);
        UmStatus statusInicial = new UmStatus("Registrado");
        status_boletim.add(statusInicial);
 
        System.out.println("===== BOLETIM REGISTRADO =====");
        System.out.println("ID: " + id_boletim);
        System.out.println("Descrição: " + descricao_boletim);
        System.out.println("Data: " + data_boletim);
        System.out.println("Status: " + getStatus());
    }
 
    public void atualizarStatus(UmStatus novoStatus) {
        if (novoStatus == null) {
            System.err.println("Erro: status inválido.");
            return;
        }
        status_boletim.add(novoStatus);
        System.out.println("Status do boletim " + id_boletim + " atualizado para: " + novoStatus.getTipo());
    }
 

    public void visualizar() {
        System.out.println("===== BOLETIM DE OCORRÊNCIA =====");
        System.out.println("ID: " + id_boletim);
        System.out.println("Descrição: " + descricao_boletim);
        System.out.println("Data: " + data_boletim);
        System.out.println("Ativo: " + ativo);
 
        System.out.println("\n--- Classificações ---");
        if (classificacao_boletim.isEmpty()) {
            System.out.println("Sem classificação.");
        } else {
            for (UmaClassificacao c : classificacao_boletim) {
                System.out.println("  " + c.getTipoClassificacao());
            }
        }
 
        System.out.println("\n--- Endereços ---");
        if (enderecos_boletim.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");
        } else {
            for (UmEndereco e : enderecos_boletim) {
                e.mostrarEndereco();
            }
        }
 
        System.out.println("\n--- Evidências ---");
        System.out.println(evidencias_boletim != null ? evidencias_boletim : "Nenhuma evidência.");
 
        System.out.println("\n--- Policial Responsável ---");
        if (policialResponsavel_boletim.isEmpty()) {
            System.out.println("Nenhum policial atribuído.");
        } else {
            for (UmPolicial p : policialResponsavel_boletim) {
                System.out.println("  " + p.getNome() + " | Matrícula: " + p.getMatricula());
            }
        }
 
        System.out.println("\n--- Status Atual ---");
        System.out.println(getStatus());
    }
 
    public void adicionarEvidencia(String evidencia) {
        if (evidencia == null || evidencia.isBlank()) {
            System.err.println("Erro: evidência inválida.");
            return;
        }
        if (evidencias_boletim == null || evidencias_boletim.isBlank()) {
            evidencias_boletim = evidencia;
        } else {
            evidencias_boletim += ", " + evidencia;
        }
        System.out.println("Evidência adicionada ao boletim " + id_boletim + ": " + evidencia);
    }
 
    public void cancelar() {
        if (!ativo) {
            System.out.println("Boletim já está cancelado.");
            return;
        }
        ativo = false;
        status_boletim.add(new UmStatus("Cancelado"));
        System.out.println("Boletim " + id_boletim + " cancelado.");
    }
 

    public void adicionarPolicial(UmPolicial policial) {
        if (policial == null) {
            System.err.println("Erro: policial inválido.");
            return;
        }
        policialResponsavel_boletim.add(policial);
        System.out.println("Policial " + policial.getNome() + " vinculado ao boletim " + id_boletim);
    }
 

    public void adicionarClassificacao(UmaClassificacao classificacao) {
        if (classificacao == null) {
            System.err.println("Erro: classificação inválida.");
            return;
        }
        classificacao_boletim.add(classificacao);
        System.out.println("Classificação '" + classificacao.getTipoClassificacao()
                + "' adicionada ao boletim " + id_boletim);
    }
}
