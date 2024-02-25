package rs.singidunum.carbonfootprints.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;

import java.util.Objects;

@Data
@Entity
@Table(name = "coef")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarbonCoef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "coef")
    private Double coef;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarbonCoef that = (CarbonCoef) o;
        return id.equals(that.id) && name.equals(that.name) && coef.equals(that.coef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coef);
    }
}
