package com.sf.restapi.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SearchResultMetadata {
    private String fileType;
    private String filename;
}
