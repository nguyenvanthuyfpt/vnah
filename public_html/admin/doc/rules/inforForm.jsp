 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>     
<table  cellpadding="0" cellspacing="0" width="100%" border="0">
         <tr>
               <td height="24px"><Strong><bean:message key="doc.header.workflow.caption" bundle="<%=interfaces%>"/></strong></td>
                <td>
                        <html:select styleClass="inputbox" name="docrule" property="workflowId"  style="width:107px;" onchange="postAjax('docrule','formList',anchor + ':_VIEW');messageImg('formList');">
                            <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
                        </html:select>
                </td>
        </tr>
        <tr>
            <td height="24px"><Strong><bean:message key="rules.title.caption" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></strong> </td>
            <td height="24px" nowrap> <html:text name="docrule" property="title" styleId="title" style="width:150px" size="250"/> 
                    <bean:define name="docrule" property="active" id="active" type="java.lang.Integer" />     
                    <select  name="active" style="width:110px"> 
                    <option value="0" <%=active.intValue()==0?"selected":""%>><bean:message key="rules.unActive.compo.caption" bundle="<%=interfaces%>"/></option>
                    <option value="1" <%=active.intValue()==1?"selected":""%>><bean:message key="rules.active.compo.caption" bundle="<%=interfaces%>"/></option>
                    </select>     
            </td>
        </tr>
         <tr>
        <td colspan="2">
        <hr size="0">
        </td>
        </tr>
             <tr>
                <td height="24px" valign="top">
                     <div>
                     <Strong><bean:message key="doc.header.direct.caption" bundle="<%=interfaces%>"/></strong>
                      <html:checkbox  name="docrule" property="direct" value="1" /> 
                    </div>
                </td>
                
                <td valign="top">                     
                      <html:radio  name="docrule"  property="selectDept" value="0" />
                      <bean:message key="rules.UnSelectDept.caption.combo" bundle="<%=interfaces%>"/>
                     
                      <html:radio  name="docrule"  property="selectDept" value="1" />
                      <bean:message key="rules.selectDept.caption.combo" bundle="<%=interfaces%>"/>
                    
                
                </td>
            </tr>
           <tr>
                <td> <Strong><bean:message key="doc.header.direct.caption" bundle="<%=interfaces%>"/> (ti&#234;u &#273;&#7873;) </strong></td>
                <td> <html:text name="docrule" property="tcaption"  style="width:150px" size="250"/> </td>
            </tr>
              
            <tr>
                <td height="24px"><Strong><bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/></strong></td>
                <td>
                    <html:radio  name="docrule"  property="selectRecv" value="0" />
                    <bean:message key="rules.unSelect.compo.caption" bundle="<%=interfaces%>"/>
                    <html:radio  name="docrule"  property="selectRecv" value="1" />                            
                    <bean:message key="rules.select.compo.caption" bundle="<%=interfaces%>"/>
                   
                                              
                   
                   
                    
                </td>
            </tr>
            <tr>
                <td height="24px" nowrap><Strong><bean:message key="doc.header.excuteGroup.caption" bundle="<%=interfaces%>"/></strong><html:checkbox  name="docrule" property="excuteGroup" value="1" /> </td>
                <td>
                    <Strong><bean:message key="users.edit.sms" bundle="<%=interfaces%>"/> </strong>
                    <html:checkbox  name="docrule" property="sendSms" value="1" />
                </td>
            </tr>
             
             <tr>
        <td colspan="2">
        <hr size="0">
        </td>
        </tr>     
             <tr>
                    <td height="24px" valign="top"><strong><bean:message key="rules.comment.caption" bundle="<%=interfaces%>"/></strong>
                    </td>
                    <td>  <html:radio  name="docrule"  property="comment" value="0" />
                     <bean:message key="rules.UnComment.caption.combo" bundle="<%=interfaces%>"/>
                      <html:radio name="docrule"  property="comment" value="1" />
                     <bean:message key="rules.comment.caption.combo" bundle="<%=interfaces%>"/>
                     <html:checkbox  name="docrule" property="reply" styleId="reply" value="1" />
                     <bean:message key="doc.header.reply.caption" bundle="<%=interfaces%>"/> 
                     </td>
            </tr>  
            <tr>
                <td> <Strong><bean:message key="doc.header.reply.caption" bundle="<%=interfaces%>"/> (<bean:message key="doc.header.title.caption" bundle="<%=interfaces%>"/>) </strong></td>
                <td> <html:text name="docrule" property="recaption"  style="width:150px" size="250"/> </td>
            </tr>
             
             <tr>
                <td> <Strong><bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> (<bean:message key="doc.header.title.caption" bundle="<%=interfaces%>"/>) </strong></td>
                <td>
                        <html:text name="docrule" property="rebcaption"  style="width:150px" size="250"/>
                         
                        
                </td>
            </tr>
            <tr>
                <td> <Strong> <bean:message key="doc.header.reviewfile.caption" bundle="<%=interfaces%>"/>  </strong></td>
                <td>
                        
                        <html:checkbox  name="docrule" property="reviewfile" value="1" /> 
                </td>
            </tr>
            <tr>
                <td height="24px"><Strong><bean:message key="rules.comment.review" bundle="<%=interfaces%>"/></strong></td>
                <td>
                     <html:radio  name="docrule"  property="review" value="0" />
                     <bean:message key="rules.comment.review.private" bundle="<%=interfaces%>"/>
                    <html:radio name="docrule"  property="review" value="1" />
                    <bean:message key="rules.comment.review.extend" bundle="<%=interfaces%>"/>                                                           
                    <html:radio name="docrule"  property="review" value="2" />
                    <bean:message key="doc.header.notReview.caption" bundle="<%=interfaces%>"/>
                     
                </td>
            </tr>
             <tr>
                <td height="24px"><Strong><bean:message key="rules.trailer.comment" bundle="<%=interfaces%>"/></strong></td>
                <td>
                     <html:radio  name="docrule"  property="docTranfer" value="2" />
                     <bean:message key="rules.comment.review.private" bundle="<%=interfaces%>"/>
                    <html:radio name="docrule"  property="docTranfer" value="1" />
                    <bean:message key="rules.comment.review.extend" bundle="<%=interfaces%>"/>                                                           
                    <html:radio name="docrule"  property="docTranfer" value="0" />
                    <bean:message key="doc.header.notTrailer.caption" bundle="<%=interfaces%>"/>
                     
                </td>
            </tr>    
        
                    
        <tr>
            <td height="24px" colspan="2" align="center">
            <bean:define name="docrule" property="ruleId" id="ruleId" type="java.lang.Integer"/>
               <%                                              
               String Onclick = "selectedSubmit(document.docrule," + ruleId + ",1)"; 
               String Onclick1 = "selectedSubmit(document.docrule," + ruleId + ",0)"; 
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