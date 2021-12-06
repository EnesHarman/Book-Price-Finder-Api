package com.book.price.founder.Core.utilities.result.concretes;

import com.book.price.founder.Core.utilities.result.abstracts.DataResult;

public class ErrorDataResult<T> extends DataResult<T>{
	
	public ErrorDataResult() {
		super(false);
	}
	public ErrorDataResult(T data) {
		super(false, data);
	}
	public ErrorDataResult(T data, String message) {
		super(false,message,data);
	}
}
