<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="dossiers" method="post" >
 <div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" >
                         <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="3"/></jsp:include>
                    </td>
                </tr>
            </table>
            
    </div>   
     <div id="fragment-1">  
                  <div class="content-calendar">
                        <table width="100%" border="0px" cellpadding="0" cellspacing="0">                             
                            <TR>
                                   <TD valign="top" id="listdocs">
                                         <jsp:include page="/doc/category/dossiers/listDossier.jsp" />
                                    </td>
                            </tr>
                            </table>
                        </div>
</div>
     </div>
 </div>

</html:form>