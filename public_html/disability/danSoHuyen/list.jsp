<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div class="content-calendar">	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TBODY>
        <TR>               
            <TH   width="10px" nowrap align="center">#</TH>            
            <TH nowrap ><bean:message key="disability.dansohuyen.tinh" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.dansohuyen.kyBao" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.dansohuyen.name" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.dansohuyen.tongxa" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.dansohuyen.dateUpdate" bundle="<%=interfaces%>"/></TH>
            <TH nowrap >#</TH>
        </TR>
        
        <logic:present name="BDanSoHuyens"> 
            <bean:define name="BDanSoHuyens" id="beans" type="com.form.FBeans" />
            <%  int i =1;%>
            <logic:iterate name="BDanSoHuyens" id="bean" type="com.form.disability.FDanSoHuyen">                                       
                <tr  class="<%=(i%2==0)?"content1":"content"%>">
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')"  nowrap align="center"><span class="index"><%=i++%></span></td>
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')" nowrap align="center"><bean:write name="bean" property="tinhName" /></td>
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')" nowrap align="center"><bean:write name="bean" property="kyBao" /></td>
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')" nowrap align="center"><bean:write name="bean" property="nam" /></td>
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')" nowrap align="center"><bean:write name="bean" property="tongXa" /></td>
                    <td style="cursor:pointer" onclick="post('danSoHuyen',anchor + ':_DETAIL:id:<%=bean.getId()%>')" nowrap align="center"><bean:write name="bean" property="dateUpdate" /></td>
                    <td><img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('danSoHuyen','listIdDanSoHuyen',anchor + ':_DELETE:id:<%=bean.getId()%>')"></td>
                </tr>
            </logic:iterate>
        </logic:present>        
</tbody>
</table>
</div> 
