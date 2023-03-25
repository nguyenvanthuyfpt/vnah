<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <p><font style="color:#a0aec2">
    <b><bean:message key="categoryvia.via" bundle="<%=interfaces%>"/></b></font>                                
 </p>   
<logic:notPresent parameter="oberver">
        <logic:present name="BTransfer">
             <bean:define name="BTransfer" id="beansT" type="com.form.FBeans"/>
            <%if(beansT.size()>1){%>
                     <html:select name="docsrecv" property="views" onchange="javascript:post('docsrecv',anchor+':_VIEW');" styleClass="fieldSelect" style="width:100px;" >
                            <html:option value="-1" ><bean:message  key="status.all" bundle="<%=interfaces%>"/></html:option>
                            <html:options collection="BTransfer" property="id" labelProperty="name"/>
                    </html:select>
            <%}%>
        </logic:present>
</logic:notPresent>


               