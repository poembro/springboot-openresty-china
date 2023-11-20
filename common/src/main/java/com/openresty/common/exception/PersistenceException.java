package com.openresty.common.exception;

/**
 * @Description: 持久化异常
 * @Author: poembro
 * @Date: 2020-08-14
 */

public class PersistenceException extends RuntimeException {
	public PersistenceException() {
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}