-- SET search_path TO hospital_inventory, public;

-- CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- DROP TYPE IF EXISTS equipment_status;
-- DROP TYPE IF EXISTS equipment_type;
-- DROP TYPE IF EXISTS role_enum;

-- CREATE TYPE role_enum AS ENUM(
-- 'WATCHMAN', 'ADMIN', 'DOCTOR', 'NURSE', 
-- 'SECRETARY', 'BOSS', 'MAINTENANCE_MAIN'
-- );

-- CREATE TYPE Equipment_type AS ENUM(
-- 'COMPUTING', 'MEDICAL', 'LABORATORY', 
-- 'AUDIOVUSUAL', 'OFFICE', 'INFRASTRUCTURE',
-- 'OTHER'
-- );

-- CREATE TYPE Equipment_status AS ENUM(
-- 'NEW', 'IN_USE', 'UNDER_MAINTENANCE', 
-- 'DAMAGED', 'LOST', 'DECOMMISIONED', 
-- 'IN_STORANGE', 'RESERVADO', 'PREDING_INSPECTION', 
-- 'REPLACEMENT_NEEDED', 'RECOVERED', 'DISPOSED'
-- );

-- CREATE TABLE IF NOT EXISTS provider (
-- id UUID PRIMARY KEY,
-- name VARCHAR(255) NOT NULL,
-- Tax_id VARCHAR(80) NOT NULL UNIQUE,
-- contact_email VARCHAR(160) NOT NULL UNIQUE,
-- creaded_at TIMESTAMPTZ NOT NULL DEFAULT now(),
-- udptate_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
-- CHECK(position('@' IN contact_email) > 1)
-- );

-- -- ========= TABLAS BASE =========
-- CREATE TABLE IF NOT EXISTS provider (
--   id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--   name          VARCHAR(120)   NOT NULL,
--   tax_id        VARCHAR(80)    NOT NULL UNIQUE,      -- NIT
--   contact_email VARCHAR(160)   NOT NULL UNIQUE,
--   created_at    TIMESTAMPTZ    NOT NULL DEFAULT now(),
--   updated_at    TIMESTAMPTZ    NOT NULL DEFAULT now(),
--   CHECK (position('@' IN contact_email) > 1)
-- );

-- CREATE TABLE IF NOT EXISTS person (
--   id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--   full_name  VARCHAR(160)   NOT NULL,
--   document   VARCHAR(60)    NOT NULL UNIQUE,
--   role       role_enum      NOT NULL,
--   created_at TIMESTAMPTZ    NOT NULL DEFAULT now(),
--   updated_at TIMESTAMPTZ    NOT NULL DEFAULT now()
-- );

-- -- ========= EQUIPMENT (superclase) =========
-- CREATE TABLE IF NOT EXISTS equipment (
--   id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--   serial      VARCHAR(120)   NOT NULL UNIQUE,
--   brand       VARCHAR(120)   NOT NULL,
--   model       VARCHAR(120)   NOT NULL,
--   type        equipment_type NOT NULL,
--   state       equipment_status NOT NULL,
--   provider_id UUID           NULL REFERENCES provider(id)
--                                   ON UPDATE CASCADE
--                                   ON DELETE SET NULL,
--   image_path  TEXT           NULL,
--   created_at  TIMESTAMPTZ    NOT NULL DEFAULT now(),
--   updated_at  TIMESTAMPTZ    NOT NULL DEFAULT now()
-- );

CREATE INDEX IF NOT EXISTS idx_equipment_provider 
ON equipment(provider_id);

CREATE INDEX IF NOT EXISTS idx_equipment_type 
ON equipment(type);

CREATE INDEX IF NOT EXISTS idx_equipment_state
ON equipment(state);


--======= SUBCLASES (1 a 1 con equipment)
CREATE TABLE IF NOT EXISTS biomedical_equipment (
   id    UUID        PRIMARY KEY        REFERENCES equipment(id)
                                        ON UPDATE CASCADE
			                               ON DELETE CASCADE,
   risk_class        VARCHAR(60) NOT NULL,
   calibration_cert  VARCHAR(160) NOT NULL
);

CREATE TABLE IF NOT EXISTS tech_equipment (
   id      UUID     PRIMARY KEY      REFERENCES equipment(id)
                                     ON UPDATE CASCADE
									          ON DELETE CASCADE,
	os               VARCHAR(60) NOT NULL,
	ram_GB           VARCHAR(60) NOT NULL
);








