<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="relative" method="POST" >
<html:hidden name="relative" property="idNkt" styleId="idNkt" />
<br>
<div class="content-calendar-2">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
     <TR>
            <td>T&#236;m ki&#7871;m th&#226;n nh&#226;n :</td>
            <td align="left" >
                <input type="text" name="searchSub" id="searchSub" style="width:150px" onkeydown="if(event.keyCode==13){postAjax('relative','userId',anchor + ':_SEARCH');return false;}"  />            
            </td>
     </tr>
      <tr>
                <td  nowrap="nowrap" width="120px"><bean:message key="relative.from.label.idRelativeNkt" bundle="<%=interfaces%>"/> :</td>
                <td align="left" id="userId" >
                       <jsp:include page="/disability/relative/users.jsp" />
                 </td>
                 <td  nowrap="nowrap" width="120px"><bean:message key="relative.from.label.lydoId" bundle="<%=interfaces%>"/> :</td>
                <td align="left" >
                       <html:select styleClass="inputbox" name="relative" property="lydoId">
                       <logic:present name="BLyDos" > 
                        <html:options collection="BLyDos" property="id" labelProperty="name"/>
                        </logic:present>
                      </html:select>
                 </td>
      </tr>
    
      <tr>
        <td colspan="4">
            <logic:notEqual name="relative" property="idNkt" value="0">
            <logic:empty name="BRelatives">
                    <html:button property="_CREATE" styleClass="button" onclick="postAjax('relative','MainCate',anchor + ':_INSERT');" >
                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
            </logic:empty>
            <logic:notEqual name="relative" property="id" value="0">
           <bean:define name="relative" property="id" id="id" type="java.lang.Integer" />
                    <%String onclick="postAjax('relative','MainCate',anchor + ':_UPDATE:id:"+id+"');";%>
                    <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>"   >                 
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>        
            </logic:notEqual>
            </logic:notEqual>
        </td>
      </tr>
</table>  
</div>
</html:form>
 
