package fr.vguillet.electricitybusiness.model.app;

import lombok.Getter;

@Getter
public enum PlugType {
    TYPE_2S("2S");

    private final String displayName;

    PlugType(String displayName) {
        this.displayName = displayName;
    }
}
