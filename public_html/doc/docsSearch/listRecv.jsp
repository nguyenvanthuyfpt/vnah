<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
    <tr>
    <td  class="title-01" nowrap>
        <a href="#" class="li-title-02">
          <bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/>
        </a>
    </td>
       
    </tr>
</table>

 <div class="ct-celendar">   
<html:form action="docsrecv" method="post" />
<html:form action="docsSearch" method="post" >
<div>
<a href="javascript:openWindow('docsSearch',anchor +':_PRINTER_FORM_DOCS');">
<Strong> <bean:message key="doc.print.title.caption" bundle="<%=interfaces%>"/></strong>
</a>
</div>
<table  id="table6" style="border-collapse: collapse" cellpadding="0" cellspacing="0" width="100%">
<tr>
  <td class="tdheader" style="cursor: pointer;" width="10px" >
       <bean:message key="app.stt" bundle="<%=interfaces%>"/>
  </td>
  <td  class="tdheader" style="cursor: pointer;" >
            <bean:message key="form.docs.type.header.recv" bundle="<%=interfaces%>"/>
  </td>
   <td class="tdheader" style="cursor: pointer;" >
        <bean:message key="form.docs.informations.header" bundle="<%=interfaces%>"/>
  </td>
  </tr>

<logic:present name="BSearch" >
<bean:define name="BSearch" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
<bean:define name="docsrecv" property="checkCreator" id="checkCreator" type="java.lang.Integer" />
<logic:iterate name="BSearch" id="bean" type="com.form.doc.docsrecv.FDocsrecv">
  <tr>
            <td    align="left">
            <span class="index">
            <%=i++%>.
            </span>
            </td>
            <td   valign="top">
            <ul class="ulClassDoc" >                
            <li><Strong><bean:message key="form.docs.peopleSend" bundle="<%=interfaces%>"/>:  </Strong><bean:write name="bean" property="creator"/>
                <logic:notEqual name="bean" property="forYouId" value="0">
                [ <a href="#" onMouseover="javascript:document.getElementById('forYouId<%=i%>').style.visibility='visible';postAjax('docsrecv','forYouId<%=i%>',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');" onMouseout="document.getElementById('forYouId<%=i%>').style.visibility='hidden'" ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a>  ]
                <div id="forYouId<%=i%>" style=""></div>
                </logic:notEqual>
            </li>
            <li>
            <Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>:</strong>
            <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
            <a href="javascript:post('docsrecv',anchor +':_PREPARE_DOC_REVIEW_RECV:id:<bean:write name="bean" property="id"/>');"><bean:write name="bean" property="docCode"/></a>
            <%}else{%>
            <bean:write name="bean" property="docCode"/>
            <%}%>
            <logic:equal name="bean" property="readed" value="1">        
            <img  style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="doc.readed.caption" bundle="<%=interfaces%>"/>">
            </logic:equal>
            <logic:notEqual name="bean" property="readed" value="1"> 
            <img  style="border:0px;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="doc.unReaded.caption" bundle="<%=interfaces%>"/>">
            </logic:notEqual>
            </li>
            <li>
            <Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>:</strong>
            <bean:write name="bean" property="abstracts"/>
            </li>
            </ul>
            </td>
            <td nowrap   align="left">
            <ul class="ulClassDoc">
                <li><Strong><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>:  </Strong><bean:write name="bean" property="docDate"/></li>
                <li><Strong><bean:message key="form.docs.timeSend" bundle="<%=interfaces%>"/>:  </Strong><bean:write name="bean" property="timeSend"/></li>
                <logic:notEqual name="bean" property="deadLine" value="" >
                <li><Strong><bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/>:  </Strong><bean:write name="bean" property="deadLine"/></li>
                </logic:notEqual>
                <li><Strong><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/>:  </Strong><span style="color:<%=bean.getStatusColor()%>"><bean:write name="bean" property="statusName"/></span></li>
            </ul>
            </td>
  </tr>
</logic:iterate> 
</logic:present>
</table>
<table width="100%">
    <tr>
        <td align="left">
                <div align="left" >
                <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/><bean:write name="beans" property="totalRows"/></strong>
                </div>	 
        </td>
        <td align="right">
                <bean:define name="docsrecv" property="id" id="params"/>
                <%params = anchor + ":_SEARCH:id:" + params;%>
                <jsp:include page="/paging.jsp">
                <jsp:param name="BEANS" value="BSearch"/>
                <jsp:param name="PARAMS" value="<%=params%>"/>
                <jsp:param name="FORM" value="docsSearch"/>
                <jsp:param name="METHOD" value="post"/>
                </jsp:include>
        </td>
    </tr>
</table>
</html:form>
</div>