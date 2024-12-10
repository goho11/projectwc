package com.metacoding.projectwc._core.error;

import com.metacoding.projectwc._core.error.ex.*;
import com.metacoding.projectwc._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> err400(Exception400 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> err401(Exception401 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> err403(Exception403 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> err404(Exception404 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> err500(Exception500 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
