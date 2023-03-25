<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <p><font style="color:#a0aec2"><b><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></b></font></p>
    <logic:present name="BDossiers">
         <html:select name="docsrecv" property="dossierId" onchange="javascript:post('docsrecv',anchor+':_VIEW');" styleClass="fieldSelect" style="width:100px;" >
                <html:option value="0" key="status.all" bundle="<%=interfaces%>"/>
                <logic:present name="BDossiers">
                <html:options collection="BDossiers" property="id" labelProperty="name"/>
                </logic:present>
        </html:select>
         
    </logic:present>


               