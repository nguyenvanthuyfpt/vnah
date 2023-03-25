<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="docsrecv">
<bean:define name="docsrecv" id="bean" type="com.form.doc.docsrecv.FDocsrecv" />
<div class="ct-celendar">  
<table style="border-collapse: collapse;line-height:20px"  cellpadding="0" cellspacing="3" align="left" width="100%" border="0">
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>: </strong></td>
        <td valign="top" colspan="3">
        <bean:write name="docsrecv" property="abstracts"/></td>
    </tr>
     <logic:notEmpty name="docsrecv" property="allFiles">
    <tr>
        <td nowrap valign="top" >
            <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/> :  </strong>
        </td>
        <td valign="top"   colspan="3" align="left" >
            <ol style="line-height:20px;" >
            <logic:iterate name="docsrecv" property="allFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">                       
            <li><A href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');"><bean:write name="beanFiles" property="fileName" /></a></li>
            </logic:iterate>
            </ol>
        </td>
      
    </tr>
    </logic:notEmpty>
    <tr>
        <td nowrap valign="top"  ><Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"  ><bean:write name="docsrecv" property="creator"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="docCode"/></td>        
    </tr>
    <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="docTypeName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="localCode"/></td>
    </tr>
    
     <tr>
        <td nowrap valign="top" ><Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"  ><bean:write name="docsrecv" property="signer"/></td>
        
        
        <td nowrap valign="top"><Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="timeCreate"/></td>
    </tr>
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="viaName"/></td>
        
        
        <td nowrap valign="top"><Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="localDate"/></td>
    </tr>
     
     <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="formName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top">
        <bean:write name="docsrecv" property="dossiersName"/>
        </td>
    </tr>
    
      <tr>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="expressName"/></td>
        <td nowrap valign="top"><Strong><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top"><bean:write name="docsrecv" property="secureName"/></td>
    </tr>
    
   
     
    <tr>
        <td nowrap valign="top"  ><Strong><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/> : </strong></td>
        <td nowrap valign="top" align="left" colspan="2" ><bean:write name="docsrecv" property="statusName"/></td>
          <td valign="top" align="left">
           
        </td>
    </tr>
    </table>
    </div>
</logic:present>