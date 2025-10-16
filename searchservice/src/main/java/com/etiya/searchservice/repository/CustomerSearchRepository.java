package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch, String> {

    @Query("""
            
            {
            "query_string":{
            "query" : "*?0*",
            "fields":["firstName","lastName","nationalId","customerNumber"]
            }
            }
            
            """)
    List<CustomerSearch> searchAllFields(String keyword);
}
