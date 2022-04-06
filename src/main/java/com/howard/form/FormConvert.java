package com.howard.form;

public interface FormConvert<S, T> {
	T convert(S s);
}
