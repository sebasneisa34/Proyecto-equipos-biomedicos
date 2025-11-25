package edu.usta.domain.entities;

import edu.usta.domain.enums.EquipmentStatus;
import edu.usta.domain.enums.EquipmentType;

/**
 * Clase hija que hereda de Equipment Cuando se crea una instancia de
 * biomedicalEquipment se requiere que se le pasen todos los atributos
 * solicitados por su clase padre, a su vez podra hacer uso de los metodos
 * heredados por la clase de la cual extiende.
 *
 * Esta clase tambien es un POJO
 *
 * @author antonio-chacón
 */
public class BiomedicalEquipments extends Equipment {

    private String riskClass;
    private String calibrationCert;

    /**
     * Constructor que define los atributos solicitados por la clase padre, pero
     * a su vez, define los atributos propios (riskClass, calibrationCert).
     *
     * Los atributos del padre ebtienen
     *
     *
     * @param serial -
     * @param brand -
     * @param model -
     * @param type -
     * @param state
     * @param provider
     * @param imagePath -
     *
     */
    public BiomedicalEquipments() {
    }

    public BiomedicalEquipments(String id, String serial, String model, String imagePath, String brand, EquipmentType type,
            EquipmentStatus status, Provider provider, String riskClass, String calibrationCert) {
        super(id, serial, model, imagePath, brand, type, status, provider);
        this.riskClass = riskClass;
        this.calibrationCert = calibrationCert;
    }

    // Se retorna el valor actual
    public String getRiskClass() {
        return riskClass;
    }

    /**
     * Actualiza o modifica el valor del riskClass , con el nuevo valor enviado
     * por el usuario
     */
    public void setRiskClass(String riskClass) {
        this.riskClass = riskClass;
    }

    // Se retorna el valor actual
    public String getCalibrationCert() {
        return calibrationCert;
    }

    /**
     * Actualiza o modifica el valor del CalibrationCert , con el nuevo valor
     * enviado por el usuario
     */
    public void setCalibrationCert(String calibrationCert) {
        this.calibrationCert = calibrationCert;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BiomedicalEquipments{");
        sb.append("riskClass=").append(riskClass);
        sb.append(", calibrationCert=").append(calibrationCert);
        sb.append('}');
        return sb.toString();
    }

}
