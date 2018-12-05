package com.acuevas.sudokus.exceptions;

/**
 * This exception occurs when a critical error happens and the application can't
 * continue, usually related to problems when saving/loading data.
 * 
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class CriticalException extends Exception {

	private StructErrors error;

	/**
	 * Various types of errors
	 * 
	 * @author Alex
	 *
	 */
	public enum StructErrors {

		// @formatter:off
		PERSISTANCE_STRUCTURE("The structure of the .txt file is not supported."),
		SUDOKUES_NOT_CORRECT("The sudokus inserted are not correct."),
		MARSHALLER_ERROR("Error getting the Marshaller."),
		UNMARSHALLER_ERROR("Error getting the UnMarshaller."),
		GETTING_CONTEXT_ERROR("Error getting the context."),
		FILE_NOT_FOUND("Error, the program couldn't find the file."),
		CRITICAL_FAILURE("A critical error happened, the program will restart now."), BUFFER_NOT_CLOSABLE("Buffer cannot close.");
		// @formatter:on

		private String message;

		private StructErrors(String message) {
			this.message = message;
		}
	};

	public CriticalException(StructErrors error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		return error.message;
	}

}
