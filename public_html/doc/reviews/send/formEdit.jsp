<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

    <table cellpadding="0" cellspacing="0"  style="padding-left:10px" align="left" width="100%">  
      <tr>
           <td valign="top"  nowrap >      
                   <bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/>            </td>        
           <td valign="top" style="padding-right:8px" align="left" nowrap>  
                    <html:text name="docAssign" property="title" styleId="title" style="width:300px;" maxlength="500"/>
                    <input type="button" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docReviewSend','at_share',anchor + ':_VIEW_LIST_TILE');" />
           </td>        
    </tr>
    <tr><td>
    <bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/> 
    
    </td><td>
                    <input type="text" name="deadLine" id="deadLine" value="<bean:write name="docAssign" property="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;">
                        <html:button property="_PREPARED_CREATE" onclick="javascript:post('docReviewSend',anchor+':_PREPARED_CREATE');" styleClass="button">
                            <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRecaption" >
                                      <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRecaption" />
                            </logic:notEmpty>
                            <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRecaption" >
                                     <bean:message key="command.doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>   
                            </logic:empty>                                                                                   
                    </html:button>

    </td></tr>
      <tr>
           <td valign="top"  nowrap >                
                <bean:message key="doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>
           </td>        
           <td valign="top" style="padding-right:8px" align="left">  
               <html:textarea name="docAssign" property="issue" style="width:300px;height:100px"></html:textarea>
           </td>        
    </tr> 
     <tr>
           <td valign="top"  nowrap >  
                <bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>
           </td>        
           <td valign="top" align="left">  
              <span id="draftFile"><html:file name="docAssign" property="fileUpload" size="25"/></span>
           </td>        
    </tr>
</table>    
