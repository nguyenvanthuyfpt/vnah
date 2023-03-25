 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>     
<table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          
            <td><bean:message key="require.rule.name.caption" bundle="<%=interfaces%>"/></td>
            <td>
                <html:text name="frmRequireRule" property="title" style="width:250px"/>
                <bean:define name="frmRequireRule" property="active" id="active" type="java.lang.Integer" />     
                <select  name="active" style="width:130px"> 
                <option value="0" <%=active.intValue()==0?"selected":""%>><bean:message key="rules.unActive.compo.caption" bundle="<%=interfaces%>"/></option>
                <option value="1" <%=active.intValue()==1?"selected":""%>><bean:message key="rules.active.compo.caption" bundle="<%=interfaces%>"/></option>
                </select>  
            </td>
        </tr>
        <tr>
            <td><bean:message key="require.rule.trailer.direct.caption" bundle="<%=interfaces%>"/> <html:checkbox  name="frmRequireRule" property="direct" value="1" /> </td>
            <td>
                 <html:radio  name="frmRequireRule"  property="selectDep" value="0" />
                 <bean:message key="require.rule.trailer.not.select.department.caption" bundle="<%=interfaces%>"/>
                 <html:radio  name="frmRequireRule"  property="selectDep" value="1" />
                 <bean:message key="require.rule.trailer.select.department.caption" bundle="<%=interfaces%>"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="require.rule.trailer.select.emp.caption" bundle="<%=interfaces%>"/></td>
            <td>
                 <html:radio  name="frmRequireRule"  property="selectEmp" value="0" />
                 <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                 <html:radio  name="frmRequireRule"  property="selectEmp" value="1" />
                 <bean:message key="require.rule.excute.caption" bundle="<%=interfaces%>"/>
            </td>
        </tr>
        
        <tr>
            <td><bean:message key="require.rule.status.trailer.caption" bundle="<%=interfaces%>"/></td>
            <td>
             <logic:present name="BRmStatus">
                <html:select styleClass="inputbox" name="frmRequireRule" property="status_id"  onchange="postAjax('docrule','formList',anchor + ':_VIEW');messageImg('formList');">               
                  <logic:present name="BRmStatus" >
                    <logic:iterate name="BRmStatus" id="bean" type="com.form.admin.require.rm_status.FRmStatus">    
                        <%String statusId=bean.getStatusId()+"";%>
                        <html:option value="<%=statusId%>">
                                <bean:write name="bean" property="name"/>
                                <logic:notEmpty name="bean" property="description" >
                                     (<bean:write name="bean" property="description"/>)
                                </logic:notEmpty>
                        </html:option>
                    </logic:iterate>
                    </logic:present>  
               </html:select>
            </logic:present>
            </td>
        </tr>
        <tr>
            <td><bean:message key="require.rule.status.trailer.stop.caption" bundle="<%=interfaces%>"/></td>
            <td>
             <logic:present name="BRmStatus">
                <html:select styleClass="inputbox" name="frmRequireRule" property="status_store" >
                 <html:option value="-2"  ><B><bean:message key="rules.select.statusEndNot" bundle="<%=interfaces%>" /></B></html:option>
                 <html:option value="-3"><B><bean:message key="rules.select.statusEnd" bundle="<%=interfaces%>" /></B></html:option>
                  <logic:present name="BRmStatus" >
                    <logic:iterate name="BRmStatus" id="bean" type="com.form.admin.require.rm_status.FRmStatus">    
                        <%String statusId=bean.getStatusId()+"";%>
                        <html:option value="<%=statusId%>">
                                <bean:write name="bean" property="name"/>
                                <logic:notEmpty name="bean" property="description" >
                                     (<bean:write name="bean" property="description"/>)
                                </logic:notEmpty>
                        </html:option>
                    </logic:iterate>
                    </logic:present>               
               </html:select>
            </logic:present>
            </td>
        </tr>
        <tr>
            <td><bean:message key="require.rule.comment.caption" bundle="<%=interfaces%>"/></td>
            <td>
                 <html:radio  name="frmRequireRule"  property="comment" value="0" />
                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                 <html:radio  name="frmRequireRule"  property="comment" value="1" />
                <bean:message key="require.rule.excute.caption" bundle="<%=interfaces%>"/>
                <html:checkbox  name="frmRequireRule"  property="repply" value="2" />
                <bean:message key="require.rule.repply.caption" bundle="<%=interfaces%>"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="require.rule.review.caption" bundle="<%=interfaces%>"/></td>
            <td>
                <html:radio  name="frmRequireRule"  property="commentView" value="0" />
                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                <html:radio  name="frmRequireRule"  property="commentView" value="1" />
                <bean:message key="require.rule.review.private.caption" bundle="<%=interfaces%>"/>
                <html:radio  name="frmRequireRule"  property="commentView" value="2" />
                <bean:message key="require.rule.review.public.caption" bundle="<%=interfaces%>"/>
            </td>
        </tr>
         <tr>
            <td><bean:message key="require.rule.trailer.caption" bundle="<%=interfaces%>"/></td>
            <td>
                <html:radio  name="frmRequireRule"  property="trailer" value="0" />
                <bean:message key="require.rule.not.excute.caption" bundle="<%=interfaces%>"/>
                <html:radio  name="frmRequireRule"  property="trailer" value="1" />
                <bean:message key="require.rule.trailer.private.caption" bundle="<%=interfaces%>"/>
                <html:radio  name="frmRequireRule"  property="trailer" value="2" />
                <bean:message key="require.rule.trailer.public.caption" bundle="<%=interfaces%>"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2">
                <bean:define name="frmRequireRule" property="ruleId" id="ruleId" type="java.lang.Integer"/>
               <%                                              
               String Onclick = "selectedSubmit(document.frmRequireRule," + ruleId + ",1)"; 
               String Onclick1 = "selectedSubmit(document.frmRequireRule," + ruleId + ",0)"; 
               %>
                <html:button property="_CREATE"  onclick="<%=Onclick%>"  styleClass="button">                       
                    <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
                </html:button>
                <html:button property="_EDIT"  onclick="<%=Onclick1%>"  styleClass="button">                       
                    <bean:message key="categories.task.cmd.edit" bundle="<%=interfaces%>"/>
                </html:button>
            </td>
        </tr>
     </table>       