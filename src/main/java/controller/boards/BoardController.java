package controller.boards;

import domain.accounts.Account;
import domain.boards.Board;
import domain.posts.Post;
import service.boards.BoardService;
import service.posts.PostService;
import util.Session;
import util.request.Request;

import java.util.List;

public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public void addBoard(Request request) {
        Session session = request.getSession();
        Account authInfo = (Account) session.getAuthInfo();

        Board board = new Board.BoardBuilder()
                .boardName(request.getParam("boardName"))
                .boardDescription(request.getParam("boardDescription"))
                .createdBy(authInfo.getName())
                .build();

        boardService.addBoard(board);
        System.out.println("게시판이 등록되었습니다. 게시판 이름: " + board.getBoardName());
    }

    public void editBoard(Request request, long boardId) {
        Board board = boardService.editBoard(boardId, request.getParam("boardName"));
        if (board != null) {
            System.out.println("게시판이 수정되었습니다.");
        }
    }

    public void removeBoard(Request request, long boardId) {
        boardService.removeBoard(boardId);
        System.out.println("게시판이 삭제되었습니다.");
    }

    public void viewBoard(Request request, String boardName) {
        List<Post> posts = boardService.viewBoard(boardName).getPosts();
        for (Post post : posts) {
            System.out.println(post.getPostId() + "/" + post.getTitle() + "/" + post.getCreatedAt());
        }
    }
}
