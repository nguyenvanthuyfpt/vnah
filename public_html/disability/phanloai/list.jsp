<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div>
    <jsp:include page="/disability/phanloai/form.jsp" />
</div>
<div>
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<TBODY>
    <TR>               
	    <TH width="10px" nowrap align="center">#</TH>            
	    <TH nowrap ><bean:message key="phanloai.list.label.createDate.next" bundle="<%=interfaces%>"/></TH>
      <TH nowrap ><bean:message key="common.label.createby" bundle="<%=interfaces%>"/></TH>	    
	    <TH nowrap ><bean:message key="phanloai.list.label.reson" bundle="<%=interfaces%>"/></TH>
	    <TH width="40px" nowrap>#</TH>
    </TR>
    <logic:present name="BPhanLoaiTrailers"> 
    <%  int i =1;%>
    	<logic:iterate name="BPhanLoaiTrailers" id="bean" type="com.form.disability.FPhanLoai">                                       
            <tr class="<%=(i%2==0)?"content1":"content"%>">
	            <td align="center"><span class="index"><%=i++%></span></td>
              <td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PHANLOAI:plDtlId:<%=bean.getId()%>')">
	                    <bean:write name="bean" property="dateCreate" />
	                </a>
	            </td>
	            <td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PHANLOAI:plDtlId:<%=bean.getId()%>')">
	                    <bean:write name="bean" property="fullName" />
	                </a>
	            </td>
	            <!--<td align="center">
	                <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PHANLOAI:plDtlId:<%=bean.getId()%>')">
	                    <bean:write name="bean" property="thoiDiemKT" />
	                </a>
	            </td>-->
	            <td align="center">
                    <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PHANLOAI:plDtlId:<%=bean.getId()%>')">
                        <bean:write name="bean" property="reson" />
                    </a>
	            </td>
	            <td width="40px">
                      <%if(request.getSession().getAttribute("10.01")!=null){%>
                      <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/editdraft.png" 
                              title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" 
                              onClick="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_PHANLOAI:plDtlId:<%=bean.getId()%>')">	
                      <%}%>
                      <%if(request.getSession().getAttribute("10.03")!=null){%>
                      <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/delete.png" 
                          title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                          onClick="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_PHANLOAI:plDtlId:<%=bean.getId()%>')">
                      <%}%>
	            </td>
            </tr>
    	</logic:iterate>
    </logic:present>
</tbody>
</table>
</div>