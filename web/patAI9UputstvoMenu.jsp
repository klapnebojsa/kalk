<%-- 
    Document   : zCeneDatKalk
    Created on : Mar 15, 2015, 3:30:25 AM
    Author     : neso
--%>

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
        <title>Uputstvo (Template)</title>
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
            
            <form action="AI9_Servlet" method="post" style="text-align: center">
                <table class="izbor"  >
                    <tr>
                      <th>Uputstvo (Template):</th>
                    </tr>                
                    <tr>
                        <td><input type="radio" name="SrpskiKineski" value="Sr" checked>Uputstvo - Srpski jezik  </td>                     
                    </tr>
                    <tr>
                        <td><input type="radio" name="SrpskiKineski" value="Ki">Uputstvo - Kineski jezik </td>                  
                    </tr>
                </table>                
                
                <input type="submit" class="dugmeOK" id="OK" name="OK" value="OK" style="visibility: visible; margin-top:30px;">            
            </form>
            
            <div id="porukaSer" style="margin-left: 100px;">           
                <c:if test="${not empty Poruka9}">
                    <p class="porukaBrisanja">${Poruka9}</p>
                </c:if>  
            </div>            
        </div>

    </body>
</html>


