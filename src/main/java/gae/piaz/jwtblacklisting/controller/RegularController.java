package gae.piaz.jwtblacklisting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regular")
public class RegularController {

    @GetMapping
    public ResponseEntity<String> heiMethod() {
        return ResponseEntity.ok("hei!");
    }
}
