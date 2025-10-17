package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("""
            {
              "match": {
                "firstName": "?0"
              }
            }
            """)
    List<CustomerSearch> findByFirstNameUsingMatch(String firstName);

    @Query("""
            {
                "term": {
                    "nationalId.keyword": {
                      "value": "?0"
                    }
                }
            }
            """)
    List<CustomerSearch> findByNationalId(String natId);

    @Query("""
            {
              "fuzzy": {
                "firstName": {
                  "value": "?0",
                  "fuzziness": "AUTO"
                }
              }
            }
            """)
    List<CustomerSearch> findByFirstNameUsingFuzzy(String misspelledFirstName);

    @Query("""
    {
      "query_string": {
        "query": "*?0*",
        "fields": [
          "firstName",
          "lastName"
        ]
      }
    }
    """)
    List<CustomerSearch> searchWithSmartQuery(String userInput);
}
