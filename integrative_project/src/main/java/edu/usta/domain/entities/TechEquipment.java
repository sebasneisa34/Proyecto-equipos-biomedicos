package edu.usta.domain.entities;

import edu.usta.domain.enums.EquipmentStatus;
import edu.usta.domain.enums.EquipmentType;

/**
 * Clase hija que extiende de Equipment.
 * Esta clase tambien es un POJO.
 * 
 * @author antonio-chacón
 */
public class TechEquipment extends Equipment {

    private String os;
    private int ramGB;

/* 
Constructor
 *   @param id - 
     * @param serial - 
     * @param brand -
     * @param model - 
     * @param type - 
     * @param state 
     * @param provider 
     * @param imagePath - 
     *
 */
    
    // Constructor con todos los parametros
    public TechEquipment(String serial, String model, String imagePath, String brand, EquipmentType type,
            EquipmentStatus status, Provider provider, String os, int ramGB) {
        super(serial, model, imagePath, brand, type, status, provider);
        this.os = os;
        this.ramGB = ramGB;
    }

    
public TechEquipment(String id, String serial, String model, String imagePath, String brand, EquipmentType type,
            EquipmentStatus status, Provider provider, String os, int ramGB) {
        super(id, serial, model, imagePath, brand, type, status, provider);
        this.os = os;
        this.ramGB = ramGB;
    }

    // Se retorna el valor actual
    public String getOs() {
        return os;
    }

    /**
     * Actualiza o modifica el valor del Os , con el nuevo valor enviado por el
     * usuario
     */
    public void setOs(String os) {
        this.os = os;
    }
// Se retorna el valor actual

    public int getRamGB() {
        return ramGB;
    }

    /**
     * Actualiza o modifica el valor del ramGB , con el nuevo valor enviado por
     * el usuario
     */
    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TechEquipment{");
        sb.append("os=").append(os);
        sb.append(", ramGB=").append(ramGB);
        sb.append('}');
        return sb.toString();
    }

}
