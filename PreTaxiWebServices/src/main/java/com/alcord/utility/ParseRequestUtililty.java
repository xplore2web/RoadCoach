package com.alcord.utility;

import java.io.IOException;

import com.alcord.modelmappers.DocumentDataDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseRequestUtililty {

	public static DocumentDataDetail parse(String documentDataDetailString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		DocumentDataDetail documentDataDetail = mapper.readValue(documentDataDetailString, DocumentDataDetail.class);
		return documentDataDetail;
	}

}
