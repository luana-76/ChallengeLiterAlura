package com.LiterAlura.challengeLiterAlura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.LiterAlura.challengeLiterAlura.autor.Autor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootApplication
public class IndexApplication {

    public static void main(String[] args) throws Exception {
   
        SpringApplication.run(IndexApplication.class, args);

        Scanner leitura = new Scanner(System.in);
        String decisao;

        // Cliente HTTP para fazer requisições à API externa
        HttpClient client = HttpClient.newHttpClient();

        // Lista para armazenar autores pesquisados
        List<Autor> autoresSalvos = new ArrayList<>();

        // Loop principal do menu
        do {
            System.out.println("""
                Escolha o número de sua opção:

                1) Buscar livro pelo título
                2) Listar livros registrados
                3) Listar autores registrados
                4) Listar autores vivos em um determinado ano
                5) Listar livros em um determinado idioma
                6) Sair
                --------------------------------------
                """);

            System.out.print("Digite sua opção: ");
            decisao = leitura.nextLine();

            // Encerrar programa se o usuário digitar 6 ou "sair"
            if (decisao.equals("6") || decisao.equalsIgnoreCase("sair")) {
                System.out.println("\nObrigado pela preferência e volte sempre!\n");
                System.exit(0);
                break;
            }

            // URL base da API
            String url = "https://gutendex.com/books/";
            HttpRequest request;
            HttpResponse<String> response;

            // Avalia a opção escolhida
            switch (decisao) {

                // Buscar livro por título ou nome de autor
                case "1" -> {
                    System.out.print("Digite o título ou autor: ");
                    String busca = leitura.nextLine();
                    url += "?search=" + busca.replace(" ", "+");

                    // Faz a requisição para a API
                    request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    JSONObject json = new JSONObject(response.body());
                    JSONArray resultados = json.getJSONArray("results");

                    // Itera sobre os livros retornados
                    for (int i = 0; i < resultados.length(); i++) {
                        JSONObject livro = resultados.getJSONObject(i);
                        String titulo = livro.getString("title");

                        JSONArray autoresArray = livro.getJSONArray("authors");
                        JSONArray idiomasArray = livro.getJSONArray("languages");
                        int downloads = livro.getInt("download_count");

                        // Para cada autor do livro
                        for (int j = 0; j < autoresArray.length(); j++) {
                            JSONObject autorJson = autoresArray.getJSONObject(j);
                            String nome = autorJson.getString("name");
                            int birthYear = autorJson.optInt("birth_year", 0);
                            int deathYear = autorJson.optInt("death_year", 0);

                            // Constrói string com os idiomas
                            StringBuilder idiomas = new StringBuilder();
                            for (int k = 0; k < idiomasArray.length(); k++) {
                                idiomas.append(idiomasArray.getString(k));
                                if (k < idiomasArray.length() - 1) idiomas.append(", ");
                            }

                            // Cria objeto Autor e salva na lista
                            Autor autorObj = new Autor(
                                titulo,
                                nome,
                                idiomas.toString(),
                                String.valueOf(downloads),
                                birthYear != 0 ? birthYear : null,
                                deathYear != 0 ? deathYear : null
                            );

                            autoresSalvos.add(autorObj);

                            System.out.println("\n----------LIVRO----------");
                            System.out.println(" Título: " + titulo);
                            System.out.println(" Autor: " + nome);
                            System.out.println(" Idioma(s): " + idiomas);
                            System.out.println(" Downloads: " + downloads);
                            System.out.println("------------------------------------\n");
                        }
                    }
                    break;
                }

                // Lista os livros registrados na aplicação
                case "2" -> {
                    if (autoresSalvos.isEmpty()) {
                        System.out.println("\nNenhum autor foi pesquisado ainda.\n");
                    } else {
                        for (Autor autor : autoresSalvos) {
                            System.out.println("\n----------LIVRO----------\n");
                            System.out.println(" Título: " + autor.getTitulo());
                            System.out.println(" Autor(es): " + autor.getAutor());
                            System.out.println(" Idioma(s): " + autor.getIdioma());
                            System.out.println(" Downloads: " + autor.getDownloads());
                            System.out.println("\n------------------------------------\n");
                        }
                    }
                    break;
                }

                // Lista autores registrados
                case "3" -> {
                    if (autoresSalvos.isEmpty()) {
                        System.out.println("\nNenhum autor foi registrado ainda.\n");
                    } else {
                        for (Autor autor : autoresSalvos) {
                            System.out.println("\n----------AUTOR----------");
                            System.out.println(" Nome: " + autor.getAutor());
                            System.out.println(" Ano de nascimento: " + (autor.getBirthYear() != null ? autor.getBirthYear() : "Desconhecido"));
                            System.out.println(" Ano de Falecimento: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Desconhecido"));
                            System.out.println(" Livros: " + autor.getTitulo());
                            System.out.println("------------------------------------\n");
                        }
                    }
                    break;
                }

                // Lista autores vivos em um determinado ano
                case "4" -> {
                    System.out.print("Digite o ano para buscar autores vivos nesse período: ");
                    int ano = Integer.parseInt(leitura.nextLine());

                    boolean encontrou = false;

                    for (Autor autor : autoresSalvos) {
                        Integer nascimento = autor.getBirthYear();
                        Integer falecimento = autor.getDeathYear();

                        // Verifica se o autor estava vivo no ano informado
                        boolean estavaVivo =
                                (nascimento != null && nascimento <= ano) &&
                                (falecimento == null || falecimento >= ano);

                        if (estavaVivo) {
                            encontrou = true;
                            System.out.println("\n----------AUTOR VIVO EM " + ano + "----------\n");
                            System.out.println(" Autor: " + autor.getTitulo());
                            System.out.println(" Livro: " + autor.getAutor());
                            System.out.println(" Anos Nascimento: " + (nascimento != null ? nascimento : "Desconhecido"));
                            System.out.println(" Ano Falecimento: " + (falecimento != null ? falecimento : "Desconhecido"));
                            System.out.println("\n----------------------------------------------");
                        }
                    }

                    if (!encontrou) {
                        System.out.println("\nNenhum autor registrado estava vivo nesse ano.\n");
                    }

                    break;
                }

                // Lista livros registrados filtrando por idioma
                case "5" -> {
                    System.out.print("Digite o código do idioma (ex: en, pt, fr): ");
                    String idioma = leitura.nextLine().trim().toLowerCase();

                    boolean encontrou = false;

                    for (Autor autor : autoresSalvos) {
                        String idiomasAutor = autor.getIdioma().toLowerCase();

                        if (idiomasAutor.contains(idioma)) {
                            encontrou = true;
                            System.out.println("\n----------REGISTRADO NO IDIOMA '" + idioma + "'----------");
                            System.out.println(" Título: " + autor.getTitulo());
                            System.out.println(" Autor(es): " + autor.getAutor());
                            System.out.println(" Idioma(s): " + autor.getIdioma());
                            System.out.println(" Downloads: " + autor.getDownloads());
                            System.out.println("------------------------------------\n");
                        }
                    }

                    if (!encontrou) {
                        System.out.println("\nNenhum autor registrado possui livros nesse idioma.\n");
                    }

                    break;
                }

                default -> {
                    System.out.println("Opção inválida!");
                }
            }

        } while (true);

        leitura.close(); 
    }
}
