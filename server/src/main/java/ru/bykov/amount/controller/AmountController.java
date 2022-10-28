package ru.bykov.amount.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bykov.amount.service.AmountService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/amounts")
public class AmountController {

    private final AmountService amountService;

    @GetMapping()
    public Long getAmountByIdBalance(@RequestParam(value = "id") Integer id) {
        log.info("Получен запрос к энпоинту /amounts, метод GET по id = {}", id);
        return amountService.getAmount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAmountById(@RequestParam(value = "id") Integer id,
                              @RequestParam(value = "value") Long value) {
        log.info("Получен запрос к энпоинту /amounts, метод POST с параметрами id = {}, value = {}", id, value);
        amountService.addAmount(id, value);
    }
}
