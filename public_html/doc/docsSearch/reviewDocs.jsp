<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head>
<jsp:include page="/commons/set.jsp"/>
</head>
<%
%>
<bean:define name="BFiedls" id="fields" type="com.form.doc.docsSearch.FDocsSearch"/>
<table  id="table6" style="border-collapse: collapse" cellpadding="0" cellspacing="0" width="100%">
<tr>
    <td  class="title-01" colspan="<%=fields.getFields().length%>" nowrap>
        <a href="#" class="li-title-02">
         <bean:message key="form.docs.header.list.dossier" bundle="<%=interfaces%>"/>
        </a>
    </td>
    </tr>
    
<tr>
<%for(int k=0;k<fields.getFields().length;k++){%>

    <td class="tdheader" style="cursor: pointer;"><Strong>
     <bean:message key="<%=com.inf.IConstants.HEADER_DOCS_REVIEW[fields.getFields()[k]]%>" bundle="<%=interfaces%>"/>
    </strong></td>
    
<%}%>
</tr>
 
<logic:present name="BSearch" >
<bean:define name="BSearch" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
<logic:iterate name="BSearch" id="bean" type="com.form.doc.docsrecv.FDocsrecv">
    <tr>
     <%for(int j=0;j<bean.getValues().length;j++){%>
    <td    align="left">
    <%if(bean.getValues()[j]==null){}else{%>
    
    
    <%=bean.getValues()[j]%>
    <%}%>
    </td>
     <%}%>
    </tr>    
    </logic:iterate>

</logic:present>
</table>
