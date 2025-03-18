package domain.boards;

import domain.BaseEntity;
import domain.posts.Post;
import util.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Board extends BaseEntity {
    @Id
    private Long boardId;
    private String boardName;
    private String boardDescription;
    private String createdBy;
    private List<Post> posts;

    private Board(BoardBuilder builder) {
        this.boardName = builder.boardName;
        this.boardDescription = builder.boardDescription;
        this.createdBy = builder.createdBy;
        this.posts = new ArrayList<>();
        setCreatedAt();
    }

    public void modifyBoard(String boardName) {
        this.boardName = boardName;
        setUpdatedAt();
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public static class BoardBuilder {
        private String boardName;
        private String boardDescription;
        private String createdBy;

        public BoardBuilder boardName(String boardName) {
            this.boardName = boardName;
            return this;
        }

        public BoardBuilder boardDescription(String boardDescription) {
            this.boardDescription = boardDescription;
            return this;
        }

        public BoardBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
