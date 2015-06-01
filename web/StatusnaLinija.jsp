<%-- 
    Document   : StatusnaLinija
    Created on : Feb 8, 2015, 1:37:45 AM
    Author     : neso
--%>

<%@page import="KlaseBaze.Vlasnik"%>
<%@page import="KlaseBaze.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <% Korisnik currUser =((Korisnik)(session.getAttribute("currentSessionUser")));%>	           
        <% Vlasnik currVlasnik =((Vlasnik)(session.getAttribute("currentSessionVlasnik")));%>
        <div class="Radnik" >
            <table width="100%">
                <tr>
                    <td width=18%>
                        <img class="imgPng" alt="username" src="resursi/slike/username.png">                     
                        <a class="imgPng"><%= currUser.getUserName()%></a></td>
                   
                    <td width=27%>
                        <img class="imgPng" alt="korisnik" src="resursi/slike/korisnik.png">                            
                        <a class="imgPng"><%= currUser.getPrezime() + " " + currUser.getIme()%></a></td>
                    <td width=30% align=left>
                        <img class="imgPng" alt="vlasnik" src="resursi/slike/firma.png">                               
                        <a class="imgPng"><%= currUser.getVlasnik() + " " + currVlasnik.getNaziv() %></a></td>
                        
                    <%
                        //response.setIntHeader("Refresh", 1); 
                        java.text.DateFormat dt = new java.text.SimpleDateFormat("dd.MM.yyyy.");
                    %>                   
                    <td width=25% align=right>
                        <img class="imgPng" alt="datum" src="resursi/slike/kalendar.png">
                        <a class="txtPng"><%= dt.format(new java.util.Date()) %> </a>
                        <img class="imgPng" alt="casova" src="resursi/slike/sat.png">
                        <a class="txtPng" id="Timer"> </a> </td>    
                </tr>              
             </table>
        </div>       
        
        
    </body>
</html>
