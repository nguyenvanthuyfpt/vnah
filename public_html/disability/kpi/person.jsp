<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<%
    com.form.FBeans listPersonAc = (com.form.FBeans)request.getSession().getAttribute("LIST_PERSON");
%>
<style type="text/css">
  .ui-autocomplete {
    max-height: 200px;
    width: auto;
    overflow-y: auto;
    overflow-x: hidden;
  }
  
  * html .ui-autocomplete {
    height: 100px;
  }
  
  .ui-draggable, .ui-droppable {
    background-position: top;
  }
 </style>

<script type="text/javascript">  
  $(function() {
      autoComplete();
  });
  
  function changeLocation() {
      postAjax('kpi','div_person',anchor + ':_PERSON_CHANGE_SEL_OPTION:state:1');
      autoComplete();
  }
  
  function autoComplete() {
    $("#perName").focus();
      var arr = [];
      var persons = function (value, label, location, sex, title, agency) {
          this.value = value;
          this.label = label;
          this.sex = sex;
          this.location = location;
          this.title = title;
          this.agency = agency;
      };
      <% for (int i=0; i<listPersonAc.getTotalRows(); i++) { 
      com.form.disability.FPerson fPer = (com.form.disability.FPerson)listPersonAc.get(i);
      %>
         arr.push((new persons("<%=fPer.getId()%>","<%=fPer.getName()%>","<%=fPer.getLocationName()%>","<%=fPer.getGioiTinh()%>","<%=fPer.getTitle()%>","<%=fPer.getAgency()%>","<%=fPer.getAddress()%>","<%=fPer.getContact()%>")));
      <% } %>
     
      $("#perName" ).autocomplete({
        minLength: 3,
        source: arr,
        focus: function( event, ui ) {
          $("#perName").val( ui.item.label );
          return false;
        },
        select: function( event, ui ) {
          $("#perName").val( ui.item.label );
          $("#perId").val( ui.item.value );
          var perId = ui.item.value;
          postAjax('kpi', 'div_person', anchor + ':_PERSON_AUTO_COMPLETE:dtlId:'+perId+':inputType:2:state:2');
          return false;
        }
      })
      .autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
          .append( "<div>" + item.label + "<br/><i>"+ item.location +" - " + item.sex + (item.title!=""?" - "+item.title:"") +  (item.agency!=""? " - "+item.agency:"") +"</i></div>" )
          .appendTo( ul );
      };
  }
</script>


 <script type="text/javascript">   
    function changeLocation(inputType){
          var yearSel = document.getElementById("yearReport").value;
          postAjax('kpi','div_person', anchor + ':_PERSON_CHANGE_OPTION:inputType:'+inputType+':yearReport:'+yearSel+':state:0');
          //autoComplete();
    }
</script>
<bean:define name="kpi" property="type"   id="type"   type="java.lang.Integer" />
<bean:define name="kpi" property="nktId"  id="nktId"  type="java.lang.Integer" />
<bean:define name="kpi" property="lvl"    id="lvl"    type="java.lang.Integer" />
<bean:define name="kpi" property="objId"  id="objId"  type="java.lang.Integer" />
<bean:define name="kpi" property="indId"  id="indId"  type="java.lang.Integer" />
<bean:define name="kpi" property="eventId" id="eventId"  type="java.lang.Integer" />

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
<html:hidden name="kpi" property="perId"/>
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

<%
    String level = String.valueOf(lvl);
    String strType = String.valueOf(type);
    boolean readonly = "3".equals(strType)?true:false;
    // out.println(eventId);
%>

<div class="pd-5">
<table width="100%">
<tr>
    <td width="20%">
        <bean:message key="location" bundle="<%=interfaces%>" />
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
    </td>
    <td colspan="3">
      <logic:equal value="2" name="kpi" property="type">
          <html:select styleClass="combobox_w150" name="kpi" property="locationId" onchange="changeLocation('2');">
              <logic:present name="BTreeTinhs">                                                
                  <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
              </logic:present>
          </html:select>
      </logic:equal>
      <logic:equal value="3" name="kpi" property="type">
          <html:select styleClass="combobox_w150" name="kpi" property="locationId" onchange="changeLocation('3');">
              <logic:present name="BTreeTinhs">                                                
                  <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
              </logic:present>
          </html:select>
      </logic:equal>
      <span style="color:#005BCC"><bean:write name="kpi" property="locationName" /></span>
    </td>
</tr>
<tr>
    <td><bean:message key="common.label.event.id" bundle="<%=interfaces%>" />          
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
    </td>
    <td colspan="3">
        <logic:equal value="2" name="kpi" property="type">
            <html:select name="kpi" property="eventId" 
                onchange="javascript:postAjax('kpi', 'div_person', anchor + ':_PERSON_CHANGE_OPTION:inputType:2');" styleClass="combobox_w400">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="events">                                                
                    <html:options collection="events" property="eventId" labelProperty="activity"/>
                </logic:present>
            </html:select>
            <html:button property="_SELECT" value="..." title="L&#7921;a ch&#7885;n s&#7921; ki&#7879;n..." onclick="post('kpi',anchor + ':_SELECT:typeSel:1:inputType:2')"/>                  
        </logic:equal>
        
        <logic:equal value="3" name="kpi" property="type">
            <html:select name="kpi" property="eventId"                       
                onchange="javascript:postAjax('kpi', 'div_person', anchor + ':_PERSON_CHANGE_OPTION:inputType:3')" styleClass="combobox_w400">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="events">                                                
                    <html:options collection="events" property="eventId" labelProperty="activity"/>
                </logic:present>
              </html:select> 
        </logic:equal>
    </td>
</tr>
<tr>
    <td width="20%"><bean:message key="common.label.event.activity" bundle="<%=interfaces%>" /></td>
    <td colspan="3">
        <html:textarea name="kpi" property="activity" rows="3" cols="55" readonly="true"/>
    </td>
</tr>
<tr>
    <td><bean:message key="common.label.event.location" bundle="<%=interfaces%>" /></td>
    <td colspan="3">              
        <html:textarea name="kpi" property="location" rows="3" cols="55" readonly="true"/>
    </td>                                     
</tr>
<tr>
    <td><bean:message key="common.label.date.start" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="startDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="startDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'startDate','dd/mm/yyyy');return false;"></td>
    <td><bean:message key="common.label.date.end" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="endDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="endDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'endDate','dd/mm/yyyy');return false;"></td>                                     
</tr>

<tr>
    <td><bean:message key="common.label.createdate" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="createDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="createDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;"></td>
    <td colspan="2">&nbsp;</td>    
    <!--<td><bean:message key="common.label.modifydate" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="modifyDate" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="modifyDate" style="width:80px" 
        onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'modifyDate','dd/mm/yyyy');return false;"></td>-->
</tr>

<tr>
    <td><bean:message key="common.label.name" bundle="<%=interfaces%>" />
        <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
    </td> 
    <td>
        <html:text name="kpi" property="perName" size="25" styleId="perName" styleClass="ui-autocomplete-input"/>
    <%--<html:text name="kpi" property="perName" size="25" styleId="perName" />--%>
    <%if("2".equals(strType)){%>
    <logic:greaterThan value="0" name="kpi" property="eventId">
    <html:button property="_SELECT" value="..." title="L&#7921;a ch&#7885;n ng&#432;&#7901;i..." onclick="post('kpi',anchor + ':_SELECT:perId:0:typeSel:2:inputType:2')"/>
    </logic:greaterThan>
    <%}%>
    </td> 
    <td><bean:message key="common.label.sex" bundle="<%=interfaces%>" /></td> 
    <td><html:radio name="kpi" property="perSex" value="0" />
        <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
        <html:radio name="kpi" property="perSex" value="1" />
        <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></td>                                      
</tr>

 <tr>
    <td><bean:message key="common.label.position" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="perTitle" size="25" /></td>
    <td><bean:message key="common.label.agency" bundle="<%=interfaces%>" /></td> 
    <td><html:text name="kpi" property="perAgency" size="25" /></td>            
   
</tr>

<tr>
    <td><bean:message key="common.label.address" bundle="<%=interfaces%>" /></td>
    <td><html:text name="kpi" property="perAddress" size="25" /></td>
    <td><bean:message key="common.label.contact" bundle="<%=interfaces%>" /></td> 
    <td><html:text name="kpi" property="perContact" size="25" /></td>
</tr>

<%if("3".equals(strType)){%>
<tr>
    <td><bean:message key="common.label.hours" bundle="<%=interfaces%>" /></td> 
    <td><html:text name="kpi" property="perHours" size="10" onkeypress="return formatInt(event, this);" styleClass="textfield_num"/></td>
    <td><bean:message key="common.label.vote" bundle="<%=interfaces%>" /></td>
    <td>
        <html:select name="kpi" property="voteResult" styleClass="combobox_w100">
          <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
          <html:options collection="mapVoteResult" property="key" labelProperty="value" />
        </html:select>
    </td>
</tr>
<%}%>
</table>
</div>

<div id="cmd">
    <jsp:include page="/disability/kpi/cmd.jsp"/>
</div>  

<div id="alert">
    <jsp:include page="/admin/alert.jsp"/>
</div>

<div id="listId">
    <jsp:include page="/disability/kpi/listPerson.jsp"/>
</div>  
