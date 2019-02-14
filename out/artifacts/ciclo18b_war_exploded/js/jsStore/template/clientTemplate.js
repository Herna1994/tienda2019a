
STORE.namespace('STORE.ClientTemplate');
STORE.ClientTemplate = {
    formImgUser :"<div class=\"contenido01\">\n" +
        "    <img src= \"../img/fotoClient/" + sessionStorage.getItem("idCliente") + ".png?" + Math.random() +" alt=\"\" height=\"200px\" width=\"300px\">\n" +
        "    <form enctype=\"multipart/form-data\" id=\"client_register\" method=\"POST\" action=\"/UpCliAvaCon\">\n" +
        "        <div class=\"menu s3 caja03\">\n" +
        "            <h4>New Image</h4>\n" +
        "            <div class=\"fileinputs etiqueta s2\" id=\"div_clientImage\">\n" +
        "                <input class=\"etiqueta s5 file\" id=\"clientImage\" type=\"file\" name=\"image\" data-functioncallback=\"ValidarFicheroName.validarImagenName\" required accept=\"image/png, image/jpeg\" placeholder=\"input your Avatar\" title=\"3 to 120 characters\">\n" +
        "                <div class=\"fakefile\">\n" +
        "                    <label class=\"labelInput\" for=\"idFile\">Elije tu foto</label>\n" +
        "                    <input id=\"idFile\" name=\"myFile\">\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"etiqueta errorColor\" id=\"alertaError\">Error:</div>\n" +
        "            <button id=\"submit\" type=\"submit\">Enviar</button>\n" +
        "        </div>\n" +
        "    </form>\n" +
        "</div>\n",

    formLoginUser : "<div class='contenido01'>" +
        "<div id='client_register'>" +
        "    <div class='menu s3 caja03'>" +
        "       <h4>Verify</h4>" +
        "       <div id='div_user'>" +
        "           <label class='labelInput' for='user'></label>" +
        "           <input class='etiqueta s8' id='user' name ='user'  type='text' data-functioncallback='ValidacionExpresionRegular.validarUsuario' size='24' minlength='7' maxlength='7' required placeholder='input your User' title='7 characters'>" +
        "       </div>" +
        "       <div id='div_password'>" +
        "          <label for='password'></label>" +
        "          <input class='etiqueta s8' id='password' name ='password'  type='password' placeholder='Contraseña' data-functioncallback='ValidacionExpresionRegular.validarPassword'>" +
        "       </div>" +
        "       <div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
        "       <button id='submit'>Enviar</button>" +
        "</div>  </div> </div>",
    formUpdateLoginUser : "<div class='contenido01'>" +
        "<div id='client_register'>" +
        "    <div class='menu s3 caja03'>" +
        "       <h4>New Login</h4>" +
        "       <div id='div_user'>" +
        "          <label class='labelInput' for='user'></label>" +
        "          <input class='etiqueta s8' id='user' name ='user'  type='text' data-functioncallback='ValidacionExpresionRegular.validarUsuario' size='24' minlength='7' maxlength='7' required placeholder='input your New User' title='7 characters'>" +
        "       </div>" +
        "       <div id='div_password'>" +
        "            <label for='password'></label>" +
        "            <input class='etiqueta s8' id='password' name ='password'  type='password' placeholder='Contraseña' data-functioncallback='ValidacionExpresionRegular.validarPassword' required placeholder='input your New User'>" +
        "       </div>" +
        "       <div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
        "       <button id='submit'>Enviar</button>" +
        "</div>  </div> </div>",

    formUpdateDaperUser : "<div class='contenido01'>" +
        "    <div id='client_register' " +
        "        <div class='menu s3 caja03'>" +
        "            <h4>Update data</h4>" +
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
        "            </div>" +
        "            <div id='div_nif'>" +
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
        "            <div class='etiqueta errorColor' id='alertaError'>Error:</div>" +
        "            <button id='submit' >Enviar</button>" +
        "        </div>" +
        "    </div>" +
        "</div> "
}