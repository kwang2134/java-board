package repository.boards;

import domain.boards.Board;
import repository.MemoryRepository;

import java.util.Optional;

public interface BoardRepository extends MemoryRepository<Board, Long> {
    Optional<Board> findByBoardName(String boardName);
}
