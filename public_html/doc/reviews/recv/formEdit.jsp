<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<logic:equal name="BRuleDocsRecv" property="checkReview" value="1"> 
<html:hidden name="docAssign" property="statusId" />
<div class="ct-celendar">  
<table cellpadding="0" cellspacing="0"  align="left" width="100%">  
      <tr>
           <td valign="top"  nowrap width="90px">      
              <bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
           </td>        
           <td valign="top" style="padding-right:8px" align="left">  
               <html:text name="docAssign" property="title" style="width:300px;" maxlength="500"/>
               <input type="button" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docReviewRecv','at_share',anchor + ':_VIEW_LIST_TILE');"  />
           </td>        
    </tr>
     <tr>
           <td valign="top"  nowrap >  
                <bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/> 
           </td>        
           <td valign="top" style="padding-right:8px" align="left">  
                    <input type="text" name="deadLine" id="deadLine" value="<bean:write name="docAssign" property="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;">
                    <html:button property="_PREPARED_CREATE" onclick="javascript:if(validateReview(this.form)){post('docReviewRecv',anchor+':_PREPARED_CREATE')};" styleClass="button">
                         <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRecaption" >
                                      <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRecaption" />
                            </logic:notEmpty>
                            <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRecaption" >
                                     <bean:message key="command.doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>   
                            </logic:empty>                                                                                    
                    </html:button>
           </td>        
    </tr> 
      <tr>
           <td valign="top"  nowrap >                
                <bean:message key="doc.review.content.caption" bundle="<%=interfaces%>"/>
           </td>        
           <td valign="top" style="padding-right:8px" align="left">  
               <html:textarea name="docAssign" property="issue" style="width:300px;height:100px" ></html:textarea>
           </td>        
    </tr> 
     <tr>
           <td valign="top"  nowrap >  
                <bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>
           </td>        
           <td valign="top" style="padding-right:8px" align="left">  
               <html:file name="docAssign" property="fileUpload" size="40"/>
           </td>        
    </tr> 
</table>    
</div>
</logic:equal>

    