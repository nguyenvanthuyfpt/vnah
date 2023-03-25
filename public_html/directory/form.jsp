<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

        
        
<html:form action="directory">
<div class="status">
        <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
            <tr>
                <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){postAjax('directory','MainDirectory',anchor + ':_SEARCH');messageImg('MainDirectory');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="nameSearch" id="name" value=""/>
                </td>
                <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('directory','MainDirectory',anchor + ':_SEARCH')">&nbsp;</td>
            </tr>
        </table>
</div>
<div class="status">
<p class="left-p-title"><bean:message key="departments.select.title.caption" bundle="<%=interfaces%>"/></p>
<p>
<html:select name="directory" property="departmentId" styleClass="fieldSelect" onchange="postAjax('directory','MainDirectory',anchor + ':_SEARCH');messageImg('MainDirectory');">              
              <html:option style="font-weight: bold;" value="0">--------<bean:message key="status.all" bundle="<%=interfaces%>"/>------</html:option>        
              <logic:present name="BDepartments">
              <html:options collection="BDepartments" property="id" labelProperty="name"/>
                </logic:present>
          </html:select>  
</p>
</div>
</html:form>