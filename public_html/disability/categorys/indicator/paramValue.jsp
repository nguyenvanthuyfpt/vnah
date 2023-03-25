<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<table width="100%">
<tr>
    <td>
        <bean:message key="common.label.year" bundle="<%=interfaces%>" />
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
    </td>
    <td>
        <html:select styleClass="inputbox" name="indicator" property="year" >
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2016">2016</html:option>
            <html:option value="2017">2017</html:option>
            <html:option value="2018">2018</html:option>
            <html:option value="2019">2019</html:option>
            <html:option value="2020">2020</html:option>
            <html:option value="2021">2021</html:option>
            <html:option value="2022">2022</html:option>
        </html:select>
    </td>  
    <td>Target/Baseline</td>
    <td>
        <html:text name="indicator" property="value" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" />
    </td>
</tr>
</table>