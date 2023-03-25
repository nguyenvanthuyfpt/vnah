<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<logic:present name="BUsersDep">
<table cellpadding="0" cellspacing="0" width="100%" >
<logic:iterate name="BUsersDep" id="user" type="com.form.messages.create.FUserExt"> 
      <tr onmouseover="className='row1'" onmouseout="className='row2'" >
      <td align="left" nowrap>
      <div class="fontSubMycontent" > 
          <span style="cursor: pointer;" id="<bean:write name="user" property="email"/>" onclick="javascript:sendAddress(this)" >
                <bean:write name="user" property="fullName"/>
          </span>      
      </div>
      </td>
   </tr>
</logic:iterate>
</table>
</logic:present>                        