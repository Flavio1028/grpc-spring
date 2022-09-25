package br.com.content.exception;

import io.grpc.Status;

public abstract class BaseBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseBusinessException(String message) {
		super(message);
	}

	public abstract Status getStatus();

	public abstract String getMessage();

}