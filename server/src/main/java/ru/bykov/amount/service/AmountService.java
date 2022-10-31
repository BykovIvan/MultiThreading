package ru.bykov.amount.service;

public interface AmountService {
    Long getAmount(Integer id);

    void addAmount(Integer id, Long value);
}
