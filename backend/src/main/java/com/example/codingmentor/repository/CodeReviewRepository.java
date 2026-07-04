package com.example.codingmentor.repository;

import com.example.codingmentor.model.CodeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // It will tell to consider this interface as a repository class. It will be used to perform database operations. and communicate with the database .
public interface CodeReviewRepository extends JpaRepository<CodeReview, Long> { // instead of writing manual code to insert , select the datas we can use this
    // Basic Database Operations (CRUD) ellam ithula automatica ready aydum!
}