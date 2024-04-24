package com.sea.service;

import com.alibaba.fastjson2.JSON;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.SearchResultPaginated;
import com.sea.bean.Poetry;
import com.sea.dao.PoetryRepository;
import com.sea.rquest.SearchReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/23
 */
@Service
@Slf4j
public class MeiliSearchService {

    @Autowired
    private Index index;
    @Autowired
    private PoetryRepository poetryRepository;

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

    public void initDocumnets() {
        long start = System.currentTimeMillis();
        int page = 0;//JpaRepository的page从0开始
        int totalPage = 1;
        long totalElements = 0;
        while (page < totalPage) {
            PageRequest pageRequest = PageRequest.of(page, 1000);
            Page<Poetry> all = poetryRepository.findAll(pageRequest);
            List<Poetry> content = all.getContent();
            index.addDocuments(JSON.toJSONString(content));
            totalPage = all.getTotalPages();
            totalElements = all.getTotalElements();
            page++;
        }
        long end = System.currentTimeMillis();
        log.info("initDocumnets totalPage={},totalElements={}", totalPage, totalElements);
        log.info("initDocumnets start={},end={},cost={}", start, end, (end - start) / 1000);
    }

    public void deleteAllDocumnets() {
        index.deleteAllDocuments();
    }

    public void updateFilters(String[] filters) {
        index.updateFilterableAttributesSettings(filters);
    }

    public void updateSorts(String[] sorts) {
        index.updateSortableAttributesSettings(sorts);
    }
}
