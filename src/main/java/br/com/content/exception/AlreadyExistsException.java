package br.com.content.exception;

import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "Produto %s já cadastrado no sistema.";

	private final String name;

	public AlreadyExistsException(String name) {
		super(String.format(ERROR_MESSAGE, name));
		this.name = name;
	}

	@Override
	public Status getStatus() {
		return Status.ALREADY_EXISTS;
	}

	@Override
	public String getMessage() {
		return String.format(ERROR_MESSAGE, this.name);
	}

}