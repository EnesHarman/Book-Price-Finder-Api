package com.book.price.founder.Core.utilities.result.concretes;

import com.book.price.founder.Core.utilities.result.abstracts.DataResult;

public class SuccessDataResult<T>  extends DataResult<T>{

	public SuccessDataResult() {
		super(true);
	}
	public SuccessDataResult(T data) {
		super(true, data);
	}
	public SuccessDataResult(T data, String message) {
		super(true,message,data);
	}

}
