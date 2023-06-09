package edu.ntnu.idatt2105.g6.backend.repo.users;

import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**

 This repository provides CRUD operations for the User entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     Retrieves an Optional User instance based on the provided username.
     @param username the username to search for
     @return an Optional User instance
     */
    Optional<User> findByUsername(String username);


}
