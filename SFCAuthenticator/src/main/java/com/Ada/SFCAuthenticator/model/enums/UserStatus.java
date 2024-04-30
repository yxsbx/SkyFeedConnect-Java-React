package com.Ada.SFCAuthenticator.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Enum representing the status of a user account.
 */
@Getter
public enum UserStatus {

    ACTIVE("A", "Ativo"),
    INACTIVE("I", "Inativo"),
    BANNED("B", "Banido"),
    DELETED("D", "Excluído"),
    PENDING("P", "Pendente");

    private final String code;

    private final String description;

    UserStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
    return code;
  }

    @JsonCreator
    public static UserStatus fromCode(String code) {
        return switch (code) {
        case "A" -> ACTIVE;
        case "I" -> INACTIVE;
        case "D" -> DELETED;
        case "P" -> PENDING;
        case "B" -> BANNED;
        default -> null;
        };
    }
}