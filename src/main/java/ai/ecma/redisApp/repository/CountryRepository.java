package ai.ecma.redisApp.repository;

import ai.ecma.redisApp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}
