package ai.ecma.redisApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class not documented :(
 *
 * @author Muhammad Mo'minov
 * @since 11.02.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDTO {
    private String name;
    private String niceName;
    private Integer phoneCode;
}
