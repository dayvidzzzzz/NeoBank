package dy.system.minebanconovo.exception;

import dy.system.minebanconovo.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        log.warn("Recurso não encontrado: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOperation(InvalidOperationException ex) {
        log.warn("Operação inválida: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalance(InsufficientBalanceException ex) {
        log.warn("Saldo insuficiente: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException ex) {
        log.warn("Conta não encontrada: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedTransaction(UnauthorizedTransactionException ex) {
        log.warn("Transação não autorizada: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCpf(InvalidCpfException ex) {
        log.warn("CPF inválido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handleExpiredToken(ExpiredTokenException ex) {
        log.warn("Token expirado: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(DatabaseConstraintException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseConstraint(DatabaseConstraintException ex) {
        log.error("Erro de integridade no banco: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, "Erro de integridade de dados");
    }

    @ExceptionHandler(InvalidTransactionStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransactionState(InvalidTransactionStateException ex) {
        log.warn("Estado de transação inválido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EmptyRequestException.class)
    public ResponseEntity<ErrorResponse> EmptyRequest(InvalidTransactionStateException ex) {
        log.warn("Estado de transação inválido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DailyLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleDailyLimitExceeded(DailyLimitExceededException ex) {
        log.warn("Limite diário excedido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAddress(InvalidAddressException ex) {
        log.warn("Endereço inválido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.warn("Erro de validação: {}", fieldErrors);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Erro de validação: " + fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Parâmetro inválido '%s' - valor recebido: %s",
                ex.getName(), ex.getValue());
        log.warn(message);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Corpo da requisição inválido: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Formato do JSON inválido");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Erro interno não tratado", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno no servidor");
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return ResponseEntity.status(status).body(error);
    }
}