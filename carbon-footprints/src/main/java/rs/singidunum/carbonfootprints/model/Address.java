package rs.singidunum.carbonfootprints.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.model.enums.BuildingType;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "flat")
    private String flat;
    @Column(name = "postalCode")
    private String postalCode;
    @ManyToMany(mappedBy = "addresses")
    private List<User> users;
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @OneToMany(mappedBy = "address")
    private List<Carbon> carbon;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return id.equals(address.id) && country.equals(address.country) && city.equals(address.city) && street.equals(
                address.street) && house.equals(address.house) && flat.equals(address.flat) && postalCode.equals(address.postalCode) && buildingType == address.buildingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, house, flat, postalCode, buildingType);
    }
}
