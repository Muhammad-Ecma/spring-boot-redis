package ai.ecma.redisApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nice_name")
    private String niceName;

    @Column(name = "phone_code")
    private Integer phoneCode;
}
