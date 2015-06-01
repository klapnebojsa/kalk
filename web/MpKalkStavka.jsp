<%-- 
    Document   : MpKalkStavka
    Created on : Mar 10, 2015, 11:16:29 PM
    Author     : neso
--%>

<%@page import="KlaseBaze.Mpkalkstavka"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="KlaseBaze.Artikal"%>
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
        <title>Stavke MP Kalkulacije</title>
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
                    <td>MP Kalkulacija:  <%=session.getAttribute("BrDok").toString()%>
                </table>
            </div>              
            
            <div id="PagerPozicija" style="padding-top: 10px;" align="center"></div>
            
            <table id="zaglavlje1" class="zaglavlje"   style="width:1080px;">         
                <tr>
                  <td width="21px"></td>
                  <td width="250px">Artikal</td>
                  <td width="100px">Kolicina</td>
                  <td width="100px">Nab.Cena</td>                  
                  <td width="100px">MP Cena</td>                  
                  <td width="120px">Nab.Cena UK</td> 
                  <td width="120px">MP Cena UK</td> 
                  <td width="120px">Raz.u Ceni UK</td> 
                  <td width="120px">Porez UK</td>                  
                </tr> 
            </table>       
            <div align="center">                           
                <form action="PiBrIzServlet" method="post" style="display:inline">                      
                    <table id="podaci1" class="GornjaTabla"  style="width:1080px;">                         
                        <%
                            String jNovi = request.getParameter("jNovi").toString();
                            String BrDok = session.getAttribute("BrDok").toString();
                           
                            if ("Cisto".equals(jNovi)) session.setAttribute( "jesteNovi", "");                            
                            session.setAttribute("KoZove", "MpKalkStavka");
                            //Obavezno unositi bez blanka pre i posle zareza
                            String ImePolja = "MIdAr,MKoli,MNCen,MMCen"; // ,MNCeS,MMCeS,MRCeS,MVPoS";
                            session.setAttribute("ImePolja", ImePolja);
                            
                            String sifra,artik,kolic,nabav,malop,nabce,mpcen,razli,porez;
                            ProcitajSve emp = new ProcitajSve();
                            List myDataList = null;
                                                 
                            Mpkalkstavka mpkalkstavka = new Mpkalkstavka();
                            mpkalkstavka.setMpIdVlasnik(currUser.getVlasnik());
                            mpkalkstavka.setMpIdKalk(BrDok);
                            InterfaceDAO v = mpkalkstavka;
                            myDataList = emp.ProcitajSvexx(v);                        
                            
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
                                    for (int i = 1; i <= 12; i++) {
                                        if (i>=8 && i<=11){singleRow.add(Double.parseDouble("0.00"));
                                        } else if (i==3){
                                            String oo = request.getAttribute("oznXX").toString(); //session.getAttribute("sifraST").toString();
                                            singleRow.add(oo);
                                        }else{singleRow.add("");}
                                    }
                                    myDataList.add(singleRow);                                    
                                    //Obavezno unositi bez blanka pre i posle zareza
                                    ImePolja = "MIdAr,MKoli,MNCen,MMCen"; // ,MNCeS,MMCeS,MRCeS,MVPoS";
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
                            Artikal aArtikal = new Artikal();
                            aArtikal.setArVlasnik(currUser.getVlasnik());
                            v = aArtikal;
                            List myArtikalCBox = emp.ProcitajSvexx(v);                           
                            //myPartnerCBox = NapCom.ProcitajComboSve("Partner", currUser.getVlasnik());                           
         
                            session.setAttribute("myDList", myDataList);                            
                            Integer i=0;
                            try{
                                for(Object category : myDataList) {
                                    List element = (List)category;
                                    sifra=element.get(2)==null? "" : element.get(2).toString();                                    
                                    artik=element.get(3)==null? "" : element.get(3).toString();
                                    kolic=element.get(4)==null? "" : element.get(4).toString();
                                    nabav=element.get(5)==null? "" : element.get(5).toString();
                                    malop=element.get(6)==null? "" : element.get(6).toString();                                    
                                    DecimalFormat df = new DecimalFormat("##,##0.00");
                                    nabce=(element.get(7)==null  || element.get(7)=="")? ""  : df.format(element.get(7));                                
                                    mpcen=(element.get(8)==null  || element.get(8)=="")? ""  : df.format(element.get(8));
                                    razli=(element.get(9)==null  || element.get(9)=="")? ""  : df.format(element.get(9));                              
                                    porez=(element.get(10)==null || element.get(10)=="")? "" : df.format(element.get(10));                         
                        %>               
                        <tr>
                            <td width="2px"   class="radioTD"><input type="radio" class="radio1" value="<%=sifra %>" name="oznXX" onclick="check('<%=ImePolja %>')" /></td>                   
                            <td width="300px" class="maxText">
                                <select id="MIdAr"  name="MIdAr" class="S100Posto"  disabled="disabled">
                                    <%
                                        String elemNP, kkpp;
                                        kkpp = "";
                                        for(Object art : myArtikalCBox) {
                                            List elem = (List)art;
                                            elemNP = elem.get(0).toString() + " - " + elem.get(2).toString();
                                            kkpp = "";
                                            if (artik.equals(elem.get(0).toString()))kkpp = "SELECTED";
                                    %>    
                                    <option <% out.println(kkpp); %> ><% out.println(elemNP); %></option>    
                                    <% } %>
                                </select>
                            </td>              
                            <td width="100px" class="maxText"><input id="MKoli" name="MKoli" placeholder="" class="S100Posto" type="text" value="<% out.println(kolic); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                              
                            <td width="100px" class="maxText"><input id="MNCen" name="MNCen" placeholder="" class="S100Posto" type="text" value="<% out.println(nabav); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                              
                            <td width="100px" class="maxText"><input id="MMCen" name="MMCen" placeholder="" class="S100Posto" type="text" value="<% out.println(malop); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                                  
                            
                            <td width="150px" class="maxText"><input id="MNCeS" name="MNCeS" placeholder="" class="S100Posto" type="text" value="<% out.println(nabce); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>
                            <td width="150px" class="maxText"><input id="MMCeS" name="MMCeS" placeholder="" class="S100Posto" type="text" value="<% out.println(mpcen); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>                            
                            <td width="150px" class="maxText"><input id="MRCeS" name="MRCeS" placeholder="" class="S100Posto" type="text" value="<% out.println(razli); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>                            
                            <td width="150px" class="maxText"><input id="MVPoS" name="MVPoS" placeholder="" class="S100Posto" type="text" value="<% out.println(porez); %>" readonly="readonly" maxlength="12" style="text-align: right"> <% i++;}}catch(Exception e){}%></td>               
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