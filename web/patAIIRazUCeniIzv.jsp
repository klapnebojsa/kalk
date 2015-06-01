<%-- 
    Document   : patAIRazUCeni
    Created on : Mar 29, 2015, 3:54:16 AM
    Author     : neso
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Povezivanje.ProcitajViseCell"%>
<%@page import="Povezivanje.ProcitajCell"%>
<%@page import="KlaseBaze.Vpkalkuk"%>
<%@page import="KlaseBaze.Mpkalkuk"%>
<%@page import="java.util.ArrayList"%>
<%@page import="KlaseBaze.Konto"%>
<%@page import="Inerfejsi.InterfaceDAO"%>
<%@page import="java.util.List"%>
<%@page import="Povezivanje.ProcitajSve"%>
<%@page import="Povezivanje.Meni"%>
<%@page import="KlaseBaze.Vlasnik"%>
<%@page import="KlaseBaze.Korisnik"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="resursi/js/Timer.js"></script>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='resursi/css/MojStil.css'> 
        <link rel='stylesheet' href='resursi/css/Meni.css'>
        <script type="text/javascript" src="resursi/js/postavljanje.js"></script>        
        <title>Razlika u ceni (Builder)</title>
    </head>
    <body class="LoginBody" onload="Timer()">        
        <% Korisnik currUser =((Korisnik)(session.getAttribute("currentSessionUser")));%>	           
        <% Vlasnik currVlasnik =((Vlasnik)(session.getAttribute("currentSessionVlasnik")));%>
        
        <div class="Logo" style="text-align: center;">
            <a class="FontAgency" style="font-size: 24px;">ACCOUNTING AGENCY </a><br>          
        </div>            

        <%=new Meni().IspisiMeni(currUser.getNivo())%>      
        <jsp:include page="StatusnaLinija.jsp" />
        
        <div  class="DivGoreTabla1">           
            <%
                String parametri = session.getAttribute("parametri").toString();
                String[] param = parametri.split(",");                                                
                            
                ProcitajViseCell emp = new ProcitajViseCell();
                List myDataList = null;
                InterfaceDAO k;
                if ("mpkalkuk".equals(param[1])){
                    Mpkalkuk mpkalkuk = new Mpkalkuk();                
                    mpkalkuk.setMpIdVlasnikUK(currUser.getVlasnik());   
                    k = mpkalkuk;
                    myDataList = emp.Vise(k, param[0] + "," + param[2] + "," + param[3]);                                 
                }
                if ("vpkalkuk".equals(param[1])){
                    Vpkalkuk vpkalkuk = new Vpkalkuk();                
                    vpkalkuk.setVpIdVlasnikUK(currUser.getVlasnik());   
                    k = vpkalkuk;
                    myDataList = emp.Vise(k, param[0] + "," + param[2] + "," + param[3]);                                 
                }                            
                String kojaKalk = param[4];
            %>
            
            <div id="kojaKalk" style="padding-top: 10px;" align="center">
                Pregled Razlike u Ceni za <% out.println(kojaKalk); %> Kalkulacije (Builder patern)
            </div>            
            
            <!--<div id="PagerPozicija" style="padding-top: 10px;" align="center"></div> -->          
            <table id="zaglavlje1" class="zaglavlje">         
                <tr>
                  <td width="150px">Broj Kalkulacije</td> 
                  <td width="150px" >Datum</td>
                  <td width="150px" >Razlika u Ceni</td>                  
                </tr> 
            </table>
            <div align="center">                                                  
                <table id="podaci1" class="GornjaTabla" >              
                    <%
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String brKalk,razuCeni,date;
                        Date datum;

                        Integer elemMyDataList = 0;
                        try{elemMyDataList = myDataList.size();    
                        }catch(Exception e){}
                        session.setAttribute("elemMyDataList", elemMyDataList);
                  
                        Integer i=0;
                        try{
                            for(Object category : myDataList) {
                                List element = (List)category;   
                                brKalk=element.get(0).toString();
                                datum=sdf.parse(element.get(1).toString());
                                date = sdf.format(datum);
                                razuCeni=element.get(2).toString();                                    
                    %>               
                    <tr> 
                        <td width="150px" class="maxText"><input class="S100Posto" type="text" value="<% out.println(brKalk); %>"   disabled="disabled"  style="text-align: center"> </td> 
                        <td width="150px" class="maxText"><input class="S100Posto" type="date" value="<%= date %>"    disabled="disabled"  style="text-align: center"> </td>
                        <td width="150px" class="maxText"><input class="S100Posto" type="text" value="<% out.println(razuCeni); %>" disabled="disabled"  style="text-align: right"> <% i++;}}catch(Exception e){}%></td>                        
               
                    </tr>
                </table>                    
                <input type="submit" id="Izmeni"   name="Izmeni"   value="Izmeni"   class="dugmeOK" disabled onclick="izmeni('1', 'true')">      
            </div>            
            <div style="display:inline">
                <input type="text" id="Cekiran"        name="Cekiran"        class="neVidi" value="${Cekiran}">
                <input type="text" id="Sifra"          name="Sifra"          class="neVidi" value="${Sifra}"> 
                <input type="text" id="Vrednosti"      name="Vrednosti"      class="neVidi" value="${Vrednosti}">                     
                <input type="text" id="Zeli"           name="Zeli"           class="neVidi" value="${Zeli}"> 
                <input type="text" id="BrPage"         name="BrPage"         class="neVidi" value="${BrPage}">                  
                <input type="text" id="imaNeispravnih" name="imaNeispravnih" class="neVidi" value="${imaNeispravnih}">
                <input type="text" id="JesteNovi"      name="JesteNovi"      class="neVidi" value="${JesteNovi}">                        
                <input type="text" id="BrNeVidiSifri"  name="BrNeVidiSifri"  class="neVidi" value="1">                    
            </div>              
            
            
            <div id="porukaSer" style="margin-left: 100px;">           
                <c:if test="${not empty Poruka}">
                    <p class="porukaBrisanja">${Poruka}</p>
                </c:if>  
            </div>            
        </div>
        <script type="text/javascript"> 
            var pager = new Pager('podaci1', 18,'<%= session.getAttribute("ImePolja") %>','PagerPozicija');                    
            var brStrana = pager.Inicijalizuj();

            var x = document.getElementById("BrPage").value;          
            //if (document.getElementById("imaNeispravnih").value == "novi") x = brStrana;           
            if (x==null || x =="") {
                pager.PrikaziStranu(1);
            }else {
                pager.PrikaziStranu(x);          
            }             
        </script> 
    </body>
</html>

