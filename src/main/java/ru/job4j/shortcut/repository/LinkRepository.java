package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.Link;

public interface LinkRepository extends CrudRepository<Link, Integer> {
}