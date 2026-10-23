package dev.midnightcoder.comments.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private Long id;
    private Long postId;
    private String name;
    private String email;

    @Column(length = 500)
    private String body;
}