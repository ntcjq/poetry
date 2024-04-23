package com.sea.service;

import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.SearchResultPaginated;
import com.sea.rquest.SearchReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/23
 */
@Service
public class MeiliSearchService {

    @Autowired
    private Index index;

    public SearchResult keywordQuery(String keyword) {
        return index.search(keyword);
    }

    public SearchResultPaginated multipleQuery(SearchReq req) {
        SearchRequest searchRequest = new SearchRequest(req.getKeyword());
        searchRequest.setSort(req.getSort());
        searchRequest.setFilter(req.getFilter());
        searchRequest.setPage(req.getPage());
        searchRequest.setHitsPerPage(req.getSize());
        return (SearchResultPaginated) index.search(searchRequest);

    }
}
