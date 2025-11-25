package edu.usta.domain.entities;

import edu.usta.infraestructure.config.UUIDGenerator;

public class EntryRequest {
 private final String id;
    private Equipment equipment;
    private Person requester;
    private Person internalResponsible;

    // DARLE VALORES NULOS A TODOS EN LOS CONSTRUCTORES
    public EntryRequest() {
        this.id = UUIDGenerator.execute();
        this.equipment = null;
        this.requester = null;
        this.internalResponsible = null;
    }

    public EntryRequest(Equipment equipment, Person internalResponsible, Person requester) {
        this.id = UUIDGenerator.execute();
        this.equipment = equipment;
        this.internalResponsible = internalResponsible;
        this.requester = requester;
    }

    public EntryRequest(String id, Equipment equipment, Person requester, Person internalResponsible) {
        this.id = id;
        this.equipment = equipment;
        this.requester = requester;
        this.internalResponsible = internalResponsible;
    }

    public String getId() {
        return id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Person getRequester() {
        return requester;
    }

    public void setRequester(Person requester) {
        this.requester = requester;
    }

    public Person getInternalResponsible() {
        return internalResponsible;
    }

    public void setInternalResponsible(Person internalResponsible) {
        this.internalResponsible = internalResponsible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntryRequest{");
        sb.append("id=").append(id);
        sb.append(", equipment=").append(equipment);
        sb.append(", requester=").append(requester);
        sb.append(", internalResponsible=").append(internalResponsible);
        sb.append('}');
        return sb.toString();
    }
}
