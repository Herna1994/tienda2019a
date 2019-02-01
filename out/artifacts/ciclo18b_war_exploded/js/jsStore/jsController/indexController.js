(function(){


    new STORE.DOMObjectLook("op_addClient");
    new STORE.DOMObjectLook("op_initSession");


    var ajax = STORE.Ajax;
    var llamada;

    STORE.namespace('STORE.Index.formAddClient');
    STORE.namespace('STORE.Index.formSessionClient');

    var reloj = function(){

        var step = function(){

        }

        setTimeout(step, 100);
    }

    STORE.Index.formAddClient = function(){

        $("cuerpo").innerHTML = STORE.IndexTemplate.formAddUser + STORE.IndexTemplate.formAddClient;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();

        $("submit").addEventListener("click",function () {
            var envio = {


            };
            alert("envio json add");
        });
    };

    STORE.Index.formSessionClient = function(){
        $("cuerpo").innerHTML = STORE.IndexTemplate.formSessionClient;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function () {

            var envio = {
                user : $("user").value,
                password : $("password").value

            };
            var json = JSON.stringify(envio);

            llamada = new ajax.CargadorContenidos("/valiCliSesion", function(){

                var estado = JSON.parse(llamada.req.responseText,JSON.dateParser);

                if ((typeof estado === "number") && (estado > 0)) {
                    sessionStorage.setItem('idCliente', estado);
                    location.reload();
                }
                else {

                    if (typeof estado.intento !== "undefined" ){

                        if (estado.intento < estado.maxIntento){

                            STORE.Error.set_message("Te quedan " + (estado.maxIntento - estado.intento) + " intento(s)")

                        }
                        else {
                            $("menuPrincipal").style.display = "none";
                            $("cuerpo").innerHTML = STORE.IndexTemplate.formSessionLocked;
                            STORE.Error = STORE.managementError();
                            $("locked").style.display = "none";
                            var seconds = 0;
                            var intervalId = null;
                            var locked = function(){
                                if (seconds >= estado.tiempoMaximoBloqueo) {
                                    STORE.Error.set_message("Pulsa Botón para desbloqueo");
                                    $("locked").style.display = "";
                                    clearInterval(intervalId);
                                    $("locked").addEventListener("click",function () {
                                        llamada = new ajax.CargadorContenidos("/offLocked", function(){
                                            location.reload();
                                        });

                                    });
                                }
                                else {
                                    seconds = seconds + 1;
                                    STORE.Error.set_message("Estas Bloqueado. Te quedan " + (estado.tiempoMaximoBloqueo - seconds) + " seconds");
                                }
                            };
                            intervalId = setInterval(locked, 1000);
                        }
                        STORE.Error.on();
                    }

                    else {
                        estado.forEach(function (error) {
                            $(error.control).setAttribute('style', 'backgroundColor:' + STORE.Error.get_colorError() + ' !important');
                            $("alertaError").innerText = error.mensajeError;
                            STORE.Error.on();
                        });
                    }
                }
            }, json);


        });

    };



    $("op_addClient").addEventListener("click",STORE.Index.formAddClient);
    $("op_initSession").addEventListener("click",STORE.Index.formSessionClient);
}());






