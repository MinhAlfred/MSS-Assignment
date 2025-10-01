package com.minhvq.orchidservice.controllers;

import com.minhvq.orchidservice.DTOs.ApiResponse;
import com.minhvq.orchidservice.entities.Orchid;
import com.minhvq.orchidservice.services.OrchidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orchids")
@CrossOrigin("*")
public class OrchidController {
    private final OrchidService orchidService;

    @PostMapping
    public ResponseEntity<ApiResponse<Orchid>> create(@RequestBody Orchid request) {
        try {
            Orchid orchid = orchidService.create(request);
            ApiResponse<Orchid> response = ApiResponse.success(orchid, "Orchid created successfully", 201);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Orchid> response = ApiResponse.error("Failed to create orchid: " + e.getMessage(), 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Orchid>>> findAll() {
        try {
            List<Orchid> orchids = orchidService.findAll();
            ApiResponse<List<Orchid>> response = ApiResponse.success(orchids, "Orchids retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Orchid>> response = ApiResponse.error("Failed to retrieve orchids: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Orchid>> findById(@PathVariable Integer id) {
        try {
            Optional<Orchid> orchidOptional = orchidService.findById(id);
            if (orchidOptional.isPresent()) {
                ApiResponse<Orchid> response = ApiResponse.success(orchidOptional.get(), "Orchid found successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Orchid> response = ApiResponse.error("Orchid not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Orchid> response = ApiResponse.error("Failed to retrieve orchid: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Orchid>> update(@PathVariable Integer id, @RequestBody Orchid request) {
        try {
            Orchid orchid = orchidService.update(id, request);
            if (orchid != null) {
                ApiResponse<Orchid> response = ApiResponse.success(orchid, "Orchid updated successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Orchid> response = ApiResponse.error("Orchid not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Orchid> response = ApiResponse.error("Failed to update orchid: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            orchidService.delete(id);
            ApiResponse<Void> response = ApiResponse.success("Orchid deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Void> response = ApiResponse.error("Failed to delete orchid: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity<ApiResponse<List<Orchid>>> search(@RequestParam String keyword) {
//        try {
//            List<Orchid> orchids = orchidService.findByNameContaining(keyword);
//            ApiResponse<List<Orchid>> response = ApiResponse.success(orchids, "Search completed successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            ApiResponse<List<Orchid>> response = ApiResponse.error("Search failed: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
