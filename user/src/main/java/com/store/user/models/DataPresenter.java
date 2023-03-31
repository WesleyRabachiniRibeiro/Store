package com.store.user.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DataPresenter<T> {
    private T data;
    private Integer statusCode;
    private LocalDateTime timestamp;

    public DataPresenter(T data) {
        this.data = data;
        this.statusCode = HttpStatus.OK.value();
        this.timestamp = LocalDateTime.now();
    }
}
