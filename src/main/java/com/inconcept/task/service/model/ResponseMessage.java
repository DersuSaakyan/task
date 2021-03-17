package com.inconcept.task.service.model;

public class ResponseMessage {

	private String message;
	private String fileDownloadUri;
	

	  public ResponseMessage(String message, String fileDownloadUri) {
	    this.message = message;
	    this.fileDownloadUri = fileDownloadUri;
	  }

	public ResponseMessage(String message) {
		this.message = message;
	}

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

}
