package org.immo.servicepublicapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConverterURL {
    String query;
    String encodedQuery;

    public ConverterURL (String query) {
        this.encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEncodedQuery() {
        return encodedQuery;
    }

    public void setEncodedQuery(String encodedQuery) {
        this.encodedQuery = encodedQuery;
    }
}
