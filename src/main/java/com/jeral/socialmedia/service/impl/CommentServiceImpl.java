package com.jeral.socialmedia.service.impl;

import com.jeral.socialmedia.dto.request.CommentRequestDTO;
import com.jeral.socialmedia.dto.response.CommentResponseDTO;
import com.jeral.socialmedia.exceptions.NotFoundException;
import com.jeral.socialmedia.model.Comment;
import com.jeral.socialmedia.repo.CommentRepo;
import com.jeral.socialmedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment createComment(Long postId, Long userId, CommentRequestDTO commentRequestDTO) {
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits();
        Comment comment = Comment.builder()
                .commentId(id)
                .comment(commentRequestDTO.getComment())
                .postId(postId)
                .userId(userId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        commentRepo.save(comment);
        return comment;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> posts = commentRepo.findAll();
        return posts;
    }

    @Override
    public CommentResponseDTO getComment(Long id) {
        Optional<Comment> selectedComment = commentRepo.findById(id);
        if (selectedComment.isEmpty()) {
            throw new NotFoundException("Comment id not found");
        }else {
            Comment comment = selectedComment.get();
            CommentResponseDTO commentResponseDTO = CommentResponseDTO.builder()
                    .commentId(comment.getCommentId())
                    .comment(comment.getComment())
                    .postId(comment.getPostId())
                    .userId(comment.getUserId())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .build();
            return commentResponseDTO;
        }
    }

    @Override
    public Comment updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        Optional<Comment> selectedComment = commentRepo.findById(id);
        if (selectedComment.isEmpty()) {
            throw new NotFoundException("Comment id not found");
        }else {
            Comment comment = selectedComment.get();
            comment.setUpdatedAt(new Date());
            comment.setComment(comment.getComment());
            commentRepo.save(comment);
            return comment;
        }
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> selectedComment = commentRepo.findById(id);
        if (selectedComment.isEmpty()) {
            throw new NotFoundException("Comment id not found");
        }else {
            commentRepo.deleteById(id);
        }
    }

    @Override
    public List<Comment> getAllCommentsByPostId(Long postId) {
        List<Comment> posts = commentRepo.findByPostId(postId);
        return posts;
    }
}
