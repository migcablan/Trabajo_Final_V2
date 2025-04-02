package dacd.cabeza.exception;

public class XoteloApiException extends Exception {
	private final int statusCode;

	public XoteloApiException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() { return statusCode; }
}
