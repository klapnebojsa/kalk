<%-- 
    Document   : zCeneDatKalk
    Created on : Mar 15, 2015, 3:30:25 AM
    Author     : neso
--%>

<%@page import="paterni6memento.AI6IKreirajMemento"%>
<%@page import="paterni6memento.AI6IMemento"%>
<%@page import="paterni6memento.AI6IKorisnik"%>
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
        <title>Razlika u ceni (Memento)</title>
    </head>
    <body class="LoginBody" onload="Timer()">        
        <% Korisnik currUser =((Korisnik)(session.getAttribute("currentSessionUser")));%>	           
        <% Vlasnik currVlasnik =((Vlasnik)(session.getAttribute("currentSessionVlasnik")));%>

        <div class="Logo" style="text-align: center;">
            <a class="FontAgency" style="font-size: 24px;">ACCOUNTING AGENCY </a><br>          
        </div>            

        <%=new Meni().IspisiMeni(currUser.getNivo())%>      
        <jsp:include page="StatusnaLinija.jsp" />
        <!-- KREIRANJE MEMENTO-a -->
        <%
            AI6IKorisnik korVp = new AI6IKorisnik(null);          
            korVp.PripremiVp();
            korVp = korVp.getkorVp();
            
            AI6IKorisnik korMp = new AI6IKorisnik(null);
            korMp.PripremiMp();            
            korMp = korMp.getkorMp(); 
            
            session.setAttribute("korisnikVp6", korVp);
            session.setAttribute("korisnikMp6", korMp);            
            
        %>
        
        <div  class="DivGoreTabla1">
            <form action="AI6_IzvRazUCeni" method="post" style="text-align: center">
                <table class="izbor"  >
                    <tr>
                      <th>Pregled razlike u ceni (Memento), Priprema :</th>
                    </tr>                
                    <tr>
                        <td><input type="radio" name="VpMp" value="Vp" checked style="visibility: hidden;">Veleprodajne kalkulacije  </td>                     
                    </tr>
                    <tr>
                        <td><input type="radio" name="VpMp" value="Mp" style="visibility: hidden;">Maloprodajne kalkulacije </td>                  
                    </tr>
                </table>             
                <input type="submit" class="dugmeOK" id="OK" name="OK" value="OK" style="visibility: visible; margin-top:30px;">          
            </form>
            
            <div id="porukaSer" style="margin-left: 100px;">           
                <c:if test="${not empty Poruka}">
                    <p class="porukaBrisanja">${Poruka}</p>
                </c:if>  
            </div>            
        </div>

    </body>
</html>

