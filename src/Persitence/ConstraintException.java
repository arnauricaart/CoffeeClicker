package Persitence;

public class ConstraintException extends RuntimeException {
    public ConstraintException(Exception e) {
        super(e.getMessage());
    }
}
