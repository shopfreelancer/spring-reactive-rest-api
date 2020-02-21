package com.sf.restapi.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sf.restapi.client.mapper.SearchResultDeserializer;
import lombok.Data;

@JsonDeserialize(using = SearchResultDeserializer.class)
@Data
public class SearchResultWithDeserializer {
    private String id;
    private String filename;
    private String createdTimestamp;
}
