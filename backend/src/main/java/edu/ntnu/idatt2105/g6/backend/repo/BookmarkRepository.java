package edu.ntnu.idatt2105.g6.backend.repo;

import edu.ntnu.idatt2105.g6.backend.model.Bookmark;
import edu.ntnu.idatt2105.g6.backend.model.BookmarkId;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>, JpaSpecificationExecutor<Bookmark> {
    Optional<List<Bookmark>> findBookmarksByUser_Username(@NonNull String username);

}
