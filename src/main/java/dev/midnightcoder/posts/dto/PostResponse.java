package dev.midnightcoder.posts.dto;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
public record PostResponse(
    Long id,
    Long userId,
    String title,
    String body
) {}