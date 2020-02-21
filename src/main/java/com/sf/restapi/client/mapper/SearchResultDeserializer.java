package com.sf.restapi.client.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sf.restapi.client.model.SearchResultWithDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public class SearchResultDeserializer extends StdDeserializer<SearchResultWithDeserializer> {

    public SearchResultDeserializer() {
        this(null);
    }

    protected SearchResultDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SearchResultWithDeserializer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode searchResultNode = jsonParser.getCodec().readTree(jsonParser);
        SearchResultWithDeserializer searchResult = new SearchResultWithDeserializer();
        searchResult.setId(searchResultNode.get("id").textValue());
        searchResult.setFilename(searchResultNode.get("metadata").get("filename").textValue());
        searchResult.setCreatedTimestamp(searchResultNode.get("metadata").get("assetCreated").get("value").toString());
        return searchResult;
    }
}
