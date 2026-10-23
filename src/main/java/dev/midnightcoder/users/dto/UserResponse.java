package dev.midnightcoder.users.dto;

/**
 * @author Glabay | Glabay-Studios
 * @project midnight-shift
 * @social Discord: Glabay
 * @since 2026-03-15
 */
public record UserResponse(
    Long id,
    String name,
    String username,
    String email,
    Address address,
    String phone,
    String website,
    Company company
) {
    public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        GeoLocation geo
    ) {
        public record GeoLocation(
            String lat,
            String lng
        ) {}
    }

    public record Company(
        String name,
        String catchPhrase,
        String bs
    ) {}
}
