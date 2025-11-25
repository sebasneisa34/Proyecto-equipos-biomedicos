-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Meditech SAS', 'NIT-900123456', 'contacto@meditech.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Leonardo', 'NIT-900676876', 'contacto@leonardo.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Dana', 'NIT-086547689', 'contacto@Dana.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Danilo', 'NIT-542876908', 'contacto@danilo.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Mariana', 'NIT-532639657', 'contacto@mariana.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Marisol', 'NIT-568843569', 'contacto@MArisol.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Samuel', 'NIT-976535678', 'contacto@samuel.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Sara', 'NIT-875434560', 'contacto@Sara.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('Keyner', 'NIT-432678954', 'contacto@Keyner.com');
-- INSERT INTO provider (name, tax_id, contact_email)
-- VALUES ('David', 'NIT-987453239', 'contacto@David.com');

-- -- Instertar registros en la tabla person
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Pedro Gomez', 'CC-123456', 'DOCTOR');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Pablo Cortes', 'CC-123478', 'NURSE');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Rafael Paredes', 'CC-128760', 'BOSS');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Isabel Martiñon', 'CC-127896', 'SECRETARY');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Maria Posada', 'CC-987543', 'BOSS');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Juan Garzon', 'CC-986543', 'NURSE');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Jose francisco', 'CC-876347', 'MAINTENANCE_MAIN');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Sofia Castillo', 'CC-876432', 'ADMIN');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Paula Montes', 'CC-987732', 'WATCHMAN');
-- INSERT INTO person(full_name, document, role)
-- VALUES ('Luiz mosquera', 'CC-965439', 'DOCTOR');

-- SELECT * FROM person;

INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-001', 'philips', 'mx450' 'MEDICAl', 'IN-USE', '9c3a6850-ae02-4f73-8fa6-90acb1e2a242', 'https://images.unsplash.com/photo-1689308271305-58e75832289b?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-002', 'HP', 'mx510' 'COMPUTING', 'NEW', '7d9c6cb8-c970-4fb1-bcfa-562eef2825ed', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVo82sXNSNfEN812oa2-EmKelw_EK-y0ux0A&s');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-003', 'Dell', 'mx642' 'OFFICE', 'IN-USE', '648e0cf6-fe88-4c13-bbb3-963c6dfcf492', 'https://es.vecteezy.com/foto/24699637-ai-generado-ai-generativo-foto-realista-ilustracion-de-humano-ojo-petroleo-dibujar-grafico-arte');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-004', 'Lenovo', 'mx230' 'INFRASTRUCTURE', 'UNDER_MAINTENANCE','ec1033aa-2e72-447d-a0ca-aef00a4f6be2','https://es.vecteezy.com/foto/23778373-ai-generado-ai-generativo-modelo-antecedentes-holi-polvo-explosion-resumen-pintada-calle-pared-arte-grafico-arte');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-005', 'Acer', 'mx315' 'MEDICAL', 'IN_STORANGE','33ea302b-0296-4ac8-b490-0023639aab7d', 'https://es.vecteezy.com/foto/22949120-generativo-ai-tinta-negro-calle-pintada-arte-en-un-texturizado-papel-clasico-fondo-inspirado-por-banksy');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-006', 'Asus', 'mx935' 'LABORATORY', 'RECOVERED','c958e2bc-e55c-4f28-8257-ef1254fb2091', 'https://es.vecteezy.com/foto/22263343-magia-hongos-terminado-sagrado-geometria-psicodelico-alucinacion-vibrante-vector-ilustracion-60s-hippie-vistoso-arte-generativo-ai');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-007', 'Toshiba', 'mx837' 'OFFICE', 'PREDING_INSPECTION','d2226157-cae6-4e2e-9333-c9b9ca5a8d29', 'https://es.vecteezy.com/foto/22263343-magia-hongos-terminado-sagrado-geometria-psicodelico-alucinacion-vibrante-vector-ilustracion-60s-hippie-vistoso-arte-generativo-ai');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-008', 'Samsung', 'mx753' 'AUDIOVUSUAL', 'NEW','f6d1acb7-992c-4299-b504-71223ec8b60a', 'https://es.vecteezy.com/foto/22697140-generativo-ai-ilustracion-de-mas-alto-yoga-relajarse-frio-fuera-dmt-visiones-espiritu-7-7-de-colores-chakras-meditacion-dmt-alucinaciones-multiverso-conectado-mediante-un-nervioso-sistema-trippy-psicodelico');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-009', 'Sony', 'mx271' 'OTHER', 'RESERVADO','735d5132-fa0f-4549-abf0-6658cbb21d10', 'https://es.vecteezy.com/foto/22949121-generativo-ai-tinta-negro-calle-pintada-arte-en-un-texturizado-papel-clasico-fondo-inspirado-por-banksy');
INSERT INTO equipment (serial, brand, model, type, state, provider_id, image_path)
VAlUES ('BIO-010', 'Apple', 'mx173' 'COMPUTING', 'DISPOSED','7fc421ed-5b7b-4357-92c3-16bbf8fe17f5', 'https://es.vecteezy.com/foto/1259909-pinceladas-abstractas-negras');