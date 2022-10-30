package ru.bykov.clients.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.bykov.clients.client.AmountClient;

@Component
@Slf4j
@PropertySource("classpath:application.properties")
public class AmountService {
    private final int rCount;
    private final int wCount;
    private final AmountClient amountClient;


    private int[] ids;

    public AmountService(AmountClient amountClient,
                         @Value("${rCount}") int rCount,
                         @Value("${wCount}") int wCount,
                         @Value("${idList}") int[] ids) {
        this.amountClient = amountClient;
        this.rCount = rCount;
        this.wCount = wCount;
        this.ids = ids;
//        start();
    }

    public int setrCount() {
        return rCount;
    }

    public int setwCount() {
        return wCount;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getrCount() {
        return rCount;
    }

    public int getwCount() {
        return wCount;
    }

    public int[] getIds() {
        return ids;
    }

    public void start() {
        for (int i = 0; i < rCount; i++) {
            log.info("lets go i = {}", i);
            GetRequestService getRequestService = new GetRequestService(amountClient, ids);
            getRequestService.run();
        }

        for (int j = 0; j < wCount; j++) {
            log.info("lets go j = {}", j);
            PostRequestService postRequestService = new PostRequestService(amountClient, ids);
            postRequestService.run();
        }
    }
}
