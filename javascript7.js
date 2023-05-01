'use strict'
// 'use strict' nos ayuda a no dejar pasar ningun error

// creamos una variable para contar las veces que pinta
var contador=0;

// creamos un array para el tablero
var tablero = [['0','0','0'],['0','0','0'],['0','0','0']];

// creamos una funcion que pinte el cuadro con un hover (activo)
function hoverino(identificador) {
    let id='c'+identificador;
    let cuadro=document.getElementById(id);
    cuadro.style.backgroundColor='rgb(0, 150, 150)';

}

// creamos una funcion generica para dibujar el simbolo
function pintar(identificador) {

    // ponemos un if para limitar a 3 los dibujos
    if (contador<3) {
        let id='c'+identificador;
        let cuadro=document.getElementById(id);
        cuadro.style.color='black';
        cuadro.innerHTML="*";
        contador+=1;
        tablero[identificador]='1';
        compruebaArray();
    } else {
        alert("Juego finalizado, reincia la pagina!!!");
    }
}

// creamos una funcion que pinta el cuadro con un hover (inactivo)
function hoverinoff(identificador) {
    let id='c'+identificador;
    let cuadro=document.getElementById(id);
    cuadro.style.backgroundColor='rgb(0, 218, 218)';
}

function compruebaArray() {
    if ((tablero[0]=='1')&&(tablero[1]=='1')&&(tablero[2]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[3]=='1')&&(tablero[4]=='1')&&(tablero[5]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[6]=='1')&&(tablero[7]=='1')&&(tablero[8]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[0]=='1')&&(tablero[3]=='1')&&(tablero[6]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[1]=='1')&&(tablero[4]=='1')&&(tablero[7]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[2]=='1')&&(tablero[5]=='1')&&(tablero[8]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[0]=='1')&&(tablero[4]=='1')&&(tablero[8]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if ((tablero[2]=='1')&&(tablero[4]=='1')&&(tablero[6]=='1')) {
        alert("Has puesto TRES EN RAYA !!!!!");
    } else if (contador==3) {
        alert("Ohhhhh, no has puesto tres en raya....");
    }

}

