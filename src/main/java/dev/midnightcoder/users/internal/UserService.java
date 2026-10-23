package dev.midnightcoder.users.internal;

import dev.midnightcoder.users.IUserService;
import dev.midnightcoder.users.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */

@Service
@RequiredArgsConstructor
class UserService implements IUserService {
    private final UserRepository userRepository;

    public String getUsernameById(Long id) {
        var response = getUserById(id);
        return response.username();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findByUserId(id)
            .stream()
            .map(this::mapToDto)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
            user.getUserId(),
            user.getName(),
            user.getUsername(),
            user.getEmail(),
            new UserDto.Address(
                user.getAddress().getStreet(),
                user.getAddress().getSuite(),
                user.getAddress().getCity(),
                user.getAddress().getZipcode(),
                new UserDto.Address.GeoLocation(
                    user.getAddress().getGeo().getLat(),
                    user.getAddress().getGeo().getLng()
                )
            ),
            user.getPhone(),
            user.getWebsite(),
            new UserDto.Company(
                user.getCompany().getName(),
                user.getCompany().getCatchPhrase(),
                user.getCompany().getBs()
            )
        );
    }
}
