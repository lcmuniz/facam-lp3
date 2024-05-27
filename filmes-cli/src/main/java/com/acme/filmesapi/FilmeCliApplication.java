package com.acme.filmesapi;

import com.acme.filmesapi.model.Episodio;
import com.acme.filmesapi.model.Serie;
import com.acme.filmesapi.model.Temporada;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.stream.Collectors;

@SpringBootApplication
public class FilmeCliApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FilmeCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //val title = Arrays.stream(args).collect(Collectors.joining(" "));

        val title = "breaking bad";

        val api = new FilmesApi();
        val jsonSerie = api.getSerie(title);
        val serie = Serie.from(jsonSerie);

        for(var i = 1; i <= serie.getNumeroTemporadas(); i++) {
            val jsonTemporada = api.getTemporada(title, i);
            val temporada = Temporada.from(jsonTemporada, serie);
            serie.getTemporadas().add(temporada);
        }

        val episodios = serie.getTemporadas().stream()
                .flatMap(temporada -> temporada.getEpisodios().stream())
                .filter(episodio -> episodio.getAvaliacao() != null)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Quantidade: " + episodios.getCount());
        System.out.println("Soma: " + episodios.getSum());
        System.out.println("Menor: " + episodios.getMin());
        System.out.println("Maior: " + episodios.getMax());
        System.out.println("Média: " + episodios.getAverage());

        val episodios1 = serie.getTemporadas().stream()
                .flatMap(temporada -> temporada.getEpisodios().stream())
                .filter(episodio -> episodio.getAvaliacao() != null)
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(5)
                .toList();

        val menorNota = serie.getTemporadas().stream()
                .flatMap(temporada -> temporada.getEpisodios().stream())
                .filter(episodio -> episodio.getAvaliacao() != null)
                .sorted(Comparator.comparing(Episodio::getAvaliacao))
                .findFirst();

        System.out.println(menorNota.get());

        val maiorNota = serie.getTemporadas().stream()
                .flatMap(temporada -> temporada.getEpisodios().stream())
                .filter(episodio -> episodio.getAvaliacao() != null)
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .findFirst();

        System.out.println(maiorNota.get());

        episodios1.forEach(e -> {
            System.out.println("--------------------------------------");
            System.out.println("Temporada: " + e.getNumeroTemporada());
            System.out.println("Episódio: " + e.getNumeroEpisodio());
            System.out.println("Título: " + e.getTitulo());
            System.out.println("Avaliação: " + e.getAvaliacao());
        });

        //mostrarSerie(serie);


    }

    private static void mostrarSerie(Serie serie) {
        System.out.println("-------------------------------------------");
        System.out.println("Série: " + serie.getTitulo());
        System.out.println("Gênero: " + serie.getGenero());
        System.out.println("Avaliação: " + serie.getAvaliacao());
        System.out.println("Temporadas: " + serie.getNumeroTemporadas());
        System.out.println("-------------------------------------------");
        serie.getTemporadas().forEach(temporada -> {
            temporada.getEpisodios().forEach(episodio -> {
                System.out.println("Temporada " + temporada.getNumeroTemporada() + " - Episódio " + episodio.getNumeroEpisodio());
                System.out.println("Título: " + episodio.getTitulo());
                System.out.println("Avaliação: "+ episodio.getAvaliacao());
                System.out.println("-------------------------------------------");
            });
        });
    }


}
