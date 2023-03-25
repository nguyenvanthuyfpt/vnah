<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="serveyQuestions" method="post">
<table align="left">
    <tr><td>
    <fieldset class="friendset">
    <legend class="lengend"><bean:message key="category.serveyquestions" bundle="<%=interfaces%>"/></legend>
    <table>
    <tr>
        <td><bean:message key="category.serveyquestions.serveyId" bundle="<%=interfaces%>" /></td>
        <td class="tdDataField">
            <html:select name="serveyQuestions" property="serveyId" styleClass="fieldSelect"  onchange="postAjax('serveyQuestions','listQuestions',anchor + ':_SELECT_SERVEY');messageImg('listQuestions');" style="width:250px;">
            <html:options collection="BServey" property="serveyId" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      </table>
      </fieldset>
     </td></tr>
<tr>
    <td id="listQuestions">
  <jsp:include page="/admin/serveyQuestions/contextQuestion.jsp" />
    </td>
</tr>
</table>
</html:form>
