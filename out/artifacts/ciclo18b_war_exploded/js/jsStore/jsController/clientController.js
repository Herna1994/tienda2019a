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

            var envio = {
                user : $("user").value,
                password : $("password").value
            };
            var json = JSON.stringify(envio);

            llamada = new ajax.CargadorContenidos("/veriLogin", function() {

                var estado = JSON.parse(llamada.req.responseText);

                if ((typeof estado === "number") && (estado > 0)) {
                    if (estado == sessionStorage.getItem("idCliente"))
                    {
                        $("cuerpo").innerHTML = STORE.ClientTemplate.formImgUser;
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
                        else{
                            STORE.Error.set_message("Has agotado todos los intentos");
                        }
                        STORE.Error.on();
                    }
                }
            }, json);
        });
    });
    $("op_updateLoginClient").addEventListener("click", function(){
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function(){

            var envio = {
                user : $("user").value,
                password : $("password").value
            };
            var json = JSON.stringify(envio);

            llamada = new ajax.CargadorContenidos("/veriLogin", function() {

                var estado = JSON.parse(llamada.req.responseText);

                if ((typeof estado === "number") && (estado > 0)) {
                    sessionStorage.setItem('idCliente', estado);
                    location.reload();
                }
            });
        });
    });

}());
