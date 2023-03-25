<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<html:form action="templateEdit" method="post" enctype="multipart/form-data">
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0" ><tr><td class="LEFT" align="left"></td><td width="7px" class="RIGHT"></td></tr></table>
<div class="content-calendar">
<table class="tableForm" style="border-collapse: collapse"  width="100%"  cellpadding="0" cellspacing="0">
            <TR><TD colspan="2"><BR></td></tr>
            <tr>
                <td ><bean:message key="title.template.label.code" bundle="<%=interfaces%>"/></td>
                <td align="left"><html:text name="template" property="code" />   <bean:message key="title.template.label.effectiveDate" bundle="<%=interfaces%>"/> :<html:text name="template" property="effectiveDate" styleId="effectiveDate" /><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'effectiveDate','dd/mm/yyyy');return false;"></td>
            </tr>            
            <tr>
                <td><bean:message key="title.template.label.department" bundle="<%=interfaces%>"/></td>
                <td align="left">
                    <html:select name="template" property="departmentId"  >
                    <logic:present name="BDepartments">
                    <html:options collection="BDepartments" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
                </td>
            </tr>            
            
            <tr>
                <td><bean:message key="title.template.label.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                <td align="left"><html:text name="template" property="name" style="width:98%;"/></td>
            </tr>            
            
            <tr>
                <td valign="top"><bean:message key="title.template.label.description" bundle="<%=interfaces%>"/></td>
                <td align="left">
                 <textarea id="description" name="description" style="width:98%;height:80px">
                        <bean:write name="template" property="description"/>
                    </textarea>
                </td>
            </tr>
            
            <tr>
                <td><bean:message key="title.template.label.upFile" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                <td align="left"><html:file name="template" property="upFile" size="40"  /></td>
            </tr>   
        
        <tr>
         <td><bean:message key="title.template.label.TemplateType_id" bundle="<%=interfaces%>"/></td>
        <td align="left">
            <html:select name="template" property="templateType_id" styleClass="fieldSelect" >
                    <logic:present name="BRuleTemplateTypes">
                     <html:options collection="BRuleTemplateTypes" property="id" labelProperty="name"/>
                     </logic:present>
            </html:select>
        </td></tr>
        
        <TR><TD colspan="2"><BR></td></tr>
        <tr><td colspan="2" class="toolCmd" style="text-align:left">
        <jsp:include page="/template/cmd.jsp" />
        </td></tr>
</table>
<html:hidden name="template" property="userId" />
<html:hidden name="template" property="creator" />
<html:hidden name="template" property="type" />
</div>
</html:form>