<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<html:form action="cabin" method="post" enctype="multipart/form-data">
<html:hidden name="cabin" property="type" />
<html:hidden name="cabin" property="cabinType_id" />
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0"   >
    <tr>
        <td class="LEFT" width="98%" align="left">
            <Strong>
            <logic:equal name="cabin" property="type" value="3">
                <bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>        
            <logic:equal name="cabin" property="type" value="2">
                <bean:message key="header.cabin.label.shere" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="1">
                <bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="0">
                <bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/><bean:write name="cabin" property="rootPath"/>
            </logic:equal>
            </Strong>
        <td  class="RIGHT">
        </td>
    </tr>
</table>


               <div class="content-calendar">
                <table width="100%" border="0px" cellpadding="0" cellspacing="0"> 
                    <tr><td valign="top">
                        <jsp:include page="/cabin/editDep/edit.jsp"/>            
                    </td></tr>
                </table>
               </div>
   

</html:form>