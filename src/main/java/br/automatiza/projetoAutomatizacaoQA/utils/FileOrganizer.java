package br.automatiza.projetoAutomatizacaoQA.utils;

import java.io.File;

public class FileOrganizer {
    public static String criarEstrutura(String userStoryId) {
        String baseDir = "inserir diretorio" + userStoryId;
        new File(baseDir + "/roteiro");
        new File(baseDir + "/evidencias");
        return baseDir;
    }
}
