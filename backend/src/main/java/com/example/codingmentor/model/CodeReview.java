package com.example.codingmentor.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // it will tell to consider the mysql table not the java class.
@Table(name = "code_reviews") // It is the table in  database is created in the database it will put name as (code_reviews) fixed.
@Data // lombok library used instead of getters and setters in java.
public class CodeReview {
    @Id // Every sql table needs primary key so we are converting the id to primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // instead of typing manually as id=1,id=2,.. it will be autoincremented during the data is inserted in the table.
    private Long id;

    @Lob
    @Column // Large text - H2 maps @Lob String to CLOB automatically; MySQL uses LONGTEXT via dialect
    private String rawCode;

    @Lob
    @Column // Large text - H2 maps @Lob String to CLOB automatically; MySQL uses LONGTEXT via dialect
    private String reviewFeedback;
}