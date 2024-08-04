package dev.diegoborba.hackathonkipper.model.enums;

import lombok.Getter;

@Getter
public enum QuestionCategory {
    NATURE("Natureza"),
    ECOLOGICAL_ATTITUDES("Atitudes Ecológicas"),
    ANIMALS("Animais"),
    CLIMATE_CHANGE("Mudanças Climáticas");

    private final String label;

    QuestionCategory(String label) {
        this.label = label;
    }
}