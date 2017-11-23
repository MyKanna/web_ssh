package ch.shop.user.utils;

public class CodeInvalidateException extends Exception {

	public CodeInvalidateException() {
	}

	public CodeInvalidateException(String message) {
		super(message);
	}

	public CodeInvalidateException(Throwable cause) {
		super(cause);
	}

	public CodeInvalidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeInvalidateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
