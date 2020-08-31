package br.com.kleber.gestaopessoaapi.exception;

import br.com.kleber.gestaopessoaapi.viewmodel.exception.ExceptionVM;
import java.sql.SQLSyntaxErrorException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //**********************************//
    //Global
    //**********************************//
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionVM("O sistema se encontra indisponível."));
    }

    //**********************************//
    //Outros
    //**********************************//
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleError(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVM(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleError(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVM(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Set<ExceptionVM>> handleError(MethodArgumentNotValidException ex) {
        final Set<ExceptionVM> erros = ex.getBindingResult().getAllErrors().stream().map(c -> new ExceptionVM(c.getDefaultMessage())).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatusError.ARGUMENTO_INVALIDO).body(erros);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleError(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVM(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleError(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVM(ex.getMessage()));
    }

    //**********************************//
    //SQL - BD
    //**********************************//
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Set<ExceptionVM>> handleError(TransactionSystemException tse) {
        if (tse.getCause() != null && tse.getCause() instanceof RollbackException) {
            final RollbackException re = (RollbackException) tse.getCause();

            if (re.getCause() != null && re.getCause() instanceof ConstraintViolationException) {
                return handleError((ConstraintViolationException) re.getCause());
            }
        }
        throw tse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<ExceptionVM>> handleError(ConstraintViolationException ex) {
        final Set<ExceptionVM> erros = ex.getConstraintViolations().stream().map(c -> new ExceptionVM(c.getMessage())).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity handleError(InvalidDataAccessResourceUsageException ex) {
        if (ex.getCause() != null && ex.getCause() instanceof SQLGrammarException) {
            return handleError((SQLGrammarException) ex.getCause());
        }
        throw ex;
    }

    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity handleError(SQLGrammarException ex) {
        if (ex.getCause() != null && ex.getCause() instanceof SQLSyntaxErrorException) {
            return handleError((SQLSyntaxErrorException) ex.getCause());
        }
        throw ex;
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity handleError(SQLSyntaxErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVM(ex.getMessage()));
    }

}
