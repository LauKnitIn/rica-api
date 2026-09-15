# Mini-ADR: límite del agregado Investigador

## Estado

El agregado `Investigador` hoy encapsula la identidad y los datos del investigador como una entidad autónoma, pero no incluye a `Publicacion` dentro de su frontera.

## Decisión

`Publicacion` no está dentro del límite de `Investigador` porque es otro agregado: tiene su propia identidad (`id`), se persiste en una colección MongoDB (`publicaciones`) y su ciclo de vida se maneja desde `PublicacionService` con referencia al investigador por correo institucional. El límite del agregado no es “todo lo relacionado con un investigador”, sino la entidad que define su identidad y sus invariantes: nombre completo, correo institucional único y válido con dominio `@uptc.edu.co`, y grupo de investigación. La regla de negocio del año y el número máximo de publicaciones se valida fuera del agregado, desde `LimitePublicacionesAnualesService` y `PublicacionService`, usando el correo del investigador como clave de asociación.

Si alguien añadiera directamente `List<Publicacion> publicaciones` dentro de `Investigador`, rompería el límite del agregado: `Investigador` dejaría de ser la raíz del agregado y pasaría a ser un contenedor de otra entidad con ciclo de vida propio. Eso introduciría acoplamiento oculto, duplicidad de fuente de verdad, y riesgo de inconsistencia entre la base relacional de investigadores y la colección de publicaciones; además, la regla de “máximo 5 publicaciones al año” quedaría mezclada con el agregado equivocado y la entidad perdería su cohesión.

## Qué vive dentro del límite

Dentro del agregado `Investigador` viven sus atributos propios y sus invariantes: identidad, nombre completo, correo institucional, grupo de investigación, y la garantía de que no haya dos investigadores con el mismo correo institucional. Fuera del límite vive `Publicacion`, porque su mismo concepto de ciclo de vida, persistencia y validación anual pertenece a otro agregado.

## Raíz del agregado

La raíz del agregado `Investigador` es la entidad `Investigador` misma, con su identidad principal en `id` y su identidad de negocio en `correoInstitucional`. La razón de esta raíz es que la unicidad del investigador y la validación de su correo son invariantes del agregado; la publicación no pertenece a esa raíz porque se registra y consulta como entidad independiente según el correo del investigador, no como parte embebida del investigador.
