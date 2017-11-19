package com.learning.converter;

import static com.learning.constant.GlobalConstants.APPLICATION_CSV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import com.learning.model.User;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Component
public class CsvHttpMessageConverter implements HttpMessageConverter<Iterator<User>> {

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return Iterator.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return Iterator.class.isAssignableFrom(clazz);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.valueOf(APPLICATION_CSV));
		return mediaTypes;
	}

	@Override
	public Iterator<User> read(Class<? extends Iterator<User>> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	public void write(Iterator<User> t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		outputMessage.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType.toString());
		outputMessage.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.csv");
		BufferedWriter buff = null;
		buff = new BufferedWriter(new OutputStreamWriter(outputMessage.getBody()));
		StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(buff)
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
		while (t.hasNext()) {
			try {
				beanToCsv.write(t.next());
			} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
		}
		buff.close();

	}

}
