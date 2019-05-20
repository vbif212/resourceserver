package com.axmor.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ISSUE_COMMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class IssueComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Status status;

    @JoinColumn
    @ManyToOne(targetEntity = Issue.class)
    private Issue issue;
}
