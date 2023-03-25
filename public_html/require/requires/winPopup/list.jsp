<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <logic:present name="BUsersDep">
 <% String statusIdsName = ""; %> 
 <logic:notEmpty name="frmRequireEmp" property="members" >
  <bean:define name="frmRequireEmp" property="members" id="members" type="java.lang.String"/>
  <% statusIdsName= members ;%>
</logic:notEmpty>
   <% statusIdsName = "," + statusIdsName + ",";
    String checkeds = ""; %>      
   <table  class="list-voffice"  cellpadding="1" cellspacing="1" border="0" align="center"  width="100%"  >
         <% int i =0;%>   
        <logic:iterate name="BUsersDep" id="bean" type="com.form.admin.users.FUser"> 
           <% i++;
           checkeds = statusIdsName.indexOf("," + 0 + "#" + bean.getId()+ "," )<0?"":"checked"; %>
            <tr class="<%=(i%2==0)?"content1":"content"%>">
                <td width="5%" nowrap align="center"><%=i%></td>                
                <td>
                    <bean:write name="bean" property="fullName" />
                </td> 
                <td width="5px" nowrap><input type="checkbox" name="disposeUser" id="disposeUser" onclick="checkX(this)" value="0#<bean:write name="bean" property="id" />" <%=checkeds%> /></td>
            </tr>
        </logic:iterate>
    </table>
 </logic:present>

