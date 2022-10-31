package ru.bykov.amount.service;

import java.time.LocalDateTime;

public interface StatisticService {
    void addRequest(Integer id, LocalDateTime now, String request);

    Long getCountByTime(String time);

    Long getCount();

    void deleteStat();
}
