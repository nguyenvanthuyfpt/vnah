<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docsrecv" method="post" />
<html:form action="docssend" method="post" />
<logic:present name="BForYou">
  <table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-foryou">
        <bean:message key="forYou.header.giay" bundle="<%=interfaces%>"/>
    </a>
</td>
</tr>
</table>
<div style="padding-left:10px"> 
   <ul class="ulClassDoc">
                        <li>
                                <strong><bean:message key="forYou.label.boss" bundle="<%=interfaces%>"/> :</strong> <bean:write name="BForYou" property="boss"/>
                                  <strong> <bean:message key="doc.header.workflow.caption" bundle="<%=interfaces%>"/> :</strong>
                                <logic:equal name="BForYou" property="workflowId" value="1" >
                                     <bean:message key="doc.recv.caption" bundle="<%=interfaces%>"/>
                                  </logic:equal>
                                   <logic:equal name="BForYou" property="workflowId" value="2" >
                                            <bean:message key="doc.send.caption" bundle="<%=interfaces%>"/>
                                     
                                  </logic:equal>
                                  
                        </li>
                        <li>
                                <strong><bean:message key="forYou.label.dateFrom" bundle="<%=interfaces%>"/> : </strong><bean:write name="BForYou" property="dateFrom"/>
                                <strong><bean:message key="forYou.label.dateTo" bundle="<%=interfaces%>"/> : </strong><bean:write name="BForYou" property="dateTo"/>
                        </li>
                        <li>
                                <strong><bean:message key="forYou.label.dateCreate" bundle="<%=interfaces%>"/> :</strong> <bean:write name="BForYou" property="dateCreate"/>
                                  <logic:equal name="BForYou" property="status" value="1">            
                                    <img style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="forYou.label.state.1" bundle="<%=interfaces%>"/>">
                                </logic:equal>
                                <logic:notEqual name="BForYou" property="status" value="1">            
                                    <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="forYou.label.state.2" bundle="<%=interfaces%>"/>">
                                </logic:notEqual>
                        </li> 
                        <li><bean:write name="BForYou" property="problem"/></li>                       
         </ul>               
</div>
  <table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-foryou">
        <bean:message key="forYou.header.forContent.caption" bundle="<%=interfaces%>"/>
    </a>
</td>
</tr>
</table>
<div style="padding-left:10px">
<logic:present name="BDetailts">
<bean:define name="BDetailts" id="beans" type="com.form.FBeans"/>
<% int i=0;  if (beans.size()==0) {  %>
  <strong>  <bean:message key="forYou.not.caption" bundle="<%=interfaces%>"/></strong>
<%}%>
<logic:equal name="BForYou" property="workflowId" value="1" >
    <logic:iterate name="BDetailts" id="bean" type="com.form.doc.docsrecv.FDocsrecv">     
     <ul class="ulClass">
   <li>
            <Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="docCode"/>
            <Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="localCode"/>
           
        </li>
        <li>
            <Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="creator"/>
            <Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="signer"/>
           
        </li>
        
        <li>
              <Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/>:</strong>
              <bean:write name="bean" property="timeCreate"/>
              <Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/>:</strong>
              <bean:write name="bean" property="localDate"/>
                        
        </li>
         <li>
                <Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</strong>
                <bean:write name="bean" property="formName"/>
         </li><li>       <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>:</strong>
                <bean:write name="bean" property="dossiersName"/>
        </li>
         
         <li>
                 <Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>:</strong>
                 <bean:write name="bean" property="abstracts"/>
        </li>
  </ul>
  </logic:iterate>
</logic:equal>

<logic:notEqual name="BForYou" property="workflowId" value="1" >
    <logic:iterate name="BDetailts" id="bean" type="com.form.doc.docssend.FDocssend">     
       <ul class="ulClass">
    <li>
            <Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="docCode"/>
            <Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="localCode"/>
           
        </li>
        <li>
            <Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="creator"/>
            <Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="signer"/>
           
        </li>
         
        <li>
              <Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/>:</strong>
              <bean:write name="bean" property="timeCreate"/>
              <Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/>:</strong>
              <bean:write name="bean" property="localDate"/>
                        
        </li>
         <li>
                <Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</strong>
                <bean:write name="bean" property="formName"/>
          </li><li>      <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>:</strong>
                <bean:write name="bean" property="dossiersName"/>
        </li>
         <li>
                 <Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>:</strong>
                 <bean:write name="bean" property="abstracts"/>
        </li>
  </ul>
  </logic:iterate>
</logic:notEqual>
</logic:present>
</div>
</logic:present>
