<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="kpi" property="state" id="stateId" type="java.lang.Integer" />
<bean:define name="kpi" property="eventId" id="eventId" type="java.lang.Integer" />

<logic:present name="listDataDtl" >
<%   
    String params = anchor + ":_VIEW_PERSON:state:"+stateId+":eventId:"+eventId; 
%>
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
    <tbody>
        <tr>
            <TH width="5%">&nbsp;</TH>
            <%if(stateId==1){%>
            <TH width="15%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
            <%}%>
            <%if(stateId==0){%>
            <TH width="15%"><bean:message key="common.label.createdate" bundle="<%=interfaces%>"/></TH>
            <%}%>
            <TH width="15%"><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
            <TH width="8%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
            <TH><bean:message key="common.label.position" bundle="<%=interfaces%>"/></TH>
            <TH><bean:message key="common.label.agency" bundle="<%=interfaces%>"/></TH>
            <logic:equal value="3" name="kpi" property="type">
            <TH><bean:message key="common.label.hours" bundle="<%=interfaces%>"/></TH>
            </logic:equal>
            <TH>#</TH>
        </tr>
        <logic:present name="listDataDtl"> 
            <bean:define name="listDataDtl" id="beans" type="com.form.FBeans" />
            <%  int i = beans.getFirstRecord();
                String strAnchor = (stateId==0)?"_PERSON_VIEW_DETAIL":"_PERSON_SEL_DETAIL";
                String strDel = (stateId==0)?"_PERSON_DELETE":"_PERSON_SEL_DELETE";
            %>
            <logic:iterate name="listDataDtl" id="bean" type="com.form.disability.FPerson">                
                <tr class="<%=(i%2==0)?"content1":"content"%>">
                    <td align="center">
                        <%if(stateId==0){%>
                            <span class="index"><%=i++%></span>
                        <%
                            }else{
                            i++;
                        %>                        
                            <input type="checkbox" name="perSel"  value="<%=bean.getId()%>"/>                                          
                        <%}%>
                    </td>
                    <%if(stateId==1){%>
                    <td align="left"><bean:write name="bean" property="locationName" /></td>
                    <%}%>
                    <%if(stateId==0){%>
                    <td align="left"><bean:write name="bean" property="createDate" /></td>
                    <%}%>
                    <td align="left" width="25%"><bean:write name="bean" property="name" /></td>
                    <td align="left">
                        <logic:equal name="bean" property="sex" value="0" >
                            <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                        </logic:equal>                                          
                        <logic:equal name="bean" property="sex" value="1" >
                            <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                        </logic:equal>  
                    </td>                                                    
                    <td align="left"><bean:write name="bean" property="title" /></td>
                    <td align="left"><bean:write name="bean" property="agency" /></td>
                    <logic:equal value="3" name="kpi" property="type">
                      <td align="left"><bean:write name="bean" property="hours" /></td>
                    </logic:equal>
                    <td  width="20px;">
                        <%if(eventId>0 && stateId==0){%>
                            <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                              title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                              onClick="postAjax('kpi', 'div_person', anchor + ':<%=strAnchor%>:dtlId:<%=bean.getId()%>:state:<%=stateId%>')">
                        <%}%>
                        
                        <%if(stateId==1){%>
                            <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                              title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                              onClick="postAjax('kpi', 'div_person', anchor + ':<%=strAnchor%>:dtlId:<%=bean.getId()%>:state:<%=stateId%>')">
                        <%}%>
                    </td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>                 

<logic:present name="listDataDtl" >
 <%   
    String params = anchor + ":_VIEW_PERSON:state:"+stateId; 
 %>
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