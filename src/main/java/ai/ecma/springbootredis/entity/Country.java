package ai.ecma.springbootredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Country implements Serializable {
    @Id
    private Integer id;

    private String name;

    @Column(name = "nice_name")
    private String niceName;

    @Column(name = "phone_code")
    private Integer phoneCode;
}
