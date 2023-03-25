<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docReviewRecv" style="margin-top:0px"  method="post" enctype="multipart/form-data">
<table  class="list-voffice"  cellpadding="0" cellspacing="0" border="0" width="100%" >
<tr>
        <th width="5%" nowrap ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
        <th width="5%" nowrap style="text-align: center;"><img src="<%=contextPath%>/images/attach.gif" /></th>
        <th width="10%" nowrap ><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/></th>
        <th width="25%" nowrap ><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/></th>
        <th width="10%" nowrap ><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/></th>
        <th width="15%" nowrap ><bean:message key="form.docs.fromId2" bundle="<%=interfaces%>"/></th>
        <th width="7%" nowrap ><bean:message key="form.docs.classify.title" bundle="<%=interfaces%>"/></th>
        <th width="5%" nowrap >&nbsp;</th>
</tr>
<tr>
              <td colspan="2"  nowrap class="tdcontent1" ><span  id="trSearch1"    class="tdSearchHidden">
                    <strong><bean:message key="cmd.search" bundle="<%=interfaces%>"/></strong></span>
              </td>
              <td   nowrap class="tdcontent1"><span id="trSearch2" class="tdSearchHidden"><input name="docCode" id="docCode"  class="noBorder" onkeydown="if(event.keyCode==13){post('docReviewRecv',anchor+':_SEARCH');return false;}" /></span></td>
              <td   nowrap class="tdcontent1"><span id="trSearch3" class="tdSearchHidden"><input name="abstracts" id="abstracts"  class="noBorder" onkeydown="if(event.keyCode==13){post('docReviewRecv',anchor+':_SEARCH');return false;}" /></span></td>
              <td   nowrap class="tdcontent1"><span id="trSearch4" class="tdSearchHidden"><input name="docDate" id="docDate"  class="noBorder" maxlength="10"  onkeydown="if(event.keyCode==13){post('docReviewRecv',anchor+':_SEARCH');return false;}" /></span></td>
              <td   nowrap class="tdcontent1"><span id="trSearch5" class="tdSearchHidden"><input name="fromVnName" id="fromVnName"  class="noBorder" onkeydown="if(event.keyCode==13){post('docReviewRecv',anchor+':_SEARCH');return false;}" /></span></td>
              <td   nowrap class="tdcontent1" colspan="2"><span id="trSearch6" class="tdSearchHidden"></span></td>
</tr>
<html:hidden name="docsrecv" property="pageIndex" />
<logic:present name="BSearch" >
<input type="hidden" name="temp" value="" > 
<input type="hidden" name="temp2" value="" > 
<input type="hidden" name="readed" id="readed" value="1" />
<html:hidden  name="docsrecv" property="obServer"/>
<bean:define name="BSearch" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
<logic:iterate name="BSearch" id="bean" type="com.form.doc.docsrecv.FDocsrecv">
 <tr class="<%=(i%2==0)?"content1":"content"%>">
            <td align="center" ><span class="index"><%=i++%></span></td>
             <td  align="center" valign="top">
              <bean:define name="bean" property="allFiles" id="beanFiles111" type="com.form.FBeans"/>  
             <% if (bean.getAllFiles().size()>0 && ((com.form.doc.docsrecv.FFilesRecv)beanFiles111.get(0)).getBlockFile()==0) {%>
              <%  if (bean.getAllFiles().size()>1) {%>
                <img src="images/attach.gif"  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docsrecv','at_share',anchor + ':_FILEDINHKEM:id:<%=bean.getId()%>');" />
               <%}else{%>
               <logic:iterate name="bean" property="allFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">  
                    <a href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" >
                        <img src="images/attach.gif" border="0"/>
                   </a>
                </logic:iterate>
               <%}}%>
            </td>
            
            <td  valign="top"   style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" >
                <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                    <a title="<%=bean.getStatusName()%>" class="linkDoc" href="javascript:showTag('removeId<%=bean.getId()%>',<%=bean.getId()%>,<%=bean.getForYouId()%>)">
                    <bean:write name="bean" property="docCode"/>
                    </a>
                <%}else{%>
                    <bean:write name="bean" property="docCode"/>
                <%}%>
            </td>
            <td  style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" valign="top">
                 <logic:notEqual name="bean" property="forYouId" value="0">
                        [ <a href="#" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docsrecv','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');"  ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
                    </logic:notEqual>
                <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                <a title="<%=bean.getStatusName()%>" class="linkDoc" href="javascript:showTag('removeId<%=bean.getId()%>',<%=bean.getId()%>,<%=bean.getForYouId()%>)">
                    <bean:write name="bean" property="abstracts"/>
                </a>
                <%}else{%>
                 <bean:write name="bean" property="abstracts"/>
                <%}%>
               
            </td>
            <td  style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;text-align: center;" valign="top">
                <bean:write name="bean" property="docDate"/>
            </td>
            <td  style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" valign="top" >
                <bean:write name="bean" property="fromVnName"/>
            </td>
            <td style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;text-align: center;" valign="top" nowrap>              
              <span id="classifyName<%=bean.getId()%>">
                <logic:notEmpty name="bean" property="classifyName">
                   <bean:write name="bean" property="classifyName"/>
                </logic:notEmpty>
                <logic:empty name="bean" property="classifyName" >
                   --
                </logic:empty>
                </span>
            </td>
            
            
            
            <td  align="center" nowrap  valign="top">
          <% if (com.inf.doc.IKeyDoc.DOC_CLASSIFY_ACTIVE==1) {%> 
                 <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkClassify" value="1">
                        <img style="border:0px" src="<%=contextPath%>/images/classify.gif" title="<bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>','','', '');postAjax('docsrecv','at_share',anchor + ':_UPDATE_CLASSIFY:id:<bean:write name="bean" property="id"/>');">
                 </logic:equal>
             <%}%>
             
            
              <logic:equal name="docsrecv" property="obServer"  value="1">
                   <% if (bean.getAllFiles().size()>0) {%>  
                      <logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV_BLOCKFILE%>"  value="1">              
                           <% if (bean.getAllFiles().size()>0 && ((com.form.doc.docsrecv.FFilesRecv)beanFiles111.get(0)).getBlockFile()==0){ %> 
                                <img style="border:0px" width="15px" height="15" src="<%=contextPath%>/images/security_f2.png" title="<bean:message key="doc.block.file.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('docsrecv',anchor + ':_BLOCK_FILE:id:<%=bean.getId()%>:blockFile:1')">    
                           <%}else if (bean.getAllFiles().size()>0 && ((com.form.doc.docsrecv.FFilesRecv)beanFiles111.get(0)).getBlockFile()==1) {%>  
                                <img style="border:0px" width="15px" height="15" src="<%=contextPath%>/images/security_f2.png" title="<bean:message key="doc.unBlock.file.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('docsrecv',anchor + ':_BLOCK_FILE:id:<%=bean.getId()%>:blockFile:0')">    
                           <%}%>
                      </logic:equal>
                   <%}%>  
                     <logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSRECV_DELETE_DOCS%>" value="1">                        
                                <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docsrecv',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
                    </logic:equal>
           </logic:equal>
            
            <logic:notEqual name="docsrecv" property="obServer"  value="1"> 
             
                    <logic:notEqual name="bean" property="statusId" value="-1">
                        <logic:notEqual name="bean" property="views" value="1">
                          
                                <logic:equal name="bean" property="blockUpdate" value="1">
                                        <logic:notPresent name="tracking" > 
                                        <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docsrecv',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                        <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docsrecv',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">                        
                                        </logic:notPresent>
                                </logic:equal>
                                
                                <logic:equal name="bean" property="blockUpdate" value="0">
                                    <logic:notPresent name="tracking" > 
                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSRECV%>" value="1">
                                            <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docsrecv',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                            <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docsrecv',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
                                        </logic:equal>
                                    </logic:notPresent>
                                </logic:equal>
                            
                          </logic:notEqual>      
                      </logic:notEqual>   
               </logic:notEqual>    
              
            </td>
  </tr>
  <tr>
        <td class="detail" colspan="8" id="removeId<%=bean.getId()%>" width="100%" ></td>
  </tr>
  
</logic:iterate> 
</logic:present>
</table>
</html:form>
