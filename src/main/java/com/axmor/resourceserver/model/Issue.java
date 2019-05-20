package com.axmor.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "ISSUES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Timestamp timestamp;
    @Column(nullable = false)
    private String description;
    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = IssueComment.class)
    private Set<IssueComment> comments;
}
