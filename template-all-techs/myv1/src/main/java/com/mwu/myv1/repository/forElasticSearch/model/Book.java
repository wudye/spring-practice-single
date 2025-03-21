package com.mwu.myv1.repository.forElasticSearch.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "mwu")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String releaseDate;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
