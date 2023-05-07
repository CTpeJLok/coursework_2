package com.sport_objects.api;

import com.sport_objects.entities.Place;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/place")
public class PlaceApi {

    @Autowired
    private PlaceService placeService;

    @GetMapping({"", "/"})
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(placeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(placeService.getOne(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Place place) {
        try {
            placeService.save(place);
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
            placeService.del(id);
            return ResponseEntity.ok("Успешно");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

}
