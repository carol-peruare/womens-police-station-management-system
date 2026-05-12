package model;

import java.util.ArrayList;
import java.util.List;

public class UmHistorico {
    // Controle de ID auto-incrementado
    private static int contadorId = 1;
    
    private int id; 
    private List<UmPolicial> policiais;
    private String dataUltimaAtividade;
    private List<String> listaOperacoes; 
    private List<RegistroOcorrencia> listaOcorrenciasAtendidas;

    public UmHistorico(String dataUltimaAtividade) {
        this.id = contadorId++; // incrementa automaticamente a cada novo objeto
        this.dataUltimaAtividade = dataUltimaAtividade;
        this.policiais = new ArrayList<>();
        this.listaOperacoes = new ArrayList<>();
        this.listaOcorrenciasAtendidas = new ArrayList<>();
    }

    // registrar atendimento de ocorrência
    public void registrarAtendimentoOcorrencia(String descricaoOcorrencia, UmPolicial policial, String dataOcorrencia, String localOcorrencia, UmaDenuncia denuncia, UmBoletim boletim) {
        RegistroOcorrencia registro = new RegistroOcorrencia(descricaoOcorrencia, dataOcorrencia, localOcorrencia, policial, denuncia, boletim);
        
        this.listaOcorrenciasAtendidas.add(registro);
        if (!policiais.contains(policial)) {
            policiais.add(policial);
        }

        this.dataUltimaAtividade = dataOcorrencia;
        System.out.println("Ocorrência ID #" + id + " registrada com sucesso!");
    }

    // registrar atendimento de boletim
    public void registrarAtendimentoBoletim(UmBoletim boletim, UmPolicial policial, String dataAtendimento) {
        String registro = String.format("Boletim ID: %s | Atendido por: %s | Data: %s", boletim.getId(), policial.getNome(), dataAtendimento);

        this.listaOperacoes.add(registro);
        this.dataUltimaAtividade = dataAtendimento;
        System.out.println("Boletim atendido e registrado no histórico!");
    }

    public void registrarAtendimentoDenuncia(UmaDenuncia denuncia, UmPolicial policial, String dataAtendimento) {
        String registro = String.format("Denúncia ID: %s | Atendida por: %s | Data: %s", denuncia.getId(), policial.getNome(), dataAtendimento);

        this.listaOperacoes.add(registro);
        this.dataUltimaAtividade = dataAtendimento;
        System.out.println("Denúncia atendida e registrada no histórico!");
    }

    // Listar o histórico de atendimentos de um policial específico
    public void listarHistorico(UmPolicial policial) {
        System.out.println("\n--- BUSCANDO HISTÓRICO: Policial " + policial.getNome() + " ---");
        boolean encontrou = false;

        // busca nas ocorrências
        for (RegistroOcorrencia registro : listaOcorrenciasAtendidas) {
            if (registro.getPolicial().equals(policial)) {
                System.out.println("[OCORRÊNCIA]");
                System.out.println("Data: " + registro.getData_ocorrencia());
                System.out.println("Local: " + registro.getLocal_ocorrencia());
                System.out.println("Descrição: " + registro.getDescricao_ocorrencia());
                System.out.println("------------------------------------");
                encontrou = true;
            }
        }

        // busca nos boletins 
        for (String operacao : listaOperacoes) {
            if (operacao.contains(policial.getNome())) {
                System.out.println("[BOLETIM]");
                System.out.println(operacao);
                System.out.println("------------------------------------");
                encontrou = true;
            }
        }
        // busca nas denúncias
        for (String operacao : listaOperacoes) {
            if (operacao.contains(policial.getNome())) {
                System.out.println("[DENÚNCIA]");
                System.out.println(operacao);
                System.out.println("------------------------------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum registro encontrado para este policial.");
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDataUltimaAtividade() {
        return dataUltimaAtividade;
    }
}