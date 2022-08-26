package com.example.banco.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger= Logger.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler //Manejador global en caso de que no recaiga en ninguna otra excepcion
    @ResponseBody
    public ErrorMessage handleAllErrors(Exception ex, HttpServletRequest req){
        return createAndlogErrorMessage(req.getRequestURI(),ex.getMessage(), ex.getStackTrace(),
                "Error Interno del Servidor, contacte con el administrador");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ErrorMessage handleNotFoundException(HttpServletRequest req,Exception ex){
        return createErrorMessage(req.getRequestURI(),ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidIdException.class,ClientIllegalArgumentException.class})
    @ResponseBody
    public ErrorMessage handleInvalidIdException(HttpServletRequest req,Exception ex){
        return createErrorMessage(req.getRequestURI(),ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DateTimeParseException.class})
    @ResponseBody
    public ErrorMessage handleInvalidDateException(HttpServletRequest req,Exception ex){
        return createErrorMessage(req.getRequestURI(),"La fecha no fue enviada con el formato apropiado de año-mes-dia. Ejemplo: 1997-11-28");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorMessage handleValidationExceptions(
            HttpServletRequest req,
            MethodArgumentNotValidException ex) {
        StringBuilder sb= new StringBuilder();
        sb.append("Errores de Validación: ");
        ex.getBindingResult().getAllErrors().forEach((error) ->
        {
            sb.append(((FieldError) error).getField())
            .append(": ")
            .append(error.getDefaultMessage())
            .append(", ");
        });
        sb.deleteCharAt(sb.length()-1);
        return createErrorMessage(req.getRequestURI(),sb.toString());
    }



    private ErrorMessage createErrorMessage(String uri, String errorMessage){
        logger.error("Error en la URI: "+uri+" Error: "+errorMessage);
        return new ErrorMessage(uri,errorMessage);
    }

    private ErrorMessage createAndlogErrorMessage(String uri, String errorMessage, StackTraceElement[] stackTrace,String messageForClient){
        StringBuilder st = new StringBuilder("Error en la URI: "+uri+" Error: "+errorMessage+" StackTrace: ");
        Arrays.stream(stackTrace).forEach(
                (e)-> st.append(e.toString()).append("\n")
        );
        logger.error(st);
        return new ErrorMessage(uri,messageForClient);
    }


}
