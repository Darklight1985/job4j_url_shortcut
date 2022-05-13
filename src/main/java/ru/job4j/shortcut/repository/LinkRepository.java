package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.domain.Link;
import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    Optional<Link> findByUri(String uri);

    @Transactional
    @Modifying
    @Query("update Link l set l.count = :linkCount + 1 where l.id = :linkId")
    public void incrCount(@Param("linkCount") int count, @Param("linkId") int id);

    Optional<Link> findByCode(String code);
}
