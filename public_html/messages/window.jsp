<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="250px" border="1">
  <tbody>
  <tr>
    <td vAlign=top>
    <TABLE class=adminform>
        <TBODY>
        <TR>
          <TH align="left" nowrap>
              <img src="<%=contextPath%>/images/disable.png" style="cursor:poiter" align="right" onclick="javascript:closeWindow();" >
                   <bean:message key="apps.notice" bundle="<%=interfaces%>"/>
          </TH>
        </TR>
        
        <TR><td nowrap><img src="<%=contextPath%>/images/security_f2.png" height="35" width="35" align="left" >&nbsp;
        <br><strong><html:errors property="alert" bundle="<%=interfaces%>" /></strong></td>
        </tbody>
        </table>
        </td>
        </tr>
        </tbody></table>