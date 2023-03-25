<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table  class="adminlist"  width="100%">
<tr>
  <th class="title" style="cursor: pointer;" width="5%" ><bean:message key="app.stt" bundle="<%=interfaces%>"/></th>
  <th  class="title" style="cursor: pointer;" ><bean:message key="list.trailerstore.usersendId" bundle="<%=interfaces%>"/></th>
  <th class="title" style="cursor: pointer;" ><bean:message key="list.trailerstore.userrecvId" bundle="<%=interfaces%>"/></th>
  <th class="title" style="cursor: pointer;" nowrap><bean:message key="list.trailerstore.createTime" bundle="<%=interfaces%>"/></th>
  <th class="title" style="cursor: pointer;" ><bean:message key="list.trailerstore.amount" bundle="<%=interfaces%>"/></th>
  <th class="title" style="cursor: pointer;" ></th>
  <th class="title" style="cursor: pointer;" >
  <input type="checkBox" name="checkAll" id="checkAll" value="1" onclick="javascript:checkAllIdsCabin(document.trailerStore.ids,this)" />
  </th>
</tr>
<logic:present name="BTrailerStore" >
<bean:define name="BTrailerStore" id="beans" type="com.form.FBeans"/>
<%  int i =0;%>
<logic:iterate name="BTrailerStore" id="bean" type="com.form.admin.doc.rules.FTrailerStore">
  <tr  class=row0>
            <td align="center" ><span class="index"><%=++i%></span></td>
            <td valign="top"   >
                    <bean:write name="bean" property="userSendName"/>
            </td>
            <td   >
                 <bean:write name="bean" property="userRecvName"/>
            </td>
            <td   >
                <bean:write name="bean" property="createTime"/>
            </td>
            <td   >
                <bean:write name="bean" property="amountDoc"/>
            </td>
            <td   >
                <bean:write name="bean" property="docIds"/>
            </td>
            <td   >
            
                <input type="checkBox" name="ids" id="ids" onclick="checkIds(this,this.form.ids)" value="<bean:write name="bean" property="id"/>" />
            
            </td>
  </tr>
</logic:iterate> 
</logic:present>
</table>