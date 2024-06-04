package models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Country {
    DE, BE, CH, IT, FR, HU, AT, AU, GB, IE, US, IN, CA;

    private static final Map<String, Country> NAMES_MAP = new HashMap<>();

    static {
        for (Country country : Country.values()) {
            NAMES_MAP.put(country.name().toLowerCase(), country);
        }
        NAMES_MAP.put("united states of america", US);
        NAMES_MAP.put("germany", DE);
        NAMES_MAP.put("belgium", BE);
        NAMES_MAP.put("switzerland", CH);
        NAMES_MAP.put("italy", IT);
        NAMES_MAP.put("france", FR);
        NAMES_MAP.put("hungary", HU);
        NAMES_MAP.put("austria", AT);
        NAMES_MAP.put("australia", AU);
        NAMES_MAP.put("great britain", GB);
        NAMES_MAP.put("ireland", IE);
        NAMES_MAP.put("india", IN);
        NAMES_MAP.put("canada", CA);
    }

    @JsonCreator
    public static Country forValue(String value) {
        Country country = NAMES_MAP.get(value.toLowerCase());
        if (country == null) {
            throw new IllegalArgumentException("Invalid country name: " + value);
        }
        return country;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
