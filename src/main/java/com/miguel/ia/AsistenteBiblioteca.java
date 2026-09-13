package com.miguel.ia;

import com.miguel.Book;
import com.miguel.Library;

public class AsistenteBiblioteca {

    private final Library library;
    public final GeminiClient geminiClient;

    public AsistenteBiblioteca(Library library) {
        this.library = library;
        this.geminiClient = new GeminiClient();
    }

    public String responder(String preguntaUsuario) throws Exception {
        String contexto = construirContexto();
        return geminiClient.preguntar(contexto, preguntaUsuario);
    }

    private String construirContexto() {
        StringBuilder sb = new StringBuilder();

        for (Book libro : library.obtenerLibros()) {
            boolean disponible = !library.estaPrestado(libro);
            sb.append("Titulo: ").append(libro.getTitulo())
                    .append(", Autor: ").append(libro.getAutor())
                    .append(", Año: ").append(libro.getAño())
                    .append(", Disponible: ").append(disponible)
                    .append(". ");
        }

        return sb.toString();
    }
}
