package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.exception.XException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.transaction.xa.XAException;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(XException.class)
    public ResponseEntity<String> handleXException(XException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
