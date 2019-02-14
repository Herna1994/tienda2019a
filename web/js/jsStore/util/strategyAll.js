STORE.namespace('STORE.strategyAll');

STORE.strategyAll = function(){

    'use strict';

    var form = $("client_register");

    STORE.list_input = form.querySelectorAll("[data-functioncallback]");

    STORE.Error.off();
    STORE.Submit.off();

    for (var i = 0; i < STORE.list_input.length; i++) {

        STORE.list_input[i].addEventListener("input",eval("STORE." + STORE.list_input[i].dataset.functioncallback),false);

    }
};



