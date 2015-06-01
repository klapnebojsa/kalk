/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function Pager(ImeTabele, VrstaNaStrani, ImePolja) {
        this.ImeTabele = ImeTabele;
        this.VrstaNaStrani = VrstaNaStrani;       
        this.TrenutnaStrana = 1;
        this.KolikoStrana = 0;
        this.JesteInicijalizovano = false;
        this.NovaTrenStrana = 1;
        this.PrikaziVrste = function (from, to) {
            var rows = document.getElementById(ImeTabele).rows;

            // ako tabela ima zaglavlje i=1 da bi se preskočilo zaglavlje tabele
            for (var i = 0; i < rows.length; i++) {
                if (i < from || i > to)
                    rows[i].style.display = 'none';
                else
                    rows[i].style.display = '';
            }
        }

        this.PrikaziStranu = function (donPrikaziStranu) {

            if (!this.JesteInicijalizovano) {
                alert("Nije Inicijalizovano");
                return;
            }
            document.getElementById("BrPage").value = donPrikaziStranu; 

            this.NovaTrenStrana = donPrikaziStranu;
            pager.PrikaziNavigator('pager', 'PagerPozicija');

            this.TrenutnaStrana = donPrikaziStranu;                   
            var NovaStrana = document.getElementById('pg' + this.TrenutnaStrana + 'PagerPozicija');

            var from = (donPrikaziStranu - 1) * VrstaNaStrani; // +1; Ovo treba ako tabela ima zaglavlje
            var to = from + VrstaNaStrani - 1;
                      
            NovaStrana.className = 'pg-selected';           
            this.PrikaziVrste(from, to);

        }

        this.prethodna = function () {
            if (this.TrenutnaStrana > 1) {
                this.PrikaziStranu(this.TrenutnaStrana - 1);
            }
        }

        this.sledeca = function () {

            if (this.TrenutnaStrana < this.KolikoStrana) {
                this.PrikaziStranu(this.TrenutnaStrana + 1);
            }
        }
       
        this.prva = function () {
            if (this.TrenutnaStrana > 1) {
                this.PrikaziStranu(1);
            }
        }

        this.zadnja = function () {
            if (this.TrenutnaStrana < this.KolikoStrana) {
                this.PrikaziStranu(this.KolikoStrana);
            }
        }

        this.Inicijalizuj = function () {
            var rows = document.getElementById(ImeTabele).rows;
            var Vrsta = (rows.length); // - 1); Ovo treba ako tabela ima zaglavlje
            this.KolikoStrana = Math.ceil(Vrsta / VrstaNaStrani);
            this.JesteInicijalizovano = true;
            return this.KolikoStrana;
        }

        this.PrikaziNavigator = function (pagerName, positionId) {

            
            if (!this.JesteInicijalizovano) {
                alert("Nije Inicijalizovano");
                return;
            }
    
            if (this.KolikoStrana < 1) {
                return;
            }
            
            var element = document.getElementById(positionId);
            var pagerHtml = '';
            var page;
            
            if (this.KolikoStrana > 7) {
                var TrStr = this.NovaTrenStrana;

                var odStrane = parseInt(TrStr) - parseInt(3); 
                var doStrane = parseInt(TrStr) + parseInt(3);

                var KolikoMax = 7;
                if (KolikoMax > this.KolikoStrana) { KolikoMax = this.KolikoStrana }

                if (odStrane <= 0) {
                    doStrane = KolikoMax;
                    odStrane = 1;
                } else {
                    if (doStrane > this.KolikoStrana) {
                        doStrane = this.KolikoStrana;
                        odStrane = this.KolikoStrana - KolikoMax + 1;
                    }
                    if (odStrane > 1) {
                        pagerHtml += '<span onclick="' + pagerName + '.prva();" class="pg-normal">|« </span> ';
                        pagerHtml += '<span onclick="' + pagerName + '.prethodna();" class="pg-normal"> « </span> ';
                        pagerHtml += '<span class="pg-normal"> . . . </span>';
                    }
                }
                for (page = odStrane; page <= doStrane; page++) {
                    pagerHtml += '<span id="pg' + page + positionId + '" class="pg-normal" onclick="' + pagerName + '.PrikaziStranu(' + page + ');">' + page + '</span> ';
                }
                if (doStrane < this.KolikoStrana) {
                    pagerHtml += '<span class="pg-normal"> . . . </span>';
                    pagerHtml += '<span onclick="' + pagerName + '.sledeca();" class="pg-normal"> » </span>';
                    pagerHtml += '<span onclick="' + pagerName + '.zadnja();" class="pg-normal"> »|</span> ';
                }
            } else {
                for (page = 1; page <= this.KolikoStrana; page++) {
                    pagerHtml += '<span id="pg' + page + positionId + '" class="pg-normal" onclick="' + pagerName + '.PrikaziStranu(' + page + ');">' + page + '</span> ';
                }
            }          
            element.innerHTML = pagerHtml;
            var xx = document.getElementById('imaNeispravnih').value;
            //alert("koji " + xx);   
            switch(xx){
                case "cancel": 
                    izmeni(ImePolja, "false");
                    break;
                case "cancelIzmeni":
                    checkiraj(document.getElementById('Cekiran').value); 
                    izmeni(ImePolja, "false");
                    break;                    
                case "true":
                    izmeni(ImePolja, "false");
                    break; 
                case "false":
                    ResetujRadioButton(ImePolja);
                    break;
                case "cancelBrisi":
                    document.getElementById('imaNeispravnih').value = "";                    
                    checkiraj(document.getElementById('Cekiran').value);                  
                    neVidiSveButton();
                    vidiButton("Izmeni");
                    vidiButton("Brisi");                    
                    break;
                case "novi":
                    ResetujRadioButton(ImePolja);                   
                    checkiraj(document.getElementById('Cekiran').value);                   
                    izmeni(ImePolja, "false");
                    break; 
                case "cancelNovaStav":
                    document.getElementById('imaNeispravnih').value = "";                    
                    checkiraj(document.getElementById('Cekiran').value);                  
                    neVidiSveButton();
                    vidiButton("Izmeni");
                    vidiButton("Brisi");                  
                    vidiButton("NovaStav");                 
                default:
                    ResetujRadioButton(ImePolja);                    
            }
        }
    }
    
    function ResetujRadioButton(ImePolja){
            var ElByName = new Array();
            ElByName = ImePolja.split(',');        
            //Decekiranje polja u tabeli
            try{
                var ele = document.getElementsByName('oznXX');
                var i = document.getElementById('Cekiran').value;                
                ele[i].checked=false;
                //Polja za menjanje
                for(var k=0;k<ElByName.length;k++){
                    document.getElementsByName(ElByName[k])[i].disabled=true;  
                }                    
            }catch(e){}
            neVidiSveButton();
            vidiButton("Novi");                     
    }
    function checkiraj(rbrPolja){
        var ele = document.getElementsByName('oznXX');
        ele[rbrPolja].checked = true;
    }
    function check(ImePolja) {
        var ele = document.getElementsByName('oznXX');
        for(var i=0;i<ele.length;i++) {
            if(ele[i].checked){
                document.getElementById('Cekiran').value= i; 
                document.getElementById('Sifra').value= ele[i].value; 
            }          
        }
        neVidiSveButton();
        vidiButton("Izmeni");
        vidiButton("Brisi");
        try{    
            vidiButton("NovaStav");
        }catch(e){}
    }

    function izmeni(ImePolja, pitanje) { 
        //alert("izmeni : " + ImePolja);
        if (pitanje=="true") {
            var hoceLiIzmenu = zeliLi('izmenite');
            if (hoceLiIzmenu=="false") return;
        }

        var hoceLi = document.getElementById("Zeli").value;
        //alert("hh   " + hoceLi);
        //Polja koja se menjaju
        var ElByName = new Array();
        ElByName = ImePolja.split(',');
                                
        var ele = document.getElementsByName('oznXX'); 
        //alert ("ele    " + ele.length);
        if (document.getElementById('imaNeispravnih').value=="cancel"){
            var k = document.getElementById('Cekiran').value;
            ele[k].checked = true;               
        }
               
        for(var i=0;i<ele.length;i++) {
            if(ele[i].checked){                
                document.getElementById('Cekiran').value= i;           
                document.getElementById('Sifra').value= ele[i].value;
                try {document.getElementsByName('oznXX').value= ele[i].value;} catch(e){}
                var imaNeispravnih = document.getElementById('imaNeispravnih').value;
                //Upisati ukucano
                //1. ako je neko od polja uneto neispravno
                if (imaNeispravnih=="true" && hoceLi=="true"){IspisiUkucano(i, ImePolja, hoceLi);}
                //2. Ako se odustalo od upisa ili neceg slicnog. Vracanje na stanje pre izbora opcije
                if (imaNeispravnih!="cancelIzmeni" && hoceLi=="false"){IspisiUkucano(i, ImePolja, hoceLi);}
                //3. Ako se odustalo od izmene. Zato sto je ova opcija van servleta
                if (imaNeispravnih=="cancelIzmeni"){IspisiUkucano(i, ImePolja, "true");}                    

                //Polja za menjanje da budu vidljiva za unos
                ele[i].disabled=false;                    
                for(var k=0;k<ElByName.length;k++){
                    document.getElementsByName(ElByName[k])[i].disabled=false;
                } 
            } else{
                //Polja za menjanje se ne mogu menjati
                ele[i].disabled=true; 
                for(var k=0;k<ElByName.length;k++){ 
                    document.getElementsByName(ElByName[k])[i].disabled=true;  
                }                
            }
        }
        document.getElementById("PagerPozicija").innerHTML.disabled=true;        

        var spanovi = document.getElementById("PagerPozicija").getElementsByTagName('*');
        for(var i = 0; i < spanovi.length; i++) {
            spanovi[i].onclick ='none';    
        } 
        neVidiSveButton();    
        vidiButton("Upisi");
        vidiButton("Odustani");                 
    }
    
    function IspisiUkucano(row, ImePolja, hoceLi){
        //alert ("Doso " + hoceLi);
        //alert("1-----  " + ImePolja);
        var ElByName = new Array();
        ElByName = ImePolja.split(',');
        //Vrednosti polja selektovanog row-a
        var ValueAll = document.getElementById('Vrednosti').value;
        //alert("2----  " + ValueAll);
        var ValueElByName = new Array();
        ValueElByName = ValueAll.split("####____");
        //alert (ImePolja + " ---- " + ValueAll);
        
        //Koliko polja se preskace do polja za izmenu
        var Dodaj=parseInt(document.getElementById('BrNeVidiSifri').value);
        
        var innHTML;
        for(var z=0;z<ElByName.length;z++) {        
            innHTML = podaci1.rows[row].cells[z+1].innerHTML;
            //alert(innHTML + "------" + ElByName[0]);
            var res = innHTML.replace(ElByName[0],"");
            if (res != innHTML){break;}
            Dodaj += 1;  
            //alert(Dodaj);
        }

        //alert("1");
        //Ispravljanje vrednosti polja na blank (koja su za izmenu) SAMO ZA IZMENU
        if ((document.getElementById('imaNeispravnih').value == "cancelIzmeni" && document.getElementById('JesteNovi').value != "novi") 
                    || (document.getElementById('imaNeispravnih').value == "true"&& document.getElementById('JesteNovi').value != "novi")){
            for(var z=Dodaj-1;z <= ElByName.length;z++) {  
                innHTML = podaci1.rows[row].cells[z+1].innerHTML;
                var sta = innHTML.split('value="');            
                for(var p=1;p<sta.length;p++) {
                    var vred = sta[p].split('"');                
                    var staUk = 'value="' + vred[0];
                    podaci1.rows[row].cells[z+1].innerHTML = innHTML.replace(staUk, 'value="');
                }           
            } 
        }
            
        //Punjenje vrednosti kada je kliknuto na radio button
        document.getElementById('Vrednosti').value = "";
        for(var u=0;u<ElByName.length;u++) {
            if (ValueElByName[u]=="null") ValueElByName[u]="";       
            //Upisivanje vrednosti u polje Vrednosti
            if (u!=0)document.getElementById('Vrednosti').value +="####____";
            document.getElementById('Vrednosti').value += ValueElByName[u];

            //Upisivanje vrednosti u text boksove koji se menjaju (Oni su u okviru cell-ova tabele podaci1)
            var cellInnerHTML = podaci1.rows[row].cells[u+Dodaj].innerHTML;
            var ValueNull = new Array();
            var ValueNull = cellInnerHTML.split('value="null');
            var ValueNull1 = new Array();
            var ValueNull1 = cellInnerHTML.split('value="0.00');            
            
            if (ValueNull.length > 1){
                var res = cellInnerHTML.replace('value="null', 'value="'+ ValueElByName[u]);                 
            }else if (ValueNull1.length > 1){
                var res = cellInnerHTML.replace('value="0.00', 'value="'+ ValueElByName[u]);                 
            }else{
                var res = cellInnerHTML.replace('value="', 'value="'+ ValueElByName[u]);                
            }
            podaci1.rows[row].cells[u+Dodaj].innerHTML = res;
            if (hoceLi=="true"){
                if (ValueElByName[u] = null || ValueElByName[u] == "") {
                    res = cellInnerHTML.replace('placeholder="', 'placeholder=" Obavezno !!! ');
                    podaci1.rows[row].cells[u+Dodaj].innerHTML = res;                                
                }
            } else{
                if (ValueElByName[u] = null || ValueElByName[u] == "") {
                    res = cellInnerHTML.replace( 'placeholder=" Obavezno !!! ' ,'placeholder="');
                    podaci1.rows[row].cells[u+Dodaj].innerHTML = res;
                }                
            }
        }         
    }

    function vidiButton (imeButton){     
        document.getElementById(imeButton).style.visibility = "visible";         
        document.getElementById(imeButton).disabled=false;    
    }
    function neVidiButton (imeButton){
        document.getElementById(imeButton).style.visibility = "hidden";         
        document.getElementById(imeButton).disabled=true;    
    }    
    function neVidiSveButton (){
        document.getElementById('Izmeni').style.visibility = "hidden";         
        document.getElementById('Izmeni').disabled=true;
        document.getElementById('Brisi').style.visibility = "hidden";         
        document.getElementById('Brisi').disabled=true;       
        document.getElementById('Upisi').style.visibility = "hidden";         
        document.getElementById('Upisi').disabled=true;      
        document.getElementById('Novi').style.visibility = "hidden";         
        document.getElementById('Novi').disabled=true;     
        document.getElementById('Odustani').style.visibility = "hidden";         
        document.getElementById('Odustani').disabled=true;
        try{
            document.getElementById('NovaStav').style.visibility = "hidden";         
            document.getElementById('NovaStav').disabled=true;            
        }catch(e){}
              
    }

    function zeliLi(p) {
        var x;
        if (confirm("Zelite li da " + p + " podatke") == true){
            x = "true";          
        } else {
            x = "false"; 
        }
        document.getElementById("Zeli").value = x;
        //alert ("x " + x);
        return x;
    }
    