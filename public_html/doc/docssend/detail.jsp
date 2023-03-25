<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<% String CHECK_RULE_SEND = com.lib.AppConfigs.CHECK_RULE_DOCSSEND ; %>
<logic:notEqual name="BDocssends" property="forYouId" value="0">        
       <% CHECK_RULE_SEND = "BRuleForYou"; %>
</logic:notEqual>
<bean:define name="<%=CHECK_RULE_SEND%>"  id="beanRule" type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocssends"  id="beanSend" type="com.form.doc.docssend.FDocssend" />
<bean:define name="BDocssends" property="obServer"  id="obServer"  type="java.lang.Integer" /> 
 <% if (beanRule.getCheckDetail()==1 || beanSend.getObServer()==1){ %>
<logic:present name="BDocssends">
 <table  style="border-collapse: collapse" class="docDetail"  cellpadding="0" cellspacing="0" align="center" width="100%">
                        <tr>
                            <td nowrap valign="top" ><Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top" ><bean:write name="BDocssends" property="creator"/></td>
                            <td nowrap valign="top" ><Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top" ><bean:write name="BDocssends" property="signer"/></td>
                        </tr>
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="docCode"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="localCode"/></td>
                        </tr>
                         <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="timeCreate"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="localDate"/></td>
                        </tr>
                         
                         <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="formName"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="dossiersName"/></td>
                        </tr>
                        
                          <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="expressName"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="secureName"/></td>
                        </tr>
                        
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="docTypeName"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocssends" property="viaName"/></td>
                        </tr>
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td colspan="3" ><bean:write name="BDocssends" property="docDate"/></td>
                        </tr>
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>:</strong></td>
                            <td colspan="3" ><bean:write name="BDocssends" property="abstracts"/></td>
                        </tr>
                        <logic:notEmpty name="BDocssends" property="allFiles">
                        <bean:define name="BDocssends" property="allFiles" id="beanFiles111" type="com.form.FBeans"/>  
                        <% if (beanFiles111.size()>0 && ((com.form.doc.docssend.FFilesSend)beanFiles111.get(0)).getBlockFile()==0) {%>
                        <tr>
                          <td nowrap valign="top" >
                          <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/> :  </strong>
                          </td>
                            <td valign="top"   colspan="3" align="left" >
                                                <ol class="calassFile">
                                                <% String fileName = "";int id= 0,dem = 0; %>
                                                
                                                <logic:iterate name="BDocssends" property="allFiles" id="beanFiles" type="com.form.doc.docssend.FFilesSend">   
                                                <% if (!fileName.equals(beanFiles.getFileName())){  fileName = beanFiles.getFileName(); id = beanFiles.getIdFiles();dem =0; %>
                                                <li>
                                                
                                                <a href="javascript:post('docssend',anchor + ':_DOWNLOAD_DRAFT:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
                                                            <logic:equal name="beanFiles" property="description" value="">
                                                            <bean:write name="beanFiles" property="fileName" />
                                                            </logic:equal>
                                                            <logic:notEqual name="beanFiles" property="description" value="">
                                                                <bean:write name="beanFiles" property="description" />
                                                            </logic:notEqual>
                                                            (<bean:write name="beanFiles" property="numberReadedFile" />)
                                                                                    
                                                </a>
                                                 <%}else{ dem ++;
                                                        if (dem==1){
                                                  %>
                                                
                                                    <logic:equal name="BDocssends" property="type" value="2">
                                                    <logic:equal name="trackingInfor" value="0">
                                                    <img  style="border:0px;" title='<bean:message key="system.action.delete" bundle="<%=interfaces%>"/>' src="images/delete.png" onclick="javascript:post('docssend',anchor + ':_DELETE_FILE_INFOR:fileId:<bean:write name="beanFiles" property="idFiles"/>')">
                                                    </logic:equal>
                                                    </logic:equal>
                                                    <input  style="border:0px;cursor: pointer;"  type="button"   value=" + "  onclick="checkedInnerHtml();addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('docssend','at_share',anchor + ':_FILEDINHKEM_IN_INFOR:id:<bean:write name="docssend" property="id"/>:fileId:<%=id%>');"  />
                                                        </li>
                                                   <%}}%>
                                                </logic:iterate>
                                                </ol>
                            </td>
                        </tr>
                        <%}%>
                        </logic:notEmpty>
                        </table>
</logic:present>
<%}%>
