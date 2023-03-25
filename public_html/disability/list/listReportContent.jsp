<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>   
<bean:define name="beans" id="beans" type="com.form.FBeans"/>
<bean:define name="list" property="idEditName" id="idEditName" type="java.lang.Integer"/>
<div class="content-calendar">      
<div class="content-calendar-2">
            <bean:message key="listReport.title" bundle="<%=interfaces%>"/><jsp:include page="/disability/list/paginglist.jsp"/>
        </div> 
    <table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
        <TBODY>
          <tr>
            <th><bean:message key="search.listReport.stt" bundle="<%=interfaces%>"/></th>
            <th></th>     
            <th><bean:message key="search.listName" bundle="<%=interfaces%>"/></th>                   
            <th align="center"><bean:message key="search.listReport.amount" bundle="<%=interfaces%>"/></th>
            <th><bean:message key="search.listReport.date" bundle="<%=interfaces%>"/></th>
            <th>#</th>
          </tr>
   <%int j=0;int i = beans.getFirstRecord();%>
    <logic:iterate name="beans" id="bean" type="com.form.disability.list.FList">             
     <%String fontBold=bean.getSelected()>0?"font-weight: bold;":"font-weight: normal;";%>
        <tr class="<%=(i%2==0)?"content1":"content"%>" id="<%=++j%>_" style="<%=fontBold%>">
            <td align="center" height="22px"><%=(i++)%></td>   
           
            <td>
             <input name="radioCheck" type="radio" <%=(bean.getSelected()>0?"checked":"")%> value="<bean:write name="bean" property="listId"/>"  onclick="set_click('<%=j%>_');postAjax('list','divMainListEmp',anchor + ':_SHOW_LISTEMP:idSelect:<bean:write name="bean" property="listId"/>')"/>
             </td>
            <td nowrap >                      
             <%if (bean.getListId()==idEditName.intValue()){%>
                <html:text name="bean" property="listName" style="width:229px;" />
                <html:button property="_EDIT" onclick="javascript:postAjax('list','divMainListReport',anchor+':_EDIT');" styleClass="button">
                  <bean:message key="search.listReport" bundle="<%=interfaces%>"/>
                </html:button>      
                <html:button property="end" styleClass="button" onclick="javascript:postAjax('list','divMainListReport',anchor+':_LIST_SHOW');">
                <bean:message key="action.close" bundle="<%=interfaces%>"/>
                </html:button>
             <%}else{%>              
                 <bean:write name="bean" property="listName"/>    
            <%}%>
            </td>                               
            <td><strong><bean:write name="bean" property="amountEmp"/></strong></td>
            <td><bean:write name="bean" property="dateCreate"/></td>
            <td nowrap width="40px">
                <img src="images/update.png" border="0" style="cursor: pointer;" onclick="postAjax('list','divMainListReport',anchor+ ':_PREPARED_EDIT:idSelect:<bean:write name="bean" property="listId"/>:idEditName:<bean:write name="bean" property="listId"/>')"/>
                <img src="images/delete.png" border="0" style="cursor: pointer;" onclick="if(messageDelete()){post('list',anchor + ':_DELETE:idSelect:<bean:write name="bean" property="listId"/>');}"/>
            </td>
        </tr>
    </logic:iterate>             
</tbody>
</table>   
</div> 
