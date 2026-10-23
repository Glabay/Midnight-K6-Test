package dev.midnightcoder.posts.internal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findByUserId(Long userId, Pageable pageable);

    Long countPostByUserId(Long userId);
}
