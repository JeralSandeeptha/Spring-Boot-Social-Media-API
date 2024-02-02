package com.jeral.socialmedia.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "contactNumber",
                column = @Column(name = "contact_number")
        ),
        @AttributeOverride(
                name = "city",
                column = @Column(name = "city")
        ),
        @AttributeOverride(
                name = "postalCode",
                column = @Column(name = "postal_code")
        )
})
public class Details {

    private String contactNumber;
    private String city;
    private String postalCode;
}
