package iamcoda.example.springwebservicedemo.repository;

import iamcoda.example.springwebservicedemo.entity.Post;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
