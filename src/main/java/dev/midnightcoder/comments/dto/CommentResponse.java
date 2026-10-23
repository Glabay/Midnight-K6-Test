package dev.midnightcoder.comments.dto;

public record CommentResponse(
    Long id,
    Long postId,
    String name,
    String email,
    String body
) {}