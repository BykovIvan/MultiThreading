package ru.bykov.amount.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bykov.amount.exception.BadRequestException;
import ru.bykov.amount.model.Statistic;
import ru.bykov.amount.service.StatisticService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private Long idOfStat = 0L;
    private Long countByTime = 0L;
    Map<Long, Statistic> statisticMap = new HashMap<>();

    @Override
    public void addRequest(Integer id, LocalDateTime timeOfRequest, String request) {
        idOfStat++;
        statisticMap.put(idOfStat, new Statistic(id, timeOfRequest, request));

    }

    @Override
    public Long getCountByTime(String time) {
        int size = statisticMap.size();
        if (size > 1) {
            Statistic first = statisticMap.get(1L);
            Statistic last = statisticMap.get(idOfStat);
            Duration duration = Duration.between(first.getTime(), last.getTime());
            switch (time) {
                case "millis":
                    countByTime = size / duration.toMillis();
                    break;
                case "sec":
                    countByTime = size / duration.toSeconds();
                    break;
                case "min":
                    countByTime = size / duration.toMinutes();
                    break;
                case "hour":
                    countByTime = size / duration.toHours();
                    break;
                case "day":
                    countByTime = size / duration.toDays();
                    break;
                default:
                    throw new BadRequestException("Такого параметра не существует, попробуйте из предложенных: millis, sec, min, hour, day");
            }
        }
        return countByTime;
    }

    @Override
    public Long getCount() {
        return idOfStat;
    }

    @Override
    public void deleteStat() {
        idOfStat = 0L;
        countByTime = 0L;
        statisticMap.clear();
    }
}
