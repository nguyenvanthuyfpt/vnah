<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docReviewSend" style="margin-top:0px" method="post" enctype="multipart/form-data">
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<tr>
      <th  nowrap width="5%" ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
      <th  nowrap width="5%" style="text-align: center;"><img src="<%=contextPath%>/images/attach.gif" /></th>
      <th  nowrap><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/></th>
      <th  nowrap><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/></th>
      <th  nowrap width="10%"><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/></th>
      <th  nowrap><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/></th>
      <th  nowrap width="7%"><bean:message key="form.docs.classify.title" bundle="<%=interfaces%>"/></th>
      <th  nowrap ></th>
</tr>
<tr>
      <td colspan="2" class="tdcontent1"  nowrap >
         <div  id="trSearch1" class="tdSearchHidden"><strong><bean:message key="cmd.search" bundle="<%=interfaces%>"/></strong></div>
      </td>
      <td nowrap class="tdcontent1"><div id="trSearch2" class="tdSearchHidden"><input name="docCode" id="docCode" class="noBorder"  onkeydown="if(event.keyCode==13){post('docReviewSend',anchor+':_SEARCH');return false;}" /></div></td>
      <td nowrap class="tdcontent1"><div id="trSearch3" class="tdSearchHidden"><input name="abstracts" id="abstracts" class="noBorder" onkeydown="if(event.keyCode==13){post('docReviewSend',anchor+':_SEARCH');return false;}" /></div></td>
      <td nowrap class="tdcontent1"><div id="trSearch4" class="tdSearchHidden"><input name="docDate" id="docDate" class="noBorder" maxlength="10" size="10" onkeydown="if(event.keyCode==13){post('docReviewSend',anchor+':_SEARCH');return false;}" /></div></td>
      <td nowrap class="tdcontent1"><div id="trSearch5" class="tdSearchHidden"><input name="signer" id="signer" class="noBorder" onkeydown="if(event.keyCode==13){post('docReviewSend',anchor+':_SEARCH');return false;}" /></div></td>
      <td nowrap class="tdcontent1" colspan="2"><div id="trSearch6" class="tdSearchHidden"></div></td>
</tr>
<html:hidden name="docssend" property="pageIndex" />
<logic:present name="BSearch" >
<input type="hidden" name="temp" value="" > 
<input type="hidden" name="temp2" value="" > 
<input type="hidden" name="readed" id="readed" value="1" />
<html:hidden  name="docssend" property="obServer"/>
<bean:define name="BSearch" id="beans" type="com.form.FBeans"/>
<% int i = beans.getFirstRecord();%>
<logic:iterate name="BSearch" id="bean" type="com.form.doc.docssend.FDocssend">
  <tr class="<%=(i%2==0)?"content1":"content"%>">
    <td  align="center" ><span class="index"><%=i++%></span></td>
     <td  align="center">
              <bean:define name="bean" property="allFiles" id="beanFiles111" type="com.form.FBeans"/>  
                <% if (bean.getAllFiles().size()>0 && ((com.form.doc.docssend.FFilesSend)beanFiles111.get(0)).getBlockFile()==0) {%>
                <% if (bean.getAllFiles().size()>1) {%>
                        <img src="images/attach.gif"  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_FILEDINHKEM:id:<%=bean.getId()%>');" />
                <%}else{%>
                     <logic:iterate name="bean" property="allFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">  
                          <logic:notEqual name="bean" property="docCode" value="">
                          <a href="javascript:post('docssend',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
                                 <img src="images/attach.gif" border="0"/>
                          </a>
                          </logic:notEqual>
                          
                          <logic:equal name="bean" property="docCode" value="">
                          <a href="javascript:post('docssend',anchor + '<%=beanFiles.getFileName().indexOf("doc",beanFiles.getFileName().length()-3)>=0?":_DOWNLOAD_DRAFT":":_DOWNLOAD"%>:docId:<bean:write name="bean" property="id"/>');remove('docssend',anchor);remove('docssend','docId');" >
                                 <img src="images/attach.gif" border="0"/>
                          </a>
                          </logic:equal>
                     </logic:iterate>
                                
                <%}}%>
    </td>
    <td   style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" nowrap>

            <div style="float:left">            
                    <logic:notEqual name="bean" property="docCode" value="">
                        <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                            <a title="<%=bean.getStatusName()%>" class="linkDoc" href="javascript:showTag('removeId<%=bean.getId()%>',<%=bean.getId()%>,<%=bean.getForYouId()%>)">
                            <bean:write name="bean" property="docCode" /></a>
                        <%}else{%>
                            <bean:write name="bean" property="docCode" />
                        <%}%>
                    </logic:notEqual>
                    <logic:equal name="bean" property="docCode" value="">
                        <bean:message key="line.space.none" bundle="<%=interfaces%>"/>
                    </logic:equal>
             </div>

            <div style="float:right"> 
            <logic:equal name="bean" property="docCode" value="">
                <logic:notPresent name="tracking" >
                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>"  value="1" >
                               <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_PREPARED_EMIT&id=<%=bean.getId()%>&type=2" rel="{handler: 'iframe', size: {x:640, y:370},bookmark:'if(SqueezeBox.presets.target==0){ post(\'docssend\',anchor + \':_VIEW\'); }'}">
                                <input align="left" style="font-size:9px;" type="button" value="<bean:message key="cmd.docssend.docssend" bundle="<%=interfaces%>"/>" />
                                </a>
                    </logic:equal>
                </logic:notPresent>
            </logic:equal>
            </div>
    </td>
    <td  style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;">
            
            <logic:notEqual name="bean" property="forYouId" value="0">
                            [ <a href="#" onmouseover="javascript:checkedInnerHtml();addthis_open(this,'N&#7897;i dung &#7911;y quy&#7873;n','','', '');postAjax('docssend','at_share',anchor + ':_TIP_FORYOU:forYouId:<bean:write name="bean" property="forYouId"/>');"  ><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a> ]
            </logic:notEqual>
            <%if(me.isRole(com.inf.IRoles.rLEADER)){%>
                <a title="<%=bean.getStatusName()%>" class="linkDoc" href="javascript:showTag('removeId<%=bean.getId()%>',<%=bean.getId()%>,<%=bean.getForYouId()%>)">
                <bean:write name="bean" property="abstracts" />
                </a>
            <%}else{%>
                <bean:write name="bean" property="abstracts" />
            <%}%>
      
    </td>
    <td  style="text-align: center;font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;"><bean:write name="bean" property="docDate" /></td>
    <td  style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;" nowrap><bean:write name="bean" property="signer" /></td>
    <td  align="center" style="font-weight:<%=(bean.getReaded()==0?"bold":"normal")%>;text-align: center;" nowrap>
          <span id="classifyName<%=bean.getId()%>">
           <logic:notEmpty name="bean" property="classifyName"  >
                   <bean:write name="bean" property="classifyName"/>
           </logic:notEmpty>
            <logic:empty name="bean" property="classifyName"  >
                   --
            </logic:empty>
            </span>
    </td>
    
    <td  align="center"  nowrap>
        <% if (com.inf.doc.IKeyDoc.DOC_CLASSIFY_ACTIVE==1) {%> 
        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkClassify" value="1">
                    <img style="border:0px" src="<%=contextPath%>/images/classify.gif" title="<bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_UPDATE_CLASSIFY:id:<bean:write name="bean" property="id"/>');">
        </logic:equal>
      <%}%>
      
      
      <logic:equal name="docssend" property="obServer"  value="1">        
         <% if (bean.getAllFiles().size()>0) {%>  
          <logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSSEND_BLOCKFILE%>"  value="1">              
            <% if (bean.getAllFiles().size()>0 && ((com.form.doc.docssend.FFilesSend)beanFiles111.get(0)).getBlockFile()==0){ %>
                <img style="border:0px" width="15px" height="15" src="<%=contextPath%>/images/security_f2.png" title="<bean:message key="doc.block.file.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('docssend',anchor + ':_BLOCK_FILE:id:<%=bean.getId()%>:blockFile:1')">    
            <%}else if (bean.getAllFiles().size()>0 && ((com.form.doc.docssend.FFilesSend)beanFiles111.get(0)).getBlockFile()==1){ %>
                    <img style="border:0px" width="15px" height="15" src="<%=contextPath%>/images/security_f2.png" title="<bean:message key="doc.unBlock.file.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('docssend',anchor + ':_BLOCK_FILE:id:<%=bean.getId()%>:blockFile:0')">    
            <%}%>
          </logic:equal>
      <%}%>
        <logic:equal name="<%=com.lib.AppConfigs.CHECK_OBSERVER_DOCSSEND_DELETE_DOCS%>"  value="1">
                <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docssend',anchor + ':_DELETE:id:<%=bean.getId()%>')">
        </logic:equal>
    </logic:equal>
        
        <logic:notEqual name="docssend" property="obServer"  value="1">    
            <logic:notEqual name="bean" property="statusId" value="-1">
                         <logic:equal name="bean" property="blockUpdate" value="1">
                                <logic:notPresent name="tracking" >
                                <logic:equal name="bean" property="docCode" value="" >
                                          <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docssend',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>:type:2')">
                                </logic:equal>
                                <logic:notEqual name="bean" property="docCode" value="" >
                                          <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docssend',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>:type:1')">                            
                                </logic:notEqual>
                                          <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docssend',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>')">
                                </logic:notPresent>
            </logic:equal>
            
            <logic:equal name="bean" property="blockUpdate" value="0">
                                <logic:notPresent name="tracking" >
                                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>"  value="1">
                                            <logic:equal name="bean" property="docCode" value="" >
                                                          <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docssend',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>:type:2')">
                                            </logic:equal>
                                            <logic:notEqual name="bean" property="docCode" value="" >
                                                          <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docssend',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>:type:1')">
                                            </logic:notEqual>
                                    <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('docssend',anchor + ':_DELETE:id:<%=bean.getId()%>')">
                                    </logic:equal>
                                    <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSSEND%>"  value="1">
                                        <logic:equal name="bean" property="docCode" value="" >
                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkUpdateDraft" value="1">
                                        <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:this.src='images/loading.gif';post('docssend',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>:type:2')">
                                        </logic:equal>
                                        </logic:equal>
                                    </logic:notEqual>
                                </logic:notPresent>
            </logic:equal>
            </logic:notEqual>
        </logic:notEqual>
    </td>
  </tr>
  <tr>
       <td class="detail"  id="removeId<%=bean.getId()%>" valign="top" colspan="8"  width="100%" ></td>
  </tr>
</logic:iterate> 
</logic:present>
</table>
</html:form>
