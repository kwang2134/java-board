package domain.posts;

import domain.BaseEntity;
import global.util.annotation.Id;

public class Post extends BaseEntity {
    @Id
    private Long postId;
    private String title;
    private String content;
    private String createdBy;
    private Long boardId;
    private Long accountId;

    private Post(PostBuilder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.createdBy = builder.createdBy;
        this.accountId = builder.accountId;
        this.boardId = builder.boardId;
        setCreatedAt();
    }

    public void modifyPost(String title, String content) {
        this.title = title;
        this.content = content;
        setUpdatedAt();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public static class PostBuilder {
        private String title;
        private String content;
        private String createdBy;
        private Long boardId;
        private Long accountId;

        public PostBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public PostBuilder AccountId(Long AccountId) {
            this.accountId = AccountId;
            return this;
        }

        public PostBuilder boardId(Long boardId) {
            this.boardId = boardId;
            return this;
        }

        public Post build() {
            if(title == null || content == null || boardId == null) {
                System.out.println("제목, 내용, 게시판 ID는 필수입니다.");
                return null;
            }
            return new Post(this);
        }
    }
}
