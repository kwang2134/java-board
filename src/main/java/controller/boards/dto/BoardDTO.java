package controller.boards.dto;

public class BoardDTO {

    private String boardName;
    private String boardDescription;
    private String createdBy;

    public BoardDTO(String boardName, String boardDescription, String createdBy) {
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.createdBy = createdBy;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
