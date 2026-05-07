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
}
