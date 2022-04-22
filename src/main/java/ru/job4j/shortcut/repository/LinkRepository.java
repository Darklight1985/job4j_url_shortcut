package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.domain.Resource;

import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    Optional<Link> findByUri(String uri);

    Optional<Link> findByCode(String code);
}
