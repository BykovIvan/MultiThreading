package ru.bykov.clients.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@RequiredArgsConstructor
public class BaseClient {
    protected final RestTemplate rest;

    protected <T> ResponseEntity<Object> post(String path, @Nullable Map<String, Object> parameters) {
        return makeAndSendRequest(HttpMethod.POST, path, parameters);
    }

    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path, @Nullable Map<String, Object> parameters) {
        HttpEntity<T> requestEntity = new HttpEntity<>(null);
        ResponseEntity<Object> amountServerResponse;
        try {
            if (parameters != null) {
                amountServerResponse = rest.exchange(path, method, requestEntity, Object.class, parameters);
            } else {
                amountServerResponse = rest.exchange(path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(amountServerResponse);
    }

    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());
        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }
        return responseBuilder.build();
    }

    protected ResponseEntity<Long> get(String path, Map<String, Object> parameters) {
        return makeAndSendRequestForGet(path, parameters);
    }

    private ResponseEntity<Long> makeAndSendRequestForGet(String path, Map<String, Object> parameters) {
        ResponseEntity<Long> statServerResponse = rest.getForEntity(path, Long.class, parameters);
        return prepareGatewayResponseForGet(statServerResponse);
    }

    private static ResponseEntity<Long> prepareGatewayResponseForGet(ResponseEntity<Long> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());
        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }
        return responseBuilder.build();
    }
}
