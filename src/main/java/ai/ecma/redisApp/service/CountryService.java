package ai.ecma.redisApp.service;

import ai.ecma.redisApp.entity.Country;
import ai.ecma.redisApp.exception.RestException;
import ai.ecma.redisApp.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "country", cacheManager = "countryCacheManager")
public class CountryService {
    private final CountryRepository countryRepository;

    @Cacheable(value = "countries")
    public List<Country> getAll() {
        System.out.println("Get from DB.....");
        return countryRepository.findAll();
    }

    @Cacheable(key = "#id", sync = true /*, condition = "#id <= 100", unless = "#result.id != 1"*/)
    public Country getById(Long id) {
        System.out.println("Get from DB.....");
        Country country = countryRepository.findById(id).orElseThrow(() -> RestException.notFound("Country not found"));
        return country;
    }

    @CachePut(key = "#countryDto.id")
    public Country update(Country countryDto) {
        Country country = countryRepository.findById(countryDto.getId()).orElseThrow(() -> RestException.notFound("Country not found"));
        country.setName(countryDto.getName());
        country.setNiceName(countryDto.getNiceName());
        country.setPhoneCode(countryDto.getPhoneCode());
        return countryRepository.save(country);
    }

    @CachePut(key = "#country.id")
    public Country create(Country country) {
        Country savedCountry = countryRepository.save(country);
        return savedCountry;
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.notFound("Country not found");
        }
    }

    @CacheEvict(allEntries = true)
    public void deleteAll() {
        countryRepository.deleteAll();
    }

    public void sleep() {
        try {
            System.err.println("I'm sleeping..........");
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}