<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" style="border-collapse: collapse"  cellpadding="0" cellspacing="0" class="list-voffice">
                        <tr>
                            <th >#</th>
                            <th><bean:message key="category.mailAccount.userMail" bundle="<%=interfaces%>"/></th>
                            <th><bean:message key="category.mailAccount.serverMail" bundle="<%=interfaces%>"/></th>
                            <th  width="80px" nowrap><bean:message key="category.mailAccount.status" bundle="<%=interfaces%>"/></th>
                            <th width="50px" nowrap><bean:message key="category.mailAccount.orderEmail" bundle="<%=interfaces%>"/></th>
                            <th width="30px" nowrap>#</th>
                        </tr>
                    <logic:present name="BRegisters">
                    <bean:define name="BRegisters" id="beans" type="com.form.FBeans"/>
                    <%  int i = beans.getFirstRecord();%>
                    <logic:iterate name="BRegisters" id="bean" type="com.form.admin.mail.FMailAccount">
                        <tr>
                            <td><span class="index"><%=i++%></span></td>
                            <td>
                                    <logic:equal name="bean" property="status" value="1"  >
                                        <a href="javascript:post('loginEmail',anchor +':_LOGIN:accountId:<%=bean.getId()%>')">
                                            <bean:write name="bean" property="userMail"  />
                                        </a>
                                    </logic:equal>
                                    <logic:equal name="bean" property="status" value="0"  >
                                            <bean:write name="bean" property="userMail"  />
                                    </logic:equal>
                            </td>
                            <td><bean:write name="bean" property="serverMail"  /></td>
                            <td style="text-align:center">
                            
                            <logic:equal name="bean" property="status" value="1"  >
                                <img onclick="getObj('status').value=0;postAjax('registerMail','right',anchor + ':_ACTIVE:id:<%=bean.getId()%>')" style="cursor: pointer;" src="<%=contextPath%>/images/tick.png" />
                            </logic:equal>
                            
                             <logic:equal name="bean" property="status" value="0"  >
                                 <img onclick="getObj('status').value=1;postAjax('registerMail','right',anchor + ':_ACTIVE:id:<%=bean.getId()%>')" style="cursor: pointer;" src="<%=contextPath%>/images/disable.png" />
                            </logic:equal>
                            
                            </td>
                            <td width="50px" nowrap>
                                <% if (i==beans.getFirstRecord()+1){%>
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/downarrow.png" title="<bean:message key="broadcast.downarrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('registerMail','right',anchor + ':_ORDERS:id:<bean:write name="bean" property="id"/>:orderEmail:<bean:write name="bean" property="orderEmail"/>:index:-1')">
                                <%}else if (i-1 == beans.size()){%>
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/uparrow.png" title="<bean:message key="broadcast.uparrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('registerMail','right',anchor + ':_ORDERS:id:<bean:write name="bean" property="id"/>:orderEmail:<bean:write name="bean" property="orderEmail"/>:index:1')">
                                <%}else{%>                          
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/uparrow.png" title="<bean:message key="broadcast.uparrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('registerMail','right',anchor + ':_ORDERS:id:<bean:write name="bean" property="id"/>:orderEmail:<bean:write name="bean" property="orderEmail"/>:index:1')">
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/orders/downarrow.png" title="<bean:message key="broadcast.downarrow.caption" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('registerMail','right',anchor + ':_ORDERS:id:<bean:write name="bean" property="id"/>:orderEmail:<bean:write name="bean" property="orderEmail"/>:index:-1')">
                                <%}%>
                            </td>
                            <td width="30px"  nowrap align="right">
                                    <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onclick="javascript:postAjax('registerMail','idEdit',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>');" >
                                    <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('registerMail',anchor + ':_DELETE:id:<%=bean.getId()%>')">
                            </td>
                            
                        </tr>
                    </logic:iterate>
                    </logic:present> 
                    </table>
		   