package com.book.price.founder.Core.utilities.result.concretes;

import com.book.price.founder.Core.utilities.result.abstracts.Result;

public class ErrorResult extends Result{

	public ErrorResult() {
		super(false);
	}
	
	public ErrorResult(String message) {
		super(false, message);
	}

}
