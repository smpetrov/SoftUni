package bg.softuni.repositories;

import bg.softuni.models.Post;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Query("SELECT p FROM Post p Left JOIN FETCH p.author ORDER BY p.date DESC")
    //@Query("SELECT p FROM Post p ORDER BY p.date DESC")
    List<Post> findLatesPosts();    
}
