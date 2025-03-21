package com.mwu.myv1.repository.forElasticSearch;

import com.mwu.myv1.repository.forElasticSearch.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Long> {


    Page<Book> findByAuthor(String author, Pageable pageable);
    List<Book> findByTitle(String title);
}
