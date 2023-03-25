<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div>
    <jsp:include page="/disability/relative/form.jsp" />
</div>
<div>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
            <TR>               
                    <TH   width="10px" nowrap align="center">#</TH>            
                    <TH nowrap ><bean:message key="relative.from.label.idRelativeNkt" bundle="<%=interfaces%>"/></TH>
                    <TH nowrap ><bean:message key="relative.from.label.lydoId" bundle="<%=interfaces%>"/></TH>
                    <TH   width="40px" nowrap>#</TH>
            </TR>
            <logic:present name="BRelatives"> 
            <%  int i =1;%>
            <logic:iterate name="BRelatives" id="bean" type="com.form.disability.FRelative">                                       
                    <tr class="<%=(i%2==0)?"content1":"content"%>" >
                    <td  nowrap align="center" onclick="javascript:postAjax('relative','MainCate',anchor + ':_DETAIL:id:<%=bean.getId()%>')" ><span class="index"><%=i++%></span></td>
                    <td  nowrap align="center" onclick="javascript:postAjax('relative','MainCate',anchor + ':_DETAIL:id:<%=bean.getId()%>')">
                        <bean:write name="bean" property="ten" />
                    </td>
                    <td  nowrap align="center" onclick="javascript:postAjax('relative','MainCate',anchor + ':_DETAIL:id:<%=bean.getId()%>')" >
                        <bean:write name="bean" property="tenLyDo" />
                    </td>
                    <td  nowrap width="40px">
                            <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('relative','MainCate',anchor + ':_DELETE:id:<%=bean.getId()%>')">                        
                    </td>
                    </tr>
            </logic:iterate>
            </logic:present>
</tbody>
</table>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>
</div>

 
