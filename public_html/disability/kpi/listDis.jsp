<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<logic:present name="listDataDtl" >
<%   String params = anchor + ":_VIEW"; %>
<div>	  
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDataDtl"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>  

  <table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
  <TBODY>
      <TR>               
          <TH width="5%">#</TH> 
          <TH width="15%"><bean:message key="common.label.regdate" bundle="<%=interfaces%>"/></TH>
          <TH width="15%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
          <TH width="15%"><bean:message key="common.label.dis.code" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.birth" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>          
          <TH width="7%">#</TH>
      </TR>    
      <logic:present name="listDataDtl"> 
          <bean:define name="listDataDtl" id="beans" type="com.form.FBeans" />
          <%  int i = beans.getFirstRecord();
              if (i>0) {
          %>
          <logic:iterate name="listDataDtl" id="bean" type="com.form.disability.FDisability">              
              <tr class="<%=(i%2==0)?"content1":"content"%>" style="<%=(bean.getTrangthai()==1)?"font-style:italic;font-weight:bold;":""%>">
                  <td align="center"><span class="index"><%=i++%></span></td>
                  <td align="center">
                      <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                          <bean:write name="bean" property="dateLastUpdate" />
                      </a>    
                  </td>
                  <td align="left">
                       <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                          <bean:write name="bean" property="tinhName" />
                      </a>
                  </td>
                  <td align="left">
                      <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                          <bean:write name="bean" property="ma" />
                      </a>
                  </td>
                  <td align="left">                     
                      <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                        <bean:write name="bean" property="nkt" />
                      </a>
                  </td>
                  <td align="center">
                      <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                        <bean:write name="bean" property="ngaySinh" />
                      </a>    
                  </td>
                  <td align="left">
                      <a href="javascript:postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                         <logic:equal name="bean" property="sex" value="0" >
                              <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                          </logic:equal>
                          
                          <logic:equal name="bean" property="sex" value="1" >
                              <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                          </logic:equal>
                      </a>
                  </td>
                  <td>
                      <%if(request.getSession().getAttribute("10.02")!=null){%>
                      <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" 
                        onClick="postAjax('kpi','MainCate', anchor + ':_DIS_VIEW_DETAIL:dtlId:<%=bean.getId()%>:type:1:objId:56');">
                      <%}%>
                      <%if(request.getSession().getAttribute("10.03")!=null){%>
                      <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete()){postAjax('kpi','MainCate',anchor + ':_DELETE_DIS:dtlId:<%=bean.getId()%>:type:1:indId:0');}">
                      <%}%>      
                  </td>
              </tr>
          </logic:iterate>
          <% } else {%>
               <td colspan="8" style="text-align:center;">
                  <span class="error" >
                      <bean:message key="there-are-no-disability-people" bundle="<%=interfaces%>"/>                      
                  </span>
              </td>
          <% } %>
      </logic:present>
  </tbody>
  </table>
 
<logic:present name="listDataDtl" >
<%   String params = anchor + ":_VIEW"; %>
<div>	
    <jsp:include page="/admin/paging.jsp">
        <jsp:param name="BEANS" value="listDataDtl"/>
        <jsp:param name="FORM" value="kpi"/>
        <jsp:param name="METHOD" value="postAjax"/>
        <jsp:param name="POSITION" value="listId"/>
        <jsp:param name="PARAMS" value="<%=params%>"/>
    </jsp:include>	
</div>    
</logic:present>	