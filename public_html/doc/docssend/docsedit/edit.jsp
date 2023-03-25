<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<fieldset class="friendset" style>
<legend class="LEGEND-DOCSFORM"><bean:message key="form.docs.send.formdt.infor.caption" bundle="<%=interfaces%>"/></legend> 
<html:form action="from" method="post" />
<html:form action="dossiers" method="post" />
<html:form action="docsrecv" method="post" />
<html:form action="classify" method="post" />
<html:form action="docssend" method="post" enctype="multipart/form-data">               
<html:hidden name="docssend" property="type" />
 <html:hidden name="docssend" property="statusId" />
 <html:hidden name="docssend" property="id" />
<html:hidden name="docssend" property="changeId" /> 
   <table  class="tableForm" width="97%" align="center" cellpadding="0" style="border-collapse: collapse" cellspacing="0" border="0">
    
            <tr>
                    <td   nowrap width="70px"><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/></td>
                    <td   nowrap width="100px">
                            <html:select name="docssend" property="formId" styleClass="fieldSelect" onchange="postAjax('docssend','idDoccode',anchor + ':_VIEW_CODE')">
                            <logic:present name="BForms">
                            <html:options collection="BForms" property="id" labelProperty="name"/>
                            </logic:present>
                            </html:select>
                    </td> 
                    <td  nowrap width="70px">
                       <bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>
                    </td>
                    <td  nowrap >
                       <span id="idDossiers">
                            <html:select name="docssend" property="dossierId" styleClass="fieldSelect" >
                            <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
                            <logic:present name="BDossiers">
                            <html:options collection="BDossiers" property="id" labelProperty="name"/>
                            </logic:present>
                            </html:select>
                       </span>
                          <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_CREATE_DOSS_SEND" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_SEND\')'}">   
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">  
                           </a>                        
                    </td>  
                </tr>
            <tr>
                    <td  nowrap><bean:message key="form.docs.send.localCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                    <td nowrap>
                         <span id="idDoccode"><jsp:include page="/doc/docssend/localCode.jsp" /></span>
                    </td>
                    <td nowrap ><bean:message key="form.docs.send.localDate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                    <td nowrap > <input type="text" name="localDate" id="localDate" style="width:80px;" value="<bean:write name="docssend" property="localDate"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'localDate','dd/mm/yyyy');return false;"></td>
                    
            </tr>
                <tr>
                   <td  nowrap>
                                   <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >                
                                    <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                                        <bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>
                                        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                    </logic:notEqual>
                                    </logic:equal>     
                    </td>
                    <td nowrap>
                                <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
                                <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                                    <html:text name="docssend" property="docCode" maxlength="100"/>   
                                </logic:notEqual>
                                </logic:equal>    
                    </td>
                     <td nowrap >
                            <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
                             <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                             <bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>
                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                            </logic:notEqual>
                            </logic:equal>
                    </td>
                    <td nowrap >    
                           <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >
                             <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_CREATE">
                           <input type="text" name="docDate" style="width:80px;" id="docDate" value="<bean:write name="docssend" property="docDate"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'docDate','dd/mm/yyyy');return false;">
                            </logic:notEqual>
                            </logic:equal>
                    </td>
                </tr>
                <tr>
                <td  nowrap><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                    <td  colspan="3" >
                     <html:textarea name="docssend" property="abstracts" styleId="abstracts"  style="width:470px;height:60px;" />
                </tr>
                <tr>
                                <td  nowrap><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></td>
                                <td nowrap>
                                <html:text name="docssend" style="width:120px" property="signer" styleId="signer" maxlength="1000"/>
                                 <input type="button" style="height:18px" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.sign.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_VIEW_LIST_TILE');addthis_none();" />
                                </td>
                                <td  nowrap><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/></td>
                                <td nowrap>
                                   <span id="tdcomboDocsTypeId">
                                        <html:select name="docssend" property="docsTypeId" styleClass="fieldSelect" >
                                        <logic:present name="BDocTypes">
                                        <html:options collection="BDocTypes" property="idDocType" labelProperty="nameDocType"/>
                                        </logic:present>
                                        </html:select>          
                                    </span>
                                </td>
                </tr>
                 <tr>
                            <td nowrap >
                            <bean:message key="form.docs.fromId.to" bundle="<%=interfaces%>"/>
                            </td>
                            <td nowrap >
                                <jsp:include page="/doc/docssend/fromForm.jsp" />
                            </td>                    
                            <td nowrap >
                            <bean:message key="doc.addNew.docReference.cmd.caption" bundle="<%=interfaces%>"/>
                            </td>
                            <td nowrap >
                                    <jsp:include page="/doc/docssend/reference.jsp" />
                            </td>
                </tr>
                <tr>
                        <td colspan="2"   >
                                <logic:notEmpty name="BFroms">
                                <logic:iterate name="BFroms" id="bean" type="com.form.doc.from.FFrom">                       
                                <div>  <Strong><input type="checkBox" checked name="fromsId" id="fromsId" value="<%=bean.getId()%>"><bean:write name="bean" property="code" /></strong>:<bean:write name="bean" property="vnName" />
                                </div>
                                </logic:iterate>
                                </logic:notEmpty>
                                <div id='from_1'></div>
                        </td>
                        <td colspan="2"   nowrap>
                                 <logic:notEmpty name="BDocsReference">
                                <logic:iterate name="BDocsReference" id="bean" type="com.form.doc.docsrecv.FDocsrecv">                       
                                <div>  <input type="checkBox" checked name="docsId" id="docsId" value="<%=bean.getId()%>">
                                            <strong><bean:write name="bean" property="docCode" /></strong>:<bean:write name="bean" property="abstracts" />
                                </div>
                                </logic:iterate>
                                </logic:notEmpty>
                                <div id='doc_1'></div>
                        </td>
                </tr>
                
                <tr>
                    <td valign="top"  nowrap><strong><bean:message key="docs.download" bundle="<%=interfaces%>"/></strong></td>
                    <td   colspan="3" nowrap>
                        <jsp:include page="/doc/docssend/upload.jsp" />
                    </td>
                </tr>
                <tr>
                        <td colspan="4" id="detailtReferent" style="display:none" ></td>
                </tr>
                <tr>
                            <td style="padding-left:0px;"  colspan="4" align="left">
                            <div style="padding-left:20px;"><a href="javascript:hideshow('detail');"><bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>...</a></div>
                                <div id="detail" style="Display:none;">
                                <table style="border-collapse: collapse" cellpadding="0" cellspacing="0" border="0" align="left" width="100%" >
                                    <tr>
                                          <td  nowrap ><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/></td>
                                            <td  nowrap>
                                                      <span id="tdcomboStoreAgeId"> 
                                                            <html:select name="docssend" property="storeAgeId" styleClass="fieldSelect" >
                                                            <logic:present name="BDeps">
                                                            <html:options collection="BDeps" property="id" labelProperty="name"/>
                                                            </logic:present>
                                                            </html:select>
                                                    </span>
                                            </td>
                                             <td  nowrap ><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/></td>
                                            <td  nowrap>
                                                         <span id="tdcomboSecureId">
                                                            <html:select name="docssend" property="secureId" styleClass="fieldSelect" >
                                                            <logic:present name="BSecures">
                                                            <html:options collection="BSecures" property="id" labelProperty="name"/>
                                                            </logic:present>
                                                            </html:select>
                                                        </span>
                                            </td>
                                    </tr>
                                  
                                    <tr>
                                              
                                              <td nowrap><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/></td>
                                            <td  nowrap>
                                                        <html:select name="docssend" property="statusId" styleClass="fieldSelect" disabled="true">
                                                        <logic:present name="BStatus">
                                                        <html:options collection="BStatus" property="id" labelProperty="name"/>
                                                        </logic:present>
                                                        </html:select>
                                            </td>
                                                <td  nowrap><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/></td>
                                                <td nowrap>
                                                   <span id="tdcomboExpressId">
                                                    <html:select name="docssend" property="expressId" styleClass="fieldSelect" >
                                                    <logic:present name="BExpresss">
                                                    <html:options collection="BExpresss" property="id" labelProperty="name"/>
                                                    </logic:present>
                                                    </html:select>
                                                  </span>
                                                </td>
                                            
                                    </tr>
                                    <tr>                                  
                                            <td nowrap ><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/></td>
                                            <td nowrap>
                                                          <span id="tdcomboViaId">
                                                            <html:select name="docssend" property="viaId" styleClass="fieldSelect" >
                                                            <logic:present name="BVias">
                                                            <html:options collection="BVias" property="id" labelProperty="name"/>
                                                            </logic:present>
                                                            </html:select>
                                                        </span>
                                            </td>
                                            <td  nowrap><bean:message key="form.docs.address" bundle="<%=interfaces%>"/></td>
                                            <td nowrap><html:text name="docssend" property="address"   maxlength="200" style="width:145px"/></td> 
                                    </tr>
                                    <tr>
                                            <td nowrap ><Strong><bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/></strong></td>
                                            <td align="left"  id="IDdeadLine" >
                                            <input type="text" name="deadLine" id="deadLine" style="width:80px;" value="<bean:write name="docssend" property="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;">
                                            </td>
                                            <td nowrap >
                                            <Strong><bean:message key="form.docs.classify" bundle="<%=interfaces%>"/></Strong>
                                            </td>
                                            <td  nowrap>
                                                <span ID="tdcomboClassifyId">
                                                    <input type="hidden" name="arrayClassify" id="arrayClassify" value="<bean:write name="arrayClassify" />" />                                                                            
                                                    <html:select name="docssend" property="classifyId" onchange="javascript:onchangeClassify(this)" styleClass="fieldSelect" >
                                                    <html:option value="0"><bean:message key="action.select" bundle="<%=interfaces%>"/></html:option>
                                                    <logic:present name="BClassify">
                                                    <html:options collection="BClassify" property="id" labelProperty="name"/>
                                                    </logic:present>
                                                    </html:select>
                                                </span>
                                            </td>
                                    </tr>
                                </table>
                                </div>
                            </td>
                </tr>
              
                <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkReview" value="1">     
                <tr>
                    <td nowrap>
                        <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRebcaption" value="">
                        <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRebcaption"/> :
                        </logic:notEqual>
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRebcaption" value="">
                        <bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> :
                        </logic:equal>
                    </td>
                    <td  colspan="3" >                
                        <html:textarea name="docssend" property="title" styleId="title"  style="width:470px;height:60px;" />
                    </td>
                </tr>
                </logic:equal>
                <tr>
                <td style="text-align:left" class="toolCmd" colspan="4" >
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkStoreDrapt" value="1">                                
                 <%if(me.isRole(com.inf.IRoles.rLEADER) ){%>
                                        <logic:notEqual name="docssend" property="id" value="0">
                                            <html:button property="_EDIT" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_EDIT')}" >
                                                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                           </html:button>
                                        </logic:notEqual>
        
                                        <logic:equal name="docssend" property="id" value="0">
                                            <html:button property="_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_CREATE')}" >
                                                <bean:message key="action.prepared.store" bundle="<%=interfaces%>"/>                        
                                            </html:button>
                                        </logic:equal>
                                <%}else{%>
                                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>"  value="1">  
                                            <logic:notEqual name="docssend" property="id" value="0">
                                            <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_EDIT')}" >
                                            <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                            </html:button>
                                            </logic:notEqual>
                                    </logic:equal>
                                <%}%>
        </logic:equal>   
        <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkStoreDrapt" value="1">
        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkUpdateDraft" value="1">
                    <logic:notEqual name="docssend" property="id" value="0">
                    <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_EDIT')}" >
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:notEqual>
        </logic:equal>
        </logic:notEqual>
                                            
                                
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkSelectRecv" value="1">
                    <bean:define name="docssend" property="id" id="id" />
                    <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV&id=<%=id%>" rel="{handler: 'iframe', size: {x:390, y: 345},bookmark:'if(SqueezeBox.presets.target==0){post(\'docssend\',anchor + \':_PREPARED_CREATE_AND_CREATE_DOC\');}'}">
                    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" style="width:100px;"  >
                    <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                    </html:button>
                    </a>
            </logic:equal>
            
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkDirect" value="1">
                    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:this.disabled=true;post('docssend',anchor+':_DOC_ASSIGN_CREATE');" >
                                <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" >
                                  <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" />
                                </logic:notEmpty>
                                <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" >
                                         <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                                </logic:empty>
                    </html:button>
                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkSelectDept" value="1">            
                            <logic:present name="BDepartments">
                            <html:select styleClass="inputbox"  name="docssend" property="departmentId">           
                            <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
                            <html:options collection="BDepartments" property="id" labelProperty="name"/>                                         
                            </html:select> 
                            </logic:present>
                    </logic:equal>
            </logic:equal>
                </td>
        </tr>
</table>
  </html:form>    
</fieldset>