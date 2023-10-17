package br.com.atilara.authenticationjwt.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @GetMapping
    public ResponseEntity<String> managerGet() {
        return ResponseEntity.ok("GET: manager");
    }

    @PostMapping
    public ResponseEntity<String> managerPost() {
        return ResponseEntity.ok("POST: manager");
    }

    @PutMapping
    public ResponseEntity<String> managerPut() {
        return ResponseEntity.ok("PUT: manager");
    }

    @DeleteMapping
    public ResponseEntity<String> managerDelete() {
        return ResponseEntity.ok("DELETE: manager");
    }
}
