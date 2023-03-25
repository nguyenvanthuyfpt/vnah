<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BdocsMove" >
<bean:define name="BdocsMove" id="beans" type="com.form.FBeans"/>
<% if (beans.size()>0){%>
<fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="form.docs.trailer.excute.caption" bundle="<%=interfaces%>"/></legend>
    <table class="tableForm" cellpadding="0" width="100%" style="border-collapse: collapse" cellspacing="0" border="0">    
        <tr>
            <td> <jsp:include page="/doc/docssend/inforForm/trailerMove.jsp" />  
            </td>
        </tr>
    </table>                        
</fieldset>
<%}%>
</logic:present>
