package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
