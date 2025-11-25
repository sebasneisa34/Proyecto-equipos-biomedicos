package edu.usta.infraestructure.config;

import java.util.UUID;

public class UUIDGenerator {

    /**
     *
     * UUID: Universal Unique Identifier
     *
     * @return Un identificadr único en cada llamada
     */
    public static String execute() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

}
