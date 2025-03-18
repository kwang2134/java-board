package util.request;

import controller.accounts.AccountController;
import controller.boards.BoardController;
import controller.posts.PostController;
import util.Container;
import util.filter.*;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private final Container container;
    private FilterChain filterChain;

    public RequestHandler(Container container) {
        this.container = container;
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(new AuthenticationFilter());
        filters.add(new AuthorizationFilter());
        this.filterChain = new FilterChainImpl(filters);
    }

    public void handle(Request request) {
        if(filterChain.doFilter(request)) {
            String path = request.getUrl().split("\\?")[0];

            if (path.equals("/boards/add")) {
                BoardController boardController = (BoardController) container.get("boardController");
                if (boardController != null) {
                    boardController.addBoard(request);
                }
            } else if (path.equals("/boards/edit")) {
                BoardController boardController = (BoardController) container.get("boardController");
                if (boardController != null) {
                    String boardId = request.getParam("boardId");
                    if (boardId != null) {
                        boardController.editBoard(request, Long.parseLong(boardId));
                    }
                }
            } else if (path.equals("/boards/remove")) {
                BoardController boardController = (BoardController) container.get("boardController");
                if (boardController != null) {
                    String boardId = request.getParam("boardId");
                    if (boardId != null) {
                        boardController.removeBoard(request, Long.parseLong(boardId));
                    }
                }
            } else if (path.equals("/boards/view")) {
                BoardController boardController = (BoardController) container.get("boardController");
                if (boardController != null) {
                    String boardName = request.getParam("boardName");
                    if (boardName != null) {
                        boardController.viewBoard(request, boardName);
                    }
                }
            } else if (path.equals("/posts/add")) {
                PostController postController = (PostController) container.get("postController");
                if (postController != null) {
                    String boardId = request.getParam("boardId");
                    if (boardId != null) {
                        postController.addPost(request, Long.parseLong(boardId));
                    }
                }
            } else if (path.equals("/posts/remove")) {
                PostController postController = (PostController) container.get("postController");
                if (postController != null) {
                    String postId = request.getParam("postId");
                    if (postId != null) {
                        postController.removePost(request, Long.parseLong(postId));
                    }
                }
            } else if (path.equals("/posts/edit")) {
                PostController postController = (PostController) container.get("postController");
                if (postController != null) {
                    String postId = request.getParam("postId");
                    if (postId != null) {
                        postController.editPost(request, Long.parseLong(postId));
                    }
                }
            } else if (path.equals("/posts/view")) {
                PostController postController = (PostController) container.get("postController");
                if (postController != null) {
                    String postId = request.getParam("postId");
                    if (postId != null) {
                        postController.viewPost(request, Long.parseLong(postId));
                    }
                }
            } else if (path.equals("/accounts/signup")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    accountController.signUp(request);
                }
            } else if (path.equals("/accounts/signin")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    accountController.signIn(request);
                }
            } else if (path.equals("/accounts/signout")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    accountController.signOut(request);
                }
            } else if (path.equals("/accounts/detail")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    String accountId = request.getParam("accountId");
                    if (accountId != null) {
                        accountController.getDetail(request, Long.parseLong(accountId));
                    }
                }
            } else if (path.equals("/accounts/edit")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    String accountId = request.getParam("accountId");
                    if (accountId != null) {
                        accountController.editAccount(request, Long.parseLong(accountId));
                    }
                }
            } else if (path.equals("/accounts/remove")) {
                AccountController accountController = (AccountController) container.get("accountController");
                if (accountController != null) {
                    String accountId = request.getParam("accountId");
                    if (accountId != null) {
                        accountController.removeAccount(request, Long.parseLong(accountId));
                    }
                }
            } else {
                System.out.println("인가되지 않은 URL 입니다.");
            }
        }
    }
}
