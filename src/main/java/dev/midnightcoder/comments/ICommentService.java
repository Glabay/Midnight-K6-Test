package dev.midnightcoder.comments;

import dev.midnightcoder.comments.dto.CommentResponse;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-21
 */
public interface ICommentService {
    List<CommentResponse> getCommentsByPostId(Long postId, Integer pageId, Integer size);
}
