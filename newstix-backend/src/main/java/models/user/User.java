package models.user;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private final Set<UserRole> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String timeframe;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles.add(UserRole.USER);
        this.language = Language.EN;
        this.country = Country.US;
        this.category = Category.POLITICS;
        this.timeframe = "";
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Language getLanguage() {
        return language;
    }

    public Country getCountry() {
        return country;
    }

    public Category getCategory() {
        return category;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }
}
