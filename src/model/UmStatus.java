package model;

public class UmStatus {

    private String tipo_status;

    public UmStatus(String tipo_status) {
        this.tipo_status = tipo_status;
    }


    public String getTipo() {
        return tipo_status;
    }

    public void setTipo(String tipo_status) {
        this.tipo_status = tipo_status;
    }


    public void identificarStatus() {
        System.out.print("Status: " + tipo_status + " → ");
        switch (tipo_status.toLowerCase()) {
            case "registrado":
                System.out.println("O caso foi registrado e aguarda análise.");
                break;
            case "em análise":
            case "em analise":
                System.out.println("O caso está sendo analisado pela delegacia.");
                break;
            case "em atendimento":
                System.out.println("Um policial está atendendo este caso.");
                break;
            case "encerrado":
                System.out.println("O caso foi encerrado.");
                break;
            case "cancelado":
                System.out.println("O caso foi cancelado.");
                break;
            case "aguardando informações":
            case "aguardando informacoes":
                System.out.println("A delegacia aguarda mais informações da solicitante.");
                break;
            default:
                System.out.println("Status não reconhecido pelo sistema.");
                break;
        }
    }

    @Override
    public String toString() {
        return "Status: " + tipo_status;
    }
}