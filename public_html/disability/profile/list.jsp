<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div>
    <jsp:include page="/disability/profile/form.jsp" />
</div>
<div>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
	    <TH width="10px" nowrap align="center">#</TH>            
	   <TH nowrap ><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
      <TH nowrap ><bean:message key="common.label.createby" bundle="<%=interfaces%>"/></TH>
      <TH nowrap ><bean:message key="kpi.dis.profile.status" bundle="<%=interfaces%>"/></TH>      
	    <TH width="40px" nowrap>#</TH>
    </TR>
    <logic:present name="BProfiles"> 
    <%  int i =1;%>
    	<logic:iterate name="BProfiles" id="bean" type="com.form.disability.FDisProfile">                                       
            <tr class="<%=(i%2==0)?"content1":"content"%>">
	            <td align="center"><span class="index"><%=i++%></span></td>
              <td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PROFILE:pfId:<%=bean.getId()%>')">
	                    <bean:write name="bean" property="createOn" />
	                </a>
	            </td>
	            <td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PROFILE:pfId:<%=bean.getId()%>')">
	                    <bean:write name="bean" property="createBy" />
	                </a>
	            </td>	           
              <td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PROFILE:pfId:<%=bean.getId()%>')">	                    
                      <logic:equal name="bean" property="status" value="0">
                            &#272;ang m&#7903;                            
                      </logic:equal>
                      <logic:equal name="bean" property="status" value="1">
                            &#272;&#227;/&#273;ang &#273;&#243;ng
                      </logic:equal>
	                </a>
	            </td>	           
	            <td width="40px">
                    <%if(request.getSession().getAttribute("10.02")!=null){%>
                      <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/editdraft.png" 
                          title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" 
                          onClick="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PROFILE:pfId:<%=bean.getId()%>')">		    
                    <%}%>
                    <%if(request.getSession().getAttribute("10.03")!=null){%>
                      <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/delete.png" 
                          title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                          onClick="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_PROFILE:pfId:<%=bean.getId()%>')">
                    <%}%>
	            </td>
            </tr>
    	</logic:iterate>
    </logic:present>
</tbody>
</table>
</div>