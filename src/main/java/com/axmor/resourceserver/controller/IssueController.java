package com.axmor.resourceserver.controller;

import com.axmor.resourceserver.model.Issue;
import com.axmor.resourceserver.model.IssueComment;
import com.axmor.resourceserver.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class IssueController {

    private final IssueService issueServiceImpl;

    public IssueController(IssueService issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @PostMapping("/issues")
    @ResponseBody
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueServiceImpl.create(issue));
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/issues")
    @ResponseBody
    public ResponseEntity<Iterable<Issue>> getIssues() {
        return ResponseEntity.ok(issueServiceImpl.issues());
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/issues/{id}")
    @ResponseBody
    public ResponseEntity<Issue> getIssue(@PathVariable int id) {
        try {
            return ResponseEntity.ok(issueServiceImpl.read(id));
        } catch (SQLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @DeleteMapping("/issues/{id}")
    @ResponseBody
    public ResponseEntity deleteIssue(@PathVariable int id) {
        issueServiceImpl.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @PutMapping("/issues/{id}")
    @ResponseBody
    public ResponseEntity<Issue> updateIssue(@PathVariable int id, @RequestBody Issue issue) {
        try {
            return ResponseEntity.ok(issueServiceImpl.update(id, issue));
        } catch (SQLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @PostMapping("/issues/{id}/comments")
    @ResponseBody
    public ResponseEntity addComment(@PathVariable int id, @RequestBody IssueComment issueComment) {
        issueServiceImpl.addComment(id, issueComment);
        return ResponseEntity.ok().build();
    }
}
