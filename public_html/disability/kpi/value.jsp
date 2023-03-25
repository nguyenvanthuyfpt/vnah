<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:hidden name="kpi" property="id"/>
<html:hidden name="kpi" property="objId"/>
<html:hidden name="kpi" property="indId"/>
<logic:equal name="kpi" property="mode" value="DETAIL_LIST">
    <html:hidden name="kpi" property="dtlId"/>
</logic:equal>
<html:hidden name="kpi" property="locationName"/>
<html:hidden name="kpi" property="type"/>
<html:hidden name="kpi" property="eventSel"/>
<html:hidden name="kpi" property="perSel"/>
<html:hidden name="kpi" property="yearReport"/>

<!--
hdrId : <bean:write name="kpi" property="id" />-
dtlId : <bean:write name="kpi" property="dtlId" />-
nktId : <bean:write name="kpi" property="nktId" />-
perId : <bean:write name="kpi" property="perId" />-
objId :<bean:write name="kpi" property="objId" />-
indId :<bean:write name="kpi" property="indId" />-
eventId :<bean:write name="kpi" property="eventId" />-
type :<bean:write name="kpi" property="type" />-
inputType :<bean:write name="kpi" property="inputType" />-
yearReport :<bean:write name="kpi" property="yearReport" />
-->

<!--typePeriod :<bean:write name="kpi" property="typePeriod" />-->

<div class="pd-5">
<table width="100%">
<tr>
  <td width="25%">
      <bean:message key="location" bundle="<%=interfaces%>" />
      <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>    
  </td>
  <td colspan="2">
      <html:select styleClass="combobox_w150" name="kpi" property="locationId" onchange="postAjax('kpi','div_value', anchor + ':_VALUE_CHANGE_OPTION:inputType:0');">
          <logic:present name="BTreeTinhs">
              <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
          </logic:present>
      </html:select>
      <span style="color:#005BCC"><bean:write name="kpi" property="locationName" /></span>
  </td>
</tr>
<tr>
    <td><bean:message key="indicator.edit.type.time" bundle="<%=interfaces%>"/></td>
    <td colspan="2">
        <logic:equal name="kpi" property="lvl" value="0">
            <html:select name="kpi" property="typePeriod" onchange="javascript:postAjax('kpi','div_value',anchor + ':_CHANGE_OPTION:lvl:0')" styleClass="combobox_w100">
                <%--<html:option value="0"><bean:message key="indicator.edit.type.month" bundle="<%=interfaces%>"/></html:option>--%>
                <html:option value="1"><bean:message key="indicator.edit.type.quarter" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="indicator.edit.type.year" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </logic:equal>
        <logic:equal name="kpi" property="lvl" value="1">
            <html:select name="kpi" property="typePeriod" onchange="javascript:postAjax('kpi','div_value',anchor + ':_CHANGE_OPTION:lvl:1')" styleClass="combobox_w100">
                <%--<html:option value="0"><bean:message key="indicator.edit.type.month" bundle="<%=interfaces%>"/></html:option>--%>
                <html:option value="1"><bean:message key="indicator.edit.type.quarter" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="indicator.edit.type.year" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </logic:equal>
    </td>
</tr>
</table>

<!-- Thang -->   
<!-- <logic:equal value="0" name="kpi" property="typePeriod">
<table width="100%">
<tr>
    <td><bean:message key="common.label.createdate" bundle="<%=interfaces%>" /></td>
    <td colspan="3"><html:text name="kpi" property="createDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="createDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;"></td>
    
    <td><bean:message key="common.label.modifydate" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="modifyDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="modifyDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'modifyDate','dd/mm/yyyy');return false;"></td>                                     
</tr>
<tr>
    <td height="21" width="100"><bean:message key="common.label.month" bundle="<%=interfaces%>" /></td>
    <td>
        <html:select name="kpi" property="month">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
            <html:option value="5">5</html:option>
            <html:option value="6">6</html:option>
            <html:option value="7">7</html:option>
            <html:option value="8">8</html:option>
            <html:option value="9">9</html:option>
            <html:option value="10">10</html:option>
            <html:option value="11">11</html:option>
            <html:option value="12">12</html:option>                
        </html:select>       
    </td>
    <td height="21"><bean:message key="common.label.year" bundle="<%=interfaces%>" /></td>
    <td>
        <html:select styleClass="inputbox" name="kpi" property="year" >
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2016">2016</html:option>
            <html:option value="2017">2017</html:option>
            <html:option value="2018">2018</html:option>
            <html:option value="2019">2019</html:option>
            <html:option value="2020">2020</html:option>
        </html:select>
    </td>
    <td colspan="2">&nbsp;</td>
</tr>
<logic:equal name="kpi" property="lvl" value="0">
<tr>
    <td height="21"><bean:message key="common.label.target.month" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="targetM" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td height="21"><bean:message key="common.label.actual.month" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="actual" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td colspan="2">&nbsp;</td>
</tr>

<tr>
    <td height="21"><bean:message key="common.label.accumulated.quarter" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="accQ" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td height="21"><bean:message key="common.label.accumulated.year" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="accY" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td colspan="2">&nbsp;</td>
</tr>
</logic:equal>
</table>
</logic:equal>--%>

<!-- Quy -->
<logic:equal value="1" name="kpi" property="typePeriod">
<table width="100%">
<tr>
    <td><bean:message key="common.label.createdate" bundle="<%=interfaces%>" /></td>
    <td colspan="3"><html:text name="kpi" property="createDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="createDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;"></td>
    
    <!--<td><bean:message key="common.label.modifydate" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="modifyDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="modifyDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'modifyDate','dd/mm/yyyy');return false;"></td>                                     
    -->
</tr>
<tr>
    <td height="21" width="100"><bean:message key="common.label.quarter" bundle="<%=interfaces%>" /></td>
    <td>
         <html:select styleClass="inputbox" name="kpi" property="quarter" >
            <%--<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>--%>
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
        </html:select>
    </td>
    <td height="21"><bean:message key="common.label.year" bundle="<%=interfaces%>" /></td>
     <td>
        <html:select styleClass="inputbox" name="kpi" property="year" >
            <%--<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>--%>
            <html:option value="2016">2016</html:option>
            <html:option value="2017">2017</html:option>
            <html:option value="2018">2018</html:option>
            <html:option value="2019">2019</html:option>
            <html:option value="2020">2020</html:option>
            <html:option value="2021">2021</html:option>
            <html:option value="2022">2022</html:option>
        </html:select>
    </td>
    <td colspan="2">&nbsp;</td>
</tr>

<logic:equal name="kpi" property="lvl" value="0">
<tr>
    <td height="21"><bean:message key="common.label.target.quarter" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="targetQ" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td height="21"><bean:message key="common.label.actual.quarter" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="actual" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td height="21"><bean:message key="common.label.accumulated.year" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="accY" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
</tr>
</logic:equal>

</table>
</logic:equal>

<!-- Nam -->
<logic:equal value="2" name="kpi" property="typePeriod">
<table width="100%">
<tr>
    <td><bean:message key="common.label.createdate" bundle="<%=interfaces%>" /></td>
    <td colspan="3"><html:text name="kpi" property="createDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="createDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;"></td>
    
    <!--<td><bean:message key="common.label.modifydate" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="modifyDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="modifyDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'modifyDate','dd/mm/yyyy');return false;"></td>                                     
    -->
</tr>
<tr>
    <td height="21" width="100"><bean:message key="common.label.year" bundle="<%=interfaces%>" /></td>
    <td colspan="4">
        <html:select styleClass="inputbox" name="kpi" property="year" >
            <%--<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>--%>
            <html:option value="2016">2016</html:option>
            <html:option value="2017">2017</html:option>
            <html:option value="2018">2018</html:option>
            <html:option value="2019">2019</html:option>
            <html:option value="2020">2020</html:option>
            <html:option value="2021">2021</html:option>
            <html:option value="2022">2022</html:option>
        </html:select>
    </td>    
</tr>
<logic:equal name="kpi" property="lvl" value="0">
<tr>
    <td height="21"><bean:message key="common.label.target.year" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="targetY" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td height="21"><bean:message key="common.label.actual.year" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="actual" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
</tr>
</logic:equal>
</table>
</logic:equal>

<%--<bean:write name="kpi" property="lvl"/>--%>

<logic:equal name="kpi" property="lvl" value="1">
<table width="100%">
<tr>
    <td width="150">&nbsp;</td>
    <td width="100"><bean:message key="common.label.target" bundle="<%=interfaces%>" /></td>    
    <td><bean:message key="common.label.actual" bundle="<%=interfaces%>" /></td>
        
</tr>
<tr>
    <td height="21">&nbsp;</td>
    <logic:equal value="0" name="kpi" property="typePeriod">
    <td><html:text name="kpi" property="targetM" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    </logic:equal>
    <logic:equal value="1" name="kpi" property="typePeriod">
    <td><html:text name="kpi" property="targetQ" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    </logic:equal>
    <logic:equal value="2" name="kpi" property="typePeriod">
    <td><html:text name="kpi" property="targetY" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    </logic:equal>
    <td><html:text name="kpi" property="actual" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
</tr>
<tr>
    <td height="21">
        <logic:equal value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.gvn" bundle="<%=interfaces%>" />            
        </logic:equal>
        <logic:notEqual value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.tw" bundle="<%=interfaces%>" />
        </logic:notEqual>
    </td>
    <td><html:text name="kpi" property="targetTw" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td><html:text name="kpi" property="tw" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>   
</tr>
<tr>
    <td height="21">
        <logic:equal value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.registed.ngo" bundle="<%=interfaces%>" />
        </logic:equal>
        <logic:notEqual value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.ttp" bundle="<%=interfaces%>" />
        </logic:notEqual>    
    </td>
    <td><html:text name="kpi" property="targetTtp" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td><html:text name="kpi" property="ttp" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
</tr>
<tr>
    <td height="21">
        <logic:equal value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.non-registed.ngo" bundle="<%=interfaces%>" />
        </logic:equal>
        <logic:notEqual value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.qhu" bundle="<%=interfaces%>" />
        </logic:notEqual>
    </td>
    <td><html:text name="kpi" property="targetQhu" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td><html:text name="kpi" property="qhu" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>    
</tr>
<tr>
    <td height="21">
        <logic:equal value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.uni.and.ins" bundle="<%=interfaces%>" />
        </logic:equal>
        <logic:notEqual value="DO2.PM7" name="kpi" property="code">
            <bean:message key="common.label.input.pxa" bundle="<%=interfaces%>" />
        </logic:notEqual>
    </td>
    <td><html:text name="kpi" property="targetPxa" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>
    <td><html:text name="kpi" property="pxa" size="10" styleClass="textfield_num" onkeypress="return formatInt(event, this);" /></td>    
</tr>
</table>
</logic:equal>

<table width="100%">
<tr>
      <td><bean:message key="common.label.input.note" bundle="<%=interfaces%>" /></td>
      <td colspan="3">
          <html:textarea name="kpi" property="note" rows="5" cols="70"/>
      </td>
</tr>
</table>
</div>

<div id="div_cmd">
    <jsp:include page="/disability/kpi/cmd.jsp"/>
</div>

<div id="alert">
    <jsp:include page="/admin/alert.jsp"/>
</div>

<div id="listId">
    <jsp:include page="/disability/kpi/listValue.jsp"/>
</div>