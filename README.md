# API REST de juegos de mesa

Este proyecto es el backend de una aplicación para organizar una colección de juegos de mesa y sus préstamos. Recibe peticiones de otras aplicaciones y devuelve o guarda la información.

## ¿Qué permite hacer?

- Consultar, crear y actualizar juegos. Se pueden buscar por título o categoría.
- Consultar, crear, actualizar y borrar autores.
- Consultar, crear, actualizar y borrar categorías.
- Consultar, crear, actualizar y borrar clientes.
- Registrar y consultar préstamos de juegos. Se pueden buscar por juego, cliente o fecha.
- Consultar autores y préstamos en páginas, útil cuando hay muchos resultados.

Al guardar un préstamo, la API comprueba que las fechas sean correctas, que no dure más de 14 días, que el juego no esté prestado a otra persona en esas fechas y que el cliente no tenga más de dos juegos a la vez.

## Rutas principales

Todas las rutas empiezan por `http://localhost:8080` cuando la aplicación se ejecuta en el equipo local.

| Ruta | Para qué sirve |
| --- | --- |
| `/game` | Consultar juegos y guardarlos. Permite filtrar por `title` e `idCategory`. |
| `/author` | Consultar autores, buscarlos por páginas y guardarlos. |
| `/category` | Consultar y guardar categorías. |
| `/client` | Consultar y guardar clientes. |
| `/loan` | Consultar préstamos, buscarlos por páginas y guardarlos. Permite filtrar por `gameTitle`, `idClient` y `date`. |

Para guardar se usa `PUT` en la ruta del recurso. Para borrar autores, categorías, clientes o préstamos se usa `DELETE` en la ruta seguida del identificador, por ejemplo `/client/1`. Los juegos no tienen una ruta para borrarlos en la API actual.

## Cómo ejecutarlo

Necesitas tener Java 17 instalado. Desde la carpeta del proyecto, ejecuta:

```bash
./mvnw spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

## Datos

El proyecto usa una base de datos H2 en memoria y carga algunos datos de ejemplo al iniciarse. Como está en memoria, los cambios se pierden al parar la aplicación y se vuelven a cargar los datos iniciales al arrancarla de nuevo.