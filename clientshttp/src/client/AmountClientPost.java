package client;

import model.ConfigFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmountClientPost extends Thread {
    private String url;
    private ConfigFile configFile;

    public AmountClientPost(String url, ConfigFile configFile) {
        this.url = url;
        this.configFile = configFile;
    }

    @Override
    public void run() {
        int numOfId = (int) Math.floor(Math.random() * configFile.getIdList().size());
        long value = 1 + (long) (Math.random() * (100 - 1));
        post(configFile.getIdList().get(numOfId), value);
    }

    public void post(Integer id, Long value) {
        URI uri = URI.create(url + "/amounts" + "?id=" + id + "&value=" + value);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Код ответа метода Post: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}