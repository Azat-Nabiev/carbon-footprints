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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "carbons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carbon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "coef_id")
    private CarbonCoef coef;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "last_upd")
    private LocalDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Column(name = "produced")
    private Long produced;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carbon carbon = (Carbon) o;
        return id.equals(carbon.id) && amount.equals(carbon.amount) && lastUpdated.equals(carbon.lastUpdated) && status == carbon.status && produced.equals(
                carbon.produced);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, lastUpdated, status, produced);
    }
}
