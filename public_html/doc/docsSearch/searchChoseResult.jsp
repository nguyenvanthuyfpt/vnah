<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
       <table class="winSearch"  width="500px">
	<tr>
		<th class="tdheader"><bean:message key="report.select.fields" bundle="<%=interfaces%>"/></th>
	</tr>
        <tr><td>
        <table width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse">
	<tr>
		<td   align="left" >
                <input type="checkbox" name="fields" value="0" id="0" checked>
                <label for="0" >
                <bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>
                </label>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="1" id="1" checked>
                <label for="1" >
                <bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>
                </label>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="2" id=2 checked>
                <label for="2" >
                <bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/>
                </label>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="3" checked>
                <bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="4">
                <bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/>
                </td>
	</tr>
	<tr>
		<td   align="left">
                <input type="checkbox" name="fields" value="5" checked>
                <bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="6">
                <bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="7">
                <bean:message key="form.docs.fromId" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="8">
                <bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="9">
                <bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/>
                </td>
	</tr>
	<tr>
		<td   align="left">
                <input type="checkbox" name="fields" value="10">
                <bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="11" checked>
                <bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="12" checked>
                <bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="13">
                <bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left">
                <input type="checkbox" name="fields" value="14">
                <bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/>
                </td>
	</tr>
	<tr>
		<td   align="left">
                <input type="checkbox" name="fields" value="15">
                <bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/>
                </td>
		<td   align="left" colspan="4" >
                <input type="checkbox" name="fields" value="16">
                <bean:message key="form.docs.address" bundle="<%=interfaces%>"/>
                </td>
		
	</tr>
	
</table>    
</td></tr>
<tr>
  <td  align="center">
  
  
B&#225;o c&#225;o 
 <input type="checkbox" name="export" value="1">
    <html:button   property="_REVIEW" styleClass="button" onclick="javascript:post('docsSearch',anchor + ':_REVIEW');remove('docsSearch',anchor)">
   In C&#244;ng v&#259;n
    </html:button>
    <html:button property="_and" styleClass="button" onclick="closeWindow();">
    Tho&#225;t
    </html:button>
</td>
</tr>
</table>