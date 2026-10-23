package dev.midnightcoder.photos.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}