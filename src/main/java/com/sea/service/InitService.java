package com.sea.service;

import com.alibaba.fastjson2.JSON;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.sea.bean.Author;
import com.sea.bean.Poetry;
import com.sea.dao.AuthorRepository;
import com.sea.dao.PoetryRepository;
import com.sea.dto.AuthorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2023/1/12
 */
@Slf4j
@Service
public class InitService {

    @Value("${poetry.filePath}")
    private String filePath;

    @Autowired
    private PoetryRepository poetryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void initPoetry(String fileName) {
        String path = filePath + File.separator + fileName;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String json = "";
        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
            json = ZhConverterUtil.toSimple(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Poetry> poetries = JSON.parseArray(json, Poetry.class);
        log.warn("initPoetry fileName={},size={}", fileName, poetries.size());
        poetryRepository.saveAll(poetries);
    }

    @Transactional
    public void initAuthor(String fileName) {
        String path = filePath + File.separator + fileName;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String json = "";
        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
            json = ZhConverterUtil.toSimple(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<AuthorDto> authors = JSON.parseArray(json, AuthorDto.class);
        List<Author> authorList = new ArrayList<>();
        for (AuthorDto authorDto : authors) {
            Author author = new Author();
            author.setTags("宋");
            author.setName(authorDto.getName());
            author.setProfile(authorDto.getDesc());
            authorList.add(author);
        }
        authorRepository.saveAll(authorList);
    }

}
