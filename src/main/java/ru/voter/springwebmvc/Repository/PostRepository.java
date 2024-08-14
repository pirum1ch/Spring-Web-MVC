package ru.voter.springwebmvc.Repository;

import ru.voter.springwebmvc.Model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    public List<Post> all();

    public Optional<Post> getById(long id);

    public Post save(Post post);

    public void removeById(long id);
}
