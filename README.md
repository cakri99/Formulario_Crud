# Formulario CRUD (Java + SQLite)

Proyecto de CRUD con interfaz gráfica en **Java Swing**, utilizando **SQLite** como base de datos.  
Permite:
- Guardar información (nombre, correo).
- Listar y mostrar datos.
- Eliminar datos.

---

## Requisitos previos
- **Java JDK 8+** instalado  
  Verifica con:
  ```bash
  java --version

## Driver JDBC de SQLite:
- Descarga el .jar desde Xerial SQLite JDBC, Ejemplo: sqlite-jdbc-3.50.3.0.jar.
- Coloca el archivo .jar en el mismo directorio del proyecto.

## Ejecución del proyecto

Clonar el repositorio (Recomiendo esta opcion)
```bash
git clone https://github.com/cakri99/Formulario_Crud.git
cd Formulario_Crud
```
Tambien puedes copiar los archivos en tu directorio.

# Compilar

Windows
```
javac *.java
```

Linux/MacOs
```
javac *.java
```

# Ejecutar

Windows
```
java -cp ".;sqlite-jdbc-3.50.3.0.jar" Main
```

Linus/MacOs
```
java -cp ".:sqlite-jdbc-3.50.3.0.jar" Main
```
