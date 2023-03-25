<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="docsrecv" property="id" id="id" />
             
             <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkStoreDrapt" value="1">
                <logic:equal name="<%=com.lib.AppConfigs.CHECK_CREATOR_DOCSRECV%>" value="1" >
                            <logic:notEqual name="docsrecv" property="id" value="0">
                                <html:button property="_EDIT" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docsrecv',anchor + ':_EDIT')}" >                 
                                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                </html:button>                 
                            </logic:notEqual>
                </logic:equal>
                       
                 <logic:equal name="docsrecv" property="id" value="0">
                                    <html:button property="_CREATE" styleClass="button" onclick="javascript:if(checkSubmit(this.form)) {this.disabled=true;post('docsrecv',anchor + ':_CREATE')}" >                 
                                    <bean:message key="action.prepared.store" bundle="<%=interfaces%>"/>
                                    </html:button>                 
                </logic:equal>
            </logic:equal>
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkSelectRecv" value="1">
                        
                        <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV&id=<%=id%>" rel="{handler: 'iframe', size: {x:390, y: 345},bookmark:'if(SqueezeBox.presets.target==0){post(\'docsrecv\',anchor + \':_PREPARED_CREATE_AND_CREATE_DOC\');}'}">
                            <html:button property="_PREPARE_CHOSE_RECV" styleClass="button" style="width:120px;" >
                            <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                            </html:button>
                        </a>
                        
            </logic:equal>
            
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkDirect" value="1">
                    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) { post('docsrecv',anchor+':_DOC_ASSIGN_CREATE') };" >
                             <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                                      <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" />
                            </logic:notEmpty>
                            <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                                     <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                            </logic:empty>
                        
                        
                    </html:button>
                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkSelectDept" value="1">
                                 <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkSelectDept" value="1">
                                      <logic:present name="BDepartments">
                                      <html:select styleClass="inputbox"  name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="departmentId">           
                                        <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
                                            <html:options collection="BDepartments" property="id" labelProperty="name"/>                                         
                                    </html:select> 
                                     </logic:present>
                                 </logic:equal>
                    </logic:equal>       
            </logic:equal>
            
