<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkSelectRecv" value="1">
             <bean:define name="docssend" property="id" id="id" />
             <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV&id=<%=id%>" rel="{handler: 'iframe', size: {x:390, y: 345},bookmark:'if(SqueezeBox.presets.target==0){post(\'docssend\',anchor + \':_PREPARED_CREATE_AND_CREATE_DOC\');}'}">
                 <html:button property="_PREPARED_CREATE_AND_CREATE_DOC" styleClass="button" style="width:120px;"  >
                 <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                 </html:button>
             </a>
</logic:equal>

<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkDirect" value="1">
            <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:if(checkSubmit(this.form)) { post('docssend',anchor+':_DOC_ASSIGN_CREATE') };" >
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
