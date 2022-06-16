package com.kcar.adminpage.handler;

import com.kcar.adminpage.controller.dto.ResponseDto;
import com.kcar.adminpage.handler.ex.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseDto<String> handlerArgumentException(CustomValidationException e){
        return new ResponseDto<String >(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
