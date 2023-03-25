<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<TABLE class=adminlist>
  <TBODY>
<TR>
    <TH class=title noWrap>#</th>
    <TH class=title noWrap>
        <bean:message key="category.sms.fullname" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>    
        <bean:message key="category.sms.day" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>
        <bean:message key="category.sms.week" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>
        <bean:message key="category.sms.month" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap width="25px" >#</th>
</TR>

                   
  <TR class=row0>
    <td></td>
    <TD><html:select styleClass="inputbox"  name="sms" property="userId" styleId="userId" >                                     
                   <logic:present name="BUsers">
                      <html:options collection="BUsers" property="id" labelProperty="fullName"/>
                    </logic:present>
              </html:select>   </TD>
    <TD><html:text  name="sms" property="day" styleClass="inputbox" size="20" /></TD>   
    <TD><html:text  name="sms" property="week" styleClass="inputbox" size="20" /></TD>
    <TD>
<html:text  name="sms" property="month" styleClass="inputbox" size="20" />
<html:button property="_EDIT"  onclick="javascript:postAjax('sms','tdMainBody',anchor + ':_CREATE:id:0')" styleClass="button">
<bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
</html:button></TD>
    <TD nowrap  width="40px">
    <logic:notEqual name="sms" property="id" value="0">
<bean:define name="sms" property="id" id="id" type="java.lang.Integer" />
<%String onclick="postAjax('sms','tdMainBody',anchor + ':_EDIT:id:"+id+"')";%>
<html:button property="_EDIT"  onclick="<%=onclick%>" styleClass="button">
<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
</html:button>
</logic:notEqual>
    </TD>
    </TR>
</TBODY></TABLE>
