<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<Script type="text/javascript">
   function mdotabOption(obj,params){
    if(obj.className=='tab'){
        for(i=0;i<obj.parentNode.cells.length-1;i++){
                obj.parentNode.cells[i].className='tab';
        }
        obj.className='tabactive';       
        postAjax('docMainAssignSend','changeOption',anchor + ':' + params);
        messageImg('changeOption')
    }    
}
</script>
<html:form action="dossiers" method="post" />
<html:form action="docMainAssignSend" method="post"/>
<html:form action="docssend" method="post"/>

<table style="border-collapse: collapse;" width="100%" cellpadding="2" cellspacing="2"  >
<tr>
    <td colspan="2">
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr><td  class="title-01" nowrap>
            <logic:equal name="docAssign" property="type" value="2"><a href="#" class="li-title-04"><bean:message key="form.docs.type.header.send.dt" bundle="<%=interfaces%>"/></a></logic:equal>
            <logic:notEqual name="docAssign" property="type" value="2"><a href="#" class="li-title-03"><bean:message key="form.docs.type.header.send" bundle="<%=interfaces%>"/></a></logic:notEqual>
            </td>
            </tr>
            </table>
    </td>
</tr>
<tr>
                <td align="right" colspan="2">
                    <bean:define name="docAssign" property="type" id="type"  />
                    <bean:define name="docAssign" property="dossierId" id="dossierId" type="java.lang.Integer" />
                    <bean:define name="docAssign" property="views" id="views" type="java.lang.Integer" />
                    <bean:define name="docAssign" property="statusId" id="statusId" />
                    <bean:define name="docssend" property="id" id="id" />
                    <logic:equal name="trackingInfor" value="0">
                    <bean:define name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" id="checkStore" property="checkStore" />
                        <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkStore" value="-2">         
                                <% String onclick="javascript:this.style.display='none';postAjax('docssend','docMain',anchor+':_UPDATE_STATUS_IN_INFOR:storeId:"+ checkStore +":type:"+ type + ":id:"+ id +"')";%>
                                <html:button styleClass="button" property="_PREPARED_CREATE"  onclick="<%=onclick%>" >
                                <bean:message key="action.close" bundle="<%=interfaces%>"/>
                                </html:button>
                        </logic:notEqual>       
                   </logic:equal> 
                </td>
            </tr>
<tr>
    <td  valign="top"  width="70%" style="margin-right:20px;">
             <table width="100%" style="border-collapse: collapse;margin-top:20px;"  cellpadding="0" cellspacing="0" >
            <tr><td ><jsp:include page="/doc/reviews/send/tag.jsp"/></td></tr>
                    <html:form action="docReviewSend" enctype="multipart/form-data" method="post">
                    <%long temp = System.currentTimeMillis();request.getSession().setAttribute("secureId",temp);%>
<input type="hidden" name="secureId" value="<%=temp%>"/>
                        <logic:equal name="trackingInfor" value="0">
                            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkReview" value="1">
                            <tr><td><jsp:include page="/doc/reviews/send/formEdit.jsp"/></td></tr>
                            </logic:equal>
                            <tr><td><jsp:include page="/doc/reviews/send/cmd.jsp"/></td></tr>
                        </logic:equal>
                    </html:form>
            <tr><td  id="docMain"><jsp:include page="/doc/reviews/send/docInfor.jsp"/></td></tr>
            </table>
</td>
     <logic:notEmpty name="BDocReviews" >
    <td width="100%"  valign="top">
            <jsp:include page="/doc/reviews/send/list.jsp"/>
    </td>
      </logic:notEmpty>
</tr>
</table>
