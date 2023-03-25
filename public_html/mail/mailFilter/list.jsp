<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" style="border-collapse: collapse"  cellpadding="0" cellspacing="0" class="list-voffice">
                        <tr>
                            <th >#</th>
                            <th><bean:message key="mail.filter.subject" bundle="<%=interfaces%>"/></th>
                            <th><bean:message key="mail.filter.from" bundle="<%=interfaces%>"/></th>
                            <th><bean:message key="mail.filter.perform" bundle="<%=interfaces%>"/></th>
                            <th width="30px" nowrap>#</th>
                        </tr>
                    <logic:present name="BMailFilters">
                    <bean:define name="BMailFilters" id="beans" type="com.form.FBeans"/>
                    <%  int i =1;%>
                    <logic:iterate name="BMailFilters" id="bean" type="com.form.mail.mailFilter.FMailFilter">
                        <tr>
                            <td><span class="index"><%=i++%></span></td>
                            <td align="left">
                                <bean:write name="bean" property="subject"  />
                            </td>
                            <td><bean:write name="bean" property="from"  /></td>
                            <td>
                            <logic:equal name="bean" property="likeFrom" value="0" >
                            <bean:message key="mail.filter.move.to.spam" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:equal name="bean" property="likeFrom" value="1" >
                            <bean:message key="mail.filter.delete.it" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            
                            </td>
                            <td width="30px"  nowrap align="right">
                                    <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onclick="javascript:postAjax('mailFilter','idEdit',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>');" >
                                    <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('mailFilter',anchor + ':_DELETE:id:<%=bean.getId()%>')">
                            </td>
                            
                        </tr>
                    </logic:iterate>
                    </logic:present> 
                    </table>
		   