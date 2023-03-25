<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<bean:define name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" id="beanRule"  type="com.form.admin.require.trailer.FRequireTrailer" />
<table  class="tab-review" width="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td  nowrap="nowrap" align="left">
                 <span class="tabactive1" onclick="mdotab(this,'_PREPARED_REVIEW','divReview');">
                   <bean:message key="doc.assign.excute.caption" bundle="<%=interfaces%>"/> 
                  <% if ((beanRule.getCommentView()==1 || beanRule.getCommentView()==2) || (beanRule.getTrailer()==1 || beanRule.getTrailer()==2)){ %>  
                   |
                  <%}%> 
                 </span>
               <% if (beanRule.getTrailer()==1 || beanRule.getTrailer()==2){ %>  
                <span class="tab1" onclick="mdotab(this,'_PREPARED_SAVE','divReview');">
                    <bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/> |
                </span>
                <%}%>
            <% if (beanRule.getCommentView()==1 || beanRule.getCommentView()==2){ %>   
               <span class="tab1"  onclick="mdotab(this,'_DETAIL','divReview');">
                <bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>
             </span>    
             <%}%>
     </td>
   </tr>
</table>
