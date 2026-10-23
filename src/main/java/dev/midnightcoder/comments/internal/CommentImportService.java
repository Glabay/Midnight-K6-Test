package dev.midnightcoder.comments.internal;

import dev.midnightcoder.comments.ICommentImportService;
import dev.midnightcoder.comments.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentImportService implements ICommentImportService {
    private final RestClient restClient;
    private final CommentRepository commentRepository;

    @Override
    public void importComments() {
        var response = restClient.get()
            .uri("/comments")
            .retrieve()
            .body(new ParameterizedTypeReference<List<CommentResponse>>() {});
        if (response == null || response.isEmpty()) {
            return;
        }

        var comments = response.stream()
            .map(comment -> {
                var model = new Comment();
                    model.setId(comment.id());
                    model.setPostId(comment.postId());
                    model.setName(comment.name());
                    model.setEmail(comment.email());
                    model.setBody(comment.body());
                return model;
            })
            .toList();

        commentRepository.saveAll(comments);
    }
}