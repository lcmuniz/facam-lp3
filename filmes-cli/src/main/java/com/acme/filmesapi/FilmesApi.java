package com.acme.filmesapi;

import lombok.val;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FilmesApi {

    private final String API_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apiKey=73e50d37";

    private final HttpClient client = HttpClient.newHttpClient();

    private String send(String url) {
        val request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            val json = response.body().toString();
            return json;
        }
        catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilme(String tituloFilme) {
        val url = API_URL + tituloFilme.replaceAll(" ", "+") + API_KEY;
        return send(url);
    }

    public String getSerie(String tituloSerie) {
        val url = API_URL + tituloSerie.replaceAll(" ", "+") + API_KEY;
        return send(url);
    }

    public String getTemporada(String tituloSerie, int temporada) {
        val url = API_URL
                + tituloSerie.replaceAll(" ", "+")
                + "&season=" + temporada
                + API_KEY;
        return send(url);
    }

    public String getEpisodio(String tituloSerie, int temporada, int episodio) {
        val url = API_URL
                + tituloSerie.replaceAll(" ", "+")
                + "&season=" + temporada
                + "&episode=" + episodio
                + API_KEY;
        return send(url);
    }

}
