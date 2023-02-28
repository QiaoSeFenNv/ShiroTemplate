package com.qiaose.exception;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.qiaose.entity.Result;
import com.qiaose.entity.enums.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest req, Exception e){
        return Result.error(ResultMsgEnum.FAIL.getCode(),e.getMessage());
    }

    /**
     * 针对Vild捕获异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<?> resolveConstraintViolationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            return  Result.error(ResultMsgEnum.FAIL.getCode(), errorMessage);
        }
        return Result.error(ResultMsgEnum.FAIL.getCode(),ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<?> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return  Result.error(ResultMsgEnum.FAIL.getCode(), errorMessage);
        }
        return Result.error(ResultMsgEnum.FAIL.getCode(),ex.getMessage());
    }
}
