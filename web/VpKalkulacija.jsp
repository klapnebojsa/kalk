<%-- 
    Document   : VpKalkulacija
    Created on : Mar 10, 2015, 12:48:00 AM
    Author     : neso
--%>

<%@page import="KlaseBaze.Vpkalkuk"%>
<%@page import="KlaseBaze.Partner"%>
<%@page import="KlaseBaze.Finnaloguk"%>
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
        <title>Veleprodajna kalkulacija</title>
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
            
            <table id="zaglavlje1" class="zaglavlje"  style="width:1160px;">         
                <tr>
                  <td width="21px"></td>
                  <td width="100px">Br.Kalkul.</td>  <!--SIFRA -->
                  <td width="100px">Br.Dokum.</td>
                  <td width="80px">Datum</td> 
                  <td width="140px">Valuta</td>                  
                  <td width="320px">Dobavljac</td> 
                  
                  <td width="120px">Uplac.porez</td>
                  <td width="120px">Nabavna Cena</td> 
                  <td width="120px">VP Cena</td>                  
                  <td width="120px">Razl.u ceni</td> 
                  <td width="120px">Uk.Porez</td>                  
                </tr> 
            </table>       
            <div align="center">                           
                <form action="PiBrIzServlet" method="post" style="display:inline">                      
                    <table id="podaci1" class="GornjaTabla"  style="width:1160px;">                         
                        <%
                            String jNovi = request.getParameter("jNovi").toString();
                            if ("Cisto".equals(jNovi)) session.setAttribute( "jesteNovi", "");                            
                            session.setAttribute("KoZove", "VpKalkulacija");
                            //Obavezno unositi bez blanka pre i posle zareza
                            String ImePolja = "VBrDo,VDatu,VValu,VDoba,VUPor"; //,VNaba,VPCen,VPRaz,VPPor;
                            session.setAttribute("ImePolja", ImePolja);
                            
                            String sifra,vbrdo,vdatu,vvalu,vdoba,vpore,vnaba,vpcen,vpraz,vppor;
                            ProcitajSve emp = new ProcitajSve();
                            List myDataList = null;
                            
                            InterfaceDAO k;                       
                            Vpkalkuk vpkalkuk = new Vpkalkuk();
                            vpkalkuk.setVpIdVlasnikUK(currUser.getVlasnik()); 
                            k = vpkalkuk;
                            myDataList = emp.ProcitajSvexx(k);                            
                            
                            //myDataList = emp.ProcitajSvexx("Vpkalkuk", currUser.getVlasnik(),null);
                 
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
                                        if (i>6 && i<=11){singleRow.add(Double.parseDouble("0.00"));
                                        } else {singleRow.add("");}
                                    }
                                    myDataList.add(singleRow);                                    
                                    //Obavezno unositi bez blanka pre i posle zareza
                                    ImePolja = "VKalk,VBrDo,VDatu,VValu,VDoba,VUPor";//,VNaba,VPCen,VPRaz,VPPor;
                                    elemMyDataList = myDataList.size();
                                    session.setAttribute("elemMyDataList", elemMyDataList);                                    
                                    session.setAttribute("ImePolja", ImePolja);
                                }                                
                            }catch(Exception e){
                                //request.setAttribute("imaNeispravnih", "");   
                            }
                            emp = new ProcitajSve();;             
                            Partner pPartner = new Partner();
                            k = pPartner;
                            List myPartnerCBox = emp.ProcitajSvexx(k); 
                            //Prebacivanje tabele u Servlet. Direktno prebaci DataList ali i napusti izvrsavanje jsp-a 
                            //RequestDispatcher rd = request.getRequestDispatcher("PiBrIzServlet");
                            //request.setAttribute("DataList1", myDataList);                    
                            //rd.forward(request, response);
                            session.setAttribute("myDList", myDataList);                            
                            Integer i=0;
                            try{   
                                for(Object category : myDataList) {
                                    List element = (List)category;   
                                    sifra=element.get(0)==null? "" : element.get(0).toString();
                                    vbrdo=element.get(2)==null? "" : element.get(2).toString();
                                    
                                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date;
                                    try{
                                        date=sdf.parse(element.get(3).toString())==null? null : sdf.parse(element.get(3).toString());
                                        vdatu = sdf.format(date);                                    
                                    }catch(Exception e){vdatu=null;}
                                    try{
                                        date=sdf.parse(element.get(4).toString())==null? null : sdf.parse(element.get(4).toString());
                                        vvalu = sdf.format(date);                                    
                                    }catch(Exception e){vvalu=null;}                                    
                                    vdoba=element.get(5)==null? "" : element.get(5).toString();                                    

                                    DecimalFormat df = new DecimalFormat("##,##0.00");
                                    vpore=element.get(6)==null?  "" : df.format(element.get(6));                                
                                    vnaba=element.get(7)==null?  "" : df.format(element.get(7));                                    
                                    vpcen=element.get(8)==null?  "" : df.format(element.get(8));                                
                                    vpraz=element.get(9)==null?  "" : df.format(element.get(9));
                                    vppor=element.get(10)==null? "" : df.format(element.get(10));                              
                        %>               
                        <tr>
                            <td width="2px"   class="radioTD"><input type="radio" class="radio1" value="<%=sifra %>" name="oznXX" onclick="check('<%=ImePolja %>')" /></td>      
                            <td width="100px" class="maxText"><input id="VKalk" name="VKalk" placeholder="" class="S100Posto" type="text" value="<% out.println(sifra); %>"  disabled="disabled" maxlength="10"> </td>
                            <td width="100px" class="maxText"><input id="VBrDo" name="VBrDo" placeholder="" class="S100Posto" type="text" value="<% out.println(vbrdo); %>"  disabled="disabled" maxlength="10"> </td>  
                            <td width="80px" class="maxText"><input id="VDatu" name="VDatu"                class="S100Posto" type="date" value="<%= vdatu %>"               disabled="disabled" style="text-align: center"> </td>
                            <td width="80px" class="maxText"><input id="VValu" name="VValu"                class="S100Posto" type="date" value="<%= vvalu %>"               disabled="disabled" style="text-align: center"> </td>
                            
                            <td width="250px" class="maxText">
                                <select id="VDoba"  name="VDoba" placeholder="" class="S100Posto" type="text" value="<% out.println(vdoba); %>" disabled="disabled">
                                    <%
                                        String elemNP, kkpp;
                                        kkpp = "";
                                        for(Object par : myPartnerCBox) {
                                            List elem = (List)par;
                                            elemNP = elem.get(0).toString() + " - " + elem.get(1).toString();
                                            kkpp = "";
                                            if (vdoba.equals(elem.get(0).toString()))kkpp = "SELECTED";
                                    %>    
                                    <option <% out.println(kkpp); %> ><% out.println(elemNP); %></option>    
                                    <% } %>
                                </select>
                            </td>                            

                            <td width="120px" class="maxText"><input id="VUPor" name="VUPor" placeholder="" class="S100Posto" type="text" value="<% out.println(vpore); %>" disabled="disabled" maxlength="12" style="text-align: right"> </td>                              
                            
                            <td width="120px" class="maxText"><input id="VNaba" name="VNaba" placeholder="" class="S100Posto" type="text" value="<% out.println(vnaba); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>                                  
                            <td width="120px" class="maxText"><input id="VPCen" name="VPCen" placeholder="" class="S100Posto" type="text" value="<% out.println(vpcen); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>                              
                            <td width="120px" class="maxText"><input id="VPRaz" name="VPRaz" placeholder="" class="S100Posto" type="text" value="<% out.println(vpraz); %>" readonly="readonly" maxlength="12" style="text-align: right"> </td>                                  
                            <td width="120px" class="maxText"><input id="VPPor" name="VPPor" placeholder="" class="S100Posto" type="text" value="<% out.println(vppor); %>" readonly="readonly" maxlength="12" style="text-align: right"> <% i++;}}catch(Exception e){}%></td>                        
               
                        </tr>
                    </table>                    

                    <input type="submit" id="Brisi"    name="Brisi"    value="Briši"       class="dugmeOK" disabled onclick="zeliLi('izbrišete')">
                    <input type="submit" id="Upisi"    name="Upisi"    value="Upiši"       class="dugmeOK" disabled onclick="zeliLi('upišete')">
                    <input type="submit" id="Novi"     name="Novi"     value="Novi"        class="dugmeOK" style="visibility: visible" onclick="zeliLi('ovorite novi')">                    
                    <input type="submit" id="NovaStav" name="NovaStav" value="Pregled Stavki" class="dugmeOK" disabled  style="width:120px" onclick="zeliLi('pregledate i unosite stavke dokumenta')"> 
                    <input type="submit" id="Odustani" name="Odustani" value="Odustani"    class="dugmeOK" disabled onclick="zeliLi('odustanete od upisa')">
                
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