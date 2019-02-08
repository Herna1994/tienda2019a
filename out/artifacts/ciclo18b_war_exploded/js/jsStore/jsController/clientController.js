(function(){


    new STORE.DOMObjectLook("op_updateLoginClient");
    new STORE.DOMObjectLook("op_updateDaperClient");
    new STORE.DOMObjectLook("op_updateAvatarClient");
    new STORE.DOMObjectLook("op_deleteClient");

    var ajax = STORE.Ajax;
    var llamada;

    $("op_updateAvatarClient").addEventListener("click",function () {
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function(){
           var verificar = verificarUser();
            if ((typeof verificar !== "undefined") && verificar.indexOf("VERYOK")) {

                $("cuerpo").innerHTML = STORE.ClientTemplate.formImgUser;
            }
        });
    });
    $("op_updateLoginClient").addEventListener("click", function(){
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function(){
            var verificar = verificarUser();
            if ((typeof verificar !== "undefined") && verificar.indexOf("VERYOK")) {

                $("cuerpo").innerHTML = STORE.ClientTemplate.formUpdateLoginUser;
                STORE.Error = STORE.managementError();
                STORE.Submit = STORE.managementSubmit();
                STORE.strategyOneByOne();
                $("submit").addEventListener("click",function() {

                    var validar = validarNewUser();
                    alert("validarNewUser:" + validar);
                    if ((typeof validar !== "undefined") && validar.indexOf("OK")) {

                        // Update
                        alert("Update User");
                        var envio = {
                            user : $("user").value,
                            password : $("password").value
                        };
                        var json = JSON.stringify(envio);

                        llamada = new ajax.CargadorContenidos("/updateLogin", function() {
                            if (llamada.req.responseText.indexOf("OK") !== -1){
                                $("cuerpo").innerHTML ="";
                            }

                        },json);
                    }else{
                        STORE.Error.set_message("Login Modificado");
                        STORE.Error.on();
                    }
                });
            }
        });
    });

}());
var verificarUser = function(){
    var ajax = STORE.Ajax;
    var verify = "NO";
    var envio = {
        user : $("user").value,
        password : $("password").value
    };
    var json = JSON.stringify(envio);

    llamada = new ajax.CargadorContenidos("/veriLogin", function() {

        if (llamada.req.responseText.indexOf("Lock") !== -1){
            STORE.Error.set_message(llamada.req.responseText);
            STORE.Error.on();
            STORE.Submit.off();
            var seconds = 0;
            var tiempoMaximo = 8;
            var intervalId = null;
            var locked = function(){
                if (seconds >= tiempoMaximo) {
                    clearInterval(intervalId);
                    location.reload();
                }
                else {
                    seconds = seconds + 1;
                    STORE.Error.set_message("Bloqueado. Mira tu correo. Adios en " + (tiempoMaximo - seconds) + " seconds");
                }
            };
            intervalId = setInterval(locked, 1000);

        }else {
            var estado = JSON.parse(llamada.req.responseText);

            if ((typeof estado === "number") && (estado > 0)) {
                if (estado == sessionStorage.getItem("idCliente")) {
                    verify = "VERYOK";
                }
                else {
                    STORE.Error.set_message("¡Uy! No te esperaba");
                    STORE.Error.on();
                }
            }
            else {
                if (typeof estado.intento !== "undefined") {

                    if (estado.intento < estado.maxIntento) {

                        STORE.Error.set_message("Te quedan " + (estado.maxIntento - estado.intento) + " intento(s)")
                    }
                    else {
                        STORE.Error.set_message("Estas Bloqueado. Mira tu Email");
                    }
                    STORE.Error.on();
                }
            }
        }
    }, json);
    return verify;
};
var validarNewUser = function(){
    STORE.Error = STORE.managementError();
    STORE.Error.on();
    var ajax = STORE.Ajax;
    var validar = "INVALID";
    var envio = {
        user : $("user").value,
        password : $("password").value
    };
    var json = JSON.stringify(envio);

    llamada = new ajax.CargadorContenidos("/valiNewSession", function() {


        if (llamada.req.responseText.indexOf("OK") !== -1){
            STORE.Error.set_message("Login Modificado");
            validar = "OK";
        }
        if (llamada.req.responseText.indexOf("INVALID") !== -1){

            STORE.Error.set_message("Login NO Modificado");

        }
            else {
                estado.forEach(function (error) {
                    $(error.control).setAttribute('style', 'backgroundColor:' + STORE.Error.get_colorError() + ' !important');
                    $("alertaError").innerText = error.mensajeError;
                    STORE.Error.on();
                });
            }

    }, json);
return validar;
}