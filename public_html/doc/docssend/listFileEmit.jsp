<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<% String CHECK_RULE_SEND = com.lib.AppConfigs.CHECK_RULE_DOCSSEND ; %>
<logic:notEqual name="BDocssends" property="forYouId" value="0">        
       <% CHECK_RULE_SEND = "BRuleForYou"; %>
</logic:notEqual>
<bean:define name="BDocssends" property="id"  id="idDoc" type="java.lang.Integer" />
<bean:define name="<%=CHECK_RULE_SEND%>"  id="beanRule" type="com.form.doc.assign.FDocAssign" />
            <logic:iterate name="BDocssends" property="allFiles"  id="bean" type="com.form.doc.docssend.FFilesSend">   
            <div>
                                                    
                 <input type="checkbox"  name="idFiles" id="idFiles" value="<bean:write name="bean" property="idFiles" />" onclick="javascript:addMemeber(this)" />                                              
                 <a href="javascript:post('docssend',anchor + ':_DOWNLOAD:fileId:<bean:write name="bean" property="idFiles"/>');remove('docssend',anchor);remove('docssend','fileId');" >
                      <logic:equal name="bean" property="description" value="">
                    <bean:write name="bean" property="fileName" />
                    </logic:equal>
                    <logic:notEqual name="bean" property="description" value="">
                    <bean:write name="bean" property="description" />
                    </logic:notEqual>
                                        (<bean:write name="bean" property="numberReadedFile" />)-
                     (<bean:write name="bean" property="createTimeName" />)   - <span class="version"> (V<bean:write name="bean" property="version" />) </span>     
                    
                 </a>  
 
             </div>
            </logic:iterate>
  