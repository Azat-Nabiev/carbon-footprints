package rs.singidunum.carbonfootprints.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.model.enums.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "created_dt")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Carbon> carbons;

    @OneToMany(mappedBy = "user")
    private List<CarbonCoef> carbonCoefs;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany
    @JoinTable(name = "user_address_assn",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private List<Address> addresses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && lastName.equals(user.lastName) && email.equals(user.email) && password.equals(
                user.password) && createdDate.equals(user.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, password, createdDate);
    }
}
