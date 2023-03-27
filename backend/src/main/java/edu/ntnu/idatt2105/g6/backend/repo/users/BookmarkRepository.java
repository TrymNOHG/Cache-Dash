package edu.ntnu.idatt2105.g6.backend.repo.users;

import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.users.BookmarkId;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**

 This repository provides CRUD operations for the Bookmark entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>, JpaSpecificationExecutor<Bookmark> {
    /**
     * Find all bookmarks belonging to a specific user.
     *
     * @param user The user whose bookmarks to retrieve.
     * @return An optional list of bookmarks belonging to the user, empty if none were found.
     */
    Optional<List<Bookmark>> findAllByUser(@NonNull User user);

    /**
     * Find a specific bookmark belonging to a specific user.
     *
     * @param item The item that is bookmarked.
     * @param user The user who has bookmarked the item.
     * @return An optional bookmark matching the provided item and user, empty if none were found.
     */
    Optional<Bookmark> findByItemAndUser(Item item, User user);

}
