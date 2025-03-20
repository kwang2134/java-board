package global.config;

import controller.accounts.AccountController;
import controller.boards.BoardController;
import controller.posts.PostController;
import global.handler.CommandProcessor;
import repository.accounts.AccountRepository;
import repository.accounts.AccountRepositoryImpl;
import repository.boards.BoardRepository;
import repository.boards.BoardRepositoryImpl;
import repository.posts.PostRepository;
import repository.posts.PostRepositoryImpl;
import service.accounts.AccountService;
import service.boards.BoardService;
import service.posts.PostService;
import global.util.Container;

public class MemoryApplication {
    public static void run(String[] args) {
        System.out.println("MemoryApplication.run() start");
        Container container = Container.getInstance();

        if (container == null) {
            System.out.println("Container 초기화 실패");
            return;
        }

        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        container.register("accountRepository", accountRepository);

        BoardRepositoryImpl BoardRepository = new BoardRepositoryImpl();
        container.register("boardRepository", BoardRepository);

        PostRepositoryImpl postRepository = new PostRepositoryImpl();
        container.register("postRepository", postRepository);

        AccountService accountService = new AccountService((AccountRepository) container.get("accountRepository"));
        container.register("accountService", accountService);

        BoardService boardService = new BoardService((BoardRepository) container.get("boardRepository"));
        container.register("boardService", boardService);

        PostService postService = new PostService((PostRepository) container.get("postRepository"), (BoardRepository) container.get("boardRepository"));
        container.register("postService", postService);

        AccountController accountController = new AccountController(accountService);
        container.register("accountController", accountController);

        BoardController boardController = new BoardController(boardService);
        container.register("boardController", boardController);

        PostController postController = new PostController(postService);
        container.register("postController", postController);

        CommandProcessor processor = new CommandProcessor(container);
        processor.start();
    }
}
