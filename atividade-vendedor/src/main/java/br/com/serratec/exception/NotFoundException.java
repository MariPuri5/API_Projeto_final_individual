package br.com.serratec.exception;

//o extends RuntimeException, diferente do extends Exception
//não precisa tratar o exception em tempo de projeto
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
