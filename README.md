# Récord del Jugador (JavaFX + MVC)

Aplicación JavaFX que permite **guardar** y **consultar** el nombre y el puntaje de un jugador
en el archivo de texto `record.txt`, usando `BufferedReader`, `BufferedWriter` y manejo de excepciones.

## Interfaz

| Elemento | Función |
|---|---|
| Campo **Nombre** | Ingresar el nombre del jugador |
| Campo **Puntaje** | Ingresar un entero mayor o igual a cero |
| Botón **Guardar** | Guarda los datos y reemplaza el registro anterior |
| Botón **Cargar** | Recupera los datos del archivo y los muestra en los campos |
| Etiqueta de mensaje | Informa el resultado (verde) o explica el error (rojo) |

## Estructura (MVC)

- **Vista** (`RecordVista`): solo construye los controles y ofrece métodos para mostrar mensajes. No valida ni toca archivos.
- **Controlador** (`RecordControlador`): registra los eventos `setOnAction` de *Guardar* y *Cargar*, valida que el nombre no esté vacío y que el puntaje sea un entero `>= 0`, y traduce las excepciones a mensajes para el usuario.
- **Modelo**:
  - `Jugador`: guarda nombre y puntaje; también rechaza datos inválidos en su constructor.
  - `RecordArchivo`: usa `BufferedWriter` (con `FileWriter(..., false)` para **reemplazar** el registro) y `BufferedReader` para leerlo. Usa *try-with-resources* para cerrar siempre los archivos.

### Formato de `record.txt`

Línea 1: nombre. Línea 2: puntaje.

## Manejo de errores

| Situación | Mensaje mostrado |
|---|---|
| Nombre vacío | "Error: el nombre no puede estar vacío." |
| Puntaje vacío | "Error: debe ingresar un puntaje." |
| Puntaje con letras o decimales (`abc`, `12.5`) | "Error: el puntaje debe ser un número entero" |
| Puntaje negativo | "Error: el puntaje debe ser mayor o igual a cero." |
| Se pulsa *Cargar* sin haber guardado nunca | "Aún no hay un récord guardado..." (`FileNotFoundException`) |
| `record.txt` vacío o modificado a mano con datos inválidos | "No se pudo cargar el récord: ..." (`IOException` / `NumberFormatException`) |
| Error al escribir (permisos, disco) | "No se pudo guardar el archivo: ..." (`IOException`) |

## Cómo ejecutar

Requisitos: JDK 17 o superior y Maven (o un IDE como IntelliJ / NetBeans, que traen Maven integrado).

```bash
mvn clean javafx:run
```


## Dudas que me surgieron

- No entendi bien donde se crea el record.txt. La primera vez lo busque en la carpeta src y no estaba, al final aparecio en la raiz del proyecto. Lei que es por el "directorio de trabajo" pero no se si al hacer un .jar se crearia en otro lado, o si es mejor ponerle una ruta fija

- La validacion la hice en el controlador como dice el enunciado pero tambien puse que la clase Jugador no acepte puntajes negativos. No se si eso esta bien o estoy repitiendo codigo o si la validacion deberia ir solo en un lado

- Para que sirve el BufferedWriter si igual tambien se usa el FileWriter adentro? entiendo que el buffer guarda todo y despues escribe de una vez pero con 2 lineas no veo la diferencia

- El FileWriter tiene un true/false al final, con false se borra lo que habia y con true se agrega. Aca use false porque hay que reemplazar el registro, pero si quisiera guardar varios jugadores (como un top 10) como se haria? tocaria leer todo el archivo primero?

- Que pasa si alguien escribe un nombre con un salto de linea? como guardo el nombre en una linea y el puntaje en la otra creo que se dañaria el archivo. Probe y el TextField no me deja dar enter, pero no se si con copiar y pegar si se puede

- En el controlador puse un catch para FileNotFoundException para que salga un mensaje cuando todavia no se ha guardado nada. Pero me quedo la duda si el controlador deberia saber eso del archivo o si eso le toca al modelo nada mas

- El enunciado dice reemplazar el registro anterior, entonces siempre lo reemplazo aunque el puntaje nuevo sea menor. Pero un "record" no deberia cambiar solo si se supera? no supe cual era la idea

- Al principio los nombres con tilde o ñ me salian raros, por eso le puse UTF-8. No entiendo muy bien porque pasa eso ni si en otro computador se veria diferente

