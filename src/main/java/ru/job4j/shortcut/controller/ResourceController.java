package ru.job4j.shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.dto.AnswerDto;
import ru.job4j.shortcut.dto.LinkDto;
import ru.job4j.shortcut.dto.ResourceDto;
import ru.job4j.shortcut.dto.StatisticDto;
import ru.job4j.shortcut.service.ResourceService;
import java.util.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Регистрация сайта по URL
     * @param resourceDto
     * @return возрвщаает информацию с логином и паролем для доступа пользователя
     */
    @PostMapping("/registration")
    public ResponseEntity<AnswerDto> create(@RequestBody ResourceDto resourceDto) {
      return resourceService.registration(resourceDto);
    }

    /**
     *ЗАпрос для регистрации URI
     * @param linkDto
     * @return Объект содержащий уникальный код,
     * позволяющий перенаправить клиент по соответствующему URI
     */
    @PostMapping("/convert")
    public ResponseEntity<Link> addLink(@RequestBody LinkDto linkDto) {
     return resourceService.addLink(linkDto);
    }

    /**
     * Запрос переадресует клиента по URI соответствующему уникальнмоу коду
     * @param code
     * @return
     */
    @GetMapping("/redirect/{code}")
    public ResponseEntity<Object> getSite(@PathVariable String code) {
        return resourceService.redirect(code);
    }

    /**
     * Запрос на получение статистики посещения определенных URI
     * @return
     */
    @GetMapping("/statistic")
    public List<StatisticDto> getStatistic() {
      return resourceService.statistic();
    }
}
