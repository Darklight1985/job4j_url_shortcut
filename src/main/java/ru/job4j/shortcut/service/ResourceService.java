package ru.job4j.shortcut.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.domain.Resource;
import ru.job4j.shortcut.domain.User;
import ru.job4j.shortcut.dto.AnswerDto;
import ru.job4j.shortcut.dto.LinkDto;
import ru.job4j.shortcut.dto.ResourceDto;
import ru.job4j.shortcut.dto.StatisticDto;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.ResourceRepository;
import ru.job4j.shortcut.repository.UserRepository;

import java.net.URI;
import java.util.*;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository repository;
    private final LinkRepository linkRepository;
    private BCryptPasswordEncoder encoder;

    public ResourceService(ResourceRepository resourceRepository, UserRepository repository, LinkRepository linkRepository, BCryptPasswordEncoder encoder) {
        this.resourceRepository = resourceRepository;
        this.repository = repository;
        this.linkRepository = linkRepository;
        this.encoder = encoder;
    }

    public ResponseEntity<AnswerDto> registration(ResourceDto resourceDto) {
        var resource = resourceRepository.findBySite(resourceDto.getSite());
        User user = User.of(resourceDto.getSite(), encoder.encode(resourceDto.getPassword()));
        ResponseEntity<AnswerDto> rsl;
        if (resource.isPresent()) {
            rsl = new ResponseEntity<>(new AnswerDto(false, user.getUsername(),
                    user.getPassword()),
                    HttpStatus.ACCEPTED);
        } else {
            resource = Optional.of(Resource.of(resourceDto.getSite(), user));
            resourceRepository.save(resource.get());
            rsl =  new ResponseEntity<>(new AnswerDto(true, user.getUsername(), user.getPassword()),
                    HttpStatus.ACCEPTED);
        }
        return rsl;
    }

    public ResponseEntity<Link> addLink(LinkDto linkDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = (String) authentication.getPrincipal();
        User user = repository.findByUsername(name);
        var resource = resourceRepository.findByUser(user);
        var link = linkRepository.findByUri(linkDto.getUri());
        if (link.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "The link is already registered");
        } else {
            String uniqueID = UUID.randomUUID().toString();
            Link linkNew = Link.of(linkDto.getUri(), uniqueID, resource.get());
            return new ResponseEntity<>(linkRepository.save(linkNew),
                    HttpStatus.ACCEPTED);
        }
    }

    public ResponseEntity<Object> redirect(String code) {
        var link = linkRepository.findByCode(code);
        linkRepository.incrCount(link.get().getCount(), link.get().getId());
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(link.get().getUri())).build();
    }

    public List<StatisticDto> statistic() {
        Iterator<Link> links = linkRepository.findAll().iterator();
        List<StatisticDto> linkList = new ArrayList<>();
        while (links.hasNext()) {
            Link link = links.next();
            linkList.add(new StatisticDto(link.getUri(), link.getCount()));
        }
        return linkList;
    }
}
