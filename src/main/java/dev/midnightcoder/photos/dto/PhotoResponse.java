package dev.midnightcoder.photos.dto;

public record PhotoResponse(
    Long id,
    Long albumId,
    String title,
    String url,
    String thumbnailUrl
) {}