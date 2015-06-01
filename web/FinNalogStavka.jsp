<%-- 
    Document   : FinNalogStavka
    Created on : Mar 4, 2015, 3:28:14 PM
    Author     : neso
--%>

<%@page import="KlaseBaze.Partner"%>
<%@page import="KlaseBaze.Finnalogstavka"%>
<%@page import="Inerfejsi.InterfaceDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="javax.xml.soap.Node"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="KlaseBaze.Konto"%>
<%@page import="Povezivanje.ProcitajSve"%>
<%@page import="Povezivanje.Meni"%>
<%@page import="KlaseBaze.Vlasnik"%>
<%@page import="KlaseBaze.Korisnik"%>

<!DOCTYPE html>
    <head>
        <script type="text/javascript" src="resursi/js/Timer.js"></script>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='resursi/css/MojStil.css'> 
        <link rel='stylesheet' href='resursi/css/Meni.css'>
        <script type="text/javascript" src="resursi/js/postavljanje.js"></script>        
        <title>Stavke Finansijskog naloga</title>
    </head>

    <body class="LoginBody" onload="Timer()">        
        <% Korisnik currUser =((Korisnik)(session.getAttribute("currentSessionUser")));%>	           
        <% Vlasnik currVlasnik =((Vlasnik)(session.getAttribute("currentSessionVlasnik")));%>

        <div class="Logo" style="text-align: center;">
            <a class="FontAgency" style="font-size: 24px;">ACCOUNTING AGENCY </a><br>          
        </div>            

        <%=new Meni().IspisiMeni(currUser.getNivo())%>      
        <jsp:include page="StatusnaLinija.jsp" />

        <div class="DivGoreTabla1">
            <div>
                <table  id="naslov">                  
                    <td>Finansijski Nalog:  <%=session.getAttribute("BrDok").toString()%>
                </table>
            </div>               
            
            <div id="PagerPozicija" style="padding-top: 10px;" align="center"></div>
            
            <table id="zaglavlje1" class="zaglavlje">         
                <tr>
                  <td width="21px"></td>
                  <td width="100px">Broj Dokumenta</td>
                  <td width="200px">Partner</td>
                  <td width="200px">Konto</td>                  
                  <td width="150px">Duguje</td>                  
                  <td width="150px">Potražuje</td> 
                  <td width="150px">Saldo</td>                  
                </tr> 
            </table>       
            <div align="center">                           
                <form action="PiBrIzServlet" method="post" style="display:inline">                      
                    <table id="podaci1" class="GornjaTabla" >                         
                        <%
                            String jNovi = request.getParameter("jNovi").toString();
                            String BrDok = session.getAttribute("BrDok").toString();
                           
                            if ("Cisto".equals(jNovi)) session.setAttribute( "jesteNovi", "");                            
                            session.setAttribute("KoZove", "Finnalogstavka");
                            //Obavezno unositi bez blanka pre i posle zareza
                            String ImePolja = "NBrDok,NPartn,NKonto,NDuguj,NPotra"; // ,NSaldo";
                            session.setAttribute("ImePolja", ImePolja);
                            
                            String sifra, brdok, partn, konta, duguj,potra,saldo;
                            ProcitajSve emp = new ProcitajSve();
                            List myDataList = null;
                            
                            InterfaceDAO k;                       
                            Finnalogstavka fFinnalogstavka = new Finnalogstavka();
                            fFinnalogstavka.setFNIdVlasnik(currUser.getVlasnik());
                            fFinnalogstavka.setFNIdFN(BrDok);
                            k = fFinnalogstavka;
                            myDataList = emp.ProcitajSvexx(k);                        
                            
                            //myDataList = emp.ProcitajSvexx("Finnalogstavka", currUser.getVlasnik(), BrDok);
                 
                            Integer elemMyDataList = 0;
                            try{elemMyDataList = myDataList.size();    
                            }catch(Exception e){}

                            session.setAttribute("elemMyDataList", elemMyDataList);
                             
                            // Deo za novislog u tabeli
                            try{
                                //Setujemo atribute koji se ispravljaju. Samo polja i to koja ili treba i sifra
                                String jesteNovi = session.getAttribute("jesteNovi").toString();
                                if (jesteNovi == "novi"){
                                    List singleRow = new ArrayList();
                                    for (int i = 1; i <= 10; i++) {
                                        if (i==7 || i==8 || i==9){singleRow.add(Double.parseDouble("0.00"));
                                        } else if (i==3){
                                            String oo = request.getAttribute("oznXX").toString(); //session.getAttribute("sifraST").toString();
                                            singleRow.add(oo);
                                        }else{singleRow.add("");}
                                    }
                                    myDataList.add(singleRow);                                    
                                    //Obavezno unositi bez blanka pre i posle zareza
                                    ImePolja = "NBrDok,NPartn,NKonto,NDuguj,NPotra"; //,NSaldo";
                                    elemMyDataList = myDataList.size();
                                    session.setAttribute("elemMyDataList", elemMyDataList);                                    
                                    session.setAttribute("ImePolja", ImePolja);
                                }                                
                            }catch(Exception e){
                                //request.setAttribute("imaNeispravnih", "");   
                            }

                            //Prebacivanje tabele u Servlet. Direktno prebaci DataList ali i napusti izvrsavanje jsp-a 
                            //RequestDispatcher rd = request.getRequestDispatcher("PiBrIzServlet");
                            //request.setAttribute("DataList1", myDataList);                    
                            //rd.forward(request, response);
                            
                            //Kada je sifra automatska tj. ne unosi se u polje
                            /*try{
                                sifra=session.getAttribute("sifraST").toString();
                            }catch(Exception e){sifra="";}*/
                            emp = new ProcitajSve();       
                            Partner pPartner = new Partner();
                            k = pPartner;
                            List myPartnerCBox = emp.ProcitajSvexx(k);                       
                            //myPartnerCBox = NapCom.ProcitajComboSve("Partner", currUser.getVlasnik());
                            emp = new ProcitajSve();                            
                            Konto kKonto = new Konto();
                            kKonto.setVlasnik(currUser.getVlasnik());
                            k = kKonto;
                            List myKontoCBox = emp.ProcitajSvexx(k);                         
                            //myKontoCBox = NapCom.ProcitajComboSve("Konto", currUser.getVlasnik());                            
         
                            session.setAttribute("myDList", myDataList);                            
                            Integer i=0;
                            try{
                                for(Object category : myDataList) {
                                    List element = (List)category; 
                                    sifra=element.get(2)==null? "" : element.get(2).toString();
                                    brdok=element.get(3)==null? "" : element.get(3).toString();
                                    partn=element.get(4)==null? "" : element.get(4).toString();
                                    konta=element.get(5)==null? "" : element.get(5).toString();                                    
                                    DecimalFormat df = new DecimalFormat("##,##0.00");
                                    duguj=(element.get(6)==null || element.get(6)=="")? "" : df.format(element.get(6));                                
                                    potra=(element.get(7)==null || element.get(7)=="")? "" : df.format(element.get(7));
                                    saldo=(element.get(8)==null || element.get(8)=="")? "" : df.format(element.get(8));                              
                        %>               
                        <tr> 
                            <td width="2px"   class="radioTD"><input type="radio" class="radio1" value="<%=sifra %>" name="oznXX" onclick="check('<%=ImePolja %>')" /></td>                   
                            <td width="100px" class="maxText"><input id="NBrDok" name="NBrDok" placeholder="" class="S100Posto" type="text" value="<% out.println(brdok); %>"  disabled="disabled" maxlength="10"> </td>    
                            <td width="200px" class="maxText">
                                <select id="NPartn"  name="NPartn" placeholder="" class="S100Posto" type="text" value="<% out.println(partn); %>" disabled="disabled">
                                    <%
                                        String elemNP, kkpp;
                                        kkpp = "";
                                        for(Object par : myPartnerCBox) {
                                            List elem = (List)par;
                                            elemNP = elem.get(0).toString() + " - " + elem.get(1).toString();
                                            kkpp = "";
                                            if (partn.equals(elem.get(0).toString()))kkpp = "SELECTED";
                                    %>    
                                    <option <% out.println(kkpp); %> ><% out.println(elemNP); %></option>    
                                    <% } %>
                                </select>
                            </td>
                            <td width="200px" class="maxText">
                                <select id="NKonto"  name="NKonto" placeholder="" class="S100Posto" type="text" value="<% out.println(konta); %>" disabled="disabled">
                                    <%
                                        String elemNK, kkko;
                                        kkko = "";
                                        for(Object kon : myKontoCBox) {
                                            List elem = (List)kon;
                                            elemNK = elem.get(0).toString() + " - " + elem.get(2).toString();
                                            kkko = "";
                                            if (konta.equals(elem.get(0).toString()))kkko = "SELECTED";
                                    %>    
                                    <option <% out.println(kkko); %> ><% out.println(elemNK); %></option>    
                                    <% } %>
                                </select>
                            </td>                            
                            <td width="150px" class="maxText"><input id="NDuguj" name="NDuguj" placeholder="" class="S100Posto" type="text" value="<% out.println(duguj); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                              
                            <td width="150px" class="maxText"><input id="NPotra" name="NPotra" placeholder="" class="S100Posto" type="text" value="<% out.println(potra); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                                  
                            <td width="150px" class="maxText"><input id="NSaldo" name="NSaldo" placeholder="" class="S100Posto" type="text" value="<% out.println(saldo); %>" readonly="readonly" maxlength="12" style="text-align: right"> <% i++;}}catch(Exception e){}%></td>                        
               
                        </tr>
                    </table>                    

                    <input type="submit" id="Brisi"    name="Brisi"    value="Briši"    class="dugmeOK" disabled onclick="zeliLi('izbrišete')">
                    <input type="submit" id="Upisi"    name="Upisi"    value="Upiši"    class="dugmeOK" disabled onclick="zeliLi('upišete')">
                    <input type="submit" id="Novi"     name="Novi"     value="Novi"     class="dugmeOK" style="visibility: visible" onclick="zeliLi('ovorite novi')">                   
                    <input type="submit" id="Odustani" name="Odustani" value="Odustani" class="dugmeOK" disabled onclick="zeliLi('odustanete od upisa')">
                
                    <div  style="display:inline">
                        <input type="text" id="Cekiran"        name="Cekiran"        class="neVidi" value="${Cekiran}">
                        <input type="text" id="Sifra"          name="Sifra"          class="neVidi" value="${Sifra}"> 
                        <input type="text" id="Vrednosti"      name="Vrednosti"      class="neVidi" value="${Vrednosti}">                     
                        <input type="text" id="Zeli"           name="Zeli"           class="neVidi" value="${Zeli}"> 
                        <input type="text" id="BrPage"         name="BrPage"         class="neVidi" value="${BrPage}">                  
                        <input type="text" id="imaNeispravnih" name="imaNeispravnih" class="neVidi" value="${imaNeispravnih}">
                        <input type="text" id="JesteNovi"      name="JesteNovi"      class="neVidi" value="${JesteNovi}">                        
                        <input type="text" id="BrNeVidiSifri"  name="BrNeVidiSifri"  class="neVidi" value="1">                    
                    </div>  
                    
                    <script type="text/javascript">
                        var xx = document.getElementById('imaNeispravnih').value;
                        if ( xx == "true" ){
                            var ele = document.getElementsByName('oznXX');
                            var i = document.getElementById('Cekiran').value;
                            ele[i].checked = true;
                        }
                    </script>
                </form>                     
                <input type="submit" id="Izmeni"   name="Izmeni"   value="Izmeni"   class="dugmeOK" disabled onclick="izmeni('<%=ImePolja %>', 'true')">    
            </div>
 
            <script type="text/javascript">
                window.setTimeout("ZatvoriDiv();", 10000);
                function ZatvoriDiv(){
                    document.getElementById("porukaSer").style.display=" none";
                }
           
            </script>           
            <div id="porukaSer" class="porukaBrisanjaiDiv" onload="setTimeout">           
                <c:if test="${not empty Poruka}">
                    <p class="porukaBrisanja">${Poruka}</p>
                </c:if>  
            </div>
              
        </div>
                           
        <script type="text/javascript"> 
            var pager = new Pager('podaci1', 17,'<%= session.getAttribute("ImePolja") %>','PagerPozicija');                    
            var brStrana = pager.Inicijalizuj();

            var x = document.getElementById("BrPage").value;          
            if (document.getElementById("imaNeispravnih").value == "novi") x = brStrana;           
            if (x==null || x =="") {
                pager.PrikaziStranu(1);
            }else {
                pager.PrikaziStranu(x);          
            }             
        </script> 
    </body>
   </html>

