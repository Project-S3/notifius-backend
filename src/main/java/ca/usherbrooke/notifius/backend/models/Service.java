package ca.usherbrooke.notifius.backend.models;

public enum Service
{
    BOOK("Vente-échange de livres", "Projet S3 Équipe 1"),
    SCHEDULE("Horarius personalisé", "Projet S3 Équipe 2"),
    MENTORING("Mentorat entre étudiants", "Projet S3 Équipe 3"),
    EXAM("Locaux d'examens", "Projet S3 Équipe 5"),
    TUTORING("Échange groupe tutorat", "Projet S3 Équipe 6"),
    STEWARD("Évaluation des intendants", "Projet S3 Équipe 7"),
    GRADE("Grille de notes", "Alerte lors d'une nouvelle note"),
    MESSAGE("Message", "Alerte lors d'un nouveau message sur les sites de sessions"),
    NOTIFIUS("Notifius", "Envoie de notification pour tester vos paramètres"),
    TEST("Test", "utiliser pour les tests");

    private String displayName;
    private String description;

    Service(String displayName, String description)
    {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public Service withDisplayName(String displayName)
    {
        this.setDisplayName(displayName);
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Service withDescription(String description)
    {
        this.setDescription(description);
        return this;
    }
}
