 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%"   cellpadding="0" cellspacing="0" class="list-voffice">
<tr>
    <th width="10px" ><bean:message key="doc.header.stt.caption" bundle="<%=interfaces%>"/></th>
    <th><bean:message key="categories.task.title" bundle="<%=interfaces%>"/></th>
    <th ><bean:message key="categories.task.timeCreator" bundle="<%=interfaces%>"/></th>
    <th ><bean:message key="categories.task.description" bundle="<%=interfaces%>"/></th>
    <th  nowrap width="50px" ></th>     
</tr>
<logic:present name="BCategories">
<bean:define name="BCategories" id="beans" type="com.form.FBeans"/>
<%int i=beans.getFirstRecord();%>
<logic:iterate name="BCategories" id="bean" type="com.form.tasks.categories.FCategories">    
<tr class="<%=(i%2==0)?"content1":"content"%>" >
    <td><span class="index"><%=i++%></span></td>
    <td>
    <logic:equal name="bean" property="block" value="1" >
        <s><bean:write name="bean" property="title"/></s>
    </logic:equal>
    
    <logic:equal name="bean" property="block" value="0" >
        <bean:write name="bean" property="title"/>
    </logic:equal>
    
    </td>
    <td>
    
    <logic:equal name="bean" property="block" value="1" >
        <s><bean:write name="bean" property="timeCreate"/></s>
    </logic:equal>
    
    <logic:equal name="bean" property="block" value="0" >
        <bean:write name="bean" property="timeCreate"/>
    </logic:equal>
    
    </td>
    <td>
    <logic:equal name="bean" property="block" value="1" >
        <s><bean:write name="bean" property="description"/></s>
    </logic:equal>
    
    <logic:equal name="bean" property="block" value="0" >
        <bean:write name="bean" property="description"/>
    </logic:equal>
    </td>
    <td nowrap width="50px">
    <a class="modal-button" href="categories<%=extention%>?<%=anchor%>=_PREPARED_EDIT&id=<%=bean.getId()%>" rel="{handler: 'iframe', size: {x: 320, y: 210}}"><img src="<%=contextPath%>/images/newImages/i_14.gif" title="Sua" /></a>
    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="Xoa" onClick="javascript:if(messageDelete()){ postAjax('categories','MainCate',anchor + ':_DELETE:id:<%=bean.getId()%>');messageImg('MainCate');}">
    </td>
</tr>
</logic:iterate>
</logic:present>
</table>
<div>
        <div style="float:left"><strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <bean:write name="beans" property="totalRows"/></strong></div>
        <div style="float:right">
        <% String params = anchor + ":_SEARCH_PAGE";%>
    <jsp:include page="/paging.jsp">
    <jsp:param name="BEANS" value="BCategories"/>
    <jsp:param name="PARAMS" value="<%=params%>"/>
    <jsp:param name="POSITION" value="MainCate"/>
    <jsp:param name="FORM" value="categories"/>
    <jsp:param name="METHOD" value="postAjax"/>
    </jsp:include>       
        </div>
</div>
