<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<bean:define name="BDocssends" property="id"  id="id" type="java.lang.Integer" />
<html:hidden name="BDocssends" property="obServer" />
<% String CHECK_RULE_SEND = com.lib.AppConfigs. CHECK_RULE_DOCSSEND ; %>
<logic:notEqual name="BDocssends" property="forYouId" value="0">        
       <% CHECK_RULE_SEND = "BRuleForYou"; %>
</logic:notEqual>
<bean:define name="<%=CHECK_RULE_SEND%>"  id="beanRule" type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocssends"  id="beanSend" type="com.form.doc.docssend.FDocssend" />
<%
int open=0; int hr=0;
if(beanSend.getViews()==0 || beanSend.getViews()==-1){//la cong van xu ly
    open=1;
}else if(beanRule.getCheckExcuteNotView()==0){//xu ly de biet
    open=1;
}%>
<%if(open==1 && beanSend.getObServer()!=1){%>
<table class="tab-review" cellpadding="0" cellspacing="0" width="100%" >
<tr valign="bottom">  
<td   nowrap="nowrap" width="200px">
<%if(beanRule.getCheckViewReview()!=2 || beanRule.getCheckReview()==1 || (beanSend.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1)){  hr=1;%>
        <span class="tabactive1"  onclick="mdotab(this,'_DOC_REVIEW:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
        <bean:message key="doc.assign.excute.caption" bundle="<%=interfaces%>"/>
        </span>
         
          <% if (beanRule.getCheckDocTranfer()==1 || beanRule.getCheckDocTranfer()==2){ %>
         |
         <%}%>
         <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDocTranfer" value="0">
             <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDetail" value="1">
             |
             </logic:equal>
         </logic:equal>
 <%}%>              
        
          <% if (beanRule.getCheckDocTranfer()==1 || beanRule.getCheckDocTranfer()==2){ %>
        <span class="tab1" onclick="mdotab(this,'_PREPARED_SAVE:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
            <bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>
        </span>
            <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDetail" value="1">
            |
            </logic:equal>                    
      <%}%>               

        <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDetail" value="1">
        <span class="tab1"  onclick="mdotab(this,'_DETAIL:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
            <bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>
        </span>
        </logic:equal>
        
</td>                        
 <td nowrap="nowrap" style="text-align: right;" align="right">
    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkDossier" value="1"> 
    <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_CREATE_DOSS_SEND" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_SEND\')'}">   
    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">  
    </a>
    <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> :</strong>
    <span id="idDossiers">
    <html:select name="BDocssends" property="dossierId" styleId="dossierId" styleClass="fieldSelect" >
    <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
    <logic:present name="BDossiers">
    <html:options collection="BDossiers" property="id" labelProperty="name"/>
    </logic:present>
    </html:select>
    </span>
   <input type="button"   onclick="javascript:if(document.docReviewSend.dossierId.value>0){ postAjax('docssend','docMain',anchor + ':_SAVE_DOC:dossierId_doc:' + document.docReviewSend.dossierId.value );}else{ return false;}" value='<bean:message key="cmd.doc.update.dossier" bundle="<%=interfaces%>"/>' />              
    </logic:equal>
</td>
</tr>
</table>
<%}else if (beanSend.getObServer()==1){%>

        <table class="tab-review" cellpadding="0" cellspacing="0" width="100%" >
<tr valign="bottom">  
<td   nowrap="nowrap" width="200px">
        <span class="tabactive1"  onclick="mdotab(this,'_DOC_REVIEW:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
        <bean:message key="doc.assign.excute.caption" bundle="<%=interfaces%>"/>
        </span>
             |
        
       
        <span class="tab1" onclick="mdotab(this,'_PREPARED_SAVE:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
            <bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>
        </span>
               |        

        
        <span class="tab1"  onclick="mdotab(this,'_DETAIL:statusId:<%=beanSend.getStatusId()%>:id:<%=id%>:forYouId:<%=beanSend.getForYouId()%>','divReview');">
            <bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>
        </span>
</td>                        
</tr>
</table>
<%}%>