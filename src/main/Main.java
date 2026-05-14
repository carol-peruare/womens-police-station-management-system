    // Maressa Luiza dos Reis Machado - 202421901046
    // Carolina Peruare Ferreira - 202412901003
package main;

import model.Antecedentes;
import model.UmaClassificacao;
import model.UmBoletim;
import model.UmPolicial;
import model.UmStatus;

public class Main {
    public static void main(String[] args) {
   //testando policial
        System.out.println("1. TESTANDO POLICIAL");
        
        UmPolicial policial = new UmPolicial(
            "Sargenta Flores", 
            "999.888.777-66", 
            "15/08/1985",
             "senhaSegura", 
             "65998775341",
             "2022101",
            "flores@policia.gov.br", 
            "Sargenta", 
            false,  // em_servico inicial
            false   // autenticado inicial
        );

        // Testando os métodos do policial isoladamente
        policial.autenticar("flores@policia.gov.br", "senhaSegura"); // Autentica
        policial.baterPonto(); // Entra em serviço
        policial.baterPonto(); // Bate o ponto de novo (Sai de serviço)
        
        System.out.println();
     
        //testando boletim e classificação

        System.out.println("2. TESTANDO BOLETIM");

        UmBoletim boletim = new UmBoletim(
            305, 
            "Meu vizinho está gritando com a esposa e abusando verbalmente dela desde madrugada.", 
            "12/05/2026", 
            "Nenhuma evidencia inicial"
        );

        // Adicionando informações soltas ao boletim
        boletim.adicionarEvidencia("Audio da gritaria");
        
        UmaClassificacao classificacao = new UmaClassificacao(UmaClassificacao.OpcaoViolencia.AGRESSAO);
        boletim.adicionarClassificacao(classificacao);
        
        UmStatus status = new UmStatus("Em análise");
        boletim.atualizarStatus(status);

        // Mostrando o boletim completo
        System.out.println("\n-> Visualizando o Boletim:");
        boletim.visualizar();

        System.out.println("\n-> Cancelando o Boletim:");
        boletim.cancelar();
        boletim.visualizar(); 

        System.out.println("3. TESTE DE STATUS");
        
        UmStatus status1 = new UmStatus("Em análise");
        UmStatus status2 = new UmStatus("Concluído"); 

        status1.identificarStatus(); 
        status2.identificarStatus();
        System.out.println();

        //testando antecedentes 
        System.out.println("4. TESTE DE ANTECEDENTES ");

        // var1 (int qtd), var2 (boolean possui), var3 (int id), var4 (String data), var5 (String obs)
        Antecedentes ficha = new Antecedentes(0, false, 777, "Nenhuma", "Cidadão ficha limpa");

        ficha.verificarAntecedentes();
        System.out.println();

        System.out.println("\n--- Relatório Atualizado ---");
        ficha.verificarAntecedentes(); // Agora dirá que POSSUI antecedentes
        ficha.listarOcorrencia();     // Vai listar a denúncia que adicionamos
    }
}
    

