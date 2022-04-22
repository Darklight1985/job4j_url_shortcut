package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.Resource;
import ru.job4j.shortcut.domain.User;

import java.util.Optional;

public interface ResourceRepository extends CrudRepository<Resource, Integer> {
    Optional<Resource> findBySite(String site);

    Optional<Resource> findByUser(User user);
}
