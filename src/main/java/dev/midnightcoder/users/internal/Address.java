package dev.midnightcoder.users.internal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
@Table(name = "addresses")
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long userId;
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geo", referencedColumnName = "userId")
    private GeoLocation geo;
}
