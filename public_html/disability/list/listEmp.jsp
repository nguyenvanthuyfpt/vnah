<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="beansEmp">
<div class="titleEmp">
Danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t
</div>         
(<A class="menu_topGV" href="javascript:openWindow('list',anchor+ ':_PREPARED_SHOW')"><strong>B&#225;o c&#225;o</strong></a>
)
<br>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TBODY>
        <tr>
            <TH   width="10px" nowrap align="center">#</TH>
            <TH nowrap ><bean:message key="disability.from.label.nkt" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.from.label.ngaySinh" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.from.label.cmnd" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.from.label.soNha" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.from.label.sex" bundle="<%=interfaces%>"/></TH>
            <TH   width="40px" nowrap>
              <span onclick="if(messageDelete()){post('list',anchor + ':_DELETE_EMP')}">
                <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                </span>    
              </th>    
        </tr>
<bean:define name="beansEmp" id="beans" type="com.form.FBeans"/>
<%
  int i = beans.getFirstRecord();
%>
            <logic:iterate name="beansEmp" id="bean" type="com.form.disability.search.FSearch">                                       
                    <tr class="<%=(i%2==0)?"content1":"content"%>">
                    <td  nowrap align="center"><span class="index"><%=i++%></span></td>
                    <td  nowrap align="center">
                    <bean:write name="bean" property="nkt" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="ngaySinh" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="cmnd" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="soNha" /></td>
                    <td  nowrap align="center">
                    <logic:equal name="bean" property="sex" value="0" >
                        <bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal name="bean" property="sex" value="1" >
                        <bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    </td>
                    <td  nowrap width="40px">
                        <input type="checkbox" name="checkEmp" value="<%=bean.getId()%>">

                    </td>
                    </tr>
            </logic:iterate>
        </tbody>
    </table>  

<jsp:include page="/disability/list/paging.jsp"/>
</logic:present> 
