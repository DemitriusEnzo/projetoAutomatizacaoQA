package br.automatiza.projetoAutomatizacaoQA.ia;

import java.net.http.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class IAService {

    private static final String API_KEY = "";
    private static final String API_URL = "";

    public static String gerarRoteiroTestes(String userStory) throws IOException, InterruptedException {
        String prompt = "" + userStory;
        String json = "".formatted(prompt);

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

