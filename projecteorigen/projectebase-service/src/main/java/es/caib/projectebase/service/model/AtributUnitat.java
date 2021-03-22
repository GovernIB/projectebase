package es.caib.projectebase.service.model;

/**
 * Representació dels noms d'atributs que es poden emprar per filtrar o ordenadr Unitats Organiques.
 * Els camps s'haurien de correspondre amb aquells presents al UnitatOrganicaDTO, i no al entity, ja que
 * aquesta és una abstracció de la capa de serveis i no de la capa de persistència.
 */
public enum AtributUnitat implements Atribut {
    id,
    codiDir3,
    nom,
    dataCreacio,
    estat
}
