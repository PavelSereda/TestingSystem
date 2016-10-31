package by.tc.nb.command.exception;

public class CommandException extends Exception{
	private static final long serialVersionUID = 1L;

	public CommandException(Exception e){
		super();
	}
	
	public CommandException(String message){
		super(message);
	}

}
