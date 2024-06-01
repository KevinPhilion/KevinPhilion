package exceptions;

public class InsufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
		super("insufficient funds! cannot withdraw this amount!");
	}
	
	
    
}
