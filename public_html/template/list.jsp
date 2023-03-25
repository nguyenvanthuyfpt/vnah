<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%"   cellpadding="0" cellspacing="0" class="list-voffice">
<tr>
    <th ><bean:message key="doc.header.stt.caption" bundle="<%=interfaces%>"/></th>
    <th  width="80px;"><bean:message key="title.template.label.code" bundle="<%=interfaces%>"/></th>
    <th ><bean:message key="title.template.label.name" bundle="<%=interfaces%>"/></th>
    <th ><bean:message key="title.template.label.department" bundle="<%=interfaces%>"/></th>
    <th ><bean:message key="title.template.label.effectiveDate" bundle="<%=interfaces%>"/></th>
    <th  nowrap  >
    <logic:equal name="template" property="type" value="0">
     <input type="checkBox" name="checkAll" id="checkAll" value="1" onclick="javascript:checkAllIdsCabin(document.template.ids,this)" />
    </logic:equal>
    </th>     
</tr>
<logic:present name="BTemplate">
<bean:define name="BTemplate" id="beans" type="com.form.FBeans"/>
<%int i=beans.getFirstRecord();%>

<logic:iterate name="BTemplate" id="bean" type="com.form.template.FTemplate">    
  <tr class="<%=(i%2==0)?"content1":"content"%>">
    <TD><Span class="index"><%=i++%></span></TD>
    <TD  nowrap>
    <bean:write name="bean" property="code"/>
    <logic:equal name="bean" property="hostNew" value="1">
    <img  src="<%=contextPath%>/images/hot.gif" />
    </logic:equal>
    </TD>
    <TD >
    
    <div style="float:left;">
    <a  href="javascript:post('template',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('template',anchor);remove('template','id');" >
    <logic:equal name="bean" property="permission" value="0">
    <logic:equal name="template" property="type" value="0">
    <bean:write name="bean" property="name"/>
    </logic:equal>
    <logic:equal name="template" property="type" value="1">
    <s><bean:write name="bean" property="name"/></s>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="bean" property="permission" value="0">
    <bean:write name="bean" property="name"/>
    </logic:notEqual>
    </a>
    </div>
    <div style="float:right;">
    <logic:equal name="bean" property="versionId" value="1">
        <input type="button" style="cursor:pointer;" value="+" onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('template','at_share',anchor + ':_SAVE_FALSE:code:<bean:write name="bean" property="code"/>');"    />
    </logic:equal>
    </div>
    </TD>
    <TD ><bean:write name="bean" property="nameDep"/></TD>
    <TD >
    
    <bean:write name="bean" property="effectiveDate"/>
    
    </TD>
   <td align="center"  nowrap >
        <logic:equal name="template" property="type" value="1">
            
            <logic:equal name="bean" property="block" value="1">
                <img  src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('template',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                <img  src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete()) post('template',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
            </logic:equal>
            
            <logic:notEqual name="bean" property="block" value="1">
             <bean:message key="action.none" bundle="<%=interfaces%>"/>
            </logic:notEqual>
            
        </logic:equal>
        
        <logic:equal name="template" property="type" value="0">
        <input type="checkBox" name="ids" id="ids" onclick="checkIds(this,this.form.ids)" value="<bean:write name="bean" property="id"/>" />
        </logic:equal>
    </td>     
  </tr>
</logic:iterate>
</logic:present>
</table>
<table width="100%">
<tr>
    <td align="left" colspan="2">
            <div align="left" >
            <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <bean:write name="beans" property="totalRows"/></strong>
            </div>	 
    </td>
    <td colspan="4" align="right">
                <%String params="";%>
                <logic:equal parameter="<%=anchor%>" value="_SEARCH">
                    <%params = anchor + ":_SEARCH";%>
                </logic:equal>
                <logic:notEqual parameter="<%=anchor%>" value="_SEARCH" >
                    <%params = anchor + ":_SHOW";%>
                </logic:notEqual>
                <jsp:include page="/paging.jsp">
                <jsp:param name="BEANS" value="BTemplate"/>
                <jsp:param name="PARAMS" value="<%=params%>"/>
                <jsp:param name="FORM" value="template"/>
                <jsp:param name="METHOD" value="post"/>
                </jsp:include>
    </td>
</tr>
</table>
