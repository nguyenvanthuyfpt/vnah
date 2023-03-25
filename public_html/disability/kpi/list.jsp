<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define id="typeVal" name="kpi" property="type" type="java.lang.Integer" />
<bean:define id="indIdVal" name="kpi" property="indId" type="java.lang.Integer" />
<%
    String strType = String.valueOf(typeVal);      
    String params = anchor + ":_VIEW:type:" + strType;
%>

<!-- Values -->
<logic:equal value="0" name="kpi" property="type">
<logic:present name="listDataDtl" >
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
        <TH colspan="9"><div style="text-align:left;">Danh s&#225;ch d&#7919; li&#7879;u &#273;&#227; nh&#7853;p</div></TH>
    </TR>
    <TR>               
        <TH width="10px"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
        <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.username" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.period.type" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.period" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.target" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.actual" bundle="<%=interfaces%>"/></TH>
        <TH width="40px" align="center">#</TH>
    </TR>    
    <logic:present name="listDataDtl"> 
        <bean:define name="listDataDtl" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="listDataDtl" id="bean" type="com.form.disability.FDataDtl">
        <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td align="center"><bean:write name="bean" property="createDate" /></td>
                <td align="left"><bean:write name="bean" property="fullName" /></td>
                <td align="left"><bean:write name="bean" property="location" /></td>
                <td align="left">
                    <logic:equal value="0" name="bean" property="period">
                        <bean:message key="common.label.month" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:message key="common.label.quarter" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:message key="common.label.year" bundle="<%=interfaces%>"/>
                    </logic:equal>
                <td align="center">
                     <logic:equal value="0" name="bean" property="period">
                        <bean:write name="bean" property="month" />/<bean:write name="bean" property="year" />
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:write name="bean" property="quarter" />/<bean:write name="bean" property="year" />
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:write name="bean" property="year" />
                    </logic:equal>
                </td>
                <td align="right">
                    <logic:equal value="0" name="bean" property="period">
                        <bean:write name="bean" property="targetM" />
                    </logic:equal>
                    <logic:equal value="1" name="bean" property="period">
                        <bean:write name="bean" property="targetQ" />
                    </logic:equal>
                    <logic:equal value="2" name="bean" property="period">
                        <bean:write name="bean" property="targetY" />
                    </logic:equal></td> 
                <td align="right">
                    <bean:write name="bean" property="actual" />
                </td>              
                <td>
                    <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="post('kpi',anchor + ':_DETAIL:dtlId:<%=bean.getId()%>:inputType:0')">
                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete()){post('kpi',anchor + ':_DELETE:dtlId:<%=bean.getId()%>:inputType:0');}">
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>

<logic:present name="listDataDtl" >
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
</logic:equal>

<!-- Person -->
<logic:equal value="2" name="kpi" property="type">
  <logic:present name="listPersons" >
  <div>	  
      <jsp:include page="/admin/paging.jsp">
          <jsp:param name="BEANS" value="listPersons"/>
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
          <TH width="10px"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
          <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
          <TH width="20%"><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.agency" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.position" bundle="<%=interfaces%>"/></TH>         
          <TH width="40px" align="center">#</TH>
      </TR>    
      <logic:present name="listPersons"> 
          <bean:define name="listPersons" id="beans" type="com.form.FBeans" />
          <%  int i = beans.getFirstRecord();%>
          <logic:iterate name="listPersons" id="bean" type="com.form.disability.FPerson">  
               <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                  <td align="center"><span class="index"><%=i++%></span></td>
                  <td align="center"><bean:write name="bean" property="createDate" /></td>                 
                  <td><bean:write name="bean" property="name" /></td>
                  <td align="left">
                     <logic:equal name="bean" property="sex" value="0" >
                          <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                      </logic:equal>
                      
                      <logic:equal name="bean" property="sex" value="1" >
                          <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                      </logic:equal>                  
                  </td>
                  <td><bean:write name="bean" property="agency" /></td>
                  <td><bean:write name="bean" property="title" /></td>
                  <!--<td align="right"><bean:write name="bean" property="hours" /></td>-->                  
                  <td><img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="post('kpi',anchor + ':_DELTAIL_DTL:dtlId:<%=bean.getId()%>:inputType:2')">
                      <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete()){post('kpi',anchor + ':_DELETE:dtlId:<%=bean.getId()%>:inputType:2');}"></td>
              </tr>
          </logic:iterate>
      </logic:present>
  </tbody>
  </table>
  
  <logic:present name="listPersons" >
  <div>	
      <jsp:include page="/admin/paging.jsp">
          <jsp:param name="BEANS" value="listPersons"/>
          <jsp:param name="FORM" value="kpi"/>
          <jsp:param name="METHOD" value="postAjax"/>
          <jsp:param name="POSITION" value="listId"/>
          <jsp:param name="PARAMS" value="<%=params%>"/>
      </jsp:include>	
  </div>    
  </logic:present>	 
</logic:equal>

<!-- Link -->
<logic:equal value="3" name="kpi" property="type">
  <logic:present name="listPersons" >
  <div>	  
      <jsp:include page="/admin/paging.jsp">
          <jsp:param name="BEANS" value="listPersons"/>
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
          <TH width="10px"><bean:message key="disability.unit.label.stt" bundle="<%=interfaces%>"/></TH>            
          <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
          <TH width="20%"><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.agency" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.title" bundle="<%=interfaces%>"/></TH>         
          <TH><bean:message key="common.label.hours" bundle="<%=interfaces%>"/></TH>
          <TH width="40px" align="center">#</TH>
      </TR>    
      <logic:present name="listPersons"> 
          <bean:define name="listPersons" id="beans" type="com.form.FBeans" />
          <%  int i = beans.getFirstRecord();%>
          <logic:iterate name="listPersons" id="bean" type="com.form.disability.FPerson">  
               <tr style="cursor:pointer" class="<%=(i%2==0)?"content1":"content"%>">
                  <td align="center"><span class="index"><%=i++%></span></td>
                  <td align="center"><bean:write name="bean" property="createDate" /></td>                 
                  <td><bean:write name="bean" property="name" /></td>
                  <td align="left">
                     <logic:equal name="bean" property="sex" value="0" >
                          <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                      </logic:equal>
                      
                      <logic:equal name="bean" property="sex" value="1" >
                          <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                      </logic:equal>                  
                  </td>
                  <td><bean:write name="bean" property="agency" /></td>
                  <td><bean:write name="bean" property="title" /></td>
                  <td align="right"><bean:write name="bean" property="hours" /></td>                  
                  <td align="center"><img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="post('kpi',anchor + ':_DELTAIL_DTL:dtlId:<%=bean.getId()%>:inputType:3')">
                  </td>
              </tr>
          </logic:iterate>
      </logic:present>
  </tbody>
  </table>
  
  <logic:present name="listPersons" >
  <div>	
      <jsp:include page="/admin/paging.jsp">
          <jsp:param name="BEANS" value="listPersons"/>
          <jsp:param name="FORM" value="kpi"/>
          <jsp:param name="METHOD" value="postAjax"/>
          <jsp:param name="POSITION" value="listId"/>
          <jsp:param name="PARAMS" value="<%=params%>"/>
      </jsp:include>	
  </div>    
  </logic:present>	 
</logic:equal>

<!-- 1. Disability people -->
<logic:equal value="1" name="kpi" property="type">
<logic:present name="listDataDtl" >
<%   params = anchor + ":_VIEW"; %>
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
          <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
          <TH width="20%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
          <TH width="15%"><bean:message key="common.label.dis.code" bundle="<%=interfaces%>"/></TH>
          <TH><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
          <TH width="10%"><bean:message key="common.label.birth" bundle="<%=interfaces%>"/></TH>
          <TH width="8%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>          
          <TH width="5%">#</TH>
      </TR>    
      <logic:present name="listDataDtl"> 
          <bean:define name="listDataDtl" id="beans" type="com.form.FBeans" />
          <%  int i = beans.getFirstRecord();
              if (i>0) {
          %>
          <logic:iterate name="listDataDtl" id="bean" type="com.form.disability.FDisability">
              <tr onclick="post('kpi',anchor + ':_DETAIL:dtlId:<%=bean.getId()%>:inputType:1:indId:56');"  class="<%=(i%2==0)?"content1":"content"%>" >
                  <td align="center"><span class="index"><%=i++%></span></td>
                  <td align="center"><bean:write name="bean" property="dateLastUpdate" /></td>
                  <td align="left"><bean:write name="bean" property="tinhName" /></td>
                  <td align="left"><bean:write name="bean" property="ma" /></td>
                  <td align="left"><bean:write name="bean" property="nkt" /></td>
                  <td align="center"><bean:write name="bean" property="ngaySinh" /></td>
                  <td align="left">
                     <logic:equal name="bean" property="sex" value="0" >
                          <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                      </logic:equal>
                      
                      <logic:equal name="bean" property="sex" value="1" >
                          <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                      </logic:equal>                  
                  </td>
                  <td><img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete()){post('kpi',anchor + ':_DELETE:nktId:<%=bean.getId()%>:inputType:1');}"></td>
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
<%   params = anchor + ":_VIEW"; %>
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
</logic:equal>