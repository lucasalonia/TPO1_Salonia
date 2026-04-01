# TPO1_Salonia

### Aplicación de conversión de moneda - Tecnicatura en Desarrollo de Software (ULP)

**Descripción General**
Desarrollo individual de un conversor de divisas nativo para Android que permite definir tasas de cambio personalizadas y realizar cálculos bidireccionales.

**Arquitectura y Patrones**
* **MVVM (Model-View-ViewModel):** Separación de la lógica de negocio y la interfaz.
* **View Binding:** Manipulación segura de componentes del layout.
* **LiveData:** Observación reactiva de cambios de datos desde la Activity.

**Desarrollo de Vistas (UI)**
* **Diseño Híbrido:** Los layouts se crearon inicialmente de forma manual. Posteriormente, se reestructuraron mediante código utilizando inteligencia artificial para agilizar y optimizar el diseño de la primera vista (MainActivity).

**Componentes Técnicos**
* **ViewModel:** Se encarga de la logica de negocios, realizando las conversiones. Gestiona el estado y la navegación. `MainActivityViewModel` verifica si el objeto de referencia es nulo; en tal caso, dispara un Intent hacia la configuración para forzar la carga inicial de datos.
* **Modelo (Clase Conversion):** Se limita solo a la obtencion de datos de la entidad CRUD (En este caso solo set y get, update y select) Implementa la interfaz `Serializable`. Esto permite la transferencia de la instancia completa a través de los extras del Intent, evitando la desestructuración de datos. 
* **Lógica de Cálculo:** Implementación de regla de tres simple para procesar conversiones basadas en el HashMap de referencia (Dólar/Peso).
* **Validación:** Manejo de excepciones `NumberFormatException` y control de strings vacíos para prevenir cierres inesperados. Quedan pendientes otros tipos como en la carga de datos para tasas de conversion

**Autor:** Luca Salonia
**Año:** 2026