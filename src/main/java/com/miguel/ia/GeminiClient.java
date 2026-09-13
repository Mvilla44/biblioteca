package com.miguel.ia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiClient {

    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final String URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent";

    public String preguntar(String contexto, String preguntaUsuario) throws Exception {
        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException(
                    "No se encontró GEMINI_API_KEY. Configurala como variable de entorno.");
        }

        String prompt = "Eres un asisten de biblioteca. Usa este contexto de libros disponibles: "
                + contexto + ". Pregunta del usuario: " + preguntaUsuario;

        String body = """
            {
              "contents": [{
                "parts": [{ "text": "%s" }]
              }]
            }
            """.formatted(prompt.replace("\"", "\\\""));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return extraerTexto(response.body());
    }

    private String extraerTexto(String jsonCrudo) throws Exception {
        JsonNode raiz = new ObjectMapper().readTree(jsonCrudo);

        JsonNode errorNode = raiz.get("error");
        if (errorNode != null) {
            return "Error de la API: " + errorNode.get("message").asText();
        }

        return raiz
                .path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text")
                .asText();
    }
}
