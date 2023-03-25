<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<% int checkDetailt = 0,checkDocreference = 0; %>
<%String CHECK_RULE_DOCSRECV=com.lib.AppConfigs.CHECK_RULE_DOCSRECV;%>
<logic:notEqual name="BDocsrecvs" property="forYouId" value="0">
        <%CHECK_RULE_DOCSRECV="BRuleForYouRecv";%>
</logic:notEqual>
<bean:define name="BDocsrecvs" id="beanRecv"  type="com.form.doc.docsrecv.FDocsrecv" />
<bean:define name="<%=CHECK_RULE_DOCSRECV%>" id="beanRule"  type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocsrecvs" property="id" id="id" type="java.lang.Integer" />
   <logic:equal name="BDocsrecvs" property="readed" value="0">
          <logic:equal name="BDocsrecvs" property="checkForYou" value="0">            
            <%if (beanRule.getCheckDocreference()==1){%>
                <div style="float:right;padding-right:10px">
                    <input  type="button" value="<bean:message key="command.doc.review.anwear.send.caption" bundle="<%=interfaces%>"/>"  onclick="post('docssend',anchor + ':_PREPARED_CREATE:id:<%=id%>:type:2')" />
                </div>
            <%}%>
        </logic:equal>
    </logic:equal>
    
    <logic:notEqual name="BDocsrecvs" property="readed" value="0">
            <%if (beanRule.getCheckDocreference()==1 && beanRecv.getViews()==0){%>
                <div style="float:right;padding-right:10px">
                    <input  type="button" value="<bean:message key="command.doc.review.anwear.send.caption" bundle="<%=interfaces%>"/>"  onclick="post('docssend',anchor + ':_PREPARED_CREATE:id:<%=id%>:type:2')" />
                </div>
            <%}%>        
    </logic:notEqual>
    
<% if (beanRule.getCheckDetail()==1 || beanRecv.getObServer()==1){ %>
<div id="infor">
<logic:present name="BDocsrecvs" >
<table  style="border-collapse: collapse;" class="docDetail"   cellpadding="0" cellspacing="0" align="center" width="100%" >
                        <tr>
                            <td nowrap valign="top" ><Strong><bean:message key="form.docs.creator" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="creator"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.signer" bundle="<%=interfaces%>"/>:</strong></td>
                            <td><bean:write name="BDocsrecvs" property="signer"/></td>                            
                        </tr>
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.docCode" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="docCode"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.localCode" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="localCode"/></td>
                        </tr>
                         <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.timeCreate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="timeCreate"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.localDate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="localDate"/></td>
                        </tr>
                         
                         <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.formId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top">
                            <bean:write name="BDocsrecvs" property="dossiersName"/>
                                
                            </td>
                        </tr>
                        
                          <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.expressId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="expressName"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.secureId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="secureName"/></td>
                        </tr>
                        
                        <tr>
                            <td nowrap valign="top"><strong><bean:message key="form.docs.docsTypeId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="docTypeName"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.viaId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top"><bean:write name="BDocsrecvs" property="viaName"/></td>
                        </tr>
                        <tr>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.docDate" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top" ><bean:write name="BDocsrecvs" property="docDate"/></td>
                            <td nowrap valign="top"><Strong><bean:message key="form.docs.storeAgeId" bundle="<%=interfaces%>"/>:</strong></td>
                            <td nowrap valign="top" >
                                    <logic:notEmpty name="BDocsrecvs" property="stores">
                                     <logic:iterate name="BDocsrecvs" property="stores" id="beanStores" type="com.form.admin.departments.FDepartment">
                                           <div>
                                                     <bean:write name="beanStores" property="name" />
                                           </div>
                                    </logic:iterate>
                              </logic:notEmpty>                            
                            </td>                            
                        </tr>
                        <tr>
                            <td nowrap valign="top"><strong><bean:message key="form.docs.abstracts" bundle="<%=interfaces%>"/>:</strong></td>
                            <td valign="top"colspan="3" ><bean:write name="BDocsrecvs" property="abstracts"/></td>
                        </tr>
                        <logic:notEmpty name="BDocsrecvs" property="allFiles">
                        <bean:define name="BDocsrecvs" property="allFiles" id="beanFiles111" type="com.form.FBeans"/>  
                        <% if (beanFiles111.size()>0 && ((com.form.doc.docsrecv.FFilesRecv)beanFiles111.get(0)).getBlockFile()==0) {%>
                        <tr>
                            <td nowrap valign="top">
                            <Strong><bean:message key="docs.download" bundle="<%=interfaces%>"/>:</strong>
                            </td>
                            <td  valign="top"  colspan="3"  align="left" >
                                  <ol style="line-height:20px;">
                                    <logic:iterate name="BDocsrecvs" property="allFiles" id="beanFiles" type="com.form.doc.docsrecv.FFilesRecv">                       
                                        <li><A href="javascript:post('docsrecv',anchor + ':_DOWNLOAD:fileId:<bean:write name="beanFiles" property="idFiles"/>');remove('docsrecv',anchor);remove('docsrecv','fileId');" ><bean:write name="beanFiles" property="fileName" /></a></li>
                                    </logic:iterate>
                                    </ol>
                            </td>
                        </tr>  
                        <%}%>
                        </logic:notEmpty>
                        
                        </table>                     
</logic:present>

</div>
<%}%>
