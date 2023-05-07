package com.sport_objects.api;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.services.HelpfulTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/helpful-type")
public class HelpfulTypeApi {

    @Autowired
    private HelpfulTypeService helpfulTypeService;

    @GetMapping({"", "/"})
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(helpfulTypeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(helpfulTypeService.getOne(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody HelpfulType helpfulType) {
        try {
            helpfulTypeService.save(helpfulType);
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
            helpfulTypeService.del(id);
            return ResponseEntity.ok("Успешно");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }
    }

}
