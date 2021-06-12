package ai.ecma.springbootredis.service;

import ai.ecma.springbootredis.entity.Country;
import ai.ecma.springbootredis.model.ApiResponse;
import ai.ecma.springbootredis.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
@Service
@AllArgsConstructor
public class CountryService {
    final CountryRepository countryRepository;

    @Cacheable(value = "country" /*, key = "#id" , sync = true, condition = "#id <= 100", unless = "#result.id != 1"*/)
    public ApiResponse getOne(int id) {
        sleep();
        return ApiResponse.success(countryRepository.findById(id).orElseGet(Country::new));
    }

    @CachePut(value = "country", key = "#one.id")
    public ApiResponse update(Country country) {
        Country one = countryRepository.getOne(country.getId());
        one.setName(country.getName());
        one.setNiceName(country.getNiceName());
        one.setPhoneCode(country.getPhoneCode());
        return ApiResponse.success(countryRepository.save(one));
    }

    @CachePut(value = "country", key = "#country.id")
    public ApiResponse save(Country country) {
        return ApiResponse.success(countryRepository.save(country));
    }

    @CacheEvict(value = "country", key = "#id")
    public ApiResponse deleteOne(int id) {
        countryRepository.deleteById(id);
        return ApiResponse.success("Successfully deleted!");
    }

    @CacheEvict(value = "country", allEntries = true)
    public ApiResponse deleteAll() {
        countryRepository.deleteAll();
        return ApiResponse.success("Successfully deleted!");
    }

    public void sleep() {
        try {
            System.err.println("I am sleeping..........");
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}