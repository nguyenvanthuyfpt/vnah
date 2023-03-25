<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td width="200px" valign="top" align="left" style="padding-right: 8px" >
            <jsp:include page="/doc/docssend/inforForm/listStatus.jsp" />   
            <jsp:include page="/doc/docsrecv/inforForm/listTrailer.jsp" />
        </td>
        <td style="padding-right: 20px" valign="top">
        <fieldset class="friendset" style>
                <legend class="LEGEND-DOCSFORM"><bean:message key="form.docs.send.infor.caption" bundle="<%=interfaces%>"/></legend> 
     <table class="tableForm" cellpadding="0" width="97%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
      <tr>
        <td  nowrap="nowrap"  width="70px"><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</td>
        <td  nowrap="nowrap" width="100px">
            <html:select name="docssend" property="formId" styleClass="fieldSelect" onchange="postAjax('docssend','idDoccode',anchor + ':_VIEW_CODE')">
                <logic:present name="BForms">
                 <html:options collection="BForms" property="id" labelProperty="name"/>
                 </logic:present>
          </html:select>
         </td>
        <td nowrap="nowrap"width="70px"><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></td>
        <td  nowrap>
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
        <td nowrap="nowrap"><bean:message key="form.docs.send.localCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  nowrap>
            <span id="idDoccode">
               <jsp:include page="/doc/docssend/localCode.jsp" /> 
           </span>
        </td>
       <td nowrap="nowrap"><bean:message key="form.docs.send.localDate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  nowrap><input type="text" name="localDate" style="width:80px;" id="localDate" value="<bean:write name="docssend" property="localDate"/>" /><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'localDate','dd/mm/yyyy');return false;"></td>
    </tr>
    
    <tr>
      <td nowrap="nowrap"><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  nowrap><html:text name="docssend" property="docCode" maxlength="50" style="width:145px"/></td>
        
        <td nowrap="nowrap"><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  nowrap><input type="text" name="docDate" id="docDate" style="width:80px;" value="<bean:write name="docssend" property="docDate"/>" /><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'docDate','dd/mm/yyyy');return false;"></td>
    </tr>
     <tr>
        <td nowrap="nowrap" >
            <bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
        </td>
        <td colspan="3"  nowrap="nowrap"  >
            <html:textarea name="docssend" property="abstracts"  style="width:470px;height:60px;" />
        </td>
      </tr>
        
        <tr>
                        <td nowrap="nowrap"  ><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></td>
                        <td  nowrap  >
                         <html:text name="docssend" style="width:125px" property="signer" styleId="signer" maxlength="1000"/>
                         <input type="button" style="height:18px" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.sign.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_VIEW_LIST_TILE');addthis_none();" />
                        </td>
                        
                        <td nowrap="nowrap" ><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/></td>
                        <td   nowrap="nowrap" >
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
        <td nowrap="nowrap"><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/>:</td>
        <td  nowrap="nowrap">
         <jsp:include page="/doc/docsrecv/storeArea.jsp" />
        </td>
        <td nowrap="nowrap"><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/></td>
        <td  nowrap="nowrap"   >
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
          <td align="left" colspan="2"  class="tdcontent1" style="vertical-align:top;" >
               <logic:notEmpty name="BSearchStore">
                        <logic:iterate name="BSearchStore" id="beanD" type="com.form.admin.departments.FDepartment">                       
                        <div> <Strong><input type="checkBox" checked name="storesId" id="storesId" value="<%=beanD.getId()%>"><bean:write name="beanD" property="code" /></strong>:<bean:write name="beanD" property="name" />
                        </div>
                        </logic:iterate>
                        </logic:notEmpty>
                        <div id='storeArea_1'></div>   
          </td>
            <td colspan="2"  class="tdcontent1" style="vertical-align:top;" align="left" >
                    
            </td>
   </tr>
       <tr>
        <td nowrap="nowrap"    ><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/>:</td>
        <td   nowrap="nowrap"   >
         <span id="tdStatusId">
                <html:select name="docssend" property="statusId" styleClass="fieldSelect" disabled="true">
                        <logic:present name="BStatus">
                        <html:options collection="BStatus" property="id" labelProperty="name"/>
                        </logic:present>
                </html:select>
         </span>
        </td>
            <td nowrap="nowrap"    ><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/></td>
            <td  nowrap="nowrap"   >
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
                <td nowrap="nowrap"   ><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/></td>
                <td  nowrap="nowrap">
                   <span id="tdcomboViaId">
                    <html:select name="docssend" property="viaId" styleClass="fieldSelect" >
                    <logic:present name="BVias">
                    <html:options collection="BVias" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>
                    </span>
                </td>
                <td nowrap="nowrap"   ><bean:message key="form.docs.address" bundle="<%=interfaces%>"/></td>
                <td  nowrap="nowrap" >
                    <html:text name="docssend" property="address" style="width:145px;" maxlength="1000"/>
                </td>
        </tr>
        <tr>
                <td nowrap="nowrap"  ><Strong><bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/></strong></td>
                <td  id="IDdeadLine" nowrap="nowrap" >
                <input type="text" name="deadLine" id="deadLine" style="width:80px;" value="<bean:write name="docssend" property="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;">
                </td>
                <td nowrap="nowrap"  >
                   <Strong><bean:message key="form.docs.classify" bundle="<%=interfaces%>"/></Strong>
                </td>
                <td    >
               
               <input type="hidden" name="arrayClassify" id="arrayClassify" value="<bean:write name="arrayClassify" />" />
                    <span id="tdcomboClassifyId" >
                        <html:select name="docssend" property="classifyId" onchange="javascript:onchangeClassify(this)" styleClass="fieldSelect" >
                        <html:option value="0"><bean:message key="action.select" bundle="<%=interfaces%>"/></html:option>
                        <logic:present name="BClassify">
                        <html:options collection="BClassify" property="id" labelProperty="name"/>
                        </logic:present>
                        </html:select>
                    </span>
                </td>
        </tr>
         <tr>
         
         
  <tr>
    <td  style="vertical-align:top;" >
        <bean:message key="doc.addNew.docReference.cmd.caption" bundle="<%=interfaces%>"/>
    </td>
    <td      style="vertical-align:top;" >
    <jsp:include page="/doc/docssend/reference.jsp" />
        
    </td>
    <td    style="vertical-align:top;"  >
    <bean:message key="form.docs.fromId.to" bundle="<%=interfaces%>"/>
    </td>
    <td    style="vertical-align:top;"  >
        <jsp:include page="/doc/docssend/fromForm.jsp" />
    </td>
  </tr>
   <tr>
  <td  colspan="2"    style="vertical-align:top;" >
            <logic:notEmpty name="BDocsReference">
            <logic:iterate name="BDocsReference" id="bean" type="com.form.doc.docsrecv.FDocsrecv">                       
            <div>  <input type="checkBox" checked name="docsId" id="docsId" value="<%=bean.getId()%>">
                                 <Strong><bean:write name="bean" property="docCode" /></strong>:<bean:write name="bean" property="abstracts" />
            </div>
            </logic:iterate>
            </logic:notEmpty>
            <div id='doc_1'></div>
  </td>
    <td colspan="2"    style="vertical-align:top;"  >
        <logic:notEmpty name="BFroms">
                <logic:iterate name="BFroms" id="bean" type="com.form.doc.from.FFrom">                       
                <div>  <strong><input type="checkBox" checked name="fromsId" id="fromsId" value="<%=bean.getId()%>"><bean:write name="bean" property="code" /></strong>:<bean:write name="bean" property="vnName" />
                </div>
                </logic:iterate>
                </logic:notEmpty>
                <div id='from_1'></div>
    </td>
   </tr>
   <tr>
       <td><bean:message key="form.docs.numberVersion" bundle="<%=interfaces%>"/></td>
       <td><html:text name="docssend" property="numberVersion" style="width:50px;"/></td>
       <td><bean:message key="form.docs.numberPage" bundle="<%=interfaces%>"/></td>
       <td><html:text name="docssend" property="numberPage" style="width:50px;"/></td>
   </tr>
   <tr>
                <td nowrap><strong><bean:message key="docs.download" bundle="<%=interfaces%>"/></strong></td>
                <td nowrap="nowrap" colspan="3"  class="tdcontent1" style="vertical-align:top;">                
                    <jsp:include page="/doc/docssend/upload.jsp" />
                </td>
              
      </tr>  
      
      <tr>                
                <td   class="tdcontent1" style="vertical-align:top;" colspan="4">
                    <logic:equal name="docsrecv" property="id" value="0">
                    <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_SCANNER" rel="{handler: 'iframe', size: {x: 450, y: 430},bookmark:'postAjax(\'docsrecv\',\'fileScans\',anchor + \':_GET_FILESCANS\')'}">
                    <html:button   property="_SCANNER">
                    <bean:message key="doc.scans.button.caption" bundle="<%=interfaces%>"/>
                    </html:button>
                    </a>            
                    </logic:equal>
                    <div  id="fileScans"><jsp:include page="/doc/draft/scans.jsp" /></div>
                </td>
      </tr>  
       <tr>
            <td  class="tdcontent" style="vertical-align:top"><bean:message key="docs.filePublic.send" bundle="<%=interfaces%>"/></td>
            <td class="tdcontent" style="vertical-align:top">                
                <html:checkbox  name="docssend" property="filePublic" value="1"/> 
            </td>
             <% if (com.inf.IKey.DOC_STORE_DOCSEND_DOCRECV>0) {%>           
                <td  class="tdcontent" style="vertical-align:top"><bean:message key="docs.filePublic.store.send" bundle="<%=interfaces%>"/></td>
                <td class="tdcontent" style="vertical-align:top">                
                    <html:checkbox  name="docssend" property="storeIdDocInput" value="1"/> 
                </td>
           
            <%}%>
        </tr>
         <tr>
            <td  class="toolCmd" style="text-align:left" colspan="4">                                 
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>" value="1" >                
                                    <logic:notEqual name="docssend" property="id" value="0">
                                        <html:button property="_EDIT"  styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_EDIT')}" >
                                            <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                       </html:button>                    
                                    </logic:notEqual>
                                    <logic:equal name="docssend"   property="id" value="0">
                                        <html:button property="_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docssend',anchor + ':_CREATE')}" >
                                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>                        
                                        </html:button>
                                    </logic:equal>
                        </logic:equal>                        
                        <jsp:include page="/doc/docssend/cmdSend.jsp" />                       
            </td>
        </tr> 
    </table>
       </fieldset>    
</td>
    </tr>
</table>
    
