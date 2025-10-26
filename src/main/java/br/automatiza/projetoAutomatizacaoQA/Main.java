package br.automatiza.projetoAutomatizacaoQA;

import br.automatiza.projetoAutomatizacaoQA.excel.ExcelGenerator;
import br.automatiza.projetoAutomatizacaoQA.ia.IAService;
import br.automatiza.projetoAutomatizacaoQA.utils.FileOrganizer;
import br.automatiza.projetoAutomatizacaoQA.word.WordGenerator;

import java.util.*;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor da US:");
        String userStoryId = sc.nextLine();
        System.out.println("Cole a US:");
        String userStory = sc.nextLine();

        String baseDir = FileOrganizer.criarEstrutura(userStoryId);

        System.out.println("Gerando roteiro de testes...");
        String roteiroTexto = IAService.gerarRoteiroTestes(userStory);
        Files.writeString(Path.of(baseDir, "roteiro", "ia_output.txt"), roteiroTexto);

        List<String[]> linhas = Arrays.stream(roteiroTexto.split("\n"))
                .map(linha -> linha.split("\t"))
                .toList();

        ExcelGenerator.gerarExcel(userStoryId, baseDir + "/roteiro/Roteiro_" + userStoryId + ".xlsx", linhas);

        System.out.println("Gerando documento de evidências...");
        String evidenciasTexto = IAService.gerarEvidencias(roteiroTexto, userStoryId);
        WordGenerator.gerarEvidencias(baseDir + "/evidencias/Evidencias_" + userStoryId + ".docx", evidenciasTexto);

        System.out.println("Concluído.");
        System.out.println("Pasta: " + baseDir);
    }
}

