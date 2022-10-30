package ru.bykov.clients.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
@Scope("prototype")
public class AmountClient extends BaseClient {

    private static final String API_PREFIX = "/amounts";

    @Autowired
    public AmountClient(@Value("${server-amount.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void create(Integer id, Long value) {
        Map<String, Object> parameters = Map.of(
                "id", id,
                "value", value
        );
        post("?id={id}&value={value}", parameters);
    }

    public ResponseEntity<Long> findValueById(Integer id) {
        Map<String, Object> parameters = Map.of(
                "id", id
        );
        return get("?id={id}", parameters);
    }
}
