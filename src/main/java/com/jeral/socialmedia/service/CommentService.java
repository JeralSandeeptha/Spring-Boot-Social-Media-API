package com.jeral.socialmedia.service;

import com.jeral.socialmedia.dto.request.CommentRequestDTO;
import com.jeral.socialmedia.dto.response.CommentResponseDTO;
import com.jeral.socialmedia.model.Comment;

import java.util.List;

public interface CommentService {

    public Comment createComment(Long postId, Long userId, CommentRequestDTO commentRequestDTO);
    public List<Comment> getAllComments();
    public CommentResponseDTO getComment(Long id);
    public Comment updateComment(Long id, CommentRequestDTO commentRequestDTO);
    public void deleteComment(Long id);
    public List<Comment> getAllCommentsByPostId(Long postId);
}
