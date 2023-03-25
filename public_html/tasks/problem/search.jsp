 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table   cellpadding="0"  cellspacing="0" width="100%">
    <tr>
        <td align="left" nowrap width="200px" class="BGSearch">
                <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
                <tr>
                    <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE');messageImg('MainProblem');return false;}"  onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" id="name" name="name" value=""/>
                    </td>
                    <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE');messageImg('MainProblem')" >&nbsp;</td>
                </tr>
                </table>    
 
        </td>
        <td height="26px" align="right" style="padding-right:8px" id="hiddenSearch" nowrap>                
            <bean:message key="problem.categories" bundle="<%=interfaces%>"/>
             <html:select name="problem" styleClass="inputbox" style="width:150px" property="categoriesId" onchange="postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE')">
            <html:option value="0"><bean:message key="problem.select.option" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BCategories" property="id" labelProperty="title"/>
            </html:select>
             
             <label  for="complateSearch">
             <html:checkbox  name="problem" property="complateSearch" styleId="complateSearch" value="1" onclick="postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE')" />
             <bean:message key="problem.list.complate" bundle="<%=interfaces%>"/></label>

             <label for="viewStop">
             <html:checkbox  name="problem" property="viewStop" styleId="viewStop" value="1" onclick="postAjax('problem','MainProblem',anchor + ':_PREPARED_SAVE')" />
             <bean:message key="problem.list.stop" bundle="<%=interfaces%>"/></label>
             
                <logic:notEqual name="problem" property="type" value="1">
                    <logic:equal name="problem" property="assignCheck" value="1">
                    <html:button property="_PREPARED_CREATE"  onclick="javascript:post('problem',anchor + ':_PREPARED_CREATE:root:0:problemId:0')"  styleClass="button" >                       
                    <bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:equal>
                </logic:notEqual>
         </td>
    </tr>
</table>



  


