STORE.namespace('STORE.StoreTemplate');
STORE.StoreTemplate = {
    formAddUser :
        "<div class='contenido01'>" +
        "    <form enctype='multipart/form-data' id='client_register' " +
        "        <div class='menu s3 caja03'>" +
        "            <h4>New User</h4>" +
        "            <div id='div_nif'>" +
        "                <label for='nif'></label>" +
        "                <input class='etiqueta s8' id='nif' name = 'nif' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarDniNieCif' required placeholder='input your dni' title='nif'>" +
        "            </div>" +
        "            <div id='div_postalCode'>" +
        "                <label class='labelInput' for='postalCode'></label>" +
        "                <input class='etiqueta s8' id='postalCode' name='postalCode' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarCodigoPostal' minlength='2' maxlength='100' required placeholder='Adress' title='5 characters'>" +
        "            </div>" +
        "            <div id='div_address'>" +
        "                <label class='labelInput' for='address'></label>" +
        "                <input class='etiqueta s8' id='address' name='address' value='' type='text' data-functioncallback='ValidacionExpresionRegular.validarDomicilio' required placeholder='input your cp' title='5 characters'>" +
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
        "            <div class='fileinputs etiqueta s8' id='div_image'>" +
        "                <input class='etiqueta s8 file' id='image' type='file' name='imagenCliente' data-functioncallback='ValidarFicheroName.validarImagenName' required accept='image/png, image/jpeg' placeholder='input your Avatar' title='3 to 120 characters'>" +
        "                <div class='fakefile'>" +
        "                    <label class='labelInput' for='image'>Elije tu foto</label>" +
        "                    <input id='image' name='myFile'>" +
        "                </div>" +
        "            </div>" +
        "</div> "



};