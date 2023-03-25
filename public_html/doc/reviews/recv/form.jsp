<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script language="javascript">
    function submitAssign(){ 
        checkedAll(document.docAssignRecv.usersId);
        postAjax('docAssignRecv','docMain',anchor+':_CREATE')
    }
</script>
<html:form action="docsrecv" method="post"/>

<table width="100%"  style="border-collapse: collapse"  cellpadding="2" cellspacing="2"  >
<tr>
<td  class="title-01" nowrap colspan="2">         
    <a href="#" class="li-title-02"><bean:message key="form.docs.type.header.recv" bundle="<%=interfaces%>"/></a>                
</td>
</tr>
<tr>
    <td align="right" colspan="2">
                    <bean:define name="docAssign" property="dossierId" id="dossierId" type="java.lang.Integer" />
                    <bean:define name="docAssign" property="views" id="views" type="java.lang.Integer" />
                    <bean:define name="docAssign" property="statusId" id="statusId" />
                    <bean:define name="docsrecv" property="id" id="id" />
                    <logic:equal name="trackingInfor" value="0">
                    <bean:define name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" id="checkStore" property="checkStore" />
                        <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkStore" value="-2">         
                                <% String onclick="javascript:this.style.display='none';postAjax('docsrecv','docMain',anchor+':_UPDATE_STATUS_IN_INFOR:storeId:"+ checkStore +":id:"+ id +"')";%>
                                <html:button styleClass="button" property="_PREPARED_CREATE"  onclick="<%=onclick%>" >
                                <bean:message key="action.close" bundle="<%=interfaces%>"/>
                                </html:button>
                        </logic:notEqual>       
                   </logic:equal> 
    </td>
</tr>
<tr>
    <td valign="top" width="70%" >
        <table width="100%" style="border-collapse: collapse;margin-top:20px;"  cellpadding="0" cellspacing="0" >
            <tr><td ><jsp:include page="/doc/reviews/recv/tag.jsp"/></td></tr>
                    <html:form action="docReviewRecv" enctype="multipart/form-data" method="post">
                        <% long secureId = System.currentTimeMillis();request.getSession().setAttribute("secureId",secureId);%>
                        <input type="hidden" name="secureId" value="<%=secureId%>"/>
                        <logic:equal name="trackingInfor" value="0">
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkReview" value="1">
                            <tr><td><jsp:include page="/doc/reviews/recv/formEdit.jsp"/></td></tr>
                        </logic:equal>
                            <tr><td><jsp:include page="/doc/reviews/recv/cmd.jsp"/>     </td></tr>
                        </logic:equal>
                    </html:form>
            <tr><td  id="docMain"><jsp:include page="/doc/reviews/recv/docInfor.jsp"/></td></tr>
        </table>
    </td>
    <logic:notEmpty name="BDocReviews" >
    <td valign="top" width="100%">
        <jsp:include page="/doc/reviews/recv/list.jsp"/>           
    </td>
    </logic:notEmpty>
</tr>
</table>

            
            
  
 
 
