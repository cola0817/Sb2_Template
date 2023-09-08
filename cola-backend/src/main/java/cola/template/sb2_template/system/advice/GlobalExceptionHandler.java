package cola.template.sb2_template.system.advice;

import cola.template.sb2_template.system.entity.Result;
import cola.template.sb2_template.system.exception.ColaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author Cola0817
 * @date 2023/9/8 15:36
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ColaException.class)
    public Result<Void> handleColaException(ColaException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
