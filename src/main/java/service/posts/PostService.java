package service.posts;

import domain.accounts.Account;
import domain.accounts.Role;
import domain.boards.Board;
import domain.posts.Post;
import exception.UnauthorizedAccessException;
import exception.boards.BoardNotFoundException;
import exception.posts.PostNotFoundException;
import repository.boards.BoardRepository;
import repository.posts.PostRepository;

public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    public Post addPost(Post post) {
        Post savedPost = postRepository.save(post);
        Board board = boardRepository.findById(savedPost.getBoardId()).orElseGet(() -> {
            new BoardNotFoundException("게시판을 찾을 수 없습니다.");
            return null;
        });

        board.addPost(savedPost);
        return savedPost;
    }

    public void removePost(Long postId, Account account) {
        Post post = postRepository.findById(postId).orElseGet(() -> {
            new PostNotFoundException("게시물을 찾을 수 없습니다.");
            return null;
        });

        if(post.getAccountId() == account.getAccountId() || account.getRole() == Role.ADMIN) {
            postRepository.deleteById(postId);
        } else {
            new UnauthorizedAccessException("삭제 권한이 없습니다.");
        }
    }

    public Post editPost(Long postId, String title, String content, Account account) {
        Post post = postRepository.findById(postId).orElseGet(() -> {
            new PostNotFoundException("게시물을 찾을 수 없습니다.");
            return null;
        });

        if(post.getAccountId() == account.getAccountId() || account.getRole() == Role.ADMIN) {
            post.modifyPost(title, content);
            return post;
        }else {
            new UnauthorizedAccessException("수정 권한이 없습니다.");
            return null;
        }
    }

    public Post viewPost(Long postId) {
        return postRepository.findById(postId).orElseGet(() -> {
            new PostNotFoundException("게시물을 찾을 수 없습니다.");
            return null;
        });
    }
}
