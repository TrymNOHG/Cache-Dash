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

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>, JpaSpecificationExecutor<Bookmark> {
    Optional<List<Bookmark>> findAllByUser(@NonNull User user);
    Optional<Bookmark> findByItemAndUser(Item item, User user);

}
