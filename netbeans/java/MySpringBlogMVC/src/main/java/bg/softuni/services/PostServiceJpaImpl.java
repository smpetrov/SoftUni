package bg.softuni.services;

import bg.softuni.models.Post;
import bg.softuni.repositories.PostRepository;
import bg.softuni.services.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
@Primary
public class PostServiceJpaImpl implements PostService {

    @Autowired
    private PostRepository postRepo;
    
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }

    public List<Post> findLatest5() {
        List<Post> latestPosts = postRepo.findLatesPosts();
        return latestPosts;
    }

    public Post findById(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Post currentPost = postRepo.findOne(id);
        return currentPost;
    }

    public Post create(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Post edit(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
