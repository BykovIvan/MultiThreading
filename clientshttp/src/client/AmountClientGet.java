package client;

import model.ConfigFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmountClientGet extends Thread {
    private String url;
    private ConfigFile configFile;

    public AmountClientGet(String url, ConfigFile configFile) {
        this.url = url;
        this.configFile = configFile;
    }

    @Override
    public void run() {
        int numOfId = (int) Math.floor(Math.random() * configFile.getIdList().size());
        get(configFile.getIdList().get(numOfId));
    }

    public String get(Integer id) {
        String lineOfValue = null;
        URI uri = URI.create(url + "/amounts" + "?id=" + id);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            lineOfValue = response.body();
            System.out.println("Код ответа метода Get: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return lineOfValue;
    }
}
