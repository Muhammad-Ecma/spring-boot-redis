package ai.ecma.springbootredis.controller;

import ai.ecma.springbootredis.entity.Country;
import ai.ecma.springbootredis.model.ApiResponse;
import ai.ecma.springbootredis.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muhammad Mo'minov
 * 08.06.2021
 */
@RestController
@RequestMapping("/api/country")
@AllArgsConstructor
public class CountryController {
    final CountryService countryService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable int id){
        ApiResponse response = countryService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public HttpEntity<?> edit(@RequestBody Country country){
        return ResponseEntity.ok(countryService.update(country));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable int id){
        return ResponseEntity.ok(countryService.deleteOne(id));
    }

    @DeleteMapping("/all")
    public HttpEntity<?> deleteAll(){
        return ResponseEntity.ok(countryService.deleteAll());
    }
}
