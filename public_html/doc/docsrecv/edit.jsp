<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td width="200px" valign="top" align="left" style="padding-right: 8px" >
              <jsp:include page="/doc/docsrecv/inforForm/listStatus.jsp" />   
              <jsp:include page="/doc/docsrecv/inforForm/listTrailer.jsp" />   
        </td>
        <td style="padding-right: 20px" valign="top" >
               <fieldset class="friendset" style>
                <legend class="LEGEND-DOCSFORM"><bean:message key="form.docs.recv.infor.caption" bundle="<%=interfaces%>"/></legend> 
                <table class="tableForm" cellpadding="0" width="97%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
                      <tr>
                        <td  nowrap="nowrap" width="70px"><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</td>
                        <td  align="left" width="100px">
                            <html:select name="docsrecv" property="formId" styleClass="fieldSelect" onchange="postAjax('docsrecv','iddonvi',anchor + ':_VIEW_FROM');postAjax('docsrecv','idDoccode',anchor + ':_VIEW_CODE')">
                                <logic:present name="BForms">
                                 <html:options collection="BForms" property="id" labelProperty="name"/>
                                 </logic:present>
                          </html:select>
                         </td>
                        <td nowrap="nowrap" width="70px"><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>:</td>
                        <td align="left"  nowrap>
                        <span id="idDossiers">
                            <html:select name="docsrecv" property="dossierId" styleClass="fieldSelect" >
                                <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
                                <logic:present name="BDossiers">
                                 <html:options collection="BDossiers" property="id" labelProperty="name"/>
                                 </logic:present>
                            </html:select>
                        </span>
                         <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_DOSS_RECV" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_RECV\')'}">   
                           <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">                                
                        </a>
                        </td>
                      </tr>
                    <tr>
                        <td nowrap="nowrap"  ><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td align="left" nowrap="nowrap" >
                            <span id="idDoccode">
                            <jsp:include page="/doc/docsrecv/localCode1.jsp" />     
                            </span>
                        </td>
                         <td nowrap="nowrap"   ><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td align="left" nowrap="nowrap" >        
                         <input type="text" name="localDate" id="localDate" style="width:80px;" value="<bean:write name="docsrecv" property="localDate"/>" style="width:100px;"  /><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'localDate','dd/mm/yyyy');return false;"></td>
                       
                    </tr>
                    
                    <tr>
                         <td nowrap="nowrap"  ><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td align="left" nowrap="nowrap" ><html:text name="docsrecv" property="docCode" maxlength="50" style="width:147px"/></td>
                        <td nowrap="nowrap"  ><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td align="left"  nowrap="nowrap">        
                        <input type="text" name="docDate" id="docDate" style="width:80px;"  value="<bean:write name="docsrecv" property="docDate"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'docDate','dd/mm/yyyy');return false;"></td>
                    </tr>
                     
                      <tr>
                        <td nowrap="nowrap"   ><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td colspan="3" align="left"   nowrap="nowrap">
                            <html:textarea name="docsrecv" property="abstracts"  style="width:480px;height:60px"/>
                        </td>
                      </tr>
                      
                       <tr> 
                              
                                
                                <td nowrap="nowrap"  ><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/>:</td>
                                <td align="left" nowrap="nowrap" >
                                <html:select name="docsrecv" property="docsTypeId" styleClass="fieldSelect" >
                                <logic:present name="BDocTypes">
                                <html:options collection="BDocTypes" property="idDocType" labelProperty="nameDocType"/>
                                </logic:present>
                                </html:select>
                                </td>
                               <td nowrap="nowrap" class="tdcontent1"><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/>:</td>
                                <td align="left" class="tdcontent1">
                                   <jsp:include page="/doc/docsrecv/storeArea.jsp" />
                                </td>
                      </tr>  
                      <tr>
                          <td align="left" colspan="2"  class="tdcontent1" style="vertical-align:top;" >
                                  
                          </td>
                            <td colspan="2"  class="tdcontent1" style="vertical-align:top;" align="left" >
                                    <logic:notEmpty name="BSearchStore">
                                        <logic:iterate name="BSearchStore" id="beanD" type="com.form.admin.departments.FDepartment">                       
                                        <div> <Strong><input type="checkBox" checked name="storesId" id="storesId" value="<%=beanD.getId()%>"><bean:write name="beanD" property="code" /></strong>:<bean:write name="beanD" property="name" />
                                        </div>
                                        </logic:iterate>
                                        </logic:notEmpty>
                                        <div id='storeArea_1'></div>
                            </td>
                   </tr>
                        
                          <tr>
                                
                                <td nowrap="nowrap"  ><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/>:</td>
                                <td align="left"  nowrap="nowrap">
                                  <span id="tdcomboSecureId">
                                        <html:select name="docsrecv" property="secureId" styleClass="fieldSelect" >
                                        <logic:present name="BSecures">
                                        <html:options collection="BSecures" property="id" labelProperty="name"/>
                                        </logic:present>
                                        </html:select>
                                    </span>
                                </td>
                                 <td nowrap="nowrap"  ><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/></td>
                                <td align="left"   nowrap="nowrap">
                                  <span id="tdcomboExpressId">
                                <html:select name="docsrecv" property="expressId" styleClass="fieldSelect" >
                                <logic:present name="BExpresss">
                                <html:options collection="BExpresss" property="id" labelProperty="name"/>
                                </logic:present>
                                </html:select>
                                </span>
                                </td>
                        </tr>
                        
                       <tr>
                      
                        <td nowrap="nowrap"  ><bean:message key="form.docs.fromId" bundle="<%=interfaces%>"/>:</td>
                        <td align="left" nowrap="nowrap"  >
                       <span id="iddonvi">
                                <html:select name="docsrecv" property="fromId" styleClass="fieldSelect" >
                                <logic:present name="BFroms">
                                <html:options collection="BFroms" property="id" labelProperty="vnName"/>
                                </logic:present>
                                </html:select>
                        </span>
                      <%  if (request.getSession().getAttribute("01.06")!=null){ %>
                         <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_FROM" rel="{handler: 'iframe', size: {x: 370, y: 300},bookmark:'postFroms()'}">   
                       <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="form.docs.froms.caption" bundle="<%=interfaces%>"/>">                                    
                       </a>
                          <%}%>    
                       
                        </td>
                        <td nowrap="nowrap"  ><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/>:</td>
                        <td align="left" nowrap="nowrap" >
                                 <span id="tdcomboViaId">
                                <html:select name="docsrecv" property="viaId" styleClass="fieldSelect" >
                                <logic:present name="BVias">
                                <html:options collection="BVias" property="id" labelProperty="name"/>
                                </logic:present>
                                </html:select>
                                </span>
                        </td>
                    </tr>
                     <% if (com.inf.IKey.DOC_FIELDS_OPEN>0) {%>
                      <tr>
                                 <td nowrap="nowrap"  ><bean:message key="form.docs.statusId" bundle="<%=interfaces%>"/>:</td>
                        <td align="left"  nowrap="nowrap">
                        <html:select name="docsrecv" property="statusId" styleClass="fieldSelect"  disabled="true">
                                        <logic:present name="BStatus">
                                        <html:options collection="BStatus" property="id" labelProperty="name"/>
                                        </logic:present>
                                </html:select>
                        </td>
                        <td nowrap="nowrap"><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></td>
                        <td align="left" nowrap="nowrap">
                                <html:text name="docsrecv" style="width:125px" property="signer" styleId="signer" maxlength="1000"/>
                                <input style="height:18px" type="button" value="..." onclick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="doc.sign.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docsrecv','at_share',anchor + ':_VIEW_LIST_TILE');addthis_none();" />
                        </td>
                        </tr>
                      <tr>
                                 
                       
                        </tr>
                      <tr>
                                
                                <td nowrap="nowrap"><bean:message key="form.docs.address" bundle="<%=interfaces%>"/>:</td>
                                <td  align="left" nowrap="nowrap"><html:text name="docsrecv" property="address" style="width:147px;" maxlength="1000"/></td>
                                <td nowrap="nowrap"><bean:message key="form.docs.numberVersion" bundle="<%=interfaces%>"/></td>
                                <td align="left" nowrap="nowrap"> 
                                     <html:text name="docsrecv" property="numberVersion" style="width:40px"/>  
                                </td>
                      </tr>
                      
                      <tr>
                                
                                <td nowrap="nowrap"  ><bean:message key="form.docs.branch" bundle="<%=interfaces%>"/>:</td>
                                <td  align="left" nowrap="nowrap" >
                                         <span id="tdComboOption"> 
                                            <html:select name="docsrecv" property="branchId" styleClass="fieldSelect" >
                                            <html:option value="0"><bean:message key="action.select" bundle="<%=interfaces%>"/></html:option>
                                            <logic:present name="BBRanch">
                                            <html:options collection="BBRanch" property="id" labelProperty="name"/>
                                            </logic:present>
                                            </html:select>
                                        </span>
                                </td>
                                <td nowrap="nowrap">
                                            <bean:message key="form.docs.numberPage" bundle="<%=interfaces%>"/>
                                </td>
                                  <td nowrap="nowrap">
                                            <html:text name="docsrecv" property="numberPage" style="width:40px"/>
                                </td>
                      </tr>
                      
                      <TR>
                        <td nowrap="nowrap"  ><strong><bean:message key="form.docs.deadLine" bundle="<%=interfaces%>"/></strong></td>
                        <td align="left" id="IDdeadLine" nowrap="nowrap" ><input type="text" name="deadLine" id="deadLine" style="width:80px;"   value="<bean:write name="docsrecv" property="deadLine"/>"/><img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'deadLine','dd/mm/yyyy');return false;"></td>
                        <td nowrap="nowrap" valign="top"><strong><bean:message key="form.docs.classify" bundle="<%=interfaces%>"/></Strong></td>
                        <td nowrap="nowrap"  >
                               <span id="tdcomboClassifyId" >   
                                    <input type="hidden" name="arrayClassify" id="arrayClassify" value="<bean:write name="arrayClassify" />" />                    
                                    <html:select name="docsrecv" property="classifyId" onchange="javascript:onchangeClassify(this)" styleClass="fieldSelect" >
                                    <html:option value="0"><bean:message key="action.select" bundle="<%=interfaces%>"/></html:option>
                                    <logic:present name="BClassify">
                                    <html:options collection="BClassify" property="id" labelProperty="name"/>
                                    </logic:present>
                                    </html:select>
                                </span>
                        </td>
                      </tr>
                      <%}%>
                        <% if (com.inf.IKey.DOC_CLASSIFY_ACTIVE>0) {%>
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkReview" value="1">     
                        <tr>
                            <td  nowrap="nowrap">
                                <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRebcaption" value="">
                                <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRebcaption"/> :
                                </logic:notEqual>
                                <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkRebcaption" value="">
                                <bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> :
                                </logic:equal>
                            </td>
                            <td class="tdcontent1" colspan="3" nowrap="nowrap">
                                 <textarea name="title" id="title" style="width:480px;height:60px" ></textarea>                
                            </td>
                        </tr>
                        </logic:equal>
                        <%}%>
                         <% if (com.inf.IKey.DOC_STORE_DOCSEND_DOCRECV>0) {%>
                          <tr>
                                <td  class="tdcontent" style="vertical-align:top"><bean:message key="docs.filePublic.store.send" bundle="<%=interfaces%>"/></td>
                                <td class="tdcontent" style="vertical-align:top" colspan="3">                
                                    <html:checkbox  name="docsrecv" property="storeIdDocInput" value="1"/> 
                                </td>
                         </tr>
                        <%}%>
                        <tr>
                        <td nowrap="nowrap" valign="top"><strong><bean:message key="docs.download" bundle="<%=interfaces%>"/></strong></td>
                        <td  colspan="2" align="left" valign="top" nowrap="nowrap"  style="vertical-align:top;">
                                   <jsp:include page="/doc/docsrecv/upload.jsp" />
                        </td>
                        <td  nowrap="nowrap"  valign="top" style="vertical-align:top;">    
                        <logic:equal name="docsrecv" property="id" value="0">
                            <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_SCANNER" rel="{handler: 'iframe', size: {x: 450, y: 430},bookmark:'postAjax(\'docsrecv\',\'fileScans\',anchor + \':_GET_FILESCANS\')'}">
                            <html:button   property="_SCANNER">
                                <bean:message key="doc.scans.button.caption" bundle="<%=interfaces%>"/>
                            </html:button>
                          </a>            
                        </logic:equal>
                       <div  align="left"   id="fileScans">
                              <jsp:include page="/doc/draft/scans.jsp" />            
                       </div>
                        </td>
                       
                        </tr> 
                       
                        <tr>
                            <td nowrap="nowrap" class="toolCmd" style="padding-left:10px"  colspan="4">                
                                    <jsp:include page="/doc/docsrecv/cmd.jsp" />
                            
                            </td>
                        </tr> 
                </table>  
                 </fieldset>
        </td>
    </tr>
</table>
    

     

