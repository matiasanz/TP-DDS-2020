function _crearCelda(row, tipo, nombre) {
    var cantidad_items = document.getElementById("cantidad_items");

    var celda = row.insertCell(0);
    var input = document.createElement("input");
    input.setAttribute("contenteditable", true);
    input.setAttribute("type", tipo);
    input.setAttribute("name", nombre + "_" + cantidad_items.value);
    input.setAttribute("id", nombre + "_" + cantidad_items.value);
    input.setAttribute("required", "required");
    celda.appendChild(input);
}

function _crearCeldaEtiqueta(row, tipo, nombre) {
    var cantidad_etiquetas = document.getElementById("cantidad_etiquetas");

    var celda = row.insertCell(0);
    var input = document.createElement("input");
    input.setAttribute("type", tipo);
    input.setAttribute("name", nombre + "_" + cantidad_etiquetas.value);
    input.setAttribute("required", "required");
    celda.appendChild(input);
}

function _habilitarBotones(valor) {
  document.getElementById("btn_eliminar_items").disabled = valor;
  document.getElementById("btn_guardar_compra").disabled = valor;
}

function _habilitarBotonesEtiquetas(valor) {
  document.getElementById("btn_eliminar_etiquetas").disabled = valor;
  document.getElementById("btn_guardar_etiquetas").disabled = valor;
}

function agregarItem() {
  var table = document.getElementById("tbl_items");
  var row = table.insertRow(document.getElementById("tbl_items").rows.length);

  var cantidad_items = document.getElementById("cantidad_items");
  cantidad_items.value = document.getElementById("tbl_items").rows.length- 1;

  _crearCelda(row, "number", "txt_valor");
  var txt_valor = document.getElementById("txt_valor_" + cantidad_items.value);
  txt_valor.setAttribute("step","any");
  _crearCelda(row, "text", "txt_descripcion");
  _crearCelda(row, "number", "txt_cantidad");

  var txt_valor = document.getElementById("txt_valor_" + cantidad_items.value);
  txt_valor.setAttribute("step","any");
  txt_valor.setAttribute("min", "1");

  var txt_cantidad = document.getElementById("txt_cantidad_" + cantidad_items.value);
  txt_cantidad.setAttribute("min", "1");

  _habilitarBotones(false);
}

function agregarEtiqueta() {
  var table = document.getElementById("tbl_etiquetas");
  var row = table.insertRow(table.rows.length);

  var cantidad_etiquetas = document.getElementById("cantidad_etiquetas");
  cantidad_etiquetas.value = document.getElementById("tbl_etiquetas").rows.length- 1;

  _crearCeldaEtiqueta(row, "text", "txt_etiqueta");
  var txt_etiqueta = document.getElementById("txt_etiqueta_" + cantidad_etiquetas.value);

  _habilitarBotonesEtiquetas(false);
}

function eliminarItem() {

  var cantidad_items = document.getElementById("cantidad_items");
  if(cantidad_items.value < 1)
    return;

    document.getElementById("tbl_items").deleteRow(cantidad_items.value);
    cantidad_items.value -= 1;

    _habilitarBotones(cantidad_items.value == 0);
}

function eliminarEtiqueta() {

  var cantidad_etiquetas = document.getElementById("cantidad_etiquetas");
  if(cantidad_etiquetas.value < 1)
    return;

    document.getElementById("tbl_etiquetas").deleteRow(cantidad_etiquetas.value);
    cantidad_etiquetas.value -= 1;

    _habilitarBotonesEtiquetas(cantidad_etiquetas.value == 0);
}