package com.axmor.resourceserver.service;

import com.axmor.resourceserver.model.Issue;
import com.axmor.resourceserver.model.IssueComment;

import java.sql.SQLException;

public interface IssueService {
    public Iterable<Issue> issues();
    public Issue create(Issue issue);
    public Issue update(int id, Issue issue) throws SQLException;
    public void delete(int id);
    public Issue read(int id) throws SQLException;
    public void addComment(int id, IssueComment issueComment);
}
