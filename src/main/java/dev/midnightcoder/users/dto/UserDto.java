package dev.midnightcoder.users.dto;

public record UserDto(
    Long userId,
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