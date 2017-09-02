package controller;

public class ParamGenericReplay {
	public String command;
	public String errorMessage;
	public final static String NO_VALID_COMMAND = "noValidCommand";
	public final static String SHOW_ERROR = "showError";
	
	public ParamGenericReplay(){
		super();
	}

	public ParamGenericReplay(String command, String errorMessage) {
		this.command = command;
		this.errorMessage = errorMessage;
	}
}
