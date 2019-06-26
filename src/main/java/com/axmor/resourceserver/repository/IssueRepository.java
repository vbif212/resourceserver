package com.axmor.resourceserver.repository;

import com.axmor.resourceserver.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer> {

    @Query("select i.id, i.name, i.status, i.timestamp from Issue i")
    Iterable<Object> getIssues();
}
