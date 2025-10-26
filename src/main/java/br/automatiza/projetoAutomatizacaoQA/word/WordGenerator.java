package br.automatiza.projetoAutomatizacaoQA.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class WordGenerator {
    public static void gerarEvidencias(String caminho, String textoGerado) throws IOException {
        XWPFDocument doc = new XWPFDocument();

        Set<String> titulos = Set.of(
                "Informações Gerais",
                "Evidências",
                "Descrição Cenário",
                "Procedimentos",
                "Resultado Esperado",
                "Status/Observação",
                "Erro",
                "Evidências do Teste"
        );

        Arrays.stream(textoGerado.split("\n"))
                .forEach(linha -> {
                    XWPFParagraph p = doc.createParagraph();
                    XWPFRun run = p.createRun();

                    if (titulos.contains(linha.trim())) {
                        run.setBold(true);
                        run.setFontSize(14);
                        p.setSpacingAfter(200);
                    } else {
                        p.setSpacingAfter(100);
                    }

                    run.setText(linha);
                });

        try (FileOutputStream out = new FileOutputStream(caminho)) {
            doc.write(out);
        }
        doc.close();
    }
}
