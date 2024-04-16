package models.user;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Set<Language> languages;

    @Enumerated(EnumType.STRING)
    private Set<Country> countries;

    @Enumerated(EnumType.STRING)
    private Set<Category> categories;
    private String timeframe;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.languages = Set.of(Language.ENGLISH, Language.GERMAN, Language.HUNGARIAN, Language.ITALIAN, Language.FRENCH);
        this.countries = Set.of(Country.AUSTRALIA, Country.AUSTRIA, Country.FRANCE, Country.BELGIUM, Country.CANADA, Country.GERMANY, Country.HUNGARY,
                Country.INDIA, Country.IRELAND, Country.ITALY, Country.SWITZERLAND, Country.UK, Country.US);
        this.categories = Set.of(Category.BUSINESS, Category.LIFESTYLE, Category.SPORTS, Category.ENTERTAINMENT, Category.CRIME,
                Category.POLITICS, Category.TECHNOLOGY);
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

    public Set<Language> getLanguages() {
        return Set.copyOf(languages);
    }

    public Set<Country> getCountries() {
        return Set.copyOf(countries);
    }

    public Set<Category> getCategories() {
        return Set.copyOf(categories);
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
    }

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void removeLanguage(Language language) {
        languages.remove(language);
    }
}
