package com.book.price.founder.Core.utilities.result.abstracts;

public abstract class DataResult<T>extends Result {
	
	private T data;

	public DataResult(boolean success, String message, T data) {
		super(success, message);
		this.data = data;
	}
	
	public DataResult(boolean success, T data) {
		super(success);
		this.data = data;
	}
	
	public DataResult(boolean success) {
		super(success);
	}
	
	public T getData() {
		return this.data;
	}

	
}
