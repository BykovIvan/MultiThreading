package ru.bykov.amount.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Statistic {
    private Integer id;
    private LocalDateTime time;
    private String request;
}
