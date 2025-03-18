package repository.boards;

import domain.boards.Board;
import repository.MemoryRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardRepositoryImpl extends MemoryRepositoryImpl<Board, Long> implements BoardRepository {
    private Long boardIdSequence = 1L;
    private final Map<String, Board> boardNameIndex = new HashMap<>();

    @Override
    public Board save(Board board) {
        if(board.getBoardId() == null){
            board.setBoardId(boardIdSequence++);
        }
        Board savedBoard = super.save(board);
        boardNameIndex.put(savedBoard.getBoardName(), savedBoard);
        return savedBoard;
    }

    @Override
    public Optional<Board> findByBoardName(String boardName) {
        return Optional.ofNullable(boardNameIndex.get(boardName));
    }
}
