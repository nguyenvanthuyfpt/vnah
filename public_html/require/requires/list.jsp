  <%@ include file="/commons/tags.jsp"%>
  <%@ include file="/commons/params.jsp"%>  
 <script type="text/javascript"> 
  var detailRm=null;
  function showRmDetail(showId,rmId,rmStatus){
        if (detailRm!=null && detailRm.innerHTML!='') {
            detailRm.innerHTML='';                          
        }
        postAjax('frmRequire',showId,anchor +':_SHOW_REQUIRE:rmId:'+rmId + ':rmStatus:'+rmStatus,'buildpopup()');
        messageImg(showId)       
        detailRm=getObj(showId)
 }
 
 function mdotab(obj,params,position){
    checkedInnerHtml();
    if(obj.className=='tab1'){
        for(i=0;i<obj.parentNode.childNodes.length;i++){
                if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
        }
        obj.className='tabactive1';
         postAjax('frmRequire',position,anchor + ':' + params);
        messageImg(position)
    }
}
function excuteEndRequire(id,statusStore,status){             
    post('frmRequire',anchor + ':_UPDATE_STORE:rmId:' + id + ":rmStatusStore:" + statusStore + ':status_id:' + status);        
}
</script>
<html:form action="frmRequire" method="post">
<html:hidden name="frmRequire" property="obServer" />
<bean:define name="<%=com.inf.IKey.CHECK_OBSERVER_RM%>" id="beanRmRule"  type="com.form.admin.require.trailer.FRequireTrailer" />
<bean:define name="frmRequire" property="rmStatus" id="rmStatus" type="java.lang.Integer"/>
 <table  class="list-voffice"  cellpadding="0" cellspacing="0" border="0" width="100%" >
<tr>
        <th width="5%" nowrap ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
        <th width="5%" nowrap ><bean:message key="require.from.code.cation.endUser" bundle="<%=interfaces%>"/></th>
        <th  nowrap ><bean:message key="require.from.name.cation.endUser" bundle="<%=interfaces%>"/></th>       
        <th width="8%" nowrap ><bean:message key="require.creator.caption" bundle="<%=interfaces%>"/></th>
        <th width="8%" nowrap ><bean:message key="require.dateSend.caption" bundle="<%=interfaces%>"/></th>
        <th width="8%" nowrap ><bean:message key="rm.category.form.caption" bundle="<%=interfaces%>"/></th>        
        <th width="5%" nowrap >&nbsp;</th>
</tr>
<logic:present name="BRequires">
<bean:define name="BRequires" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
<logic:iterate name="BRequires" id="bean" type="com.form.require.requires.FRequire">
<%String fontBold=bean.getReaded()==0?"bold":"normal";%>
      <tr class="<%=(i%2==0)?"content":"content1"%>" style="font-weight:<%=fontBold%>">
         <td  nowrap align="center"><span class="index"><%=++i%>.</span></td>
         <td nowrap>
            <a title="<%=bean.getCode()%>" class="linkDoc" href="javascript:showRmDetail('removeId<%=bean.getRmId()%>',<%=bean.getRmId()%>,<%=rmStatus%>)">
                <bean:write name="bean" property="code"/>
            </a>
        </td>
         <td>
            <a title="<%=bean.getCode()%>" class="linkDoc" href="javascript:showRmDetail('removeId<%=bean.getRmId()%>',<%=bean.getRmId()%>,<%=rmStatus%>)">
                    <bean:write name="bean" property="name"/>
            </a>
         </td>         
         <td nowrap><bean:write name="bean" property="creatorName"/></td>
         <td nowrap><bean:write name="bean" property="dateCreate"/></td>
         <td nowrap><bean:write name="bean" property="categoryName"/></td>         
         <td nowrap align="center" style="padding-right:8px" >
         <% if ((bean.getCreator()==(int)me.getId() && bean.getRmStatus()!=-1) || (bean.getRmStatus()!=-1 && beanRmRule.getDelRm()==1)){%>         
            <img style="border:0px;cursor: pointer;"  src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('frmRequire',anchor + ':_DELETE:rmId:<bean:write name="bean" property="rmId"/>:rmStatus:<%=rmStatus%>')">
         <%}%> 
         </td>
   </tr> 
   <tr>
        <td class="detail" colspan="9" id="removeId<%=bean.getRmId()%>" width="100%"></td>
  </tr>  
</logic:iterate>
</logic:present>
</table>
</html:form>
