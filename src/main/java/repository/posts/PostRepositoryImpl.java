package repository.posts;

import domain.posts.Post;
import repository.MemoryRepositoryImpl;

public class PostRepositoryImpl extends MemoryRepositoryImpl<Post, Long> implements PostRepository {
    private Long postIdSequence = 1L;

    @Override
    public Post save(Post post) {
        if(post.getPostId() == null){
            post.setPostId(postIdSequence++);
        }
        return super.save(post);
    }
}
