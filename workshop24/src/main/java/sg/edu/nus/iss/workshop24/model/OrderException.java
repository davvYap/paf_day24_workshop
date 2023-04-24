package sg.edu.nus.iss.workshop24.model;

public class OrderException extends Exception {

    public OrderException(String message) {
        super(message);
    }

    public OrderException() {
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }

}
