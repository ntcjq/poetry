package com.sea.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/23
 */
@Configuration
public class MeiliSearchConfig {

    /**
     * meilisearch client
     *
     * @return
     */
    @Bean
    public Client client() {
        Config config = new Config("http://localhost:7700/", "1cd055b66817e709c5f33d3bca0efbe304b605a6bcdbcc23df9b0cad839e26ff");
        return new Client(config);
    }

    @Bean
    public Index index() {
        return client().index("poetry");
    }

}
