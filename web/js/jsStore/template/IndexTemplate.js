
STORE.namespace('STORE.IndexTemplate');
STORE.IndexTemplate = {
    formAddUser :"  <div id='div_nif'>" +
        "                <label for='nif'></label>" +
        "                <input class='etiqueta s8' id='nif' name = 'nif' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarDniNieCif' required placeholder='input your dni' title='nif'>" +
        "            </div>" +
        "            <div id='div_postalCode'>" +
        "                <label class='labelInput' for='postalCode'></label>" +
        "                <input class='etiqueta s8' id='postalCode' name='postalCode' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarCodigoPostal' minlength='5' maxlength='5' required placeholder='Postal Code' title='5 characters'>" +
        "            </div>" +
        "            <div id='div_address'>" +
        "                <label class='labelInput' for='address'></label>" +
        "                <input class='etiqueta s8' id='address' name='address' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarDomicilio' required placeholder='input your address' title='100 characters'>" +
        "            </div>" +
        "            <div id='div_phone'" +
        "                <label for='phone'></label>" +
        "                <select id='prefijoFijo'></select>" +
        "                <input class='etiqueta s8' id='phone' name='phone' value='' type='tel' data-functioncallback='ValidacionExpresionRegular.validarTelefonoFijo' size='20' required placeholder='Tlf Fijo' title='Tlf Fijo'>" +
        "            </div>" +
        "            <div id='div_mobile'>" +
        "                <label for='mobile'></label>" +
        "                <select id='prefijo'></select>" +
        "                <input class='etiqueta s8' id='mobile' name='movilCliente' value='' type='tel' data-functioncallback='ValidacionExpresionRegular.validarTelefonoFijo' size='20' required placeholder='Tlf Movil' title='Tlf Movil'>" + /*"ValidacionExpresionRegular.validarNumeroMovil"*/
        "            </div>" +
        "            <div id='div_email'>" +
        "                <label for='email'></label>" +
        "                <input class='etiqueta s8' id='email' name='email'  value=''  type='email' data-functioncallback='ValidacionExpresionRegular.validarEmail' required placeholder='input your Email' title='Email'>" +
        "            </div>" +
        "            <div id='div_user'>" +
        "                <label class='labelInput' for='user'></label>" +
        "                <input class='etiqueta s8' id='user' name ='user' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarUsuario' size='24' minlength='7' maxlength='7' required placeholder='input your User' title='3 to 50 characters'>" +
        "            </div>" +
        "            <div id='div_password'>" +
        "                <label for='password'></label>" +
        "                <input class='etiqueta s8' id='password' name ='password' value='' type='password' placeholder='Contraseña' data-functioncallback='ValidacionExpresionRegular.validarPassword'>" +
        "            </div>" +
        "            <div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
        "            <button id='submit' >Enviar</button>" +
        "        </div>" +
        "    </div>" +
        "</div> ",

    formAddClient : "<div class='contenido01'>" +
        "    <div id='client_register' " +
        "        <div class='menu s3 caja03'>" +
        "            <h4>New Client</h4>" +
        "            <div id='div_firstname'>" +
        "                <label class='labelInput' for='firstname'></label>" +
        "                <input class='etiqueta s8' id='firstname' name ='firstname' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarLetrasConEspacio' size='24' minlength='3' maxlength='80' required placeholder='input your FirstName' title='3 to 50 characters'>" +
        "            </div>" +
        "            <div id='div_lastname'>" +
        "                <label class='labelInput' for='lastname'></label>" +
        "                <input class='etiqueta s8' id='lastname' name='lastname' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarLetrasConEspacio' minlength='5' maxlength='100' required placeholder='input your LastName' title='3 to 70 characters'>" +
        "            </div>" +
        "            <div id='div_birthdate'>" +
        "                <label class='labelInput' for='birthdate'></label>" +
        "                <input class='etiqueta s8' id='birthdate' name='birthdate' value='' type='date' data-functioncallback='ValidacionExpresionRegular.validarFecha'  required placeholder='birthdate' title='date'>" +
        "            </div>" +
        "            <div id='div_sex'>" +
        "                <label class='labelInput' for='sex'></label>" +
        "                <select class='etiqueta s8 file' id='sex' name='sex' value=''  data-functioncallback='ValidarListaValores.validarSexo'>" +
        "                    <option value=''>-- Select Sexo --</option>" +
        "                    <option value='m'>Hombre</option>" +
        "                    <option value='f'>Mujer</option>" +
        "                </select>" +
        "            </div>",

    formSessionClient : "<div class='contenido01'>" +
        "<div id='client_register'>" +
          "<input type='hidden' value = '' name = 'opcion'>" +
          "<div class='menu s3 caja03'>" +
          "<div id='div_user'>" +
              "<label class='labelInput' for='user'></label>" +
              "<input class='etiqueta s8' id='user' name ='usuarioCliente'  type='text' data-functioncallback='ValidacionExpresionRegular.validarUsuario' size='24' minlength='7' maxlength='7' required placeholder='input your User' title='3 to 50 characters'>" +
          "</div>" +
          "<div id='div_password'>" +
              "<label for='password'></label>" +
              "<input class='etiqueta s8' id='password' name ='password'  type='password' placeholder='Contraseña' data-functioncallback='ValidacionExpresionRegular.validarPassword'>" +
          "</div>" +
          "<div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
          "<button id='submit'>Enviar</button>" +
        "</div>  </div> </div>",

    formSessionLocked : "<div class='contenido01'>" +
        "<div class='menu s3 caja03'>" +
                "<div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
                "<button id='locked'>Locked</button>" +

        "</div>" +
    "</div>",
    formConfirmSN :"<div class='contenido01'>" +
        "<div class='menu s3 caja03'>" +
        "<div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
        "<button id='locked'>Locked</button>" +

        "</div>" +
        "</div>",

};