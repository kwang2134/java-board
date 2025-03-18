package repository.posts;

import domain.posts.Post;
import repository.MemoryRepository;

public interface PostRepository extends MemoryRepository<Post, Long> {
}
