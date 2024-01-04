<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<div class="content-div">
<div class="content-calendar-2" align="left">
    <font style="font-size:13px;">
        <b>T&#236;m ki&#7871;m theo Cung c&#7845;p DVHT</b>
    </font>
</div>

<%	
	String checkBox = "";
	String hotroIdsTemp = "";
%>

<logic:notEmpty name="support" property="hotroIds">
        <bean:define name="support" property="hotroIds" id="hotroIds" type="java.lang.String" />
        <%hotroIdsTemp=hotroIds;%>
</logic:notEmpty>

<div style="overflow-x:scroll;height:300px;">
<table class="pd-5" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<tr>
<logic:present name="BSupports">
	<logic:notEmpty name="BSupports">
		<logic:iterate id="bean" name="BSupports" indexId="i" type= "com.form.disability.categorys.FTinh">
		<%if(bean.getLevel()==0){%>  
		    <%if(i>0){%>
		    </table>
		    </td>
		    <%}%>
		    <td align="left" valign="top" width="33%">
		    <div><Strong><%=bean.getName()%></strong></div>
		    <table border="0" width="100%">
		<%}else if(bean.getLevel()==1){%>
				
		<%
        String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? ORDER BY hotro_id, order_by";
        com.form.FBeans beans = new com.form.FBeans();			
        beans = new com.bo.tree.BTreeView().getTree(bean.getId(),false,SQL_HOTRO,"","");                        
        request.setAttribute("BTemps",beans);
		%>
		    <tr>
            <td>
                <% checkBox=(hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0)?"checked":""; %>
                <input type="checkbox" name="ht_phanLoaiIds" id="ht_phanLoaiIds" <%=checkBox%> value="<%=bean.getId()%>" onclick="showCongcu('hotro_'+this.value,this.checked);">
                <%=bean.getName()%>
                    
                <logic:notEmpty name="BTemps"> 
                    <div id="hotro_<%=bean.getId()%>" >
                        <logic:iterate id="beanT" name="BTemps" indexId="i" type= "com.form.disability.categorys.FTinh">
                            <%if(beanT.getLevel()==0){%>
                                  &nbsp;&nbsp;&nbsp;
                               <%}else{%>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <%}%>
                            <input type="checkbox" name="ht_phanLoaiIds" id="ht_phanLoaiIds" <%=checkBox%> value="<%=beanT.getId()%>">
                            &nbsp;<i><%=beanT.getName().replaceAll("--- ","")%></i><br/>
                        </logic:iterate>                            
                    </div>
                </logic:notEmpty>
            </td>
        </tr>
		<%}%>
		</logic:iterate>   
	</logic:notEmpty>
</logic:present>
</table>
</td>
</tr>			
</table>
</div>	
	

<table class="pd-5" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<!--
<tr>
    <td><bean:message key="common.label.createdate.from" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_capNhatTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_capNhatTu" style="width:80px" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_capNhatTu','dd/mm/yyyy');return false;">
    </td>
    <td><bean:message key="common.label.createdate.to" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_capNhatDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_capNhatDen" style="width:80px" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_capNhatDen','dd/mm/yyyy');return false;">
    </td>
</tr>
-->
<tr>
    <td><bean:message key="hotro.list.label.formDate" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_ngayTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_ngayTu" style="width:80px" styleClass="textfield_date"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_ngayTu','dd/mm/yyyy');return false;">
    </td>
    <td><bean:message key="hotro.list.label.toDate" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_ngayDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_ngayDen" style="width:80px" styleClass="textfield_date"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_ngayDen','dd/mm/yyyy');return false;">
    </td>
</tr>

<tr>
    <td><bean:message key="hotro.list.label.tkham.from" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_taiKhamTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_taiKhamTu" style="width:80px" styleClass="textfield_date"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_taiKhamTu','dd/mm/yyyy');return false;">
    </td>
    <td><bean:message key="hotro.list.label.toDate" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:text name="timkiem" property="ht_taiKhamDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ht_taiKhamDen" style="width:80px" styleClass="textfield_date"/>
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ht_taiKhamDen','dd/mm/yyyy');return false;">
    </td>
</tr>

<!--<tr>
    <td><bean:message key="common.label.disability.support" bundle="<%=interfaces%>"/></td>
    <td colspan="3">
        <div align="left">
            <html:textarea  name="timkiem" property="ht_lydo"  style="width:530px;height:50px" />
        </div>
    </td>
</tr>-->

<tr>
    <td><bean:message key="common.label.disability.support" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select styleClass="inputbox" name="timkiem" property="nguonHoTroIds" multiple="true">
            <logic:present name="BNguonHoTros">
              <html:options collection="BNguonHoTros" property="id" labelProperty="name"/>
            </logic:present>
         </html:select>
    </td>
    <td><bean:message key="common.label.object.support" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="timkiem" property="disDoiTuong" styleClass="combobox_w150">
            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="kpi.dis.support.vnah" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="kpi.dis.support.province" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="kpi.dis.support.district" bundle="<%=interfaces%>"/></html:option> 
            <html:option value="4"><bean:message key="kpi.dis.support.ward" bundle="<%=interfaces%>"/></html:option>
        </html:select>
    </td>
</tr>

<tr>
    <td><bean:message key="common.label.support.createdby.name" bundle="<%=interfaces%>" /></td> 
    <td><html:text name="timkiem" property="nguoiTHTen" style="width:120px;" /></td>
    <td><bean:message key="common.label.support.createdby.position" bundle="<%=interfaces%>" /></td> 
    <td>
        <html:select name="support" property="nguoiTHCv" styleClass="combobox_w150">
            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="common.label.support.createdby.1" bundle="<%=interfaces%>"/></html:option>
            <html:option value="2"><bean:message key="common.label.support.createdby.2" bundle="<%=interfaces%>"/></html:option>
            <html:option value="3"><bean:message key="common.label.support.createdby.3" bundle="<%=interfaces%>"/></html:option> 
            <html:option value="4"><bean:message key="common.label.support.createdby.4" bundle="<%=interfaces%>"/></html:option>
        </html:select>
    </td>
</tr>

<tr>
    <td><bean:message key="common.label.support.other" bundle="<%=interfaces%>"/> :</td>
    <td><html:text name="timkiem" property="dungCuKhac" style="width:120px;" onkeypress="return searchKeyPress(event);"/></td>
    <td><bean:message key="hotro.list.label.diadiem" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="timkiem" property="ht_diaDiemTK" styleClass="combobox_w150">
            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="mapPLoaiDDiem" property="key" labelProperty="value" />
        </html:select>
    </td>
</tr>

<tr>
    <td><bean:message key="disability.search.form.mobile" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select name="timkiem" property="isHomeVisit" styleClass="combobox_w150">
            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:option value="0"><bean:message key="common.no" bundle="<%=interfaces%>"/></html:option>
            <html:option value="1"><bean:message key="common.yes" bundle="<%=interfaces%>"/></html:option> 
        </html:select>
    </td>
    <td colspan="2">&nbsp;</td>
</tr>

<tr>
    <td colspan="4" align="center">
      <div style="padding:16px 0">
        <span class="bt_left_Search">
            <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button property="_SEARCH_RESULT" styleClass="button" onclick="getSearchForm();">
                        <bean:message key="btn.search" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
        <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
        <span class="bt_left_reset">
            <span class="bt_right_reset">
                <span class="bt_center_reset">
                  <html:button property="_RESET" styleClass="button" onclick="cancelSearch('tdhotro');">
                      <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                  </html:button>
                </span>
            </span>
        </span>
          </div>     
    </td>
</tr>
</table>
</div>