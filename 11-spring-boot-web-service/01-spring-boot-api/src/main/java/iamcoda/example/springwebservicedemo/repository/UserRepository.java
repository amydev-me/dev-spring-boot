package iamcoda.example.springwebservicedemo.repository;

import iamcoda.example.springwebservicedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
