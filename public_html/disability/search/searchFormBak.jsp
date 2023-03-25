<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<html:form action="searchPanel"  method="post"/>
<html:form action="searchdispeople" method="post" onsubmit="javascript:getSearchResult();return false;">

<div id="divSearchResult" style="display:none"></div>

<div id="divSearchForm">
    <ul id="tree">
        <li>
            <div class="bgr2"><a href="javascript:excutePostCategorys('_SEARCH')">T&#236;m ki&#7871;m t&#7893;ng h&#7907;p</a></div>
        </li>
    </ul>
	
<table cellpadding="0" cellspacing="0" border="0" width="686">
    <tr height="30">
        <td width="33%" valign="top" align="center" colspan="2">
            <div>
                <span style="padding:10px;">
                        <label for="_PANEL_DANGTAT">
                        <input type="checkbox" name="_PANEL_DANGTAT" id="_PANEL_DANGTAT" value="dangtat" onclick="getSearchPanel(this);"> 
                            <bean:message key="panel.dangtat.title.caption" bundle="<%=interfaces%>"/>
                        </label>
                </span>
                <span style="padding:10px;">
                        <label for="_PANEL_NHUCAU">
                        <input type="checkbox" name="_PANEL_NHUCAU" id="_PANEL_NHUCAU" value="nhucau" onclick="getSearchPanel(this);"> 
                            <bean:message key="panel.nhucau.title.caption" bundle="<%=interfaces%>"/>
                        </label>
                </span>
                <span style="padding:10px;">
                        <label for="_PANEL_HOTRO">
                        <input type="checkbox" name="_PANEL_HOTRO" id="_PANEL_HOTRO" value="hotro" onclick="getSearchPanel(this);"> 
                            <bean:message key="panel.hotro.title.caption" bundle="<%=interfaces%>"/>
                        </label>
                </span>
                        <span style="padding:10px;">
                        <label for="_PANEL_CANTHIEP">
                        <input type="checkbox" name="_PANEL_CANTHIEP" id="_PANEL_CANTHIEP" value="canthiep" onclick="getSearchPanel(this);"> 
                            <bean:message key="panel.canthiep.title.caption" bundle="<%=interfaces%>"/>
                        </label>
                </span>
                <!--</span>
                        <span style="padding:10px;">
                        <label for="_PANEL_TINHTRANG">
                        <input type="checkbox" name="_PANEL_TINHTRANG" id="_PANEL_TINHTRANG" value="tinhtrang" onclick="getSearchPanel(this);"> 
                            <bean:message key="panel.tinhtrang.title.caption" bundle="<%=interfaces%>"/>
                        </label>
                </span>-->
                </div>
            </td>
	</tr>
	
	<tr><td colspan="2"><img src="images/front/imgTop_ctn.gif" width="686" height="4" /></td></tr>
		
	<tr>
            <td style="border-right:1px solid #e2e2e2; border-left:1px solid #e2e2e2;" colspan="2">
            <div style="padding:20px 15px 0 15px">
              <table cellpadding="4" cellspacing="4" border="0" width="100%">
              <tr>
                  <td nowrap width="170px"><bean:message key="disability.form.label.tinhId" bundle="<%=interfaces%>"/> :</td>
                  <td id="id_option_area" align="left" colspan="3">
                      <jsp:include page="/disability/search/optionTinh.jsp" />                        
                  </td>
              </tr>
              <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.nkt" bundle="<%=interfaces%>"/> :</td>
                    <td align="left" nowrap>
                        <html:text name="timkiem" property="nkt" style="width:120px" />
                    </td>
                    <td nowrap width="120px"><bean:message key="disability.form.label.cmnd" bundle="<%=interfaces%>"/> </td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="cmnd" onblur="isNumber(this);" style="width:120px" />
                    </td>                  
                </tr>
                
                <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.sex" bundle="<%=interfaces%>"/> :</td>
                    <td align="left"  colspan="3" nowrap>
                        <html:select styleClass="inputbox" name="timkiem" property="sex">
                            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                            <html:option value="0"><bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/></html:option>
                            <html:option value="1"><bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/></html:option>
                        </html:select>
                    </td>                    
                </tr>
                
                <tr>
                  <td nowrap>H&#7885; v&#224; t&#234;n NQL :</td>
                  <td>
                      <html:text name="timkiem" property="tenNql" style="width:120px" />
                  </td>
                  <td nowrap>L&#297;nh v&#7921;c :</td>
                  <td>
                      <html:select styleClass="inputbox" name="timkiem" property="linhvucNql" style="width:160px">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1">Lao &#273;&#7897;ng</html:option>
                        <html:option value="2">Y t&#7871;</html:option>
                        <html:option value="3">Gi&#225;o d&#7909;c</html:option>
                      </html:select>
                  </td>
                </tr>
                
                <tr>                   
                    <td  nowrap width="170px">S&#7889; th&#7913; t&#7921; NKT :</td>
                    <td  align="left" >
                        <html:text name="timkiem" property="ma" style="width:120px" />
                    </td>
                     <td nowrap width="120px">M&#227; s&#7889; NKT :</td>
                    <td align="left" >
                        <html:text name="timkiem" property="maSo" style="width:120px" />
                    </td>
                </tr>
                
                <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.soNha" bundle="<%=interfaces%>"/> :</td>
                    <td align="left" >
                        <html:text name="timkiem" property="soNha" style="width:120px" />
                    </td>
                   <td nowrap width="120px"><bean:message key="disability.form.label.thonTo" bundle="<%=interfaces%>"/> :</td>
                    <td align="left" >
                        <html:text name="timkiem" property="thonTo" style="width:120px" />
                    </td>
                </tr>
		
                <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.ngaySinh" bundle="<%=interfaces%>"/> <bean:message key="agenda.label.from" bundle="<%=interfaces%>"/>:</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngaySinh" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngaySinh" style="width:120px"
                            onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngaySinh','dd/mm/yyyy');return false;">
                    </td>
                    <td nowrap width="120px"><bean:message key="agenda.label.to" bundle="<%=interfaces%>"/> :</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngaySinhDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngaySinhDen" style="width:120px" />
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngaySinhDen','dd/mm/yyyy');return false;">
                    </td>
                </tr>
		
                <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.ngayCapNhat" bundle="<%=interfaces%>"/> <bean:message key="agenda.label.from" bundle="<%=interfaces%>"/>:</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngayCapNhatTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatTu" style="width:120px" />
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatTu','dd/mm/yyyy');return false;">
                    </td>
                    <td nowrap width="120px"><bean:message key="agenda.label.to" bundle="<%=interfaces%>"/> :</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngayCapNhatDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayCapNhatDen" style="width:120px" />
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayCapNhatDen','dd/mm/yyyy');return false;">
                    </td>
                </tr>
                
                 <tr>
                    <td nowrap width="170px"><bean:message key="disability.form.label.ngayLap" bundle="<%=interfaces%>"/> <bean:message key="agenda.label.from" bundle="<%=interfaces%>"/>:</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngayLapTu" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayLapTu" style="width:120px" />
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayLapTu','dd/mm/yyyy');return false;">
                    </td>
                    <td nowrap width="120px"><bean:message key="agenda.label.to" bundle="<%=interfaces%>"/> :</td>
                    <td align="left"  nowrap>
                        <html:text name="timkiem" property="ngayLapDen" maxlength="10" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngayLapDen" style="width:120px" />
                            <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngayLapDen','dd/mm/yyyy');return false;">
                    </td>
                </tr>
            <tr>
	    	<td nowrap width="170px">N&#7841;n nh&#226;n ch&#7845;t &#273;&#7897;c da cam :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="chatdocDaCam" style="width:180px;">
	                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="0">Kh&#244;ng</html:option>
                        <html:option value="1">C&#243;</html:option>
	            </html:select>
                </td>	    	
                <td nowrap width="120px"><bean:message key="disability.form.label.dieuKienId" bundle="<%=interfaces%>"/> :</td>
                <td align="left" >
                    <html:select styleClass="inputbox" name="timkiem" property="dieuKienId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:options collection="BDieuKiens" property="id" labelProperty="name"/>
                    </html:select>
                </td>
	    </tr>	
            
            <tr>
	    	<td nowrap width="170px">Tr&#236;nh &#273;&#7897; V&#259;n h&#243;a :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="trinhDoId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="disability.form.label.trinhDoId.1" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="disability.form.label.trinhDoId.2" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="3"><bean:message key="disability.form.label.trinhDoId.4" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="4"><bean:message key="disability.form.label.trinhDoId.5" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="5"><bean:message key="disability.form.label.trinhDoId.6" bundle="<%=interfaces%>"/></html:option>			
                    </html:select>
                </td>	    	
                <td nowrap width="120px">M&#7913;c &#273;&#7897; khuy&#7871;t t&#7853;t :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="mucDo" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<logic:present name="BMucDos" >
                            <html:options collection="BMucDos" property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select>
	        </td>
	    </tr>  
          
            <tr>
	    	<td nowrap width="170px">T&#236;nh tr&#7841;ng gi&#225;o d&#7909;c hi&#7879;n nay :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="chucVuHT" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="disability.form.label.GiaoDuc.1" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="disability.form.label.GiaoDuc.2" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="3"><bean:message key="disability.form.label.GiaoDuc.3" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="4"><bean:message key="disability.form.label.GiaoDuc.4" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="5"><bean:message key="disability.form.label.GiaoDuc.5" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="6"><bean:message key="disability.form.label.GiaoDuc.6" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="7"><bean:message key="disability.form.label.GiaoDuc.7" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="8"><bean:message key="disability.form.label.GiaoDuc.8" bundle="<%=interfaces%>"/></html:option>
                    </html:select>  
                </td>	    	
                <td nowrap width="120px">Tr&#236;nh &#273;&#7897; chuy&#234;n m&#244;n :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="tdChuyenMon" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="disability.form.label.ChuyenMon.1" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="disability.form.label.ChuyenMon.2" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="3"><bean:message key="disability.form.label.ChuyenMon.3" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="4"><bean:message key="disability.form.label.ChuyenMon.4" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="5"><bean:message key="disability.form.label.ChuyenMon.5" bundle="<%=interfaces%>"/></html:option>			
                    </html:select>
	        </td>
	    </tr>
            
            <tr>
	    	<td nowrap width="170px">T&#236;nh tr&#7841;ng h&#244;n nh&#226;n :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="ttHonNhanId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="disability.form.label.ttHonNhanId1" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="disability.form.label.ttHonNhanId2" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="3"><bean:message key="disability.form.label.ttHonNhanId3" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="4"><bean:message key="disability.form.label.ttHonNhanId4" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="5"><bean:message key="disability.form.label.ttHonNhanId5" bundle="<%=interfaces%>"/></html:option>
                    </html:select>
                </td>	
                <td nowrap width="120px">Ngh&#7873; nghi&#7879;p hi&#7879;n nay :</td>
                <td>
                    <html:select styleClass="inputbox" name="timkiem" property="ngheNghiepHT" style="width:180px;">
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
	    	<td nowrap width="170px">Nguy&#234;n nh&#226;n khuy&#7871;t t&#7853;t :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="nguyenNhanId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			<logic:present name="BNguyenNhans" >
                            <html:options collection="BNguyenNhans" property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select>
                </td>               
                <td nowrap width="120px">Ngu&#7891;n n&#432;&#7899;c s&#7917; d&#7909;ng :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="nguonNuocId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="disability.form.label.nguonNuocId.1" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="disability.form.label.nguonNuocId.2" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="3"><bean:message key="disability.form.label.nguonNuocId.3" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="4"><bean:message key="disability.form.label.nguonNuocId.4" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="5"><bean:message key="disability.form.label.nguonNuocId.5" bundle="<%=interfaces%>"/></html:option>           
                     </html:select>
                </td>
	    </tr>
            
            <tr>
                <td nowrap width="170px">
                    <bean:message key="disability.form.label.dantoc" bundle="<%=interfaces%>"/> :</td>	    	
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="danTocId" style="width:180px;">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <logic:present name="BDanTocs" >
                            <html:options collection="BDanTocs" property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select>
                </td>
                <td nowrap width="120px">T&#236;nh tr&#7841;ng nh&#224; &#7903; :</td>
	    	<td>
                <html:select styleClass="inputbox" name="timkiem" property="nhaOId" style="width:180px;">
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="1"><bean:message key="disability.form.label.NhaOId.1" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="2"><bean:message key="disability.form.label.NhaOId.2" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="3"><bean:message key="disability.form.label.NhaOId.3" bundle="<%=interfaces%>"/></html:option>
                </html:select>
        </td>
      </tr>
	    	
	    <tr>
	    	<td valign="top">
                    <bean:message key="hotro.list.label.nguonhotro" bundle="<%=interfaces%>"/> :</td>	    	
	    	<td>
              <html:select styleClass="inputbox" name="timkiem" property="nguonHoTroIds" styleId="nguonHoTroIds" multiple="false" style="width:180px;height:100px;">                        
              <logic:present name="BNguonHoTros" >
                    <html:options collection="BNguonHoTros" property="id" labelProperty="name"/>
               </logic:present>
              </html:select>
	    	</td>
                
            <td colspan="2" valign="top">
            <table width="100%">
                <tr>
                    <td width="150px">
                        T&#236;nh tr&#7841;ng NKT :
                    </td>
                    <td>
                        <html:select styleClass="inputbox" name="timkiem" property="tt_phanLoaiIds" style="width:180px;">
                            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>                           
                            <logic:present name="BTinhTrangForms" >
                                <html:options collection="BTinhTrangForms" property="id" labelProperty="name"/>
                            </logic:present>                            
                        </html:select>                    
                    </td>
                </tr>
                <tr>
                    <td width="150px">
                        Nh&#243;m &#273;&#7889;i t&#432;&#7907;ng:
                    </td>
                    <td>
                        <html:select styleClass="inputbox" name="timkiem" property="doiTuong" style="width:180px;">
                            <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                            <logic:present name="BDoiTuongForms" >
                                <html:options collection="BDoiTuongForms" property="id" labelProperty="name"/>
                            </logic:present>
                        </html:select>                    
                    </td>
                </tr>
            </table>
            </td>
      </tr> 			
	    
      <tr>
	        <td  nowrap width="170px" valign="top"><bean:message key="disability.form.label.chuanDoan" bundle="<%=interfaces%>"/> :</td>
	        <td  align="left" colspan="3" >
                    <html:textarea  name="timkiem" property="chuanDoan"  style="width:445px;height:50px" />
	        </td>
	    </tr>
		
            <tr>
                <td width="170px" valign="top">D&#7841;ng t&#7853;t :</td>
                <td  align="left" colspan="3" >
                    <html:textarea  name="timkiem" property="dangTat"  style="width:445px;height:50px" />
                </td>
            </tr>
	    	
	    <tr>
	    	<td nowrap width="170px">S&#7889; NKT trong h&#7897; :</td>
	    	<td align="left" colspan="3">
                    <html:select styleClass="inputbox" name="timkiem" property="soNKT">
	                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1">1</html:option>
                        <html:option value="2">2</html:option>
                        <html:option value="3">3</html:option>
                        <html:option value="4">4</html:option>
                        <html:option value="5">5</html:option>
	            </html:select>
	        </td>
                <!--<td nowrap width="120px">Tham gia kh&#225;ng chi&#7871;n :</td>
	    	<td>
                    <html:select styleClass="inputbox" name="timkiem" property="khangChien">
                        <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="0">Kh&#244;ng</html:option>
                        <html:option value="1">C&#243;</html:option>
                    </html:select>
		</td>-->
	    </tr>
            
            <tr>
                <td colspan="4" align="center">
                    <div style="padding:16px 0">
                        <span class="bt_left_Search">
                                <span class="bt_right_Search">
                                    <span class="bt_center_Search">
                                        <html:submit property="_SEARCH_RESULT" styleClass="button">
                                            <bean:message key="disability.search.form.name" bundle="<%=interfaces%>"/>
                                        </html:submit>
                                    </span>
                                </span>
                        </span>
                        <span><img src="images/front/spacer.gif" width="7" height="1" /></span>
                        <span class="bt_left_reset">
                            <span class="bt_right_reset">
                                <span class="bt_center_reset">
                                    <html:reset property="_RESET" styleClass="button">
                                        <bean:message key="disability.search.button.reset" bundle="<%=interfaces%>"/>
                                    </html:reset>
                                </span>
                            </span>
                        </span>
                    </div>     
                </td>
            </tr>
	    </table>
        </div>
        </td>
    </tr>
    <tr><td colspan="2"><img src="images/front/imgBottom_ctn.gif" width="686" height="4" /></td></tr>
</table>
			 
<div class="khung-div-search">
    <div id="tddangtat"></div>    
    <div id="tdnhucau"></div>
    <div id="tdhotro"></div>
    <div id="tdcanthiep"></div>
    <div id="tdketqua"></div>
</div>
</div>
</html:form>