/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Timer() {
    var danas = new Date();
    var h = danas.getHours();
    var m = danas.getMinutes();
    var s = danas.getSeconds();
    m = dodaj0(m);
    s = dodaj0(s);
    document.getElementById("Timer").innerHTML = h + ":" + m + ":" + s;
    var t = setTimeout(function(){ Timer() }, 500);
}

function dodaj0(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}
