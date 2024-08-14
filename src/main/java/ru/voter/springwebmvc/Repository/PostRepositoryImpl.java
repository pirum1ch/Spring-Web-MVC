package ru.voter.springwebmvc.Repository;

import org.springframework.stereotype.Repository;
import ru.voter.springwebmvc.Model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private Map map;

    public PostRepositoryImpl() {
        //немного наполним базу
        Post post1 = new Post(1, "Post1");
        Post post2 = new Post(2, "Post2");

        //использую вариант мапы для многопоточности. Крайне быстро работает на чтение
        map = new ConcurrentHashMap<Long, Post>();
        map.put(post1.getId(), post1);
        map.put(post2.getId(), post2);
    }

    @Override
    public List<Post> all() {
        return (List<Post>) map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Post> getById(long id) {
        return Optional.ofNullable((Post) map.get(id));
    }

    @Override
    public Post save(Post post) {
        map.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        map.remove(id);
    }
}
