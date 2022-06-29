package com.example.Authenticator.exception;

public class SessionExpiredException extends Exception {

	private static final long serialVersionUID = 3141389155141543456L;

	public SessionExpiredException() {
		super();
	}

	public SessionExpiredException(final String message) {
		super(message);
	}
}
