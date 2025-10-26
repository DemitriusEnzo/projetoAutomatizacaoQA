package br.automatiza.projetoAutomatizacaoQA.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ExcelGenerator {

    private static final String[] HEADERS = {
            "Item","Empresa","Natureza","Processo","Cenário","Atividade","Módulo","Transação","Área","Key User Responsável",
            "DEPENDÊNCIA TESTE","Info para input do dado","Tipo de Dado SAP","Dados SAP","Data Prevista Execução",
            "Data Real Execução","Status","Dependência","Evidência","Doc gerado","Nova Data","Comentários","Anotações sobre o teste"
    };

    public static void gerarExcel(String USName, String caminho, List<String[]> dados) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Roteiro de Testes - " + USName);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setWrapText(true);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setFont(dataFont);
        dataCellStyle.setWrapText(true);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataCellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataCellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataCellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(56);
        IntStream.range(0, HEADERS.length).forEach(i -> {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(HEADERS[i]);
            cell.setCellStyle(headerCellStyle);
        });

        IntStream.range(0, dados.size()).forEach(r -> {
            Row row = sheet.createRow(r + 1);
            row.setHeightInPoints(56);
            IntStream.range(0, dados.get(r).length).forEach(c -> {
                Cell cell = row.createCell(c);
                cell.setCellValue(dados.get(r)[c]);
                cell.setCellStyle(dataCellStyle);
            });
        });

        IntStream.range(0, HEADERS.length).forEach(sheet::autoSizeColumn);

        try (FileOutputStream out = new FileOutputStream(caminho)) {
            workbook.write(out);
        }
        workbook.close();
    }
}
