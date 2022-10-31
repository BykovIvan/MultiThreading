package ru.bykov.amount.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bykov.amount.service.AmountService;
import ru.bykov.amount.service.StatisticService;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/amounts")
public class AmountController {

    private final AmountService amountService;
    private final StatisticService statisticService;

    @GetMapping()
    public Long getAmountByIdBalance(@RequestParam(value = "id") Integer id) {
        log.info("Получен запрос к энпоинту /amounts, метод GET по id = {}", id);
        statisticService.addRequest(id, LocalDateTime.now(), "get");
        return amountService.getAmount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAmountById(@RequestParam(value = "id") Integer id,
                              @RequestParam(value = "value") Long value) {
        log.info("Получен запрос к энпоинту /amounts, метод POST с параметрами id = {}, value = {}", id, value);
        statisticService.addRequest(id, LocalDateTime.now(), "post");
        amountService.addAmount(id, value);
    }

    @GetMapping("/statistic")
    public Long getStatByTime(@RequestParam(value = "time") String time) {
        log.info("Получен запрос к энпоинту /amounts, метод GET получение статистики количество в ", time);
        return statisticService.getCountByTime(time);
    }

    @GetMapping("/statistic/all")
    public Long getCount() {
        log.info("Получен запрос к энпоинту /amounts, метод GET получение статистики количество запросов");
        return statisticService.getCount();
    }

    @DeleteMapping("/statistic")
    public void deleteStat() {
        log.info("Получен запрос к энпоинту /amounts, метод Delete удаление статистики");
        statisticService.deleteStat();
    }
}
