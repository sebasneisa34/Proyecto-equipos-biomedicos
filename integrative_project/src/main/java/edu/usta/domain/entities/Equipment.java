// Ubicación de la clase. En que paquete esta la clase. 
package edu.usta.domain.entities;

import edu.usta.domain.enums.EquipmentStatus;
import edu.usta.domain.enums.EquipmentType;
import edu.usta.infraestructure.config.UUIDGenerator;

/**
 * Se define la clase Equipment la cual vamos a conocer que es la clase padre
 * que va a heredar atributos y métodos a las clases hijas (TechEquipment,
 * BiomedicalEquipment).
 *
 * A este tipo de clases se les conoce como POJO (Plain Old Java Object). Se
 * identifica de esta manera ya que solo tiene atributos conbstructores, getters
 * and setters y un metodo toString. Es la representación más basica pero
 * completa de una entidad.
 *
 * @author antonio-chacón
 */
public class Equipment {
    // ATRIBUTOS

    private String id;
    private String serial;
    private String model;
    private String imagePath;
    private String brand;
    private EquipmentType type;
    private EquipmentStatus status;
    private Provider provider;
    // Constructors

    /**
     * Constructor vacío donde se inicializan los atributos con valores por
     * defecto
     */
    public Equipment() {
        this.id = UUIDGenerator.execute();
        this.serial = "POO-00";
        this.model = "Salud";
        this.imagePath = "Imagen";
        this.brand = "Brannnd";
        this.type = EquipmentType.OTHER;
        this.status = EquipmentStatus.NEW;
        this.provider = null;

    }

    /**
     *
     * @param id        - Identificador unico del equipo
     * @param serial    - Numero serial dado por la fabrica
     * @param brand     - Marca del equipo
     * @param model     - Modelo o año del equipo
     * @param type      - Tipo de uso del equipo
     * @param state     - Estado fisico del equipo
     * @param provider  - Empresa proveedora
     * @param imagePath - Ruta de la imagen cargada
     *
     *
     */
    // Constructor con todos los parametros

    public Equipment(String serial, String model, String imagePath, String brand, EquipmentType type,
            EquipmentStatus status, Provider provider) {
        this.id = UUIDGenerator.execute();
        this.serial = serial;
        this.model = model;
        this.imagePath = imagePath;
        this.brand = brand;
        this.type = type;
        this.status = status;
        this.provider = provider;
    }

    

    public Equipment(String id, String serial, String model, String imagePath, String brand, EquipmentType type,
            EquipmentStatus status, Provider provider) {
        this.id = id;
        this.serial = serial;
        this.model = model;
        this.imagePath = imagePath;
        this.brand = brand;
        this.type = type;
        this.status = status;
        this.provider = provider;
    }

    /*
     * Se retorna el valor actual del equipo
     * 
     * @return Id del equipo
     */
    public String getId() {
        return id;
    }

    /**
     * Actualiza o modifica el valor del id, con el nuevo valor enviado por el
     * usuario
     */
    public void setId(String id) {
        this.id = id;
    }
    // Se retorna el valor actual

    public String getSerial() {
        return serial;
    }

    /**
     * Actualiza o modifica el valor del serial , con el nuevo valor enviado por
     * el usuario
     *
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    // Se retorna el valor actual
    public String getModel() {
        return model;
    }

    /**
     * Actualiza o modifica el valor del model , con el nuevo valor enviado por
     * el usuario
     */
    public void setModel(String model) {
        this.model = model;
    }

    // Se retorna el valor actual
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Actualiza o modifica el valor del imagePath , con el nuevo valor enviado
     * por el usuario
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Se retorna el valor actual
    public String getBrand() {
        return brand;
    }

    /**
     * Actualiza o modifica el valor del brand , con el nuevo valor enviado por
     * el usuario
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    /**
     * El metodo toString proviene del modelo estandar de la creación de clases
     * en JAVA, y por defecto retorna el identificador de la memoria en la cual
     * se encuentra guardado la instancia u objeto de la clase.
     *
     * Se sobreescribe con el decorado @Override para brindar una mejor salida
     * en consola que sea facimente entendible por la persona que consulta sobre
     * los valores de la instancia.
     */

    @Override
    public String toString() {
        return "Equipment [id=" + id + ", serial=" + serial + ", model=" + model + ", imagePath=" + imagePath
                + ", brand=" + brand + ", type=" + type + ", status=" + status + ", provinder=" + provider + "]";
    }

}
