package ru.bykov.amount.model.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class AmountDto {
    @NotNull
    @NotBlank
    Long value;
}
