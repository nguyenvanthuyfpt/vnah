<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div class="content-div">
  <div class="content-calendar-2" align="left">
      <font style="font-size:13px;">
          <b>T&#236;m ki&#7871;m theo Ph&#226;n lo&#7841;i</b>
      </font>
  </div>
  <%
    String checkBox="";
    String dangTatIdsTemp="";
  %>
  <logic:notEmpty name="phanloai" property="dangTatIds">
          <bean:define name="phanloai" property="dangTatIds" id="dangTatIds" type="java.lang.String" />
          <%dangTatIdsTemp=dangTatIds;%>
  </logic:notEmpty>
  
  <div class="pd-5">
      <table cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
        <tr>
          <logic:present name="BPhanLoais">
            <logic:notEmpty name="BPhanLoais">
              <logic:iterate id="bean" name="BPhanLoais" indexId="i" type= "com.form.disability.categorys.FDangTat">
              <%if(bean.getParentID()==0){
                 checkBox = (dangTatIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "checked":"";
              %>
                  <td align="left" valign="top">
                      <input type="checkbox" name="dt_phanLoaiIds" id="dt_phanLoaiIds" value="<%=bean.getId()%>" >
                      <strong><%=bean.getName()%></strong>
                  </td>
              <%}%>
              </logic:iterate>   
            </logic:notEmpty>
          </logic:present>
        </tr>	
      </table>
  </div>
  
  <div class="pd-5">
      <table cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
          <tr>
              <td width="20%"><bean:message key="phanloai.list.label.createDate" bundle="<%=interfaces%>"/> :</td>
              <td>    
                  <html:text name="timkiem" property="dt_ngayTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" style="width:80px" styleClass="textfield_date" />
                  <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dt_ngayTu','dd/mm/yyyy');return false;" />
              </td>
              <td><bean:message key="common.label.createdate.to" bundle="<%=interfaces%>"/>:</td>
              <td>
                  <html:text name="timkiem" property="dt_ngayDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" style="width:80px" styleClass="textfield_date" />
                  <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dt_ngayDen','dd/mm/yyyy');return false;" />
              </td>
          </tr>
          <tr>
              <td><bean:message key="common.label.tkham.from" bundle="<%=interfaces%>"/> :</td>
              <td>    
                  <html:text name="timkiem" property="dt_tkhamTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" style="width:80px" styleClass="textfield_date" />
                  <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dt_tkhamTu','dd/mm/yyyy');return false" />
              </td>
              <td><bean:message key="common.label.tkham.to" bundle="<%=interfaces%>"/>:</td>
              <td>
                  <html:text name="timkiem" property="dt_tkhamDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" style="width:80px" styleClass="textfield_date" />
                  <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dt_tkhamDen','dd/mm/yyyy');return false;" />
              </td>
          </tr>
          
          <tr>
              <td>Nguy&#234;n nh&#226;n :</td>
              <td>
                  <html:select name="timkiem" property="nguyenNhanId" styleClass="combobox_w150">
                      <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                      <logic:present name="BNguyenNhans" >
                      <html:options collection="BNguyenNhans" property="id" labelProperty="name"/>
                      </logic:present>
                  </html:select>
              </td>
              <td><bean:message key="common.label.address.exam" bundle="<%=interfaces%>"/>:</td>
              <td>
                  <html:select name="timkiem" property="dt_diaDiem" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:options collection="mapPLoaiDDiem" property="key" labelProperty="value" />
                  </html:select>
              </td>
          </tr>
          
          <tr>
              <td>T&#236;nh tr&#7841;ng khuy&#7871;t t&#7853;t</td>
              <td colspan="3">
                  <html:textarea  name="timkiem" property="dt_tinhTrang" style="width:530px;height:50px" />
              </td>
          </tr>
          <tr>
              <td colspan="4" align="center">
                  <div style="padding:16px 0">
                      <span class="bt_left_Search"><span class="bt_right_Search"><span class="bt_center_Search">
                          <html:button property="_SEARCH_RESULT" styleClass="button" onclick="getSearchForm();">
                            <bean:message key="btn.search" bundle="<%=interfaces%>"/>
                        </html:button>
                      </span></span></span>
                      <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
                     
                      <span class="bt_left_reset"><span class="bt_right_reset"><span class="bt_center_reset">
                          <html:button property="_RESET" styleClass="button" onclick="cancelSearch('tddangtat');">
                              <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span></span></span>
                    </div>     
              </td>
        </tr>
      </table>
  </div>
</div>