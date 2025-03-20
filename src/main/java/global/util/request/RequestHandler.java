package global.util.request;

import controller.accounts.AccountController;
import controller.boards.BoardController;
import controller.posts.PostController;
import global.util.Container;
import global.util.filter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            switch (path) {
                case "/boards/add" -> {
                    BoardController boardController = (BoardController) container.get("boardController");
                    if (boardController != null) {
                        boardController.addBoard(request);
                    }
                }
                case "/boards/edit" -> {
                    BoardController boardController = (BoardController) container.get("boardController");
                    if (boardController != null) {
                        String boardId = request.getParam("boardId");
                        if (boardId != null) {
                            boardController.editBoard(request, Long.parseLong(boardId));
                        }
                    }
                }
                case "/boards/remove" -> {
                    BoardController boardController = (BoardController) container.get("boardController");
                    if (boardController != null) {
                        String boardId = request.getParam("boardId");
                        if (boardId != null) {
                            boardController.removeBoard(request, Long.parseLong(boardId));
                        }
                    }
                }
                case "/boards/view" -> {
                    BoardController boardController = (BoardController) container.get("boardController");
                    if (boardController != null) {
                        String boardName = request.getParam("boardName");
                        if (boardName != null) {
                            boardController.viewBoard(request, boardName);
                        }
                    }
                }
                case "/posts/add" -> {
                    PostController postController = (PostController) container.get("postController");
                    if (postController != null) {
                        String boardId = request.getParam("boardId");
                        if (boardId != null) {
                            postController.addPost(request, Long.parseLong(boardId));
                        }
                    }
                }
                case "/posts/remove" -> {
                    PostController postController = (PostController) container.get("postController");
                    if (postController != null) {
                        String postId = request.getParam("postId");
                        if (postId != null) {
                            postController.removePost(request, Long.parseLong(postId));
                        }
                    }
                }
                case "/posts/edit" -> {
                    PostController postController = (PostController) container.get("postController");
                    if (postController != null) {
                        String postId = request.getParam("postId");
                        if (postId != null) {
                            postController.editPost(request, Long.parseLong(postId));
                        }
                    }
                }
                case "/posts/view" -> {
                    PostController postController = (PostController) container.get("postController");
                    if (postController != null) {
                        String postId = request.getParam("postId");
                        if (postId != null) {
                            postController.viewPost(request, Long.parseLong(postId));
                        }
                    }
                }
                case "/accounts/signup" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        accountController.signUp(request);
                    }
                }
                case "/accounts/signin" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        accountController.signIn(request);
                    }
                }
                case "/accounts/signout" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        accountController.signOut(request);
                    }
                }
                case "/accounts/detail" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        String accountId = request.getParam("accountId");
                        if (accountId != null) {
                            accountController.getDetail(request, Long.parseLong(accountId));
                        }
                    }
                }
                case "/accounts/edit" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        String accountId = request.getParam("accountId");
                        if (accountId != null) {
                            accountController.editAccount(request, Long.parseLong(accountId));
                        }
                    }
                }
                case "/accounts/remove" -> {
                    AccountController accountController = (AccountController) container.get("accountController");
                    if (accountController != null) {
                        String accountId = request.getParam("accountId");
                        if (accountId != null) {
                            accountController.removeAccount(request, Long.parseLong(accountId));
                        }
                    }
                }
                default -> System.out.println("인가되지 않은 URL 입니다.");
            }
        }
    }
}
