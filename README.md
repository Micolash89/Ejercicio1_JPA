# Ejercicio1_JPA

***Sistema de Guardado de una Librería***
El objetivo de este ejercicio es el desarrollo de un sistema de guardado de libros en JAVA<br>
utilizando una base de datos MySQL y JPA como framework de persistencia.<br>
Creación de la Base de Datos MySQL:<br>
Lo primero que se debe hacer es crear la base de datos sobre el que operará el sistema de
reservas de libros. Para ello, se debe abrir el IDE de base de datos que se está utilizando
(Workbench) y ejecutar la siguiente sentencia:<br>
CREATE DATABASE libreria;<br>
De esta manera se habrá creado una base de datos vacía llamada librería.
Paquetes del Proyecto Java:<br>
Los paquetes que se utilizarán para este proyecto son los siguientes:<br>
• entidades: en este paquete se almacenarán aquellas clases que se quiere persistir en la
base de datos.<br>
• servicios: en este paquete se almacenarán aquellas clases que llevarán adelante la lógica
del negocio. En general se crea un servicio para administrar las operaciones CRUD
(Create, Remove, Update, Delete) cada una de las entidades y las consultas de cada
entidad.<br>
Nota: En este proyecto vamos a eliminar entidades, pero no es considerado una buena<br>
práctica. Por esto, además de eliminar nuestras entidades, vamos a practicar que nuestras
entidades estén dadas de alta o de baja. Por lo que las entidades tendrán un atributo “activo”
de tipo booleano, que estará en true al momento de crearlas y en false cuando las demos de
baja, para evitar eliminarlas de la base de datos.<br>
<img src="src/images/libreriaPackages.PNG">
a) Entidades<br>
Crearemos el siguiente modelo de entidades:<br>
Entidad Libro<br>
La entidad libro modela los libros que están disponibles en la biblioteca para ser prestados. En
esta entidad, el atributo “ejemplares” contiene la cantidad total de ejemplares de ese libro,
mientras que el atributo “ejemplaresPrestados” contiene cuántos de esos ejemplares se
encuentran prestados en este momento y el atributo “ejemplaresRestantes” contiene cuántos
de esos ejemplares quedan para prestar.<br>
Entidad Autor<br>
La entidad autor modela los autores de libros.<br>
Entidad Editorial<br>
La entidad editorial modela las editoriales que publican libros.<br>
Base de Datos<br>
Para este proyecto nos vamos a conectar a la base de datos Librería, que creamos
previamente.<br>
Generación de Tablas<br>
La estrategia de generación de tablas define lo que hará JPA en cada ejecución, si debe crear
las tablas faltantes, si debe eliminar todas las tablas y volver a crearlas o no hacer nada.
Recomendamos en este proyecto utilizar la opción: “Create”
Librería de Persistencia<br>
Se debe seleccionar para este proyecto la librería “EclipseLink”.<br>
c) Servicios<br>
AutorServicio<br>
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar autores (consulta, creación, modificación y eliminación).<br>
EditorialServicio<br>
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar editoriales (consulta, creación, modificación y eliminación)<br>
LibroServicio<br>
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar libros (consulta, creación, modificación y eliminación).<br>
d) Main<br>
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
interactuar con el usuario. En esta clase se muestra el menú de opciones con las operaciones
disponibles que podrá realizar el usuario.<br>
e) Tareas a realizar<br>
Al alumno le toca desarrollar, las siguientes funcionalidades:<br>
1) Crear base de datos Librería<br>
2) Crear unidad de persistencia<br>
3) Crear entidades previamente mencionadas (excepto Préstamo)<br>
4) Generar las tablas con JPA<br>
5) Crear servicios previamente mencionados.<br>
6) Crear los métodos para persistir entidades en la base de datos librería<br>
7) Crear los métodos para dar de alta/bajo o editar dichas entidades.<br>
8) Búsqueda de un Autor por nombre.<br>
9) Búsqueda de un libro por ISBN.<br>
10) Búsqueda de un libro por Título.<br>
11) Búsqueda de un libro/s por nombre de Autor.<br>
12) Búsqueda de un libro/s por nombre de Editorial.<br>
13) Agregar las siguientes validaciones a todas las funcionalidades de la aplicación:<br>
• Validar campos obligatorios.<br>
• No ingresar datos duplicados.<br>
