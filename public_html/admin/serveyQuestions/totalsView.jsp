<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" align="center" border="0" cellpadding="0"  cellspacing="0" >
              <tr>
                <td nowrap ><p class="void-title-totals"><img src="<%=contextPath%>/images/newImages/i_32.gif" /><bean:write name="servey" property="name" /></p></td>
              </tr>
              <tr>
                <td height="100%">
                    <logic:present name="BTotals" >
                    <table cellpadding="2" cellspacing="2" width="100%">
                    <logic:iterate name="BTotals" id="beanQuestions" type="com.form.admin.servey.FServeyQuestions">                       
                            <tr>
                            <td>
                            <%=beanQuestions.getQuestion()%>
                            </td>
                            <td><bean:write name="beanQuestions" property="count" /><bean:message key="servey.number" bundle="<%=interfaces%>"/></td>
                            <td width="100px"><div style="width:<%=beanQuestions.getPercent()%>px;background:<%=beanQuestions.getColor()%>">&nbsp;</div></td>
                            <td><bean:write name="beanQuestions" property="percent" />%</td>
                            </tr>
                            <tr><td colspan="4" align="right"><Strong><bean:message key="servey.number.totals" bundle="<%=interfaces%>"/> :</strong>
                    <bean:write name="beanQuestions" property="totals" /> <bean:message key="servey.number" bundle="<%=interfaces%>"/></td></tr>
                    </logic:iterate>                    
                    </table>
                    </logic:present>
                &nbsp;</td>
              </tr>
</table>
