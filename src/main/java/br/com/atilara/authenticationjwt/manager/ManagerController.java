package br.com.atilara.authenticationjwt.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasAnyRole('MANAGER')")
public class ManagerController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_READ')")
    public ResponseEntity<String> managerGet() {
        return ResponseEntity.ok("GET: manager");
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_CREATE')")
    public ResponseEntity<String> managerPost() {
        return ResponseEntity.ok("POST: manager");
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_UPDATE')")
    public ResponseEntity<String> managerPut() {
        return ResponseEntity.ok("PUT: manager");
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_DELETE')")
    public ResponseEntity<String> managerDelete() {
        return ResponseEntity.ok("DELETE: manager");
    }
}
