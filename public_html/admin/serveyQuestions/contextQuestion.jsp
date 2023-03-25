<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="category.serveyquestions" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="category.serveyquestions" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          <html:select name="serveyQuestions" property="questionId" styleClass="fieldSelect"  onchange="javascript:postAjax('serveyQuestions','tdMainBody',anchor + ':_SELECT')" style="width:200px;">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <logic:present name="BServeyQuestions" >   
            <html:options collection="BServeyQuestions" property="questionId" labelProperty="question"/>
            </logic:present>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.serveyquestions" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="serveyQuestions" property="question" styleClass="fieldText" style="width:200px;"/>  
        </td>
      </tr>
      <tr>
        <td><bean:message key="category.servey.orders" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text name="serveyQuestions" property="orders" styleClass="fieldText" style="width:20px;"/>  
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:postAjax('serveyQuestions','tdMainBody',anchor + ':_CREATE')" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('serveyQuestions','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('serveyQuestions','tdMainBody',anchor + ':_DELETE')" styleClass="button">
            <bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>
          </html:button>  
        </p>
        
        </td>
      </tr>
    </table>
  </fieldset>