DROP TABLE IF EXISTS registry;

CREATE TABLE registry (
  registry_id INT AUTO_INCREMENT  PRIMARY KEY,
  description VARCHAR(250)
);


INSERT INTO registry (registry_id, description) VALUES (1, 'registry 1');
INSERT INTO registry (registry_id, description) VALUES (2, 'registry 2');
INSERT INTO registry (registry_id, description) VALUES (3, 'registry 3');
INSERT INTO registry (registry_id, description) VALUES (4, 'registry 4');
INSERT INTO registry (registry_id, description) VALUES (5, 'registry 5');