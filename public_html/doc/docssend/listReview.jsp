<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<% String CHECK_RULE_SEND = com.lib.AppConfigs.CHECK_RULE_DOCSSEND ; %>
<logic:notEqual name="BDocssends" property="forYouId" value="0">        
       <% CHECK_RULE_SEND = "BRuleForYou"; %>
</logic:notEqual>
<bean:define name="BDocssends"  id="beanSend" type="com.form.doc.docssend.FDocssend" />
<bean:define name="<%=CHECK_RULE_SEND%>"  id="beanRule" type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocssends" property="userId" id="userIddoc" type="java.lang.Integer" />
<bean:define name="BDocssends" property="obServer"  id="obServer"  type="java.lang.Integer" />
<%

  int open = 0;
  if (obServer!=1){ 
  if (beanSend.getViews()==0 || (beanSend.getViews()==1 && beanRule.getCheckExcuteNotView()!=1)){
        if (beanSend.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1){
            open = 1;
        }else  if (beanSend.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==0){
            open = 0;
        }else {
       
            if (beanSend.getReaded()==1 && beanSend.getStatusId()==0){
                    open = 1;
            }else if (beanSend.getReaded()==0 && beanSend.getStatusId()!=0 && beanSend.getStatusId()!=-1 && (beanRule.getCheckReply()==1 || beanRule.getCheckSelectRecv()==1 || beanRule.getCheckDirect()==1)){
                    open = 1;
            }else if (beanSend.getReaded()==1 && beanSend.getStatusId()!=-1 && beanRule.getCheckReadOnly()==0 ){
                    open = 1;
            }
        }
    }
    }
    int checked = 0;
%>
<table  class="listreview" width="100%" border="0" cellpadding="0" cellspacing="4">
<% int j=0,dem = 0;
String userExits="#";
%>
<logic:notEqual name="<%=CHECK_RULE_SEND%>" property="checkViewReview" value="2">
<logic:present name="BDocReviews" >
<bean:define name="BDocReviews" id="beans" type="com.form.FBeans"/>
<logic:iterate name="BDocReviews" id="bean" indexId="i" type="com.form.doc.assign.FDocAssign">
<%if(!(bean.getCreator()==me.getId() && bean.getForyouCreator()>0)){j++;%>
        <tr>
        <td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=j%> </span> </td>
        <td width="97%">
                  <div style="color:#949494">
                        <bean:write name="bean" property="timeCreate" />
                        <logic:notEqual name="bean" property="fileName" value="">
                                <a href="javascript:post('docReviewSend',anchor + ':_SAVE:reviewId:<%=bean.getReviewId()%>');remove('docReviewSend',anchor);remove('docReviewSend','reviewId');">
                                    <img title="<bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/attach.gif"/>
                                </a>
                        </logic:notEqual>
                  </div>
                  <div>
                    <%  if (open == 1 && me.getId()!=bean.getCreator()){                        
                            if (dem==0) {dem =1 ;                    
                    %>
                                  <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReply" value="1">
                                        <%if(userExits.indexOf("#"+bean.getCreator()+"#")<0){%>
                                        <input  checked type="radio" name="userReply" value="<%=bean.getCreator()%>,<%=bean.getForYouId()%>" />                                  
                                        <%userExits+=bean.getCreator()+"#";%>
                                        <%}%>
                                  </logic:equal>                                              
                            <%}else{%>
                                  <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReply" value="1">
                                        <%if(userExits.indexOf("#"+bean.getCreator()+"#")<0){%>
                                                <input   type="radio" name="userReply" value="<%=bean.getCreator()%>,<%=bean.getForYouId()%>" />                                  
                                                <%userExits+=bean.getCreator()+"#";%>
                                        <%}%>
                                  </logic:equal> 
                            <%}%>
                    
                    <%}%>
                      <Strong><bean:write name="bean" property="nameCreator" />
                       <logic:notEqual name="bean" property="checkReviewForYou" value="0">
                            [ <a href="#" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docssend','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');" onmouseout="addthis_close()" ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
                       </logic:notEqual>
                      </strong> : <bean:write name="bean" property="title" />
                  </div>
        </td>
        </tr>
<%}%>    
</logic:iterate>

<logic:notEqual name="<%=CHECK_RULE_SEND%>" property="checkViewReview" value="0">
<tr>
<td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=++j%>  </span> </td>
    <td width="97%">
            <div style="color:#949494">               
            <bean:write name="BDocssends" property="timeCreate" /></div>
        
        <%  if (open == 1  && userIddoc!=me.getId() ){ %>
             <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReply" value="1">
                <bean:define name="BDocssends" property="userId" id="userId" type="java.lang.Integer" />
                <%if(userExits.indexOf("#"+userId+"#")<0){%>
                        <% if (dem==0) {dem =1 ; %>
                            <input checked type="radio" name="userReply" value="<%=userId%>,0" /> 
                        <%}else{%>
                            <input type="radio" name="userReply" value="<%=userId%>,0" /> 
                        <%}%>
                   <%userExits+=userId+"#";%>
                <%}%>
            </logic:equal>
        <%}%>
        
        <bean:message key="form.docs.people.creator" bundle="<%=interfaces%>"/>: <strong> <bean:write name="BDocssends" property="creator" /></strong>
    </td>
</tr>
</logic:notEqual>
</logic:present>
</logic:notEqual>
<%if (open == 1){%>
  <logic:notPresent name="trackingObserver" > 
                <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReview" value="1">     
                    <tr>
                        <td style="padding-left:6px;" colspan="2">
                           <logic:notEqual name="<%=CHECK_RULE_SEND%>" property="checkRebcaption" value="">
                               <Strong><bean:write name="<%=CHECK_RULE_SEND%>" property="checkRebcaption"/> :</Strong>
                           </logic:notEqual>
                
                            <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkRebcaption" value="">
                            <Strong><bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> :</Strong>
                            </logic:equal>
                            <textarea   name="title"  id="title" maxlength="200" style="width:350px;height:40px"></textarea>
                            <input type="button" value="..." width="10px" onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docReviewSend','at_share',anchor + ':_VIEW_LIST_TILE');"  />
                        </td>
                    </tr>
                </logic:equal> 
                <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReviewFile" value="1">
                    <tr>
                        <td style="padding-left:6px;" colspan="2">
                            <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/>:</strong><html:file name="docReviewSend" property="fileUpload" size="25"/>
                        </td>
                    </tr>
                 </logic:equal>
                <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDefineFileEmit" value="1">
                    <tr>
                        <td valign="top" style="padding-left:6px;" colspan="2">
                            <jsp:include page="/doc/docssend/listFileEmit.jsp" />
                        </td>
                    </tr>
                </logic:equal>
  </logic:notPresent>  
<%}%>
</table>
