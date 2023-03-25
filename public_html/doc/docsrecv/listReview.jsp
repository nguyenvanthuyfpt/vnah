<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String CHECK_RULE_DOCSRECV=com.lib.AppConfigs.CHECK_RULE_DOCSRECV;%>
<logic:notEqual name="BDocsrecvs" property="forYouId" value="0">
        <%CHECK_RULE_DOCSRECV="BRuleForYouRecv";%>
</logic:notEqual>
<bean:define name="BDocsrecvs" id="beanRecv"  type="com.form.doc.docsrecv.FDocsrecv" />
<bean:define name="<%=CHECK_RULE_DOCSRECV%>" id="beanRule"  type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocsrecvs" property="userId" id="userIddoc" type="java.lang.Integer" />
<bean:define name="docsrecv" property="obServer"  id="obServer"  type="java.lang.Integer" />
<%
    int open = 0;
       if (obServer!=1){ 
            if (beanRecv.getViews()==0 || (beanRecv.getViews()==1 && beanRule.getCheckExcuteNotView()!=1)){
               if (beanRecv.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1){
                    open = 1;
               }else  if (beanRecv.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==0){
                    open = 0;
               }else {
               
                    if (beanRecv.getReaded()==1 && beanRecv.getStatusId()==0){
                            open = 1;
                    }else if (beanRecv.getReaded()==0 && beanRecv.getStatusId()!=0 && beanRecv.getStatusId()!=-1){
                            open = 1;
                    }else if (beanRecv.getReaded()==1 && beanRecv.getStatusId()!=-1 && beanRule.getCheckReadOnly()==0 ){
                            open = 1;
                    }
                }
           }           
         int checked = 0;
         if (beanRecv.getForYouId()>0 && beanRule.getActiveForyou()==0){
                open = 0;
         }
    }
%>

<table  class="listreview" width="100%" border="0" cellpadding="0" cellspacing="4">
<% int j=0,dem = 0;
String userExits="#";
%>
<logic:notEqual name="<%=CHECK_RULE_DOCSRECV%>" property="checkViewReview" value="2">
<logic:present name="BDocReviews">
<bean:define name="BDocReviews" id="beans" type="com.form.FBeans"/>
<logic:iterate name="BDocReviews" id="bean" indexId="i" type="com.form.doc.assign.FDocAssign">

<%if(!(bean.getCreator()==me.getId() && bean.getForyouCreator()>0)){  ++j;%>
<tr>
 <td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=j%> </span> </td>
<td  style="padding-left:4px;" width="97%">
          <div style="color:#949494">
                     <bean:write name="bean" property="timeCreate" /> 
                      <logic:notEqual name="bean" property="fileName" value="">
                                             <a href="javascript:post('docReviewRecv',anchor + ':_SAVE:reviewId:<%=bean.getReviewId()%>');remove('docReviewRecv',anchor);remove('docReviewRecv','reviewId');">
                                             <img title="<bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/attach.gif"/>
                                             </a>
                         </logic:notEqual>
          </div>
                        <div>
                                    <%  if (open == 1 && me.getId()!=bean.getCreator()){                        
                                        if (dem==0) {dem =1 ;                    
                                %>
                                              <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReply" value="1">
                                                    <%if(userExits.indexOf("#"+bean.getCreator()+"#")<0){%>
                                                    <input  checked type="radio" name="userReply" value="<%=bean.getCreator()%>,<%=bean.getForYouId()%>" />                                  
                                                    <%userExits+=bean.getCreator()+"#";%>
                                                    <%}%>
                                              </logic:equal>                                              
                                        <%}else{%>
                                              <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReply" value="1">
                                                    <%if(userExits.indexOf("#"+bean.getCreator()+"#")<0){%>
                                                    <input   type="radio" name="userReply" value="<%=bean.getCreator()%>,<%=bean.getForYouId()%>" />
                                                    <%userExits+=bean.getCreator()+"#";%>
                                                    <%}%>
                                              </logic:equal> 
                                        <%}%>
                                
                                <%}%>
                                  <strong><bean:write name="bean" property="nameCreator" />
                                   <logic:notEqual name="bean" property="checkReviewForYou" value="0">
                                        [ <a href="#" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docssend','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');" onmouseout="addthis_close()" ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
                                   </logic:notEqual>
                                  </strong>:<bean:write name="bean" property="title" />
                        </div>
</td>
</tr>
<%}%>    
    </logic:iterate>
</logic:present>
</logic:notEqual>


<logic:notEqual name="<%=CHECK_RULE_DOCSRECV%>" property="checkViewReview" value="0">
<tr>
      <td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=++j%> </span> </td>
      <td width="97%">
            <div style="color:#949494">
              
            <bean:write name="BDocsrecvs" property="timeCreate" /></div>
            
          <%  if (open == 1  && userIddoc!=me.getId() ){ %>
             <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReply" value="1">
                <bean:define name="BDocsrecvs" property="userId" id="userId" type="java.lang.Integer" />
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
         
          <bean:message key="form.docs.people.creator" bundle="<%=interfaces%>"/>: <strong> <bean:write name="BDocsrecvs" property="creator" /></strong>
    </td>
</tr>
</logic:notEqual>


  <% if (open == 1){%>
<logic:notPresent name="trackingObserver">
 <logic:notEqual name="BDocsrecvs" property="statusId" value="-1">
     <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReview" value="1">
            <tr>
                <td style="padding-left:10px;" colspan="2">
                       <logic:notEqual name="<%=CHECK_RULE_DOCSRECV%>" property="checkRebcaption" value="">
                       <Strong><bean:write name="<%=CHECK_RULE_DOCSRECV%>" property="checkRebcaption"/> :</Strong>
                       </logic:notEqual>
                       
                       <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkRebcaption" value="">
                        <Strong><bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> :</Strong>
                       </logic:equal>
                        
                        <textarea   name="title"  id="title" maxlength="200" style="width:350px;height:40px"></textarea> 
                        <input type="button" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docReviewRecv','at_share',anchor + ':_VIEW_LIST_TILE');" />                                               
                 </td>
            </tr>
        </logic:equal>
              
      <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReviewFile" value="1">              
            <tr>
                <td style="padding-left:10px;" colspan="2">
                    <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/>:</strong><html:file name="docReviewRecv" property="fileUpload" size="25"/>
                </td>
            </tr>
        </logic:equal>            
</logic:notEqual>
</logic:notPresent>
<%}%>
</table>

