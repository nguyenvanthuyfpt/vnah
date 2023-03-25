<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<%
    String dataType = (String) request.getAttribute("dataType");
%>
<td colspan="4">
  <table width="100%">    
  <tr>
     <td nowrap width="150px"><bean:message key="common.label.location" bundle="<%=interfaces%>"/> :</td>
     <td align="left" colspan="3">
        <html:select styleClass="combobox_w150" name="timkiem" property="tinhId" 
            onchange="javascript:postAjax('searchdispeople','id_parameter_search',anchor + ':_SELECT_DATATYPE');">
                <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
        </html:select>
        <span style="color:#005BCC"><bean:write name="timkiem" property="tinhName" /></span>        
    </td>
  </tr>
  <logic:notEmpty name="districts"> 
    <tr>
        <td><bean:message key="district" bundle="<%=interfaces%>" /></td>
        <td>
            <html:select styleClass="combobox_w150" name="timkiem" property="qhuyenId" onchange="postAjax('searchdispeople','id_parameter_search',anchor + ':_SELECT_DATATYPE');">
              <logic:present name="districts">
                  <html:options collection="districts" property="id" labelProperty="name"/>
              </logic:present>
            </html:select>
        </td>
        <td>            
            <bean:message key="commune" bundle="<%=interfaces%>" />
        </td>
        <td>
            <html:select styleClass="combobox_w150" name="timkiem" property="pxaId" >
              <logic:present name="communes">
                  <html:options collection="communes" property="id" labelProperty="name"/>
              </logic:present>
            </html:select>
        </td>
    </tr>
    </logic:notEmpty>
  <tr>
      <td nowrap width="150px"><bean:message key="common.label.data.type" bundle="<%=interfaces%>"/> :</td>
      <td align="left">
          <html:select property="dataType" name="timkiem" onchange="onChangeDateType();">
              <html:option value="0"><bean:message key="common.search.value" bundle="<%=interfaces%>" /></html:option>
              <html:option value="1"><bean:message key="common.search.disability" bundle="<%=interfaces%>" /></html:option>
              <html:option value="2"><bean:message key="common.search.people" bundle="<%=interfaces%>" /></html:option>
          </html:select>
      </td>
      <td><bean:message key="common.label.year.report" bundle="<%=interfaces%>"/> : </td>
       <td>
            <html:select styleClass="inputbox" name="timkiem" property="yearReport">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2016">2016</html:option>
                <html:option value="2017">2017</html:option>
                <html:option value="2018">2018</html:option>
                <html:option value="2019">2019</html:option>
                <html:option value="2020">2020</html:option>
                <html:option value="2021">2021</html:option>
                 <html:option value="2022">2022</html:option>
                <html:option value="2023">2023</html:option>
                <html:option value="2024">2024</html:option>
                <html:option value="2025">2025</html:option>
            </html:select>
       </td>
  </tr>
  
  <logic:equal value="0" name="timkiem" property="dataType">
  <tr>
       <td><bean:message key="object.title.caption" bundle="<%=interfaces%>"/> : </td>
       <td colspan="3">
          <html:select styleClass="combobox_w400" name="timkiem" property="objId">
              <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
              <html:options collection="optObjects" property="id" labelProperty="name"/>
          </html:select>
       </td>
  </tr>
  <tr>
       <td><bean:message key="indicator.title.caption" bundle="<%=interfaces%>"/> : </td>
       <td colspan="3">
          <html:select styleClass="combobox_w400" name="timkiem" property="indId">
              <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
              <html:options collection="optIndicators" property="id" labelProperty="name"/>
          </html:select>
       </td> 
  </tr>
  </logic:equal>
  
  <!-- Person -->
  <logic:equal value="2" name="timkiem" property="dataType">      
      <tr>
           <td nowrap width="150px"><bean:message key="common.label.event.name" bundle="<%=interfaces%>"/> :</td>
           <td colspan="3">
                <html:select styleClass="combobox_w400" name="timkiem" property="eventId">
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:options collection="events" property="eventId" labelProperty="activity"/>
                </html:select>
           </td>
      </tr>
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.name" bundle="<%=interfaces%>"/> :</td>
          <td align="left" nowrap><html:text name="timkiem" property="perName" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
          <td nowrap width="120px"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/> :</td>
          <td align="left" nowrap>
              <html:select styleClass="inputbox" name="timkiem" property="perSex">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="0"><bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></html:option>
              </html:select>
          </td>
      </tr> 
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.role" bundle="<%=interfaces%>"/> :</td>
          <td align="left" nowrap><html:text name="timkiem" property="perTitle" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
          <td nowrap width="120px"><bean:message key="common.label.agency" bundle="<%=interfaces%>"/> :</td>
          <td align="left" ><html:text name="timkiem" property="perAgency" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
      </tr>
      <tr>
        <td nowrap width="150px"><bean:message key="common.label.createdate.from" bundle="<%=interfaces%>"/> :</td>
        <td align="left"  nowrap>
            <html:text name="timkiem" property="ngayCapNhatTu" maxlength="10" onkeypress="return searchKeyPress(event);" onblur="isDate(this);" styleId="ngayCapNhatTu" style="width:120px" />
            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatTu','dd/mm/yyyy');return false;">
              </td>
              <td nowrap width="120px"><bean:message key="common.label.createdate.to" bundle="<%=interfaces%>"/> :</td>
              <td align="left"  nowrap>
            <html:text name="timkiem" property="ngayCapNhatDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatDen" style="width:120px" />
            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatDen','dd/mm/yyyy');return false;">
        </td>
      </tr> 
  </logic:equal>
  
  <!-- Search Disability People -->  
  <logic:equal value="1" name="timkiem" property="dataType">      
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.name" bundle="<%=interfaces%>"/> :</td>
          <td align="left" nowrap><html:text name="timkiem" property="nkt" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
          <td nowrap width="120px">S&#7889; &#273;i&#7879;n tho&#7841;i NKT :</td>
          <td align="left" ><html:text name="timkiem" property="phoneNumber" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
      </tr>
      
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.address" bundle="<%=interfaces%>"/> :</td>
          <td align="left" ><html:text name="timkiem" property="soNha" style="width:120px" onkeypress="return searchKeyPress(event);"/></td>
          <td nowrap width="120px"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/> :</td>
          <td align="left" nowrap>
              <html:select styleClass="inputbox" name="timkiem" property="sex">
                  <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="0"><bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/></html:option>
                  <html:option value="1"><bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/></html:option>
              </html:select>
          </td>
      </tr>
      
      <tr>                   
            <td  nowrap width="150px">S&#7889; th&#7913; t&#7921; NKT :</td>
            <td  align="left" >
                <html:text name="timkiem" property="ma" style="width:120px" onkeypress="return searchKeyPress(event);"/>
            </td>
             <td nowrap width="120px">M&#227; s&#7889; NKT :</td>
            <td align="left" >
                <html:text name="timkiem" property="maSo" style="width:120px" onkeypress="return searchKeyPress(event);"/>
            </td>
      </tr>
      
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.regdate.from" bundle="<%=interfaces%>"/> :</td>
          <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayCapNhatTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatTu" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatTu','dd/mm/yyyy');return false;">
                </td>
                <td nowrap width="120px"><bean:message key="common.label.regdate.to" bundle="<%=interfaces%>"/> :</td>
                <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayCapNhatDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatDen" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatDen','dd/mm/yyyy');return false;">
          </td>
      </tr>  
      
       <tr>
          <td nowrap width="150px"><bean:message key="common.label.birth.from" bundle="<%=interfaces%>"/> :</td>
          <td align="left"  nowrap>
              <html:text name="timkiem" property="ngaySinhTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngaySinhTu" style="width:120px"
                  onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngaySinhTu','dd/mm/yyyy');return false;">
                </td>
                <td nowrap width="120px"><bean:message key="common.label.birth.to" bundle="<%=interfaces%>"/> :</td>
                <td align="left"  nowrap>
              <html:text name="timkiem" property="ngaySinhDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngaySinhDen" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngaySinhDen','dd/mm/yyyy');return false;">
          </td>
      </tr>
      
      <!--
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.re-examination.from" bundle="<%=interfaces%>"/> :</td>
          <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayTaiKhamTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayTaiKhamTu" style="width:120px"
                  onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayTaiKhamTu','dd/mm/yyyy');return false;">
                </td>
                <td nowrap width="120px"><bean:message key="common.label.re-examination.to" bundle="<%=interfaces%>"/> :</td>
                <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayTaiKhamDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayTaiKhamDen" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayTaiKhamDen','dd/mm/yyyy');return false;">
          </td>
      </tr>
      -->
      
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.nation" bundle="<%=interfaces%>"/> :</td>	    	
          <td>
              <html:select name="timkiem" property="danTocId" styleClass="combobox_w150">
                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <logic:present name="BDanTocs" >
                      <html:options collection="BDanTocs" property="id" labelProperty="name"/>
                  </logic:present>
              </html:select>
          </td>
          <td nowrap width="120px">Ngh&#7873; nghi&#7879;p :</td>
          <td>
              <html:select styleClass="combobox_w150" name="timkiem" property="ngheNghiepHT" >
                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
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
          <td nowrap width="150px"><bean:message key="common.label.disability.degree" bundle="<%=interfaces%>"/> :</td>
          <td>
              <html:select name="timkiem" property="mucDo" styleClass="combobox_w150">
                  <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                  <logic:present name="BMucDos" >
                      <html:options collection="BMucDos" property="id" labelProperty="name"/>
                  </logic:present>
              </html:select>
          </td>          
          <td><bean:message key="common.label.status.profile" bundle="<%=interfaces%>"/> :</td>
          <td align="left">
            <html:select property="statusId" name="timkiem" styleClass="combobox_w150">
                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="0"><bean:message key="common.label.status.active" bundle="<%=interfaces%>" /></html:option>
                <html:option value="1"><bean:message key="common.label.status.close" bundle="<%=interfaces%>" /></html:option>
            </html:select>
          </td>
      </tr>         
      
      <tr>
          <td  nowrap width="150px" valign="top"><bean:message key="common.label.disability.diagnose" bundle="<%=interfaces%>"/> :</td>
          <td  align="left" colspan="3" >
        <html:textarea  name="timkiem" property="chuanDoan"  style="width:445px;height:50px" onkeypress="return searchKeyPress(event);"/>
          </td>
      </tr>
      
      <tr>
          <td width="150px" valign="top"><bean:message key="common.label.disability.forms" bundle="<%=interfaces%>" /> :</td>
          <td  align="left" colspan="3" >
              <html:textarea  name="timkiem" property="dangTat"  style="width:445px;height:50px" onkeypress="return searchKeyPress(event);"/>
          </td>
      </tr>
      
       <tr>
          <td width="150px" valign="top"><bean:message key="common.label.support.desc" bundle="<%=interfaces%>" /> :</td>
          <td  align="left" colspan="3" >
              <html:textarea  name="timkiem" property="moTaDCTG"  style="width:445px;height:50px" onkeypress="return searchKeyPress(event);"/>
          </td>
      </tr>
      
       <tr>
          <td><bean:message key="common.label.dis.project" bundle="<%=interfaces%>" /></td>
          <td>
            <html:select styleClass="combobox_w120" name="timkiem" property="duAnId">
                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="0"><bean:message key="common.label.dis.project.direct" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="common.label.dis.project.inclusion3" bundle="<%=interfaces%>"/></html:option>
             </html:select>   
          </td>
          <td><bean:message key="common.label.dis.dacam.select" bundle="<%=interfaces%>" /></td>
          <td>
              <html:select styleClass="combobox_w120" name="timkiem" property="dioxin">
                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="0">Kh&#244;ng</html:option>
                <html:option value="1">C&#243;</html:option>
              </html:select>
          </td>
      </tr>
      
      <tr height="10">
          <td colspan="4">&nbsp;</td>
      </tr>
      
      <tr>
          <td colspan="4" align="center">   
            <div id="chkSearch">
                <span style="padding:10px;">
                  <label for="_PANEL_DANGTAT">
                  <input type="checkbox" name="_PANEL_DANGTAT" id="_PANEL_DANGTAT" value="dangtat" class="tddangtat" onclick="getSearchPanel(this);"> 
                    T&#236;m ki&#7871;m theo Ph&#226;n lo&#7841;i
                  </label>
                </span>
                <span style="padding:10px;">
                  <label for="_PANEL_NHUCAU">
                  <input type="checkbox" name="_PANEL_NHUCAU" id="_PANEL_NHUCAU" value="nhucau" class="tdnhucau" onclick="getSearchPanel(this);"> 
                    T&#236;m ki&#7871;m theo Nhu c&#7847;u DVHT
                  </label>
                </span>
                <span style="padding:10px;">
                  <label for="_PANEL_HOTRO">
                  <input type="checkbox" name="_PANEL_HOTRO" id="_PANEL_HOTRO" value="hotro" class="tdhotro" onclick="getSearchPanel(this);"> 
                    T&#236;m ki&#7871;m theo Cung c&#7845;p DVHT
                  </label>
                </span>
            </div>	
          </td>
      </tr>
      </logic:equal>
      
      <!-- Value -->
      <logic:equal value="0" name="timkiem" property="dataType">
      <tr>
          <td nowrap width="150px"><bean:message key="common.label.createdate.from" bundle="<%=interfaces%>"/> :</td>
          <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayCapNhatTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatTu" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatTu','dd/mm/yyyy');return false;">
                </td>
                <td nowrap width="120px"><bean:message key="common.label.createdate.to" bundle="<%=interfaces%>"/> :</td>
                <td align="left"  nowrap>
              <html:text name="timkiem" property="ngayCapNhatDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatDen" style="width:120px" />
              <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatDen','dd/mm/yyyy');return false;">
          </td>
      </tr>
      </logic:equal>
      
    </table>
</td> 