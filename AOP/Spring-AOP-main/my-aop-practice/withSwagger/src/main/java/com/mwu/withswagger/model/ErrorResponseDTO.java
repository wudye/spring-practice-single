package com.mwu.withswagger.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
	
	private Date timestamp;
	private int  status;
	private String error;
	private String message;
	private String path;
//	public ErrorResponseDTO(Date timestamp, int status, String error, String message, String path) {
//		super();
//		this.timestamp = timestamp;
//		this.status = status;
//		this.error = error;
//		this.message = message;
//		this.path = path;
//	}
//	public Date getTimestamp() {
//		return timestamp;
//	}
//	public void setTimestamp(Date timestamp) {
//		this.timestamp = timestamp;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
//	public String getError() {
//		return error;
//	}
//	public void setError(String error) {
//		this.error = error;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public String getPath() {
//		return path;
//	}
//	public void setPath(String path) {
//		this.path = path;
//	}

}