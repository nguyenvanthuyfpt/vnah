 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

        <table class="tableForm" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse">	
        <tr>
                <td  align="left">
                    <input type="checkbox" name="fields" value="2" id=2 checked>                                            
                    <bean:message key="form.docs.send.localCode" bundle="<%=interfaces%>"/>                        
                </td>
		
                <td  align="left">
                    <input type="checkbox" name="fields" value="4" checked>                   
                    <bean:message key="form.docs.send.localDate" bundle="<%=interfaces%>"/>                   
                </td>
                
                <td  align="left">
                    <input type="checkbox" name="fields" value="3" checked>
                    <bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>
                </td>
		<td  align="left">
                    <input type="checkbox" name="fields" value="5" checked>
                    <bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>
                </td>
		<td  align="left">
                    <input type="checkbox" name="fields" value="8" checked>
                    <bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>
                </td>
	</tr>
	<tr>
	
                <td  align="left">
                    <input type="checkbox" name="fields" value="12" checked>
                    <bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/>
                  
                </td>
                
                <td  align="left" >
                    <input type="checkbox" name="fields" value="0" id="0">                  
                    <bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>                    
                </td>
                
		<td  align="left">
                    <input type="checkbox" name="fields" value="1" id="1" >                 
                    <bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>                    
                </td>
		<td  align="left">
                    <input type="checkbox" name="fields" value="7" checked>              
                    <bean:message key="form.docs.fromId.to" bundle="<%=interfaces%>"/>                
                </td>    
                 <td  align="left">
                <input type="checkbox" name="fields" value="9">
                <bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/>
                </td>
	</tr>
	<tr>   
                <td  align="left">
                <input type="checkbox" name="fields" value="11">
                <bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/>
                </td>
                 <td  align="left">
                <input type="checkbox" name="fields" value="6">
                <bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/>
                </td>
                
		
		<td  align="left">
                <input type="checkbox" name="fields" value="13" >
                <bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/>
                </td>
		<td  align="left">
                <input type="checkbox" name="fields" value="15" >
                <bean:message key="form.docs.address" bundle="<%=interfaces%>"/>
                </td>
                <td  align="left">
                <input type="checkbox" name="fields" value="10" >
                <bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>
                </td>
		
	</tr>
	<tr>
		
                <td  align="left">
                <input type="checkbox" name="fields" value="14">
                <bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/>
                </td>
		<td  align="left" >
                <input type="checkbox" name="fields" value="16" checked>
                <bean:message key="form.docs.numberVersion" bundle="<%=interfaces%>"/>
                </td>
                <td  align="left" colspan="3">
                <input type="checkbox" name="fields" value="17" checked>
                <bean:message key="categorysecure.discription" bundle="<%=interfaces%>"/>
                </td>
              
	</tr>
	<tr>           
            <td align="left" colspan="5">
               <html:button   property="_REVIEW" styleClass="button" onclick="javascript:post('docreport',anchor + ':_REPORT');remove('docreport',anchor)">
                        <bean:message key="doc.docreport.chose.exportreport" bundle="<%=interfaces%>"/>
                </html:button>
                 <logic:equal name="docreport" property="checkObserver" value="1" >
                 <logic:equal name="docreport" property="totalReport" value="1" >
                 
                     <html:select name="docreport" property="type"  styleClass="inputbox" > 
                        <html:option value="6"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                       
                         <logic:equal name="docreport" property="workflowId" value="1" >
                         <html:option value="3"><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/></html:option>
                        </logic:equal>
                        <html:option value="4"><bean:message key="categorydoctype.doctype" bundle="<%=interfaces%>"/></html:option>
                     </html:select>
                 </logic:equal>
                 </logic:equal>
            </td>
        </tr>
</table>   

