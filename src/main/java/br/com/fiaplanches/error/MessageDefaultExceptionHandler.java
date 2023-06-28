package br.com.fiaplanches.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDefaultExceptionHandler {
    public List<String> exception;
    public Integer statusCode;
    public String descriptionStatusCode;
    public Date date;

    public MessageDefaultExceptionHandler(String exception, Integer statusCode, String descriptionStatusCode, Date date) {
        this.exception = List.of(exception);
        this.statusCode = statusCode;
        this.descriptionStatusCode = descriptionStatusCode;
        this.date = date;
    }
}
