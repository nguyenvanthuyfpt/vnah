<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMycontacts" >  
<div  class="contents-sliders" style="padding-left:1px">
     <logic:iterate name="BMycontacts" id="bean" type="com.form.pgroups.FPgroup">
         <bean:define name="bean" property="mycontacts" id="beansSub" type="com.form.FBeans"/>
         <div class="content-header" id="<bean:write name="bean" property="id" />" >
                 <h3  id="h3<bean:write name="bean" property="id" />" class="titleLeftEmail contents-toggler-down" >
                 <table cellpadding="0" cellspacing="0" width="100%" border="0" >
                 <tr><td>
                 <span onclick="minimize(<bean:write name="bean" property="id" />,1)">
                    <strong><bean:write name="bean" property="name" /></strong></span> 
                    (<%=beansSub.size()%>)
                  </td><td>  
                 <img style="border:0px;cursor: pointer;"  alt="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/newImages/edit.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<%=bean.getName()%>','','','',1);postAjax('formMyContact','at_share',anchor + ':_VIEW_PGROUP_EDIT:id:<%=bean.getId()%>');" />
                 <% if (beansSub.size()==0){ %>
                 <img style="border:0px;cursor: pointer;"  alt="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/newImages/i_17.gif" border="0" onclick="if(messageDelete()) postAjax('formMyContact','tdMycontact',anchor + ':_PREPARED_DELETE:id:<bean:write name="bean" property="id" />')" />
                 <%}%> 
                 </td></tr></table>
                </h3>                
         </div>
       <div class="fontSubMycontent" id="subContent<bean:write name="bean" property="id" />" >
                    <table cellpadding="0" cellspacing="0" width="100%" border="0" >
                                     <logic:iterate name="bean" property="mycontacts" id="beanMycontact" type="com.form.mycontact.FMycontact">                                  
                                      <tr onmouseover="className='row1'" onmouseout="className='row2'" >
                                      <td align="left" nowrap>
                                                      <span style="cursor: pointer;" id="<bean:write name="beanMycontact" property="icq" />" onclick="javascript:sendAddress(this)" >
                                                            <bean:write name="beanMycontact" property="fullName" />
                                                      </span>      
                                      </td>
                                      <td align="right" nowrap>
                                      <img src="<%=contextPath%>/images/newImages/edit.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:write name="beanMycontact" property="fullName" />','','','',1);postAjax('formMyContact','at_share',anchor + ':_OPEN_WINDOW:id:<bean:write name="beanMycontact" property="id" />');"/>
                                      <img onclick="javascript:postAjax('formMyContact','tdMycontact',anchor + ':_DELETE_PMYCONTACT:id:<%=beanMycontact.getId()%>');" src="<%=contextPath%>/images/newImages/i_17.gif"/>
                                      
                                      
                                   </td>
                                   </tr>
                        </logic:iterate>
                        </table>
        </div>
     </logic:iterate>
</div>
</logic:present>

    







