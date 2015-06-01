<%-- 
    Document   : PromLozinke
    Created on : Feb 2, 2015, 11:26:03 PM
    Author     : neso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='resursi/css/MojStil.css'>   
        <title>Promena Lozinke</title>
    </head>
    <body class="LoginBody">        
            <fieldset class="OkvirMC" >
            <legend align="right" class='dugmeLe'>Promena Lozinke</legend>
            <form class="Prijava" action="PromLozinkeServlet">             
                <table>
                    <tr>
                        <td style='width:170px; text-align:right'>Korisničko ime:</td>
                        <td> <input type="text" name="unamePL" /> </td>
                    </tr>
                    <tr>
                        <td style='width:170px; text-align:right'>Lozinka:</td>                
                        <td><input type="password" name="passwPL" /></td>
                    </tr> 
                    <tr>
                        <td style='width:170px; text-align:right'>Nova Lozinka:</td>                
                        <td><input type="password" name="newpasswPL" /></td>
                    </tr>
                    <tr>
                        <td style='width:170px; text-align:right'>Ponovite Novu Lozinku:</td>                
                        <td><input type="password" name="renewpasswPL" /></td>
                    </tr>                    
                </table>
                <div align="center">
                    <input type="submit" value="OK" class="dugmeOK" style="width:100px; margin:20px; visibility: visible">
                </div> 
            </form>            
        </fieldset>      
    </body>
</html>
