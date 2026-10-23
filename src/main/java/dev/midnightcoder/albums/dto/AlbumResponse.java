package dev.midnightcoder.albums.dto;

public record AlbumResponse(
    Long id,
    Long userId,
    String title
) {}