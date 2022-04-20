package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.domain.Resource;
import ru.job4j.shortcut.domain.User;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.ResourceRepository;
import ru.job4j.shortcut.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceRepository resourceRepository;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public ResourceController(ResourceRepository resourceRepository,
                              LinkRepository linkRepository, UserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Resource> create(@RequestParam("site") String site) {
        var resource = resourceRepository.findBySite(site);
        if (resource.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "The site is already registered");
        } else {
            User user = User.of(site, "password");
            resource = Optional.of(Resource.of(site, user));
            return new ResponseEntity<>(resourceRepository.save(resource.get()),
                    HttpStatus.ACCEPTED);
        }
    }
}
