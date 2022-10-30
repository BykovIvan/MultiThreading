package client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmountClient {
    private String url;

    public AmountClient(String url) {
        this.url = url;
    }

    public void post(Integer id, Long value) {
        URI uri = URI.create(url + "?id=" + id + "value=" + value);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Код ответа: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String get(Integer id) {
        String lineOfValue = null;
        URI uri = URI.create(url + "?id=" + id);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            lineOfValue = response.body();
            System.out.println("Код ответа: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return lineOfValue;
    }

}
