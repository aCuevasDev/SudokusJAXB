package com.acuevas.sudokus.exceptions;

@SuppressWarnings("serial")
public class MyException extends Exception {
	public static final int PERSISTANCESTRUCTURECODE = 200;
	public static final int SUDOKUSNOTCORRECT = 202;
	public static final int MARSHALLERERROR = 205;
	public static final int UNMARSHALLERERROR = 201;
	public static final int GETTINGCONTEXTERROR = 203;
	private final int ERRORNOTSUPPORTED = -43254;

	private String errorMessage;

	public MyException(int errorCode) {
		super();
		switch (errorCode) {
		case PERSISTANCESTRUCTURECODE:
			errorMessage = "The structure of the .txt file is not supported.";
			break;
		case SUDOKUSNOTCORRECT:
			errorMessage = "The sudokus inserted are not correct.";
			break;
		case ERRORNOTSUPPORTED:
			errorMessage = "Error not implemented.";
			break;
		default:
			// if the errorCode of the exception is wrong/not implemented.
			errorMessage = "Error not implemented.";
		}
	}

	@Override
	public String getMessage() {
		return errorMessage;
	}

}
