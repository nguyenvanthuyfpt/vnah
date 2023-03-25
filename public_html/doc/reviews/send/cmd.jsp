<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:hidden name="docAssign" property="id" /> 
<bean:define name="docssend" property="id" id="id" />


<html:hidden name="docAssign" property="statusId" />
<html:hidden name="docAssign" property="dossierId" />
<html:hidden name="docAssign" property="views" />
<html:hidden name="docAssign" property="checkComman" /> 
<html:hidden name="docAssign" property="type"  />
<bean:define name="docAssign" property="type" id="type"  />
<bean:define name="docAssign" property="statusId" id="statusId" />
<bean:define name="docAssign" property="views" id="views" type="java.lang.Integer" />
<bean:define name="docAssign" property="dossierId" id="dossierId" type="java.lang.Integer" />
<div style="padding-bottom:5px;"><input type="checkbox" name="readed" id="readed" value="1" checked ><label for="readed">T&#7921; &#273;&#7897;ng chuy&#7875;n v&#7873; danh s&#225;ch c&#244;ng v&#259;n</label></div>
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkSelectRecv" value="1">
            <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV_FROM_INFOR&id=<%=id%>&type=<%=type%>" rel="{handler: 'iframe', size: {x:420, y: 500},bookmark:'if(SqueezeBox.presets.target==0){post(\'docReviewSend\',anchor + \':_PREPARED_CREATE\');}'}">
                 <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" style="width:100px;"  >
                            <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                </html:button>
            </a>
            </logic:equal>
            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkDirect" value="1">
                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkAssign" value="1">
                    
                    
                      <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="javascript:post('docReviewSend',anchor+':_DOC_ASSIGN_CREATE')" >
                                <logic:notEmpty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" >
                                  <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" />
                                </logic:notEmpty>
                                <logic:empty name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkTcaption" >
                                         <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                                </logic:empty>
                    </html:button> 
                    
                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkSelectDept" value="1">
                            <logic:present name="BDepartments">
                                <html:select styleClass="inputbox"  name="docAssign" property="departmentId">           
                                <html:option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></html:option>                               
                                <html:options collection="BDepartments" property="id" labelProperty="name"/>          
                                </html:select> 
                            </logic:present>
                    </logic:equal>
                    </logic:equal>
            </logic:equal>

      

         
            
  
 
