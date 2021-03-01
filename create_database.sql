CREATE DATABASE bdd_jee_projet DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'java_projet'@'localhost' IDENTIFIED BY 'mdp';
GRANT ALL ON bdd_jee_projet.* TO 'java_projet'@'localhost';