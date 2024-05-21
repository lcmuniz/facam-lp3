package com.acme.demo5;

import com.acme.demo5.model.Episodio;
import com.acme.demo5.model.Serie;
import com.acme.demo5.model.Temporada;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class FilmeCliApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FilmeCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        val mapper = new ObjectMapper();
        val scanner = new Scanner(System.in);
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("pt_BR"));

        System.out.print("Digite o título da série: ");
        val title = scanner.nextLine();

        val api = new FilmesApi();
        val jsonSerie = api.getSerie(title);

        val dadosSerie = mapper.readValue(jsonSerie, DadosSerie.class);

        val serie = new Serie();
        serie.setTitulo(dadosSerie.titulo());
        serie.setGenero(dadosSerie.genero());
        serie.setAvaliacao(Double.valueOf(dadosSerie.avaliacao()));
        serie.setDataLancamento(LocalDate.parse(dadosSerie.dataLancamento(), formatter));

        for(var i = 1; i <= Integer.valueOf(dadosSerie.numeroTemporadas()); i++) {
            val jsonTemporada = api.getTemporada(title, i);
            val dadosTemporada = mapper.readValue(jsonTemporada, DadosTemporada.class);

            val temporada = new Temporada();
            temporada.setTitulo(dadosTemporada.titulo());
            temporada.setNumeroTemporada(Integer.valueOf(dadosTemporada.numeroTemporada()));
            temporada.setSerie(serie.getTitulo());

            for (var j = 0; j < dadosTemporada.episodios().size(); j++) {
                val dadosEpisodio = dadosTemporada.episodios().get(j);
                val episodio = new Episodio();
                if (dadosEpisodio.avaliacao().equals("N/A")) {
                    episodio.setAvaliacao(null);
                }
                else {
                    episodio.setAvaliacao(Double.valueOf(dadosEpisodio.avaliacao()));
                }
                episodio.setNumeroEpisodio(Integer.valueOf(dadosEpisodio.numeroEpisodio()));
                if (dadosEpisodio.dataLancamento().equals("N/A")) {
                    episodio.setDataLancamento(null);
                }
                else {
                    episodio.setDataLancamento(LocalDate.parse(dadosEpisodio.dataLancamento()));
                }
                episodio.setTitulo(dadosEpisodio.titulo());
                episodio.setNumeroTemporada(temporada.getNumeroTemporada());

                temporada.getEpisodios().add(episodio);
            }

            serie.getTemporadas().add(temporada);

        }

        System.out.println(serie);


    }
}
