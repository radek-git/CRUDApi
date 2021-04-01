package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({
        "_id"
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private String surname;
    private int age;
    private String city;
    private String address;
    private String phoneNr;


}
