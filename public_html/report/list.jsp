<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>


<div id="idTemplate">
<table width="100%"   style="border-collapse: collapse"  cellpadding="0" cellspacing="0" class="list-voffice" border="0">
<tr>
    <th nowrap><bean:message key="doc.header.stt.caption" bundle="<%=interfaces%>"/></th>
    <th nowrap><bean:message key="categoryreportType.reportType" bundle="<%=interfaces%>"/></th>
    <th nowrap><bean:message key="title.report.label.creator" bundle="<%=interfaces%>"/>/<bean:message key="title.report.label.time" bundle="<%=interfaces%>"/></th>
    <th nowrap><bean:message key="title.report.label.name" bundle="<%=interfaces%>"/>/<bean:message key="title.report.label.description" bundle="<%=interfaces%>"/></th>
    <logic:equal name="reports" property="type" value="0" >
        <th nowrap width="5%" style="text-align:center;">#</th>
    </logic:equal>
</tr>
<logic:present name="BReport">
 <bean:define name="BReport" id="beans" type="com.form.FBeans"/>
  <%  int i = beans.getFirstRecord();%>
<logic:iterate name="BReport" id="bean" type="com.form.report.FReport">    
  <tr class="<%=(i%2==0)?"content1":"content"%>">
    <TD align="center"><span class="index"><%=i++%>.</span></TD>
    <TD><bean:write name="bean" property="nameCategory"/></TD>
    <TD nowrap><bean:write name="bean" property="creator"/><br>
    <div><bean:write name="bean" property="timeCreate"/></div>
    </TD>
    <TD>
        <div >
        <Strong>
        <a href="javascript:post('reports',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('reports',anchor);remove('reports','id');" >
           <bean:write name="bean" property="name"/>    
        </a>
        </strong>
        </div>
        <div>
            <logic:notEqual name="bean" property="description" value="">
                    <bean:define name="bean" property="description" id="description"/>
                    <%=description%>
            </logic:notEqual>    
        </div>
    </TD>
    <logic:equal name="reports" property="type" value="0" >
    <td  nowrap width="5%" align="center">
            <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="s&#7917;a" onClick="javascript:this.src='images/loading.gif';post('reports',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
            <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="x&#243;a" onClick="javascript:if(messageDelete())post('reports',anchor + ':_DELETE:id:<%=bean.getId()%>')">
    </td>
    </logic:equal>
    
    
    
  </tr>
</logic:iterate>
</logic:present>

</table>
<div align="right">
            <%String params = anchor + ":_SEARCH";%>
            <jsp:include page="/paging.jsp">
            <jsp:param name="BEANS" value="BReport"/>
            <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="FORM" value="reports"/>
            <jsp:param name="METHOD" value="post"/>
            </jsp:include>
</div>
</div>