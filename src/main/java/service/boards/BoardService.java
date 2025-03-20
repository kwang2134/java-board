package service.boards;

import domain.boards.Board;
import global.exception.boards.BoardNotFoundException;
import repository.boards.BoardRepository;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board addBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board editBoard(Long boardId, String boardName) {
        Board board = boardRepository.findById(boardId).orElseGet(() -> {
            new BoardNotFoundException("게시판이 존재하지 않습니다.");
            return null;
        });

        board.modifyBoard(boardName);
        return board;
    }

    public Board viewBoard(String boardName) {
        return boardRepository.findByBoardName(boardName).orElseGet(() -> {
            new BoardNotFoundException("게시판이 존재하지 않습니다.");
            return null;
        });
    }

    public void removeBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
