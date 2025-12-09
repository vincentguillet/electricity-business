package fr.vguillet.electricitybusiness.model.app.reservation;

public enum Status {
    PENDING("En attente"),
    CONFIRMED("Confirmée"),
    CANCELLED("Annulée"),
    COMPLETED("Terminée");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}