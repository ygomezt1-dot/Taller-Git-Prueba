# GrafoJavaNetBeans

Proyecto Java de consola para practicar operaciones básicas sobre un **grafo no dirigido**. Está preparado como proyecto Ant para abrirse directamente en Apache NetBeans.

## Abrir y ejecutar
1. Descargue y extraiga el ZIP completo.
2. Abra Apache NetBeans.
3. Seleccione **File (Archivo) > Open Project (Abrir proyecto)**.
4. Busque la carpeta extraída `GrafoJavaNetBeans` y selecciónela (debe verse como proyecto de NetBeans).
5. Espere a que termine de cargar y presione **Run Project (F6)**. Si pregunta por la clase principal, seleccione `grafo.GrafoOperaciones`.

Requiere un JDK instalado (Java 17 o superior recomendado). No usa librerías externas.

## Operaciones del menú
1. Agregar vértice.
2. Agregar arista/conexión entre dos vértices existentes.
3. Buscar vértice y ver sus conexiones.
4. Eliminar una arista.
5. Eliminar un vértice y todas sus conexiones.
6. Mostrar el grafo.
7. Cargar un ejemplo de ciudades.
0. Salir.

## Prueba rápida
Seleccione `7` para cargar el ejemplo, luego `6` para mostrarlo y `3` para buscar `Bogota`. El grafo de ejemplo incluye Bogota, Ibague, Medellin y Armenia.

## Cómo funciona
Se usa un `Map` para asociar cada vértice con un conjunto de vecinos (`Set`). Es no dirigido, por lo que una conexión A-B se guarda tanto en A como en B. Al eliminar un vértice, se quita también de las conexiones de sus vecinos.
