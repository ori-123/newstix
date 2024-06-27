package models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    EN("english"),
    DE("german"),
    IT("italian"),
    FR("french"),
    HU("hungarian");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Language fromValue(String value) {
        for (Language language : Language.values()) {
            if (language.value.equalsIgnoreCase(value) || language.name().equalsIgnoreCase(value)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
