<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript">

</script>
<html:form action="indicator" method="post">
    <html:hidden name="indicator" property="id"/>
    <div class="padding-content">
        <table align="left" width="100%">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="indicator">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td width="25%">
                                            <bean:message key="indicator.edit.code" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td width="25%">
                                            <html:text name="indicator" property="code" styleClass="inputbox" size="20"/>
                                        </td>
                                        <td width="25%">
                                            <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                        </td>
                                        <td width="25%">
                                            <html:text name="indicator" property="createDate" readonly="true" size="10" styleClass="center"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="indicator.edit.name" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:textarea name="indicator" property="name" styleClass="inputbox" rows="2" cols="50"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td><bean:message key="indicator.edit.description" bundle="<%=interfaces%>"/></td>
                                        <td colspan="3">
                                            <html:textarea name="indicator" property="description" rows="2" cols="50"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td><bean:message key="indicator.edit.target.justification" bundle="<%=interfaces%>"/></td>
                                        <td colspan="3">
                                            <html:textarea name="indicator" property="targetJustification" rows="8" cols="50"/>
                                        </td>
                                    </tr>
                                    
                                    
                                    <tr>
                                        <td><bean:message key="indicator.edit.parent" bundle="<%=interfaces%>"/></td>
                                         <td colspan="3">
                                            <html:select name="indicator" property="parentID" styleClass="combobox_w300" >
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <logic:present name="optIndicators">
                                                    <html:options collection="optIndicators" property="id" labelProperty="name"/>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><bean:message key="indicator.edit.type" bundle="<%=interfaces%>"/></td>
                                        <td colspan="3">
                                            <html:select name="indicator" property="type" onchange="javascript:postAjax('indicator','test',anchor + ':_CHANGE_DATATYPE');" styleClass="combobox_w100">
                                                <html:option value="0"><bean:message key="indicator.edit.type.val" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="1"><bean:message key="indicator.edit.type.dis" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="2"><bean:message key="indicator.edit.type.per" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="3"><bean:message key="indicator.edit.type.link" bundle="<%=interfaces%>"/></html:option>
                                            </html:select>
                                        </td>
                                    </tr>
                                    
                                    <tr id="test">
                                        <td>Ch&#7881; s&#7889; ph&#226;n c&#7845;p</td>
                                        <td colspan="3">
                                            <html:select name="indicator" property="lvl" styleClass="combobox_w90">
                                                <html:option value="0">Kh&#244;ng</html:option>
                                                <html:option value="1">C&#243;</html:option>                                                
                                            </html:select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </logic:present>
                    </td>
                </tr>
                <tr>
                    <td>
                        <logic:present name="indicator">
                            <jsp:include page="/disability/categorys/indicator/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 
