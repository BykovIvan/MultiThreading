package ru.bykov.clients.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import ru.bykov.clients.client.AmountClient;

@Scope("prototype")
@Slf4j
public class PostRequestService implements Runnable {
    private final AmountClient amountClient;
    private final int[] ids;

    public PostRequestService(AmountClient amountClient, int[] ids) {
        this.amountClient = amountClient;
        this.ids = ids;
    }

    @Override
    public void run() {
        log.info("start thread post");
//        Integer[] arrayIds = new Integer[ids.length];
//        for (int i = 0; i < ids.length; i++) {
//            arrayIds[i] = Integer.valueOf(ids[i]);
//        }
        int numOfId = (int)Math.floor(Math.random() * ids.length);
        long value = 1 + (long) (Math.random() * (100 - 1));
        amountClient.create(ids[numOfId], value);
    }
}
