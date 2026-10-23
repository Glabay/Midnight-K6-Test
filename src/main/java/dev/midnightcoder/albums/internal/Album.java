package dev.midnightcoder.albums.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "albums")
public class Album {
    @Id
    private Long id;
    private Long userId;
    private String title;
}