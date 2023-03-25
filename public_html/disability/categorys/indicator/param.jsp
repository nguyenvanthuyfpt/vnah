<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<logic:equal value="0" name="indicator" property="type">
<table width="100%">      
<tr>
    <!--<td><bean:message key="indicator.edit.type.time" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select name="indicator" property="typePeriod" onchange="javascript:postAjax('indicator','test',anchor + ':_CHANGE_DATATYPE');" styleClass="combobox_w90">
            <html:option value="0"><bean:message key="indicator.edit.type.month" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="indicator.edit.type.quarter" bundle="<%=interfaces%>"/></html:option>                                                
        </html:select>
    </td>-->
    <td width="25%">Ch&#7881; s&#7889; ph&#226;n c&#7845;p</td>
    <td>
        <html:select name="indicator" property="lvl" styleClass="combobox_w90">
            <html:option value="0">Kh&#244;ng</html:option>
            <html:option value="1">C&#243;</html:option>                                                
        </html:select>
    </td>
</tr>

<!--<tr>
    <td><bean:message key="indicator.edit.target.year" bundle="<%=interfaces%>"/></td>
    <td><html:text name="indicator" property="targetYear" styleClass="right" size="9"/></td>
    <td><bean:message key="indicator.edit.baseline" bundle="<%=interfaces%>"/></td>
    <td><html:text name="indicator" property="baseline" styleClass="right" size="9"/></td>
</tr>-->

<!-- Quy -->
<%--<logic:equal value="1" name="indicator" property="typePeriod">
<tr>
    <td><bean:message key="indicator.edit.q1" bundle="<%=interfaces%>"/></td>
    <td><bean:message key="indicator.edit.q2" bundle="<%=interfaces%>"/></td>
    <td><bean:message key="indicator.edit.q3" bundle="<%=interfaces%>"/></td>
    <td><bean:message key="indicator.edit.q4" bundle="<%=interfaces%>"/></td>
</tr>

<tr>
    <td><html:text name="indicator" property="q1" styleClass="right" size="9"/></td>
    <td><html:text name="indicator" property="q2" styleClass="right" size="9"/></td>
    <td><html:text name="indicator" property="q3" styleClass="right" size="9"/></td>
    <td><html:text name="indicator" property="q4" styleClass="right" size="9"/></td>
</tr>
</logic:equal>--%>
      
<!-- Thang -->
<%--<logic:equal value="0" name="indicator" property="typePeriod">
<tr>
    <td colspan="4">
        <table width="90%">
        <tr>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;1</td>                                            
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;2</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;3</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;4</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;5</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;6</td>
        </tr>
        <tr>
              <td><html:text name="indicator" property="m1" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m2" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m3" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m4" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m5" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m6" styleClass="right" size="9"/></td>
        </tr>
        <tr>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;7</td>                                            
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;8</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;9</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;9</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;11</td>
              <td><bean:message key="common.label.month" bundle="<%=interfaces%>"/>&nbsp;12</td>
        </tr>
        <tr>
              <td><html:text name="indicator" property="m7" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m8" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m9" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m9" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m11" styleClass="right" size="9"/></td>
              <td><html:text name="indicator" property="m12" styleClass="right" size="9"/></td>
        </tr>
        </table>
    </td>
</tr>
</logic:equal>--%>
</table>
</logic:equal>
 
