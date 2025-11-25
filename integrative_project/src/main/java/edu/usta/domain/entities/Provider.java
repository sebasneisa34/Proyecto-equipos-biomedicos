package edu.usta.domain.entities;

import edu.usta.infraestructure.config.UUIDGenerator;

public class Provider {
    private String id;
    private String name;
    private String taxId;
    private String contactEmail;

    public Provider() {
        this.id = UUIDGenerator.execute();
        this.name = "Antonio";
        this.taxId = "taxld-" + UUIDGenerator.execute();
        this.contactEmail = "antonio@gmail.com";
    }

    public Provider(String name, String contactEmail) {
        this.id = UUIDGenerator.execute();
        this.taxId = "taxld-" + UUIDGenerator.execute();
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public Provider(String id, String name, String taxId, String contactEmail) {
        this.id = id;
        this.name = name;
        this.taxId = taxId;
        this.contactEmail = contactEmail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxld() {
        return taxId;
    }

    public void setTaxld(String taxld) {
        this.taxId = taxld;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Datos del proveedor: ");
        sb.append("Nombre: ").append(name);
        sb.append("Id: ").append(id);
        sb.append("TaxId: ").append(taxId);
        sb.append("Contacto: ").append(contactEmail);
        return sb.toString();
    }

}
