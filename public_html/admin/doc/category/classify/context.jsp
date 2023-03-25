<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="classify" method="post">
<script type="text/javascript">


function createclassify(){
if(document.classify.numberDay.value==0){
    var ps= document.classify.name.value.split(" ");    
    var result = '';
    for(i=0;i<ps.length;i++){
        var item = trim(ps[i]);
        if(item!=''){
            result += item.charAt(0); 
        }
    }    
    var obj =getObj('numberDay');
      var numberDay= result.toUpperCase();
      var objSelect = document.classify.numberDay;     
     objSelect.options[objSelect.selectedIndex].value = numberDay; 
     objSelect.options[objSelect.selectedIndex].selected = true;
}
postAjax('classify','tdMainBody',anchor + ':_CREATE');
}
</script>
<table align="left">
<tr>
<td>

  <fieldset class="friendset" style>
    <legend class="lengend"><bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/></legend>
    <table  align="center">
      <tr>
        <td><bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
          
          <html:select property="id" styleClass="fieldSelect"  onchange="javascript:postAjax('classify','tdMainBody',anchor + ':_SELECT')">
            <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="BClassify" property="id" labelProperty="name"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryclassify.name" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="name" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>
      <tr>
        <td><bean:message key="categoryclassify.numberDay" bundle="<%=interfaces%>"/>
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
       <html:text property="numberDay" styleId="numberDay" styleClass="fieldText" maxlength="250"/>        
        </td>
      </tr>          
      <tr>
        <td><bean:message key="categoryclassify.discription" bundle="<%=interfaces%>"/></td>
        <td class="tdDataField">
            <html:text property="description" styleClass="fieldText" maxlength="250"/>  
                        
        </td>
      </tr>
      <tr>
        <td class="tdButton" colspan="2">
        <p align="center">
          <html:button property="_CREATE" onclick="javascript:createclassify();" styleClass="button">
            <bean:message key="danhmuc.button.themmoi" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_EDIT"  onclick="javascript:postAjax('classify','tdMainBody',anchor + ':_EDIT')" styleClass="button">
            <bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>
          </html:button>
          <html:button property="_DELETE"  onclick="if(messageDelete())postAjax('classify','tdMainBody',anchor + ':_DELETE')" styleClass="button">
            <bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>
          </html:button>  
        </p>
        <p align="left">
          <html:errors property="alert"  bundle="<%=interfaces%>" />
        </p>
        </td>
      </tr>
    </table>
  </fieldset>
  </td>
</tr>
</table>
</html:form>
