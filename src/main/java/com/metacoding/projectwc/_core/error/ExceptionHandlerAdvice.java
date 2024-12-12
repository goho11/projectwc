package com.metacoding.projectwc._core.error;

import com.metacoding.projectwc._core.error.ex.*;
import com.metacoding.projectwc._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(APIException400.class)
    public ResponseEntity<?> apiErr400(APIException400 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(APIException401.class)
    public ResponseEntity<?> apiErr401(APIException401 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(APIException403.class)
    public ResponseEntity<?> apiErr403(APIException403 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(APIException404.class)
    public ResponseEntity<?> apiErr404(APIException404 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException500.class)
    public ResponseEntity<?> apiErr500(APIException500 e) {
        return new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        System.out.println("err400");
        String body = """
                <script>
                    alert('${message}');
                    history.back();
                </script>
                """.replace("${message}", e.getMessage());

        return body;
    }

    @ExceptionHandler(Exception401.class)
    public String err401(Exception401 e) {
        System.out.println("err401");
        String body = """
                <script>
                    alert('${message}');
                    history.back();
                </script>
                """.replace("${message}", e.getMessage());

        return body;
    }

    @ExceptionHandler(Exception403.class)
    public String err403(Exception403 e){
        System.out.println("err403");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e){
        System.out.println("err404");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

    public String err500(Exception500 e){
        System.out.println("err500");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }
}
