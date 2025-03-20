package global.handler;

import domain.accounts.Account;
import domain.boards.Board;
import domain.posts.Post;
import repository.accounts.AccountRepository;
import repository.boards.BoardRepository;
import repository.posts.PostRepository;
import global.util.Container;

public class InitTest {

    private  Container container;
    private static AccountRepository accountRepository;
    private static BoardRepository boardRepository;
    private static PostRepository postRepository;

    public InitTest(){
        container = Container.getInstance();
        accountRepository = (AccountRepository) container.get("accountRepository");
        boardRepository = (BoardRepository) container.get("boardRepository");
        postRepository = (PostRepository) container.get("postRepository");
    }

    public static void init(){
        for (int i = 1; i <= 5; i++) {
            Account account = new Account.AccountBuilder()
                    .loginAccount("account" + i)
                    .password("password" + i)
                    .name("name" + i)
                    .email("email" + i)
                    .build();

            if (i == 1) {
                account.setAdmin();
            }

            accountRepository.save(account);

            Board board = new Board.BoardBuilder()
                    .boardName("board" + i)
                    .createdBy("name1")
                    .boardDescription("description" + i)
                    .build();

            boardRepository.save(board);

            Post post = new Post.PostBuilder()
                    .title("title" + i)
                    .content("content" + i)
                    .createdBy(account.getName())
                    .AccountId(account.getAccountId())
                    .boardId(board.getBoardId())
                    .build();

            postRepository.save(post);
        }



    }
}
