STORE.namespace('STORE.Prefijos');
STORE.namespace('STORE.prefix_input');
STORE.Prefijos = function(){
    'use strict';

    var campoMovil = "mobile"; //1 es movil
    var campoFijo = "phone"; //2 es fijo

    // Lista de expresiones regulares.
    const NUMERO_SPAIN_MOVIL = /^[6|7][0-9]{8}$/;
    const NUMERO_US_MOVIL = /^[0-9]{10}$/;
    const NUMERO_SPAIN_FIJO = /^[9][0-9]{8}$/;
    const NUMERO_US_FIJO = /^[0-9]{10}$/;

    // Objeto prefijo con todos los refijos soportados.
    const PREFIJOS = [
        { "prefijo": "US", "value": "+1", "maximo": 10, "flag" : "us.png", "expresionRegularMovil": NUMERO_US_MOVIL, "expresionRegularFijo": NUMERO_US_FIJO},
        { "default" : true, "prefijo": "ES", "value": "+34", "maximo": 9, "flag": "spain.png", "expresionRegularMovil": NUMERO_SPAIN_MOVIL, "expresionRegularFijo": NUMERO_SPAIN_FIJO}
    ];

    var cambiarExpRegular = function(tipo) {
            // Usamos este valor para saber que tipo de campo es.
            let campoLimite = null;

            console.log(tipo);

            if (tipo === 1) {
                campoLimite = campoMovil;
                var selectedValue = prefijo.options[prefijo.selectedIndex].value;
            }

            if (tipo === 2) {
                campoLimite = campoFijo;
                var selectedValue = prefijoFijo.options[prefijoFijo.selectedIndex].value;
            }

            console.log(selectedValue);
            for (var index in PREFIJOS) {
                if (PREFIJOS[index].value === selectedValue) {
                    STORE.prefix_input.regExpMovil = PREFIJOS[index].expresionRegularMovil;
                    STORE.prefix_input.regExpFijo = PREFIJOS[index].expresionRegularFijo;
                    STORE.prefix_input.minimo = PREFIJOS[index].minimo;
                    STORE.prefix_input.maximo = PREFIJOS[index].maximo;
                    limitarCamposDeTexto(campoLimite, PREFIJOS[index].maximo);
                }
            }
        };

    var limitarCamposDeTexto = function (campo, limite) {
            document.getElementById(campo).setAttribute("maxlength", limite);
        };

    const prefijoFijo = document.getElementById('prefijoFijo');

    for (var index in PREFIJOS) {
        prefijoFijo.options[prefijoFijo.options.length] = new Option(PREFIJOS[index].prefijo, PREFIJOS[index].value, undefined, PREFIJOS[index].default);
        if (PREFIJOS[index].default) {
            STORE.prefix_input.regExpFijo = PREFIJOS[index].expresionRegularFijo;
            limitarCamposDeTexto(campoFijo, PREFIJOS[index].maximo);
        }
    }

    const prefijo = document.getElementById('prefijoMovil');

    for (var index in PREFIJOS) {
        prefijo.options[prefijo.options.length] = new Option(PREFIJOS[index].prefijo, PREFIJOS[index].value, undefined, PREFIJOS[index].default);
        if (PREFIJOS[index].default) {
            STORE.prefix_input.regExpMovil = PREFIJOS[index].expresionRegularMovil;
            limitarCamposDeTexto(campoMovil, PREFIJOS[index].maximo);
        }
    }

    // Action Listener para cambios de prefijo para número móvil.
    prefijoFijo.addEventListener("change", function () {
        cambiarExpRegular(2);
    });

    // Action Listener para cambios de prefijo para número fijo.
    prefijo.addEventListener("change", function () {
        cambiarExpRegular(1);
    });

};
