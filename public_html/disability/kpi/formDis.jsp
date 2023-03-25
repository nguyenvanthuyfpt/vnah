<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<bean:define name="listDataDtlAc" id="listDis" type="com.form.FBeans" />
<bean:define name="kpi" property="ncsName"      id="chkNcs"       type="java.lang.String" />
<%
     String disable = "false";
     if (chkNcs!=null) {
        disable = "".equals(String.valueOf(chkNcs))?"true":"false";
     }
%>
<script type="text/javascript">
  $( function() {
    
    /*
    $.preloader.start({
        modal: true,
        src : 'sprites2.png'
    });

    setTimeout(function(){
        $.preloader.stop();
    }, 3000);
    */
    
    var hasDisable = '<%=disable%>';
    if (hasDisable=='true') {
        $("#ncsBirth").attr("readonly", "true");
        $("#ncsRelation").attr("readonly","true");
        $("#ncsPhone").attr("readonly","true");
        $("#ncsSex").attr("readonly", "true");
    }
    $("#ncsName").blur(function() {
        var retval = $("#ncsName").val();
        if (!isEmpty(retval)) {          
          $("#ncsBirth").removeAttr('readonly');
          $("#ncsRelation").removeAttr('readonly');
          $("#ncsPhone").removeAttr('readonly');
          $("#ncsSex").removeAttr('readonly');
          $("#ncsBirth").focus();
        } else {
          $("#ncsBirth").val('');
          $("#ncsBirth").attr("readonly", "true");
          $("#ncsRelation").val('-1');
          $("#ncsRelation").attr("readonly", "true");
          $("#ncsPhone").val('');
          $("#ncsPhone").attr("readonly", "true");
          $("#ncsSex").val('-1');
          $("#ncsSex").attr("readonly", "true");
        }
		}); 
    
    $("#disName").focus();
    var arr = [];
    var projects = function (value, label, sex, birthday) {
        this.value = value;
        this.label = label;
        this.sex = sex;
        this.birthday = birthday;
    };
    
    <% for (int i=0; i<listDis.getTotalRows(); i++) { 
	  com.form.disability.FDisability fDis = (com.form.disability.FDisability)listDis.get(i);
    %>
       arr.push((new projects("<%=fDis.getId()%>","<%=fDis.getNkt()%>","<%=fDis.getGioiTinh()%>","<%=fDis.getNgaySinh()%>")));
    <% } %>
   
    $( "#disName" ).autocomplete({
      minLength: 1,
      source: arr,
      focus: function( event, ui ) {
        $( "#disName" ).val( ui.item.label );
        return false;
      },
      select: function( event, ui ) {
        $( "#disName" ).val( ui.item.label );
        $( "#nkt-id" ).val( ui.item.value );
        var nktId = ui.item.value;
        postAjax('kpi', 'MainCate' ,anchor + ':_DIS_VIEW_DETAIL:dtlId:'+nktId+':objId:56:type:1');
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<div>" + item.label + "<br/><i>"+ item.sex + " - " + item.birthday +"</i></div>" )
        .appendTo( ul );
    };
  } );
</script>  
<bean:define name="kpi" property="nktId"        id="nktId"       type="java.lang.Integer" />
<%
    int userId = (int)me.getId();    
    String strNktId = String.valueOf(nktId);
%>
<input type="hidden" name="nktId" value="<%=nktId%>" />
<input type="hidden" name="inputType" value="1" />
<div class="padding-content">
    <table class="tableForm" cellpadding="0" width="100%" align="center"
         style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
      <tbody>
        <tr>
            <td><bean:message key="location" bundle="<%=interfaces%>" /></td>
            <td>
                <html:select styleClass="combobox_w150" name="kpi" property="locationId" onchange="postAjax('kpi','MainCate',anchor + ':_DIS_CHANGE_OPTION:type:1');">
                  <logic:present name="BTreeTinhs">
                      <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                  </logic:present>
                </html:select>
            </td>
            <td colspan="2"><span style="color:#005BCC"><bean:write name="kpi" property="locationName" /></span></td>
        </tr>
        
        <logic:notEmpty name="districts"> 
        <tr>
            <td><bean:message key="district" bundle="<%=interfaces%>" /></td>
            <td>
                <html:select styleClass="combobox_w150" name="kpi" property="districtId" onchange="postAjax('kpi','MainCate',anchor + ':_CHANGE_OPT_DISTRICT:type:1');">
                  <logic:present name="districts">
                      <html:options collection="districts" property="id" labelProperty="name"/>
                  </logic:present>
                </html:select>
            </td>
            <td>            
                <bean:message key="commune" bundle="<%=interfaces%>" />
            </td>
            <td>
                <html:select styleClass="combobox_w150" name="kpi" property="communeId" >
                  <logic:present name="communes">
                      <html:options collection="communes" property="id" labelProperty="name"/>
                  </logic:present>
                </html:select>
            </td>
        </tr>
        </logic:notEmpty>
                                 
        <tr>
            <td>Ng&#224;y m&#7903; h&#7891; s&#417;:
            <!--<bean:message key="common.label.createdate" bundle="<%=interfaces%>" />-->
            </td>
            <td><html:text name="kpi" property="createDate" styleClass="textfield_date" 
                onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="createDate" style="width:80px" 
                onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;">
            </td>
            <td><bean:message key="common.label.dis.code" bundle="<%=interfaces%>" /></td>                 
            <td><html:text name="kpi" property="disCode" readonly="true" style="width:120px;" /></td>
        </tr>
        <tr>
            <td>
                <bean:message key="common.label.dis.name" bundle="<%=interfaces%>" />
                <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
            </td> 
            <td>
                <html:text name="kpi" property="disName" styleId = "disName" style="width:120px;"/> 
            </td>
            <td><bean:message key="common.label.dis.code.nkt" bundle="<%=interfaces%>" /></td> 
            <td><html:text name="kpi" property="disCodeNkt" style="width:120px;" /></td>
        </tr> 
        <tr>                 
            <td>
                <bean:message key="common.label.birth" bundle="<%=interfaces%>" />
                <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
            </td> 
            <td><html:text name="kpi" property="disBirth" styleClass="textfield_date" 
                onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="disBirth" style="width:80px" 
                onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'disBirth','dd/mm/yyyy');return false;">
            </td>
            <!--<td><bean:message key="common.label.id" bundle="<%=interfaces%>" /></td>                 
            <td><html:text name="kpi" property="disPassport" style="width:120px;"/></td>-->
            <td><bean:message key="common.label.phone" bundle="<%=interfaces%>" /></td>                 
            <td><html:text name="kpi" property="disPhone" style="width:120px;"/></td>
        </tr>
        
        <tr>
            <td><bean:message key="common.label.nation" bundle="<%=interfaces%>" /></td> 
            <td>
              <html:select styleClass="combobox_w120" name="kpi" property="disNation">
                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                      <logic:present name="BDanTocs">
                          <html:options collection="BDanTocs" property="id" labelProperty="name"/>
                      </logic:present>
              </html:select>
            </td>
            <td><bean:message key="common.label.dis.sex" bundle="<%=interfaces%>" /></td> 
            <td>
				<html:select styleClass="combobox_w120" styleId="disSex" name="kpi" property="disSex">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="0"><bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></html:option>
               </html:select> 
			</td>                      
        </tr>
        
        <tr>
            <td><bean:message key="common.label.dis.dacam" bundle="<%=interfaces%>" /></td>
            <td>
                <html:select styleClass="combobox_w80" name="kpi" property="disDioxin">
                  <html:option value="0">Kh&#244;ng</html:option>
                  <html:option value="1">C&#243;</html:option>
                </html:select>
            </td>
            <td><bean:message key="common.label.dis.nnghiep" bundle="<%=interfaces%>" /></td> 
            <td>
              <html:select styleClass="combobox_w120" name="kpi" property="disCarrer">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="disability.form.label.NgheNghiep.1" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="2"><bean:message key="disability.form.label.NgheNghiep.2" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="3"><bean:message key="disability.form.label.NgheNghiep.3" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="4"><bean:message key="disability.form.label.NgheNghiep.4" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="5"><bean:message key="disability.form.label.NgheNghiep.5" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="6"><bean:message key="disability.form.label.NgheNghiep.6" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="7"><bean:message key="disability.form.label.NgheNghiep.7" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="8"><bean:message key="disability.form.label.NgheNghiep.8" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="9"><bean:message key="disability.form.label.NgheNghiep.9" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="10"><bean:message key="disability.form.label.NgheNghiep.10" bundle="<%=interfaces%>"/></html:option>
              </html:select>
            </td> 
        </tr>
        
        <tr>
            <td><bean:message key="common.label.dis.address" bundle="<%=interfaces%>" /></td>
            <td colspan="3">
                <html:textarea name="kpi" property="disAddress" rows="3" cols="55" />
            </td>
        </tr>
        
        <tr>
            <td><bean:message key="common.label.ncs.carers" bundle="<%=interfaces%>" /></td>
            <td><html:text name="kpi" property="ncsName" styleId = "ncsName" style="width:120px;" /></td>
            <td><bean:message key="common.label.ncs.birth" bundle="<%=interfaces%>" /></td> 
            <td><html:text name="kpi" property="ncsBirth" styleId = "ncsBirth" style="width:120px;" onkeypress="return formatInt(event, this);"/></td>
        </tr>
        
        <tr>
            <td><bean:message key="common.label.ncs.relation" bundle="<%=interfaces%>" /></td>
            <td>
                <html:select styleClass="combobox_w120" name="kpi" styleId="ncsRelation" property="ncsRelation">
                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1">&#212;ng</html:option>
                  <html:option value="2">B&#224;</html:option>
                  <html:option value="3">C&#244;</html:option>
                  <html:option value="4">D&#236;</html:option>
                  <html:option value="5">Ch&#250;</html:option>
                  <html:option value="6">B&#225;c</html:option>
                  <html:option value="7">Anh</html:option>
                  <html:option value="8">Ch&#7883;</html:option>
                  <html:option value="9">Em</html:option>
                  <html:option value="10">B&#7889;</html:option>
                  <html:option value="11">M&#7865;</html:option>
                  <html:option value="12">Ch&#7891;ng</html:option>
                  <html:option value="13">V&#7907;</html:option>
                  <html:option value="15">Con</html:option>
                  <html:option value="16">Ch&#225;u</html:option>
                  <html:option value="14">Kh&#225;c</html:option>
                </html:select>
            </td>          
            <td><bean:message key="common.label.ncs.phone" bundle="<%=interfaces%>" /></td> 
            <td><html:text name="kpi" property="ncsPhone" styleId="ncsPhone" style="width:120px;" /></td>
        </tr>
        
        <tr>
            <td><bean:message key="common.label.ncs.sex" bundle="<%=interfaces%>" /></td>
            <td>
              <html:select styleClass="combobox_w120" styleId="ncsSex" name="kpi" property="ncsSex">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="0"><bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></html:option>
               </html:select>   
            </td>
            
            <td><bean:message key="common.label.status.profile" bundle="<%=interfaces%>"/></td>
            <td>
                <logic:equal name="kpi" property="statusId" value="0">
                      <bean:message key="common.label.status.active" bundle="<%=interfaces%>" />                            
                </logic:equal>
                <logic:equal name="kpi" property="statusId" value="1">
                      <b><bean:message key="common.label.status.close" bundle="<%=interfaces%>" /></b>
                </logic:equal>
                <html:hidden name="kpi" property="statusId" styleId="statusId" />
            </td>
        </tr>
      
        <tr>
            <td><bean:message key="common.label.dis.project" bundle="<%=interfaces%>" /></td>
            <td>
              <html:select styleClass="combobox_w120" name="kpi" property="duAnId">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="0"><bean:message key="common.label.dis.project.direct" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="common.label.dis.project.inclusion3" bundle="<%=interfaces%>"/></html:option>
               </html:select>   
            </td>
            
            <td colspan="2">&nbsp;</td>
        </tr>
      
      </tbody>
  </table>
  
  <table width="100%">
  <tr>
      <td>
          <logic:present name="kpi">
              <jsp:include page="/disability/kpi/cmd.jsp"/>
          </logic:present>
      </td>
  </tr>
  </table>
</div>

<div id="alert">
    <jsp:include page="/admin/alert.jsp"/>
</div>

<div id="listId">
  <jsp:include page="/disability/kpi/listDis.jsp"/>
</div>