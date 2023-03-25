<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<div style="padding:6px">
<logic:present name="BUsers">
 <% String statusIdsName = ""; %> 
<logic:notEmpty name="docAssign" property="members" >
  <bean:define name="docAssign" property="members" id="members" type="java.lang.String"/>
  <% statusIdsName= members ;%>
</logic:notEmpty>
   <% statusIdsName = "," + statusIdsName + ",";
    String checkeds = ""; %>
   <table  class="list-voffice"  cellpadding="1" cellspacing="0" border="0" width="100%" >
        <tbody>
            <tr>
               <td  width="3%" nowrap>#</td>
                <td  ><bean:message key="doc.assign.user.title.caption" bundle="<%=interfaces%>"/></td>
                <logic:notEmpty name="BTranfers" > 
                <bean:define name="BTranfers" id="bean1" type="com.form.admin.users.FUser" />  
                  <logic:iterate name="bean1"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                    <% if (beanTranfer.getId()==0){%>
                     <td width="10%" nowrap >
                         
                    </td>                
                  <%}%>
                </logic:iterate>
                </logic:notEmpty>
            </tr>
         <% int i =0;%>   
        <logic:iterate name="BUsers" id="bean" type="com.form.admin.users.FUser"> 
           <% i++;%>
            <tr class="<%=(i%2==0)?"content1":"content"%>">
              <td width="3%" nowrap align="center"><%=i%></td>                
                <td><bean:write name="bean" property="fullName" /></td>                
                <logic:iterate name="bean"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                 <% 
                    if (beanTranfer.getId()==0){                      
                     checkeds = statusIdsName.indexOf("," + beanTranfer.getId() + "#" + bean.getId()+ "," )<0?"":"checked";                 
                 %> 
                    <td align="center">
                        <logic:equal name="beanTranfer" property="id" value="0">
                                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkExcuteGroup" value="0">
                                          <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkY(this);checkX(this);" value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                                    </logic:equal>
                                    
                                    <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkExcuteGroup" value="1">
                                        <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this)"                value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                                    </logic:equal>
                        
                              
                            
                        </logic:equal>
                        <logic:notEqual name="beanTranfer" property="id" value="0">
                              <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this);" value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                        </logic:notEqual>
                    </td>   
                <%}%>
                </logic:iterate>                  
            </tr>
        </logic:iterate>
        </tbody>
    </table>   
    </logic:present>
<div>
</div>
</div>
