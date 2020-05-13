# GRUPO 7 - TP ANUAL

## Resolución Entrega 1

### Diagrama de clase:

![Diagrama](/Diagramas/DiagramaTPAEntrega1v2.png) 

Esta es la alternativa que finalmente decidimos entregar.
-	La organización se relaciona con un Tipo Entidad. Esta interfaz lo que permite es que tanto la EntidadJuridica como la EntidadBase sean polimórficas para la organización.
-	Eliminamos el BuilderUusario ya que la construcción del Usuario es sencilla y por ahora puede realizarse sin tener que aplicar al patrón.
-	El tipo de Usuario es un enum el cual permite acotar los valores.

### Alternativa 1:

![DiagramaAlternativa1](/Diagramas/DiagramaTPAEntrega1Alternativa1.png) 
- BuilderUsuario: en una primera instancia nos pareció buena idea tener este builder para poder utilizarlo durante la construcción y la validación de la contraseña del usuario.
- Decidimos que la compra tenga la información sobre la organización a la que pertenece.
-	La organización se relaciona con una entidad jurídica, ésta a su vez posee muchas entidades base y diferentes tipos.


### Alternativa 2:

![DiagramaAlternativa2](/Diagramas/DiagramaTPAEntrega1Alternativa2.png) 
 - En esta alternativa, realizamos un cambio con respecto a la primera. La relación entre la Organización y la Compra siendo ahora la Organización quien conoce y agrupa las compras.

## Problemática

Fundación Proyecto Emprender (PROEM) es una organización sin fines de lucro fundada en el año 2003.

Su visión reside en contribuir a la obtención del pleno empleo en la República Argentina. En dicho sentido,
su misión es acompañar la preparación laboral de las personas, promover el desarrollo y generación de
emprendimientos y fortalecer organizaciones productivas ya constituidas. PROEM promueve la inclusión
social a través del trabajo. La Fundación acoge a toda persona que necesite apoyo, asesoramiento y/o
acompañamiento en proyectar sus ideas como herramientas para generar un trabajo autónomo, así como
a jóvenes y adultos que necesitan orientación y capacitación para mejorar su formación con el fin de
conseguir un empleo.
Leandro es el presidente de esta Fundación y junto con Belén, coordinadora de proyectos, Ignacio,
coordinador de comunicación y Analía, coordinadora de administración, realizan las tareas de
administración y gestión de la entidad con el mayor de sus esfuerzos. Como muchas ONGs y Pymes,
cuenta con un equipo de trabajo no muy grande, sin especialistas en su staff relacionados con el
aprovechamiento de recursos tecnológicos ni desarrollo de software y, aunque cuentan con un contador de
la Fundación, no posee herramientas que reduzcan las dificultades de la gestión de sus actividades en el
día a día. Con el objetivo de acompañar la resolución de sus problemáticas cotidianas en la gestión se
evalúa la posibilidad de implementar un sistema de información de contabilidad de gestión.

## Sistemas de Información para la Contabilidad de Gestión

Existe diverso software que resuelve algunas problemáticas que se plantean en el presente trabajo, sin
embargo, en ocasiones, su implementación es costosa (en términos monetarios o por los recursos
especializados que requiere), por lo cual no es realizada. Muchos otros productos son enlatados , con la dificultad de personalizarlos para las complejas realidades de estas organizaciones.
Muchas organizaciones sociales se encuentran en la actualidad implementando una herramienta de primer
nivel del mercado, propiedad de una de las compañías más importantes en tecnología informática. Sin
embargo, a pesar de los programas de acceso con licencias gratuitas, no todas las organizaciones tienen
la posibilidad de pagar las consultorías o customizaciones y, por otro lado, a veces estos productos
superan, dada su envergadura, las necesidades más básicas de las organizaciones.
Finalmente, algunos de los productos existentes son implementados para su uso en áreas financieras, con
un foco en obtener la información requerida para presentaciones de índole legal (balances, estados
económicos, etc.), sin un enfoque en la gestión de los proyectos de la organización.
Nuestro sistema estará orientado a la contabilidad de gestión y no a la contabilidad financiera, como
objetivo principal. Para más diferencias entre esto podés ver el Anexo 3.

## Nuestro sistema

El Sistema de Información se orientará en primer lugar a aspectos fundamentales de la contabilidad de
gestión (aunque no trataremos aspectos complejos de contabilidad, ni economía, ni matemática) y en las
entregas finales ampliará su alcance hacia la inclusión de conceptos de proyectos.
Las funcionalidades previstas en su alcance son:
Operaciones de egreso (compras)
* Presupuestos
* Validación de transparencia administrativa (egresos - presupuestos)
* Operaciones de ingresos
* Categorización de egresos e ingresos
* Proyectos y rendición de cuentas de proyectos

## Modelo de servicio

Cabe destacar que, como se mencionó anteriormente, Fundación PROEM no es la única organización que
enfrenta estos problemas, por lo cual el sistema a diseñar y desarrollar deberá poder adaptarse a
cualquiera de éstas.
Siendo este el escenario, se optará por diseñar, desarrollar y brindar un servicio en el que cualquier
organización interesada pueda hacer uso del sistema de información directamente a través de la web y sin
la necesidad de tener que instalar ningún componente de forma local.

# Entrega 1

## Objetivo de la entrega
* Familiarizarse con el dominio, sus abstracciones preliminares y las tecnologías de base con las que trabajaremos.
* Familiarizarse con la lectura, interpretación e implementación de estándares, tales como los de seguridad

## Alcance
* Operaciones de egreso (compras)
* Entidades
* Registro para la autenticación de usuario

## Glosario
* Una operación de egreso es un gasto realizado por una organización. Una operación de egreso puede contener varios artículos (o ítems de servicios). En general, una operación de egreso está asociada a un documento comercial.
* Un medio de pago es un bien o instrumento que puede ser utilizado para adquirir bienes, servicios y/o cancelar todo tipo de obligaciones .
* Los documentos comerciales son todos aquellos comprobantes emitidos (...), y que se realizan para la actividad mercantil. Estos documentos sirven para la actividad contable, y están sujetos a las normas de fiscalización del país. Todas las operaciones que realiza la empresa deben ser plasmadas en estos documentos comerciales. Transacciones como compra, venta, pagos, cobros y otras como fabricación o inversiones, por ejemplo. Puede tratarse de facturas, tickets, etc.

## Requerimientos detallados
En la primera etapa del proyecto se llevará registro de los gastos realizados o previstos. Se plantean los siguientes requerimientos:

### Sobre las operaciones de egreso
* Se debe llevar registro de todas las operaciones de egresos de fondos a través de diversos medios de pagos.
* Aunque es deseable que una operación de egresos tenga asociado un documento comercial
(factura, ticket, etc.) no es necesariamente lo esperado en esta etapa del proyecto y se debe permitir (excepto ciertas circunstancias que deberán validarse y serán presentadas en próximas iteraciones) que existan operaciones sin su correspondiente sustento documental (o sea, se puede realizar una compra, con diversos ítems, pero no tener factura asociada).
* De cada operación de egreso de fondos se debe conocer el proveedor o prestador de servicios (nombre y apellido o razón social, DNI o CUIL/CUIT, dirección postal), la fecha de la operación, el valor total por el cual se realiza la operación y medio de pago, los datos del documento comercial (tipo y número de documento comercial) y los datos propios, según lo definido en “sobre las organizaciones”.
* Además, de cada operación de egreso de fondos se deben conocer los ítems (el detalle)
incluyendo los productos o servicios incluidos (su descripción) y valor total del ítem (la restante información no es necesaria para el objetivo del sistema)
* De los medios de pago se debe registrar el medio en sí mismo y algún dato que permita identificar el instrumento utilizado (por ejemplo, si es una tarjeta de débito, su número ; si es un cheque, su número; etc.)
* Para la definición de medios de pago se deben utilizar los definidos por Mercado Pago , a saber: Tarjeta de crédito, Tarjeta de débito, Efectivo, Cajero Automático o Dinero en Cuenta. Por ejemplo, ‘Visa’ y ‘MasterCard’ son dos medio de pago de tipo Tarjeta de Crédito mientras que ‘Rapipago’ y ‘PagoFácil’ son dos medios de pago de tipo Efectivo.

### Sobre las organizaciones
* Cada organización podrá manejar una estructura de entidades, teniendo algunas entidades
jurídicas y algunas entidades base. Las primeras son las entidades reconocidas formalmente e
inscriptas en la Inspección General de Justicia (IGJ), las segundas pueden ser estructuras que en la gestión son organizaciones distintas, pero no lo son en la formalidad. Por ejemplo, si dos microempresarios realizan producción textil (uno, remeras; el otro, pantalones) y lo hacen de manera informal (en términos normativos), entonces pueden agruparse en una cooperativa única para poder atender los requerimientos legales, pero con continuidad de sus negocios de forma separada.
* De las entidades jurídicas se conoce su razón social, nombre ficticio, CUIT, dirección postal y, optativamente, código de inscripción definitiva en IGJ.
* De las entidades base se conoce un nombre ficticio y una descripción.
* Una entidad base puede pertenecer a sólo una entidad jurídica
* Una entidad jurídica puede no tener entidades base
* A lo largo del sistema, se considerará que todos los requerimientos pueden ser asociados a una entidad jurídica o una entidad base, según el usuario seleccione en cada momento, excepto que se indique lo contrario.

### Sobre el tipo de organizaciones
* Las entidades jurídicas serán categorizadas en Empresas y OSC (Organizaciones del sector
social).
* En el caso de empresas, estas se clasifican en Micro, Pequeña, Mediana Tramo 1, Mediana Tramo
* Dicha clasificación responderá a los criterios estipulados por la AFIP

## Requerimientos de seguridad

* Se debe permitir crear usuarios para la aplicación, con dos tipos de usuarios: administrador y estándar. Por el momento, sólo se requiere guardar usuario y contraseña , no se requiere ningún correo electrónico, ni ninguna validación con el correo electrónico al momento de crear el usuario.
* Siguiendo las recomendaciones del OWASP (Proyecto Abierto de Seguridad en Aplicaciones Web), que se ha constituido en un estándar de facto para la seguridad, se pide:

** Implemente controles contra contraseñas débiles. Cuando el usuario ingrese una nueva clave, la misma puede verificarse contra la lista del Top 10.000 de peores contraseñas.

** Alinear la política de longitud, complejidad y rotación de contraseñas con las recomendaciones de la Sección 5.1.1 para Secretos Memorizados de la Guía NIST 800-63. Por ahora solamente se deberán implementar tres recomendaciones de esta guía (Por ejemplo, una recomendación puede ser Que la contraseña no lleve caracteres consecutivos como ‘abc123’). Sin embargo el diseño deberá ser lo suficientemente flexible para soportar más recomendaciones a futuro.

## Entregables requeridos
* Modelo de Objetos (Diagrama de clases)
* Implementación en código Java los requerimientos enumerados.
* Alternativas pensadas para diferentes requerimientos según corresponda y su comparación.
