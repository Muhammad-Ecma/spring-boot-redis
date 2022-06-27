package ai.ecma.redisApp.controller;

import ai.ecma.redisApp.entity.Country;
import ai.ecma.redisApp.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Muhammad Mo'minov
 * 08.06.2021
 */
@AllArgsConstructor
@CacheConfig(cacheNames = "country", cacheManager = "countryCacheManager")
@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryRepository countryRepository;

    @GetMapping
    @Cacheable(value = "countries")
    public List<Country> getAll() {
        List<Country> countryList = countryRepository.findAll();
        return countryList;
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", sync = true /*, condition = "#id <= 100", unless = "#result.id != 1"*/)
    public Country getById(@PathVariable Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            throw new RuntimeException("Country not found");
        }
        return optionalCountry.get();
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id")
    public Country edit(@PathVariable Long id, @RequestBody Country country) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            throw new RuntimeException("Country not found");
        }
        Country countryToEdit = optionalCountry.get();
        countryToEdit.setName(country.getName());
        countryToEdit.setNiceName(country.getNiceName());
        countryToEdit.setPhoneCode(country.getPhoneCode());
        return countryRepository.save(countryToEdit);
    }

    @PostMapping
    @CachePut(key = "#country.id")
    public Country create(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id")
    public void deleteOne(@PathVariable Long id) {
        countryRepository.deleteById(id);
    }

    @CacheEvict(allEntries = true)
    @DeleteMapping("/all")
    public void deleteAll() {
        countryRepository.deleteAll();
    }
}
