package com.example.denis.vjetgrouptestapp.data.source;

import com.example.denis.vjetgrouptestapp.data.model.Source;

import java.util.ArrayList;
import java.util.List;

import static com.example.denis.vjetgrouptestapp.data.constants.ApiConstants.MAX_SOURCES;

public class SearchProperties {
    private static final String COMMA_SEP = ",";

    private String sortBy;
    private List<Source> sources;
    private String fromDate;
    private String toDate;
    private int pageSize = 75;
    private int page = 1;

    public SearchProperties() {
        sources = new ArrayList<>();
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Source> getSources() {
        return sources;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getStringOfSources() {
        String str = "";
        for (int i = 0; i < (sources.size() <= MAX_SOURCES ? sources.size() : MAX_SOURCES); i++) {
            str += sources.get(i).getId() + COMMA_SEP;
        }
        return str;
    }
}
