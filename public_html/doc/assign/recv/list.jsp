<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

 <logic:present name="BUsersDep">
 <% String statusIdsName = ""; %> 
<logic:notEmpty name="docAssign" property="members" >
  <bean:define name="docAssign" property="members" id="members" type="java.lang.String"/>
  <% statusIdsName= members ;%>
</logic:notEmpty>
   <% statusIdsName = "," + statusIdsName + ",";
    String checkeds = ""; %>
       <table  class="list-voffice"  cellpadding="0" cellspacing="0" border="0"  width="100%" >
            <tr>
                <td  width="5%" nowrap>#</td>
                <td  ><bean:message key="doc.assign.user.title.caption" bundle="<%=interfaces%>"/></td>
               <logic:notEmpty name="BTranfers" > 
                <bean:define name="BTranfers" id="bean1" type="com.form.admin.users.FUser" />                
                <logic:iterate name="bean1"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                     <td  width="13%" nowrap >
                          <bean:write name="beanTranfer" property="name"/>
                    </td>                
                </logic:iterate>
              </logic:notEmpty>
 
            </tr>
         <% int i =0;%>   
        <logic:iterate name="BUsersDep" id="bean" type="com.form.admin.users.FUser"> 
           <% i++;%>
            <tr class="<%=(i%2==0)?"content1":"content"%>">
                <td width="5%" nowrap align="center"><%=i%></td>                
                <td><bean:write name="bean" property="fullName" /></td>                
                <logic:iterate name="bean"  property="tranfers" id="beanTranfer" type="com.form.admin.doc.category.transfer.FTransfer"> 
                 <%  
                   checkeds = statusIdsName.indexOf("," + beanTranfer.getId() + "#" + bean.getId()+ "," )<0?"":"checked"; %>
                    <td align="center" >
                    <logic:equal name="beanTranfer" property="id" value="0">
                        
                      
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkExcuteGroup" value="0">
                              <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this);checkY(this)" value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                        </logic:equal>
                        
                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkExcuteGroup" value="1">
                            <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this)" value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                        </logic:equal>
                        
                    </logic:equal>
                    <logic:notEqual name="beanTranfer" property="id" value="0">
                          <input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this);" value='<bean:write name="beanTranfer" property="id"/>#<bean:write name="bean" property="id"/>' <%=checkeds%>/>
                    </logic:notEqual>
                    </td>                
                </logic:iterate>                
            </tr>
        </logic:iterate>
    </table>
 </logic:present>

