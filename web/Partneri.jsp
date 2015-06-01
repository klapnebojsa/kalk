<%-- 
    Document   : Opcije
    Created on : Jan 25, 2015, 3:37:48 PM
    Author     : neso
--%>

<%@page import="KlaseBaze.Partner"%>
<%@page import="Inerfejsi.InterfaceDAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="javax.xml.soap.Node"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="KlaseBaze.Porez"%>
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
        <title>Partneri</title>
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
            <div id="PagerPozicija" style="padding-top: 10px;" align="center"></div>
            
            <table id="zaglavlje1" class="zaglavlje"  style="width:1240px;">         
                <tr>
                  <td width="10px"></td>                 
                  <td width="80px">Šifra</td> 
                  <td width="300px">Naziv Partnera</td>
                  <td width="250px">Adresa</td>
                  <td width="100px">Mesto</td> 
                  <td width="100px">Telefon</td>
                  <td width="100px">Matični broj</td> 
                  <td width="100px">PIB</td> 
                  <td width="100px">PDV<td>
                  <td width="100px">Delatnost</td>                   
                  
                </tr> 
            </table>       

  
            <div align="center">                  

                            
                <form action="PiBrIzServlet" method="post" style="display:inline">                      
                    <table id="podaci1" class="GornjaTabla"  style="width:1240px;">                         
                        <%
                            String jNovi = request.getParameter("jNovi").toString();
                            if ("Cisto".equals(jNovi)) session.setAttribute( "jesteNovi", "");                        
                            session.setAttribute("KoZove", "Partner");
                            //Obavezno unositi bez blanka pre i posle zareza
                            String ImePolja = "NazPar,AdrPar,MesPar,TelPar,MbrPar,PIBPar,PDVPar,DelPar";
                            session.setAttribute("ImePolja", ImePolja);
                            
                            String sifra,naziv,adresa,mesto,telefon,matbr,pib,pdv,sifdel; 
                            ProcitajSve emp = new ProcitajSve();
                            List myDataList = null;
                            
                            InterfaceDAO k;                       
                            Partner pPartner = new Partner();   
                            k = pPartner;
                            myDataList = emp.ProcitajSvexx(k); 
                            
                            //myDataList = emp.ProcitajSvexx("Partner", currUser.getVlasnik(),null);
                 
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
                                    for (int i = 1; i <= 9; i++) {singleRow.add("");}
                                    myDataList.add(singleRow);                                    
                                    //Obavezno unositi bez blanka pre i posle zareza
                                    ImePolja = "SifPar,NazPar,AdrPar,MesPar,TelPar,MbrPar,PIBPar,PDVPar,DelPar";
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
                            Integer i=0;
                            try{
                                for(Object category : myDataList) {
                                    List element = (List)category;   
                                    sifra=element.get(0).toString();
                                    naziv=element.get(1).toString();
                                    adresa=element.get(2).toString();
                                    mesto=element.get(3).toString();
                                    telefon=element.get(4).toString();
                                    matbr=element.get(5).toString();
                                    pib=element.get(6).toString();
                                    pdv=element.get(7).toString();
                                    sifdel=element.get(8).toString();                                    

                        %>               
                        <tr> 
                            <td width="10px"  class="radioTD"><input type="radio" class="radio1" value="<%=sifra %>" name="oznXX" onclick="check('<%=ImePolja %>')" /></td>                   
                            <td width="80px"  class="maxText"><input id="SifPar"  name="SifPar" placeholder="" class="S100Posto" type="text" value="<% out.println(sifra); %>"   disabled="disabled" maxlength="10"> </td>
                            <td width="300px" class="maxText"><input id="NazPar"  name="NazPar" placeholder="" class="S100Posto" type="text" value="<% out.println(naziv); %>"   disabled="disabled" maxlength="150"> </td>
                            <td width="250px" class="maxText"><input id="AdrPar"  name="AdrPar" placeholder="" class="S100Posto" type="text" value="<% out.println(adresa); %>"  disabled="disabled" maxlength="150"> </td>
                            <td width="100px" class="maxText"><input id="MesPar"  name="MesPar" placeholder="" class="S100Posto" type="text" value="<% out.println(mesto); %>"   disabled="disabled" maxlength="45"> </td>
                            <td width="100px" class="maxText"><input id="TelPar"  name="TelPar" placeholder="" class="S100Posto" type="text" value="<% out.println(telefon); %>" disabled="disabled" maxlength="50"> </td>                           
                            <td width="100px" class="maxText"><input id="MbrPar"  name="MbrPar" placeholder="" class="S100Posto" type="text" value="<% out.println(matbr); %>"   disabled="disabled" maxlength="13"> </td>
                            <td width="100px" class="maxText"><input id="PIBPar"  name="PIBPar" placeholder="" class="S100Posto" type="text" value="<% out.println(pib); %>"     disabled="disabled" maxlength="9"> </td>
                            <td width="100px" class="maxText"><input id="PDVPar"  name="PDVPar" placeholder="" class="S100Posto" type="text" value="<% out.println(pdv); %>"     disabled="disabled" maxlength="9"> </td>                            
                            <td width="100px" class="maxText"><input id="DelPar"  name="DelPar" placeholder="" class="S100Posto" type="text" value="<% out.println(sifdel); %>"  disabled="disabled" maxlength="6"> <% i++;}}catch(Exception e){}%></td>                        
               
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
            var pager = new Pager('podaci1', 18,'<%= session.getAttribute("ImePolja") %>','PagerPozicija');                   
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
