package com.sport_objects.api;

import com.sport_objects.entities.EventDistribution;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.services.EventDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-distribution")
public class EventDistributionApi {

    @Autowired
    private EventDistributionService eventDistributionService;

    @GetMapping({"", "/"})
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(eventDistributionService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(eventDistributionService.getOne(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EventDistribution eventDistribution) {
        try {
            eventDistributionService.save(eventDistribution);
            return ResponseEntity.ok("Успешно");
        } catch (CreateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            eventDistributionService.del(id);
            return ResponseEntity.ok("Успешно");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

}
