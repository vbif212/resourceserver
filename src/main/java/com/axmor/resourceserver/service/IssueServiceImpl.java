package com.axmor.resourceserver.service;

import com.axmor.resourceserver.model.Issue;
import com.axmor.resourceserver.model.IssueComment;
import com.axmor.resourceserver.model.Status;
import com.axmor.resourceserver.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Iterable<Object> issues() {
        return issueRepository.getIssues();
    }

    public Issue create(Issue issue) {
        issue.setStatus(Status.ADDED);
        issue.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return issueRepository.save(issue);
    }

    public Issue update(int id, Issue issue) throws SQLException {
        Optional<Issue> currentIssueOptional = issueRepository.findById(id);
        if (currentIssueOptional.isPresent()) {
            Issue currentIssue = currentIssueOptional.get();
            currentIssue.setStatus(issue.getStatus());
            return issueRepository.save(currentIssue);
        }
        throw new SQLException();
    }

    public void delete(int id) {
        issueRepository.deleteById(id);
    }

    public Issue read(int id) throws SQLException {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isPresent()) {
            return optionalIssue.get();
        }
        throw new SQLException();
    }

    public IssueComment addComment(int id, IssueComment issueComment) throws SQLException {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();
            issueComment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            issue.getComments().add(issueComment);
            issue.setStatus(issueComment.getStatus());
            issueRepository.save(issue);
            return issueComment;
        }
        throw new SQLException();
    }
}
