package controller;

public class ParamGenericReplay {
	public String command;
	public String errorMessage;
	public String errorMessageDetail;
	public final static String NO_VALID_COMMAND = "noValidCommand";
	public final static String SHOW_ERROR = "showError";
	
	public ParamGenericReplay(){
		super();
	}

	public ParamGenericReplay(String command, String errorMessage, String errorMessageDetail) {
		this.command = command;
		this.errorMessage = errorMessage;
		this.errorMessageDetail = errorMessageDetail;
	}
}
