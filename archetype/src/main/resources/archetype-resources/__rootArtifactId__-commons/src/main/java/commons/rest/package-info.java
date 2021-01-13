#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * Aquest package conté classes comunes per totes les APIs REST,
 * bàsicament filtres i gestió d'errors.
 * Atès que les classes estan anotades amb l'etiqueta <code>@Provider</code>, aquestes
 * s'incorporaran directament a qualsevol mòdul WAR que tengui configurat JAX-RS
 * que defineixi la dependència cap aquest mòdul.
 */
package ${package}.commons.rest;