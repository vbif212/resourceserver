package com.axmor.resourceserver.service;

import com.axmor.resourceserver.model.Issue;
import com.axmor.resourceserver.model.IssueComment;

import java.sql.SQLException;

public interface IssueService {
    Iterable<Object> issues();
    Issue create(Issue issue);
    void delete(int id);
    Issue read(int id) throws SQLException;
    IssueComment addComment(int id, IssueComment issueComment) throws SQLException;
}
