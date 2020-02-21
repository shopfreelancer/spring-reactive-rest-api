package com.sf.restapi.client.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResults {
    private Integer totalHits;
    private List<SearchResult> hits = new ArrayList<>();
}
