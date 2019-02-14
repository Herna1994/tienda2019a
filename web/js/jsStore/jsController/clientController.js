(function(){


    new STORE.DOMObjectLook("op_updateLoginClient");
    new STORE.DOMObjectLook("op_updateDaperClient");
    new STORE.DOMObjectLook("op_updateAvatarClient");
    new STORE.DOMObjectLook("op_deleteClient");

    var ajax = STORE.Ajax;
    var llamada;
    var envio;
    var json;
    var verificar = "NO";

    $("op_updateAvatarClient").addEventListener("click",function () {
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function(){

            envio = {
                user : $("user").value,
                password : $("password").value
            };
            json = JSON.stringify(envio);

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
                            else {
                                STORE.Error.set_message("Estas Bloqueado. Mira tu Email");
                            }
                            STORE.Error.on();
                        }
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

            envio = {
                user : $("user").value,
                password : $("password").value
            };
            json = JSON.stringify(envio);
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

                }
                    else {
                        var estado = JSON.parse(llamada.req.responseText);

                        if ((typeof estado === "number") && (estado > 0)) {

                            if (estado == sessionStorage.getItem("idCliente")) {

                                $("cuerpo").innerHTML = STORE.ClientTemplate.formUpdateLoginUser;
                                STORE.Error = STORE.managementError();
                                STORE.Submit = STORE.managementSubmit();
                                STORE.strategyOneByOne();
                                $("submit").addEventListener("click",function() {

                                    envio = {
                                        user : $("user").value,
                                        password : $("password").value
                                    };
                                    json = JSON.stringify(envio);

                                    llamada = new ajax.CargadorContenidos("/valiNewSession", function() {


                                        if (llamada.req.responseText.indexOf("OK") !== -1){

                                             envio = {
                                                user : $("user").value,
                                                password : $("password").value
                                            };
                                             json = JSON.stringify(envio);

                                            llamada = new ajax.CargadorContenidos("/updateLogin", function() {
                                                if (llamada.req.responseText.indexOf("OK") !== -1){
                                                    $("cuerpo").innerHTML ="";
                                                }
                                                else{
                                                    STORE.Error.set_message("Error en updateLogin");
                                                    STORE.Error.on();
                                                }

                                            },json);




                                        }
                                        if (llamada.req.responseText.indexOf("INVALID") !== -1){

                                            STORE.Error.set_message("Usuario ocupado");

                                        }
                                        else {
                                            estado.forEach(function (error) {
                                                $(error.control).setAttribute('style', 'backgroundColor:' + STORE.Error.get_colorError() + ' !important');
                                                $("alertaError").innerText = error.mensajeError;
                                                STORE.Error.on();
                                            });
                                        }

                                    }, json);

                                });
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
      });
    });
    $("op_deleteClient").addEventListener("click", function(){
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click",function(){
            // verificarUser()
            envio = {
                user : $("user").value,
                password : $("password").value
            };
             json = JSON.stringify(envio);

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

                            if(confirm ("estas seguro de querer eliminarte")) {
                                llamada = new ajax.CargadorContenidos("/deleteUser", function () {
                                    if (llamada.req.responseText.indexOf("OK") !== -1) {
                                        $("cuerpo").innerHTML = "";
                                        location.reload();
                                    }
                                    else {
                                        STORE.Error.set_message("Error al eliminar");
                                        STORE.Error.on();
                                    }
                                });
                            }
                            else {
                                alert("Eliminación Cancelada");
                                $("cuerpo").innerHTML = "";
                            }
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

        });
    });
    $("op_updateDaperClient").addEventListener("click", function() {
        $("cuerpo").innerHTML = STORE.ClientTemplate.formLoginUser;
        STORE.Error = STORE.managementError();
        STORE.Submit = STORE.managementSubmit();
        STORE.strategyOneByOne();
        $("submit").addEventListener("click", function () {

            envio = {
                user: $("user").value,
                password: $("password").value
            };
            json = JSON.stringify(envio);
            llamada = new ajax.CargadorContenidos("/veriLogin", function () {

                if (llamada.req.responseText.indexOf("Lock") !== -1) {
                    STORE.Error.set_message(llamada.req.responseText);
                    STORE.Error.on();
                    STORE.Submit.off();
                    var seconds = 0;
                    var tiempoMaximo = 8;
                    var intervalId = null;
                    var locked = function () {
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

                }
                else {
                     estado = JSON.parse(llamada.req.responseText);

                    if ((typeof estado === "number") && (estado > 0)) {

                        if (estado == sessionStorage.getItem("idCliente")) {


                            llamada = new ajax.CargadorContenidos("/getDaperClient", function () {
                                $("cuerpo").innerHTML = STORE.ClientTemplate.formUpdateDaperUser;
                                STORE.Error = STORE.managementError();
                                STORE.Submit = STORE.managementSubmit();
                                var estado = JSON.parse(llamada.req.responseText);
                                $("firstname").value = estado.firstname;
                                $("lastname").value = estado.lastname;
                                $("birthdate").value = estado.birthdate;
                                $("sex").value = estado.sex;
                                $("postalCode").value = estado.postalCode;
                                $("nif").value = estado.nif;
                                $("address").value = estado.address;
                                $("phone").value = estado.phone;
                                $("mobile").value = estado.mobile;
                                $("email").value = estado.email;

                                 STORE.strategyAll();
                                $("submit").addEventListener("click", function () {

                                    envio = {
                                        firstname: $("firstname").value,
                                        lastname: $("lastname").value,
                                        birthdate: $("birthdate").value,
                                        sex: $("sex").value,
                                        nif: $("nif").value,
                                        postalCode: $("postalCode").value,
                                        address: $("address").value,
                                        phone: $("phone").value,
                                        mobile: $("mobile").value,
                                        email: $("email").value
                                    };

                                    json = JSON.stringify(envio);

                                    llamada = new ajax.CargadorContenidos("/updateDaper", function () {
                                        if (llamada.req.responseText == "OK"){
                                            alert("Tus datos han sido actualizados");
                                            $("cuerpo").innerHTML  = "";
                                        }
                                        else {
                                            alert("Tus datos NO han sido actualizados");
                                        }

                                    }, json);
                                });
                            });
                        }
                        else {
                            STORE.Error.set_message("Uy No te esperaba");
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
        });
    });

}());


/* llamada = new ajax.CargadorContenidos("/valiNewSession", function() {


     if (llamada.req.responseText.indexOf("OK") !== -1){

         envio = {
             user : $("user").value,
             password : $("password").value
         };
         json = JSON.stringify(envio);

         llamada = new ajax.CargadorContenidos("/updateLogin", function() {
             if (llamada.req.responseText.indexOf("OK") !== -1){
                 $("cuerpo").innerHTML ="";
             }
             else{
                 STORE.Error.set_message("Error en updateLogin");
                 STORE.Error.on();
             }

         },json);




     }
     if (llamada.req.responseText.indexOf("INVALID") !== -1){

         STORE.Error.set_message("Usuario ocupado");

     }
     else {
         estado.forEach(function (error) {
             $(error.control).setAttribute('style', 'backgroundColor:' + STORE.Error.get_colorError() + ' !important');
             $("alertaError").innerText = error.mensajeError;
             STORE.Error.on();
         });
     }

 }, json);*/
