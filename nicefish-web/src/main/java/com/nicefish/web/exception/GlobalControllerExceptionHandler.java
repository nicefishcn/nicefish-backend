package com.nicefish.web.exception;

import com.nicefish.common.constant.ResponseState;
import com.nicefish.common.constant.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by sunye on 17/6/28.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {



    @Value("${debug}")
    private boolean debug;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Result handleException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        StringBuffer bf=new StringBuffer();
        fieldErrors.forEach(e->bf.append(e.getField()).append(", ").append(e.getDefaultMessage()));
        globalErrors.forEach(e->bf.append(e.getObjectName()).append(",").append(e.getDefaultMessage()));
        return new Result(false,debug==true?bf.toString():null, ResponseState.RESPONSE_ILLEGAL_REQUEST.code);
    }


    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Result getErrorMessage(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        StringBuffer bf=new StringBuffer();
        if (mostSpecificCause != null) {
            bf.append(mostSpecificCause.getClass().getName());
            bf.append(mostSpecificCause.getMessage());
            return new Result(false,bf.toString(),null);
        }
        return new Result(false,ex.getMessage(),null);
    }*/




    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    Result getErrorMessage(HttpMediaTypeNotSupportedException ex) {
        StringBuffer bf=new StringBuffer();
        bf.append("Unsupported content type: ").append(ex.getContentType());
        bf.append("Supported content types: ").append(MediaType.toString(ex.getSupportedMediaTypes()));
        return new Result(false,debug==true?bf.toString():null,ResponseState.RESPONSE_UNSUPPORTED_MEDIA_TYPE.code);
    }




    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Result handleException(RuntimeException ex){
        Throwable mostSpecificCause = ex.getCause();
        StringBuffer bf=new StringBuffer();
        if (mostSpecificCause != null) {
            bf.append(mostSpecificCause.getClass().getName());
            bf.append(mostSpecificCause.getMessage());
            return new Result(false,debug==true?bf.toString():null, ResponseState.RESPONSE_SYSTEM_ERROR.code);
        }
        return new Result(false,debug==true?ex.getMessage(): null,ResponseState.RESPONSE_SYSTEM_ERROR.code);
    }




}
