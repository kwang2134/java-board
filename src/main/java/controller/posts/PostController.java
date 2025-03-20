package controller.posts;

import domain.accounts.Account;
import domain.posts.Post;
import service.posts.PostService;
import global.util.Session;
import global.util.request.Request;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void addPost(Request request, long boardId) {
        Session session = request.getSession();
        Account authInfo = (Account) session.getAuthInfo();

        Post post = new Post.PostBuilder()
                .title(request.getParam("title"))
                .content(request.getParam("content"))
                .createdBy(authInfo.getName())
                .AccountId(authInfo.getAccountId())
                .boardId(boardId)
                .build();

        postService.addPost(post);

        System.out.println("게시물이 등록되었습니다. 게시물 Id = " + post.getPostId());
    }

    public void removePost(Request request, long postId) {
        Session session = request.getSession();
        Account account = (Account) session.getAuthInfo();

        postService.removePost(postId, account);
        System.out.println("게시물이 삭제되었습니다.");
    }

    public void editPost(Request request, long postId) {
        Session session = request.getSession();
        Account account = (Account) session.getAuthInfo();

        Post post = postService.editPost(postId, request.getParam("title"), request.getParam("content"), account);
        if (post != null) {
            System.out.println("게시물이 수정되었습니다.");
        }
    }

    public void viewPost(Request request, long postId) {
        Post post = postService.viewPost(postId);

        System.out.println("[" + post.getPostId() + "]번 게시물");
        System.out.println("작성일: " + post.getCreatedAt());
        System.out.println("수정일: " + post.getUpdatedAt());
        System.out.println("제목: " + post.getTitle());
        System.out.println("내용: " + post.getContent());
    }
}
