package persistence.persistenceExceptions;

public class ConstraintException extends RuntimeException {
    public ConstraintException(Exception e) {
        super(e.getMessage());
    }
}
