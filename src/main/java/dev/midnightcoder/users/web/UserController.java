package dev.midnightcoder.users.web;

import dev.midnightcoder.users.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    @GetMapping(value = "/{id}/username", produces = "text/plain")
    public String getUsernameById(@PathVariable Long id) {
        var username = userService.getUsernameById(id);
        return "<a href=\"/user/%s\">@%s</a>".formatted(id, username);
    }
}
