package ru.seppna.sportwebshop_rest.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
//объект для предупреждения об ошибке
public class ErrorMessage {
    //само сообщение на ошибку
    private final String message;
    //статус сообщения
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
