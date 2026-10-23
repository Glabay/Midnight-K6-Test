package dev.midnightcoder.albums.internal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByUserId(Long userId, Pageable pageable);
}