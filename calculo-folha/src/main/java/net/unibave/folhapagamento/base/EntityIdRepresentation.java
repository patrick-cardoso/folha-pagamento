package net.unibave.folhapagamento.base;

public class EntityIdRepresentation<PK> {

    private PK id;

    private String description;

    private EntityIdRepresentation(PK id, String description) {
        this.id = id;
        this.description = description;
    }

    public static <PK>EntityIdRepresentation of(PK id, String description) {
        return new EntityIdRepresentation<>(id, description);
    }

    public PK getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
