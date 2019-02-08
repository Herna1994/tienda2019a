
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
        "</div>  </div> </div>"
}