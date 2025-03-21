package controller.boards.dto;

public class BoardEditDTO {

    private String boardName;

    public BoardEditDTO(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
