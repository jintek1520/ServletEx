package kr.co.domain;

public class CommandAction {

	private boolean isRedirect;
	private String Where;
	
	public CommandAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CommandAction(boolean isRedirect, String where) {
		super();
		this.isRedirect = isRedirect;
		Where = where;
	}


	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getWhere() {
		return Where;
	}
	public void setWhere(String where) {
		Where = where;
	}
	
	
}
