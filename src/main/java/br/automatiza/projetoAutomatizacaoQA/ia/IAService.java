package br.automatiza.projetoAutomatizacaoQA.ia;

import java.net.http.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class IAService {

    private static final String API_KEY = "inserir chave da api";
    private static final String API_URL = "inserir url da api";

    public static String gerarRoteiroTestes(String userStory) throws IOException, InterruptedException {
        String prompt = """
                Gere um roteiro de testes para QA **em formato de tabela de Excel**, seguindo EXATAMENTE o exemplo abaixo.
                
                Colunas (na ordem):
                Item | Empresa | Natureza | Processo | Cenário | Atividade | Módulo | Transação | Área | Key User Responsável | DEPENDÊNCIA TESTE | Info para input do dado | Tipo de Dado SAP | Dados SAP | Data Prevista Execução | Data Real Execução | Status | Dependência | Evidência | Doc gerado | Nova Data | Comentários | Anotações sobre o teste
                Exemplo de linha (note que os separadores são **tabulações reais**, não "\\t"):
                1 [TAB] EmpresaA [TAB] Gás On Grid [TAB] Criação de Contrato Master [TAB] Acesso ao botão "Criar Contrato Master" [TAB] Verificar se o botão aparece em oportunidade do tipo Gás On Grid [TAB] Salesforce [TAB] Contrato Master [TAB] Comercial [TAB] Enzo Demitrius [TAB] - [TAB] - [TAB] - [TAB] - [TAB] - [TAB] - [TAB] - [TAB] Captura de tela [TAB] TE_USER_EVIDENCIAS_GASONGRID_01.docx [TAB] - [TAB] - [TAB] -
                
                **Importante:** \s
                - Use **tabulação real** entre as colunas. \s
                - Use **quebra de linha real** ao final de cada linha. \s
                - Preencha campos em branco com `-`. \s
                - Retorne **somente as linhas da tabela**, sem aspas, sem caracteres de escape. \s
                - Gere linhas coerentes com base na seguinte User Story:
                - Não esqueça de preencher as células da coluna "Anotações sobre o teste" com `-`.
                ---
                **User Story:** \s
                %s
                Retorne **somente as linhas da tabela**, usando tabulação real entre colunas e quebra de linha real ao final de cada linha.
                """.formatted(userStory);
            return callGPT(prompt);
        }

    public static String gerarEvidencias(String roteiro, String numeroUS) throws IOException, InterruptedException {
        String prompt = """
        Gere um texto para documento de evidências de testes **em formato Word**, seguindo ESTRITAMENTE o exemplo abaixo.

        Exemplo de estrutura:

        Informações Gerais

        Projeto	SGA
        Descrição	QA test da US 3458 <link>

        Sistema	Salesforce
        Módulo	Gás Off Grid
        Ambiente do teste	Homologação em ambiente de QA no Salesforce
        Autor/Usuário	Enzo Carvalhaes
        Data de execução	03/10/2025

        Evidências
                <Para casos de exceção onde existe ambiente de homologação mas não é possível gerar evidências, incluir a justificativa detalhada para validação da TI.>

                1.	Geração de Cotação de Gás Off Grid
        Criação de cotação do novo produto “Gás Off Grid” através da plataforma Salesforce

        Descrição Cenário
        Cenário 1: Validação do acesso...
        Cenário 2: ...

        Procedimentos
                (explicação breve apenas nesse parágrafo de como os testes foram realizados, não deve gerar um procedimento para cada cenário, este deve ser o geral explicativo)

        Resultado Esperado
        (resultados esperados para cada cenário)
        Cenário 1: Sistema permitiu acesso...

        Status/Observação
        Aprovado
        Cenário 1: (Deixe esse espaço reservado para ser utilizado após os testes).
        Cenário 2: (Deixe esse espaço reservado para ser utilizado após os testes).
        ...
        Erro
                (Deixe esse espaço reservado para ser utilizado após os testes).

                Evidências do Teste
        Cenário 1: Sistema permitiu acesso...
        Cenário 2: ...

        Agora gere o documento seguindo este formato, adaptado para o seguinte roteiro de testes:
        %s

        Substitua o número da US pelo número %s.
        Mantenha a estrutura e os títulos exatamente iguais.
        Siga estritamente as instruções e o padrão do texto.
        Retorne apenas o texto que irá para o documento word.
        """.formatted(roteiro, numeroUS);

        return callGPT(prompt);
    }

    private static String callGPT(String prompt) throws IOException, InterruptedException {
        String json = """
        inserir json para request
        """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

