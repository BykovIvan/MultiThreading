package ru.bykov.amount.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bykov.amount.exception.BadRequestException;
import ru.bykov.amount.exception.NoUserInHeaderException;
import ru.bykov.amount.exception.NotFoundException;
import ru.bykov.amount.exception.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandler {

    /**
     * Все ситуаций, когда искомый объект не найден, код 404
     * All situations when the desired object is not found
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleIncorrectParameterException(final NotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Ошибки сервера, код 500
     * Error of server
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIncorrectParameterException(final NoUserInHeaderException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Ошибки сервера, код 500
     * Error of server
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIncorrectParameterException(final MissingServletRequestParameterException e) {
        return new ErrorResponse("В параметрах не указан статус!");
    }

    /**
     * Плохой запрос, код 400
     * Bad Request
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(final BadRequestException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Ошибка в запросе, дубликат в email, код 409
     * Error in request
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleIncorrectParameterException(final DataIntegrityViolationException e) {
        return new ErrorResponse("Дубликат!");
    }

    /**
     * Ошибка в запросе, код 400
     * Error in request
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(final Throwable e) {
//        return new ErrorResponse(e.getClass().getName());
        return new ErrorResponse("Плохо составленный запрос! Проверь данные!");
    }
}
