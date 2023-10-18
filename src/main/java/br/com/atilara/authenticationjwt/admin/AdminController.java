package br.com.atilara.authenticationjwt.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

        @GetMapping
        @PreAuthorize("hasAnyAuthority('ADMIN_READ')")
        public ResponseEntity<String> adminGet() {
            return ResponseEntity.ok("GET: admin");
        }

        @PostMapping
        @PreAuthorize("hasAnyAuthority('ADMIN_CREATE')")
        public ResponseEntity<String> adminPost() {
            return ResponseEntity.ok("POST: admin");
        }

        @PutMapping
        @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
        public ResponseEntity<String> adminPut() {
            return ResponseEntity.ok("PUT: admin");
        }

        @DeleteMapping
        @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
        public ResponseEntity<String> adminDelete() {
            return ResponseEntity.ok("DELETE: admin");
        }
}
