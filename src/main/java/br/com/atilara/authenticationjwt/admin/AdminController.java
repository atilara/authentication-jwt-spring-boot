package br.com.atilara.authenticationjwt.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

        @GetMapping
        public ResponseEntity<String> adminGet() {
            return ResponseEntity.ok("GET: admin");
        }

        @PostMapping
        public ResponseEntity<String> adminPost() {
            return ResponseEntity.ok("POST: admin");
        }

        @PutMapping
        public ResponseEntity<String> adminPut() {
            return ResponseEntity.ok("PUT: admin");
        }

        @DeleteMapping
        public ResponseEntity<String> adminDelete() {
            return ResponseEntity.ok("DELETE: admin");
        }
}
