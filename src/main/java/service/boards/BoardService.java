package service.boards;

import controller.boards.dto.BoardDTO;
import controller.boards.dto.BoardEditDTO;
import domain.boards.Board;
import global.exception.boards.BoardNotFoundException;
import repository.boards.BoardRepository;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board addBoard(BoardDTO boardDTO) {
        Board board = new Board.BoardBuilder()
                .boardName(boardDTO.getBoardName())
                .boardDescription(boardDTO.getBoardDescription())
                .createdBy(boardDTO.getCreatedBy())
                .build();

        return boardRepository.save(board);
    }

    public Board editBoard(Long boardId, BoardEditDTO boardEditDTO) {
        Board board = boardRepository.findById(boardId).orElseGet(() -> {
            new BoardNotFoundException("게시판이 존재하지 않습니다.");
            return null;
        });

        board.modifyBoard(boardEditDTO.getBoardName());
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
