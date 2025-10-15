package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch, String> {

}
