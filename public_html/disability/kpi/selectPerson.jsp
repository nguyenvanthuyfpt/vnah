<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<bean:define name="listPersonAc" id="listPersonAc" type="com.form.FBeans" />

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
      var persons = function (value, label, sex, title, agency) {
          this.value = value;
          this.label = label;
          this.sex = sex;
          this.title = title;
          this.agency = agency;
      };
      <% for (int i=0; i<listPersonAc.getTotalRows(); i++) { 
      com.form.disability.FPerson fPer = (com.form.disability.FPerson)listPersonAc.get(i);
      %>
         arr.push((new persons("<%=fPer.getId()%>","<%=fPer.getName()%>","<%=fPer.getGioiTinh()%>","<%=fPer.getTitle()%>","<%=fPer.getAgency()%>","<%=fPer.getAddress()%>","<%=fPer.getContact()%>")));
      <% } %>
     
      $("#perName" ).autocomplete({
        minLength: 1,
        source: arr,
        focus: function( event, ui ) {
          $("#perName").val( ui.item.label );
          return false;
        },
        select: function( event, ui ) {
          $("#perName").val( ui.item.label );
          $("#perId").val( ui.item.value );
          var perId = ui.item.value;
          postAjax('kpi', 'div_person', anchor + ':_PERSON_SEL_DETAIL:perId:'+perId+':inputType:2:state:1');
          return false;
        }
      })
      .autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
          .append( "<div>" + item.label + "<br/><i>"+ item.sex + (item.title!=""?" - "+item.title:"") +  (item.agency!=""? " - "+item.agency:"") +"</i></div>" )
          .appendTo( ul );
      };
  }
</script>



<html:form action="kpi" method="post">
    <html:hidden name="kpi" property="id"/>
    <html:hidden name="kpi" property="indId"/>
    <html:hidden name="kpi" property="objId"/>
    <html:hidden name="kpi" property="type"/>
    <html:hidden name="kpi" property="perId"/>
    
<div class="content-calendar">
    <div class="padding-content"> 
        <table class="tableForm" cellpadding="0" width="100%" align="center"
               style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
            <tbody>
              <tr>
                  <td><bean:message key="location" bundle="<%=interfaces%>" /></td>
                  <td colspan="3">
                    <html:select styleClass="combobox_w200" name="kpi" property="locationId" onchange="changeLocation();">
                        <logic:present name="BTreeTinhs">
                            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select>
                    <span style="color:#005BCC"><bean:write name="kpi" property="locationName" /></span>
                  </td>
              </tr>
              
              <tr>
                  <td><bean:message key="common.label.createdate" bundle="<%=interfaces%>" /></td>
                  <td><html:text name="kpi" property="createDate" 
                      styleClass="textfield_date" 
                      onkeypress="return formatDate(event,this);" 
                      onblur="isDate(this);" 
                      styleId="createDate" style="width:80px" 
                      onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" 
                      onmouseout="UnTip()"/>
                      
                      <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                          onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;"></td>
                  <td><bean:message key="common.label.modifydate" bundle="<%=interfaces%>" /></td>
                  <td><html:text name="kpi" property="modifyDate" 
                      styleClass="textfield_date" 
                      onkeypress="return formatDate(event,this);" 
                      onblur="isDate(this);" 
                      styleId="modifyDate" style="width:80px" 
                      onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                      <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' 
                          onClick="popUpCalendar(this,'modifyDate','dd/mm/yyyy');return false;"></td>                                     
              </tr>
              
              <tr>
                  <td><bean:message key="common.label.code" bundle="<%=interfaces%>" /></td> 
                  <td><html:text name="kpi" property="perCode" size="25" /></td> 
                  <td colspan="2">&nbsp;</td>                                      
              </tr>
              
              <tr>
                  <td><bean:message key="common.label.name" bundle="<%=interfaces%>" /></td> 
                  <td><html:text name="kpi" property="perName" size="25" styleId = "perName" onchange="autoComplete();"/></td> 
                  <td><bean:message key="common.label.sex" bundle="<%=interfaces%>" /></td> 
                  <td><html:radio name="kpi" property="perSex" value="0" />
                      <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                      <html:radio name="kpi" property="perSex" value="1" />
                      <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></td>                                      
              </tr>
              
              <!--<tr>
                  <td><bean:message key="common.label.birth" bundle="<%=interfaces%>" /></td> 
                  <td><html:text name="kpi" property="perBirth" styleClass="textfield_date" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="perBirth" style="width:80px" 
                      onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                      <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'perBirth','dd/mm/yyyy');return false;"></td> 
                                            
              </tr>-->
              
              <tr>
                  <td><bean:message key="common.label.address" bundle="<%=interfaces%>" /></td>
                  <td><html:text name="kpi" property="perAddress" size="25" /></td>
                  <td><bean:message key="common.label.contact" bundle="<%=interfaces%>" /></td> 
                  <td><html:text name="kpi" property="perContact" size="25" /></td>    
              </tr>
              
               <tr>
                  <td><bean:message key="common.label.position" bundle="<%=interfaces%>" /></td>
                  <td><html:text name="kpi" property="perTitle" size="25" /></td>
                  <td><bean:message key="common.label.agency" bundle="<%=interfaces%>" /></td> 
                  <td><html:text name="kpi" property="perAgency" size="25" /></td>    
              </tr>
            </tbody>
        </table>   
        
        
        <div align="center" id="cmd">
            <jsp:include page="/disability/kpi/cmdPerson.jsp"/>
        </div>            
        <div id="alert">
            <jsp:include page="/admin/alert.jsp"/>
        </div>  
        <div align="center" id="listId">
            <jsp:include page="/disability/kpi/listPerson.jsp"/>
        </div>            
  </div>
</div>  
</html:form>   
