package com.sf.restapi.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private String id;

    private String filename;
    private String createdTimestamp;

    @SuppressWarnings("unchecked")
    @JsonProperty("metadata")
    private void unpackNested(Map<String,Object> metadata) {
        this.filename = (String) metadata.get("filename");
        Map<String,Object> assetCreated = (Map<String,Object>) metadata.get("assetCreated");
        this.createdTimestamp = String.valueOf(assetCreated.get("value"));
    }
}
