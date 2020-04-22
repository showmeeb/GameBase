package com.gamebase.general.model;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BytesToDateConverter implements Converter<byte[], Timestamp> {

	@Override
	public Timestamp convert(byte[] source) {
		String value = new String(source);
		return new Timestamp(Long.parseLong(value));
	}

}
