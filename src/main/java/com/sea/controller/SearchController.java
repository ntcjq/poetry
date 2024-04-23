package com.sea.controller;

import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.SearchResultPaginated;
import com.sea.rquest.SearchReq;
import com.sea.service.MeiliSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private MeiliSearchService meiliSearchService;

    @RequestMapping("simple")
    public SearchResult simple(String keyword) {
        return meiliSearchService.keywordQuery(keyword);
    }

    @RequestMapping("multiple")
    public SearchResultPaginated multiple(@RequestBody SearchReq req) {
        return meiliSearchService.multipleQuery(req);
    }

}
