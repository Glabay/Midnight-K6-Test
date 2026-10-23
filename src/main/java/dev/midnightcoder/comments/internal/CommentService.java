package dev.midnightcoder.comments.internal;

import dev.midnightcoder.comments.ICommentService;
import dev.midnightcoder.comments.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CommentService implements ICommentService {
    private final CommentRepository commentRepository;

    public List<CommentResponse> getCommentsByPostId(Long postId, Integer pageId, Integer size) {
        var pageable = Pageable.ofSize(size).withPage(pageId);
        return commentRepository.findByPostId(postId, pageable)
            .stream()
            .map(this::toCommentResponse)
            .toList();
    }

    private CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getPostId(),
            comment.getName(),
            comment.getEmail(),
            comment.getBody()
        );
    }


}