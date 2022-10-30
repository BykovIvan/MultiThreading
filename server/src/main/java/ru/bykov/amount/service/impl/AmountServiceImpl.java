package ru.bykov.amount.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bykov.amount.model.Amount;
import ru.bykov.amount.repository.AmountRepository;
import ru.bykov.amount.service.AmountService;

@Service
@RequiredArgsConstructor
public class AmountServiceImpl implements AmountService {

    private final AmountRepository amountRepository;

    @Override
    @Cacheable(value = "amounts", key = "#id")
    @Transactional
    public Long getAmount(Integer id) {
        if (amountRepository.findById(id).isEmpty()) return 0L;
        return amountRepository.getAmountById(id);
    }

    @Override
    @CacheEvict(value = "amounts", key = "#id")
    @Transactional
    public void addAmount(Integer id, Long value) {
        if (amountRepository.findById(id).isEmpty()) {
            amountRepository.save(Amount.builder().id(id).value(value).build());
        } else {
            amountRepository.setValueById(id, value);
        }

    }
}
