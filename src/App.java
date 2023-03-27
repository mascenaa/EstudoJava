import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Consumir os dados da API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endr = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endr).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // Selecionar os dados que sejam úteis: title, image, rating
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        
        // Exibir os dados
        for (Map<String,String> filme : listaFilmes) {
            System.out.println(
                "Titulo: " + filme.get("title") + 
                "\r\n" + "Image: " + filme.get("image") + 
                "\r\n" + "Rating: " + filme.get("imDbRating"));
        }     
    }
}
