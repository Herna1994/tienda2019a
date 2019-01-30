 new STORE.DOMObjectLook("op_addClient");
 new STORE.DOMObjectLook("op_initSession");

 var ajax = STORE.Ajax;
 var llamada;

 STORE.namespace('STORE.formAddClient');
 STORE.namespace('STORE.formSessionClient');

 STORE.formAddClient = function(){

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

 STORE.formSessionClient = function(){
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
             
           var estado = JSON.parse(llamada.req.responseText);

           if (typeof estado === "number") {
               if (estado == 0) {alert(estado);}// No está en la BB DD
               else alert(estado);

           }
  else {
               estado.forEach(function (error) {
                   //$(error.control).style.backgroundColor = STORE.Error.get_colorError();
                   $(error.control).setAttribute('style', 'backgroundColor:' + STORE.Error.get_colorError() + ' !important');
                   $("alertaError").innerText = error.mensajeError;
                   STORE.Error.on();
               });

           }
         }, json);


     });

 };

 $("op_addClient").addEventListener("click",STORE.formAddClient);
 $("op_initSession").addEventListener("click",STORE.formSessionClient);


