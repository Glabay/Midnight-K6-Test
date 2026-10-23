package dev.midnightcoder.posts.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
