package com.sea.dao;

import com.sea.bean.Poetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2023/1/12
 */
@Repository
public interface PoetryRepository extends JpaRepository<Poetry, Long> {


}
