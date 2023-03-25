<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div class="content-calendar">	
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
        <TBODY>
            <TR>               
                <TH   width="10px" nowrap align="center">#</TH>            
                <TH nowrap ><bean:message key="disability.dansotinh.tinh" bundle="<%=interfaces%>"/></TH>
                <TH nowrap ><bean:message key="disability.dansotinh.kyBao" bundle="<%=interfaces%>"/></TH>
                <TH nowrap ><bean:message key="disability.dansotinh.nam" bundle="<%=interfaces%>"/></TH>
                <TH nowrap ><bean:message key="disability.dansotinh.tongHuyen" bundle="<%=interfaces%>"/></TH>
                <TH nowrap ><bean:message key="disability.dansotinh.dateUpdate" bundle="<%=interfaces%>"/></TH>
                <TH nowrap >#</TH>
            </TR>
            <logic:present name="BDanSoTinhs"> 
            <bean:define name="BDanSoTinhs" id="beans" type="com.form.FBeans" />
            <%  int i =1;%>
            <logic:iterate name="BDanSoTinhs" id="bean" type="com.form.disability.FDanSoTinh">                                       
                    <tr   class="<%=(i%2==0)?"content1":"content"%>">
                        <td style="cursor:pointer" nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><span class="index"><%=i++%></span></td>
                        <td style="cursor:pointer"  nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><bean:write name="bean" property="tinhName" /></td>
                        <td style="cursor:pointer" nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><bean:write name="bean" property="kyBao" /></td>
                        <td style="cursor:pointer"  nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><bean:write name="bean" property="nam" /></td>
                        <td style="cursor:pointer" nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><bean:write name="bean" property="tongHuyen" /></td>
                        <td style="cursor:pointer" nowrap align="center" onclick="post('danSoTinh',anchor + ':_DETAIL:id:<%=bean.getId()%>')"><bean:write name="bean" property="dateUpdate" /></td>
                        <td>
                        <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="if(messageDelete()){postAjax('danSoTinh','listIddanSoTinh',anchor + ':_DELETE:id:<%=bean.getId()%>')}"></td>
                    </tr>
            </logic:iterate>
            </logic:present>
            <tr>
           
            </tr>
</tbody>
</table>
</div> 
