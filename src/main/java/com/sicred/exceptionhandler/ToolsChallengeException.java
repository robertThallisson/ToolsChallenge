package com.sicred.exceptionhandler;

public class ToolsChallengeException extends RuntimeException{

	public ToolsChallengeException(String msgUsuario) {
		// TODO Auto-generated constructor stub
		this.msgUsuario = msgUsuario;
	}
	public ToolsChallengeException(String msgUsuario, String msgPadrao) {
		// TODO Auto-generated constructor stub
		this.msgUsuario = msgUsuario;
		this.msgPadrao = msgPadrao;
	}
	public ToolsChallengeException() {
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgUsuario;
	private String msgPadrao;
	public String getMsgPadrao() {
		return msgPadrao;
	}
	public void setMsgPadrao(String msgPadrao) {
		this.msgPadrao = msgPadrao;
	}
	public String getMsgUsuario() {
		return msgUsuario;
	}
	public void setMsgUsuario(String msgUsuario) {
		this.msgUsuario = msgUsuario;
	}
}