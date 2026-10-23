package dev.midnightcoder.users;

import dev.midnightcoder.users.dto.UserDto;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
public interface IUserService {

    String getUsernameById(Long id);
    UserDto getUserById(Long id);

}
