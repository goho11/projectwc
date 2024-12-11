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
    public String err400(Exception400 e){
        System.out.println("err400");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

    @ExceptionHandler(Exception401.class)
    public String err401(Exception401 e){
        System.out.println("err401");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

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
