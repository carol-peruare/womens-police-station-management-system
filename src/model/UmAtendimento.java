package model;

public class UmAtendimento {
    private int id;
    private String data;
    private String horario;
    private String descricao;
    private String status;
    private UmUsuario usuario;

    public UmAtendimento(int id, String data, String horario, String descricao, UmUsuario usuario) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.descricao = descricao;
        this.status = "Agendado";
        this.usuario = usuario;
    }

    // Getters
    public int getId() {
    return id;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public UmUsuario getUsuario() {
        return usuario;
    }
    // Métodos para alterar o status do atendimento
    public void concluirAtendimento() {
        status = "Concluído";
    }

    public void cancelarAtendimento() {
        status = "Cancelado";
    }

    public void iniciarAtendimento() {
        status = "Em andamento";
    }

    @Override
    public String toString() {

        return "ID: " + id + "\nUsuário: " + usuario.getNome() + "\nData: " + data + "\nHorário: " + horario + "\nDescrição: " + descricao + "\nStatus: " + status;
    }
}
