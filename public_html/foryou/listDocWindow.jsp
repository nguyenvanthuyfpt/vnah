<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%int active=0;%>
<logic:present name="BListDocsRecv" >
<logic:notEmpty name="BListDocsRecv" >
<div style="overflow: auto;margin-left:15px;height: 100px">
    <logic:iterate name="BListDocsRecv" id="bean" indexId="i" type="com.form.doc.docsrecv.FDocsrecv">
    <%active=1;%>
            <div><Span><%=i+1%>.</span><%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                    <a href="javascript:post('docsrecv',anchor +':_PREPARE_DOC_REVIEW_RECV:id:<bean:write name="bean" property="id"/>');"><Strong><bean:write name="bean" property="docCode"/></strong></a>
                    <%}else{%>
                    <Strong><bean:write name="bean" property="docCode"/></strong>
                    <%}%> , <bean:write name="bean" property="docDate"/>
                    <br>
                    <bean:write name="bean" property="abstracts"/>        
                    </div>
    </logic:iterate>
</div>
</logic:notEmpty>
</logic:present>

<logic:present name="BListDocsSend" >
<logic:notEmpty name="BListDocsSend" >
<div style="overflow: auto;margin-left:15px;height:100px;">
<logic:iterate name="BListDocsSend" id="bean" indexId="j" type="com.form.doc.docssend.FDocssend">
<%active=1;%>
<div><Span><%=j+1%>.</span><%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                    <a href="javascript:post('docssend',anchor +':_PREPARE_DOC_REVIEW_SEND:id:<bean:write name="bean" property="id"/>');"><bean:write name="bean" property="docCode"/></a>
                    <%}else{%>
                    <bean:write name="bean" property="docCode"/>
                    <%}%>,<bean:write name="bean" property="docDate"/>
            <br>
            <bean:write name="bean" property="abstracts"/>
            </div>
           
</logic:iterate>
</div>
</logic:notEmpty>
 </logic:present>
<%if(active==0){%>
<div align="center">
    <bean:message key="not.user.forDoc" bundle="<%=interfaces%>"/>
</div>
<%}%>


