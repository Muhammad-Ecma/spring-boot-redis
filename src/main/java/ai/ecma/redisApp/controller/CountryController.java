package ai.ecma.redisApp.controller;

import ai.ecma.redisApp.entity.Country;
import ai.ecma.redisApp.model.ApiResult;
import ai.ecma.redisApp.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Muhammad Mo'minov
 * 08.06.2021
 */
@RestController
@RequestMapping("/api/country")
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ApiResult<List<Country>> getAll() {
        List<Country> countryList = countryService.getAll();
        return ApiResult.successResponse(countryList);
    }

    @GetMapping("/{id}")
    public ApiResult<Country> getById(@PathVariable Long id) {
        Country country = countryService.getById(id);
        return ApiResult.successResponse(country);
    }

    @PutMapping
    public ApiResult<Country> edit(@RequestBody Country country) {
        Country update = countryService.update(country);
        return ApiResult.successResponse(update, "Country successfully edited");
    }

    @PostMapping
    public ApiResult<Country> create(@RequestBody Country country) {
        Country update = countryService.create(country);
        return ApiResult.successResponse(update, "Country successfully saved");
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteOne(@PathVariable Long id) {
        countryService.deleteById(id);
        return ApiResult.successResponse("Country successfully deleted");
    }

    @DeleteMapping("/all")
    public ApiResult<?> deleteAll() {
        countryService.deleteAll();
        return ApiResult.successResponse("All country successfully deleted");
    }
}
