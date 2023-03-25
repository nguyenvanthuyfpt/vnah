 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" noborder style="border-collapse: collapse;margin-top:25px;"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-02">
       <bean:message key="doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>
    </a>
</td>
</tr>
<tr>
<td style="border:#CCCCCC 1px solid;">
  <bean:define name="docAssign" property="id" id="id" type="java.lang.Integer" />
        <bean:define name="docAssign" property="type" id="type" type="java.lang.Integer" />
         <logic:present name="BDocReviews" >
            <bean:define name="BDocReviews" id="beans" type="com.form.FBeans"/>           
        <logic:iterate name="BDocReviews" id="bean" indexId="i" type="com.form.doc.assign.FDocAssign">        
        <%if(!(bean.getCreator()==me.getId() && bean.getForyouCreator()>0)){%>
            <div style="padding-left:10px;" >
            <div>         
                         <bean:write name="bean" property="nameCreator" />
            </div>
          <logic:notEqual name="bean" property="deadLine" value="">
          <div>
                       <Span style="color:#153a85;">
                       <bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/>:&nbsp;<bean:write name="bean" property="deadLine" />
                       </span>
        </div>
        </logic:notEqual>
        <div>
                        <Span style="font-size:11px">
                       <strong> <bean:write name="bean" property="title" /> : </strong>
                       <bean:write name="bean" property="issue" />
                        </span>
        </div>
           <div align="right" style="color:#949494;font-size:10px">
                          <bean:write name="bean" property="timeCreate" /> 
          </div>
         <logic:notEqual name="bean" property="fileName" value="">
            <div>
                                 <a href="javascript:post('docReviewRecv',anchor + ':_SAVE:reviewId:<%=bean.getReviewId()%>');remove('docReviewRecv',anchor);remove('docReviewRecv','reviewId');">
                                 <bean:write name="bean" property="fileName" />
                                 </a>
              </div>
             </logic:notEqual>
      
          </div>
        <hr size="0">
<%}%>
        </logic:iterate>
        </logic:present>
</td>
</tr>
</table>
  