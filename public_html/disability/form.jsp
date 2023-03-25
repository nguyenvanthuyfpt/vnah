<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
  window.onload=function(){
      document.disability.soNha.focus();
  }
  
  function changeOption(e){
      // Trinh do chuyen mon
      if (e.name == "tdChuyenMon") {
          if (e.value == "5") {
              document.getElementById("tdChuyenMon").style.display = "";
              document.disability.chuyenMonKhac.focus();
          } else {
              document.disability.chuyenMonKhac.value = "";
              document.getElementById("tdChuyenMon").style.display = "none";
          }
      }
      
      // Tinh trang giao duc
      if (e.name == "chucVuHT") {
          if (e.value == "8") {
              document.getElementById("chucVuHT").style.display = "";
              document.disability.giaoDucKhac.focus();
          } else {
              document.disability.giaoDucKhac.value = "";
              document.getElementById("chucVuHT").style.display = "none";
          }
      }
      
      // Nghe nghiep
      if (e.name == "ngheNghiepHT") {
          if (e.value == "10") {
              document.getElementById("ngheNghiepHT").style.display = "";
              document.disability.ngheNghiepKhac.focus();
          } else {
              document.disability.ngheNghiepKhac.value = "";
              document.getElementById("ngheNghiepHT").style.display = "none";
          }
      }
      
      // Tinh trang hon nhan
      if (e.name == "ttHonNhanId") {
          if (e.value == "5") {
              document.getElementById("ttHonNhanId").style.display = "";
              document.disability.honNhanKhac.focus();
          } else {
              document.disability.honNhanKhac.value = "";
              document.getElementById("ttHonNhanId").style.display = "none";
          }
      }    
  }
</script>
<html:form action="disability" method="POST" >
<html:hidden  name="disability" property="id" />

<div class="list-view" >
    <table width="100%" cellspacing="2" cellpadding="4" border="0">    
    <tr>
        <td><bean:message key="disability.form.label.tinhId" bundle="<%=interfaces%>"/> :</td>
        <td colspan="3" align="left" >
            <html:select styleClass="inputbox" name="kpi" property="locationId" onchange="post('disability',anchor + ':_SELECT_IDTINH');">
                <logic:present name="BTreeTinhs">
                    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                </logic:present>
            </html:select>
            <span style="color:#005BCC">(<bean:write name="disability" property="tinhName" />)</span>
         </td>

    </tr>
    	
    <tr>
        <td><bean:message key="disability.form.label.soNha" bundle="<%=interfaces%>"/>:</td>
        <td  align="left" >
           <html:text name="disability" property="soNha" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        </td>
        <td><bean:message key="disability.form.label.phoneNumber" bundle="<%=interfaces%>"/>:</td>
        <td align="left" >
               <html:text name="disability" property="phoneNumber" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>               
         </td>
    </tr>
    	
    <tr>
        <td><bean:message key="disability.form.label.lastupdate" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left">
               <html:text name="disability" property="dateLastUpdate" onkeypress="return formatDate(event,this);" 
               onblur="isDate(this);" styleId="dateLastUpdate" style="width:80px" 
               onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
               <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateLastUpdate','dd/mm/yyyy');return false;">
         </td>
        <td>Th&#7893;n / T&#7893; / &#7844;p:</td>
        <td align="left" >
               <html:text name="disability" property="thonTo" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>               
        </td>
    </tr>
    
    <tr height="10"><td colspan="4"><hr></hr></td></tr>	    	
    <tr>
        <td colspan="4">
            <div><b>Ng&#432;&#7901;i qu&#7843;n l&#253; tr&#7921;c ti&#7871;p:</b></div>
        </td>        
    </tr>
    
    <tr>
        <td>H&#7885; v&#224; t&#234;n:</td>
        <td><html:text name="disability" property="nguoiQuanLyTen" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
        <td>&#272;&#417;n v&#7883; c&#244;ng t&#225;c:</td>
        <td><html:text name="disability" property="nguoiQuanLyDonVi" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
    </tr>
    
    <tr>
        <td>L&#297;nh v&#7921;c:</td>
        <td>
          <html:select styleClass="inputbox" name="disability" property="nguoiQuanLyLinhVuc" style="width:160px">
              <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
              <html:option value="1">Lao &#273;&#7897;ng</html:option>
              <html:option value="2">Y t&#7871;</html:option>
              <html:option value="3">Gi&#225;o d&#7909;c</html:option>
          </html:select>
        </td>
        <td  colspan="2">&nbsp;</td>
    </tr>
    
    <tr height="10"><td colspan="4" height="19"><hr></hr></td></tr>		    	
    <tr><td colspan="4"><b>Th&#244;ng tin v&#7873; NKT</b></td></tr>
    	
    <tr height="25">
        <td nowrap>S&#7889; TT NKT<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left" id="idCodeDis">
            <html:text name="disability" property="ma" readonly="true" style="width:160px;cursor:pointer;" onmouseover="Tip('S&#7889; TT NKT kh&#244;ng &#273;&#432;&#7907;c ph&#233;p thay &#273;&#7893;i',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
        <td nowrap width="120px"><bean:message key="disability.form.label.nkt" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left" nowrap>
            <html:text name="disability" property="nkt" style="width:160px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        </td>
    </tr>
      
    <tr height="25">
        <td nowrap><bean:message key="disability.form.label.ngaySinh" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left"  nowrap>
                <html:text name="disability" property="ngaySinh" onkeypress="return formatDate(event,this);" onblur="isDate(this);" styleId="ngaySinh" style="width:80px" 
                onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
                <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'ngaySinh','dd/mm/yyyy');return false;">
        </td>
        <td nowrap>M&#227; s&#7889; NKT:</td>
        <td align="left" nowrap>
            <html:text name="disability" property="ma_nkt" style="width:120px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        </td>
    </tr>
      
    <tr height="25">
        <td><bean:message key="disability.form.label.cmnd" bundle="<%=interfaces%>"/>:</td>
        <td align="left">
            <html:text name="disability" property="cmnd" style="width:120px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/>
        </td>
        <td><bean:message key="disability.form.label.dantocId" bundle="<%=interfaces%>"/> :</td>
        <td align="left" >
            <html:select styleClass="inputbox" name="disability" property="dantocId" style="width:160px;">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BDantocForms">
                        <html:options collection="BDantocForms" property="id" labelProperty="name"/>
                    </logic:present>
            </html:select>
         </td>
    </tr>
      
    <tr height="25">
        <td><bean:message key="disability.form.label.trinhDoId" bundle="<%=interfaces%>"/>:</td>
        <td align="left" >
            <html:select styleClass="inputbox" name="disability" property="trinhDoId" style="width:180px;">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="disability.form.label.trinhDoId.1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="disability.form.label.trinhDoId.2" bundle="<%=interfaces%>"/></html:option>
                <html:option value="3"><bean:message key="disability.form.label.trinhDoId.4" bundle="<%=interfaces%>"/></html:option>
                <html:option value="4"><bean:message key="disability.form.label.trinhDoId.5" bundle="<%=interfaces%>"/></html:option>
                <html:option value="5"><bean:message key="disability.form.label.trinhDoId.6" bundle="<%=interfaces%>"/></html:option>			
            </html:select>
        </td>
        <td><bean:message key="disability.form.label.sex" bundle="<%=interfaces%>"/>:</td>
        <td align="left"  nowrap>
            <html:radio name="disability" property="sex" value="0" />
            <bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/>
            <html:radio name="disability" property="sex" value="1" />
            <bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/>
        </td>
    </tr>
            
    <tr height="25">
        <td><bean:message key="disability.form.label.tdChuyenMon" bundle="<%=interfaces%>"/>:</td>
        <td align="left">
            <html:select styleClass="inputbox" name="disability" property="tdChuyenMon" style="width:180px;" onchange="changeOption(this);" >
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="disability.form.label.ChuyenMon.1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="disability.form.label.ChuyenMon.2" bundle="<%=interfaces%>"/></html:option>
                <html:option value="3"><bean:message key="disability.form.label.ChuyenMon.3" bundle="<%=interfaces%>"/></html:option>
                <html:option value="4"><bean:message key="disability.form.label.ChuyenMon.4" bundle="<%=interfaces%>"/></html:option>
                <html:option value="5"><bean:message key="disability.form.label.ChuyenMon.5" bundle="<%=interfaces%>"/></html:option>			
            </html:select>
        </td>
        <bean:define id="strChuyenMon" name="disability" property="chuyenMonKhac" />
        <td colspan="2">
            <div id="tdChuyenMon" <%="".equals(strChuyenMon)?"style='display:none;'":""%>>
              <table width="100%">
              <tr>
                <td width="120px" nowrap="nowrap"><bean:message key="disability.form.label.ChuyenMon.other" bundle="<%=interfaces%>" /> :</td>
                <td><html:text name="disability" property="chuyenMonKhac" style="width:150px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
              </tr>
              </table>
            </div>
        </td>
    </tr>
   
    <tr height="25">
        <td><bean:message key="disability.form.label.chucVuHT" bundle="<%=interfaces%>"/>:</td>
        <td align="left">      
            <html:select styleClass="inputbox" name="disability" property="chucVuHT" style="width:180px;" onchange="changeOption(this);">
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
        <bean:define id="strGiaoDuc" name="disability" property="giaoDucKhac" />
        <td colspan="2">
            <div id="chucVuHT" <%="".equals(strGiaoDuc)?"style='display:none;'":""%>>
                <table width="100%">
                <tr>
                  <td width="120px" nowrap="nowrap"><bean:message key="disability.form.label.GiaoDuc.other" bundle="<%=interfaces%>" /> :</td>
                  <td><html:text name="disability" property="giaoDucKhac" style="width:150px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
                </tr>
                </table>
            </div>
        </td>
    </tr>
   
    <tr height="25">
        <td><bean:message key="disability.form.label.ngheNghiepHT" bundle="<%=interfaces%>"/>:</td>
        <td align="left" >
            <html:select styleClass="inputbox" name="disability" property="ngheNghiepHT" style="width:180px;" onchange="changeOption(this);">
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
        <bean:define id="strNgheNghiep" name="disability" property="ngheNghiepKhac" />
        <td colspan="2">
            <div id="ngheNghiepHT"  <%="".equals(strNgheNghiep)?"style='display:none;'":""%> >
            <table width="100%">
            <tr>
              <td width="120px" nowrap="nowrap"><bean:message key="disability.form.label.NgheNghiep.other" bundle="<%=interfaces%>" /> :</td>
              <td><html:text name="disability" property="ngheNghiepKhac" style="width:150px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
            </tr>
            </table>
            </div>
        </td>
    </tr>	
        
    <tr height="25">
        <td><bean:message key="disability.form.label.ttHonNhanId" bundle="<%=interfaces%>"/>:</td>
        <td align="left">
            <html:select styleClass="inputbox" name="disability" property="ttHonNhanId" onchange="changeOption(this);">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="disability.form.label.ttHonNhanId1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="disability.form.label.ttHonNhanId2" bundle="<%=interfaces%>"/></html:option>
                <html:option value="3"><bean:message key="disability.form.label.ttHonNhanId3" bundle="<%=interfaces%>"/></html:option>
                <html:option value="4"><bean:message key="disability.form.label.ttHonNhanId4" bundle="<%=interfaces%>"/></html:option>
                <html:option value="5"><bean:message key="disability.form.label.ttHonNhanId5" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </td>
        <bean:define id="strHonNhan" name="disability" property="honNhanKhac" />
        <td colspan="2">
            <div id="ttHonNhanId"  <%="".equals(strHonNhan)?"style='display:none;'":""%> >
            <table width="100%">
            <tr>
              <td width="120px;" nowrap="nowrap"><bean:message key="disability.form.label.ttHonNhanId.other" bundle="<%=interfaces%>" /> :</td>
              <td><html:text name="disability" property="honNhanKhac" style="width:150px" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
             </tr>
            </table>
            </div>	
        </td>
    </tr>
    
    <tr height="25">
            <td>T&#7893;ng s&#7889; con:</td>
            <td><html:text name="disability" property="tongSoCon" onblur="isNumber(this);" style="width:50px;text-align:right;" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
            <td>S&#7889; con d&#432;&#7899;i 16</td>
            <td><html:text name="disability" property="tongSoConDuoi16" onblur="isNumber(this);" style="width:50px;text-align:right;" onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()"/></td>
    </tr>
  
    <tr height="25">
        <td>L&#224; n&#7841;n nh&#226;n ch&#7845;t &#273;&#7897;c da cam:</td>
        <td>
            <html:select styleClass="inputbox" name="disability" property="chatDocDaCam" style="width:80px">
                <html:option value="0">Kh&#244;ng</html:option>
                <html:option value="1">C&#243;</html:option>
            </html:select>
        </td>
        <td>Nh&#243;m &#273;&#7889;i t&#432;&#7907;ng:</td>
        <td>
            <html:select styleClass="inputbox" name="disability" property="doiTuongId" style="width:160px;" >
	            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
	            <logic:present name="BDoiTuongForms" >
	                <html:options collection="BDoiTuongForms" property="id" labelProperty="name"/>
	            </logic:present>
            </html:select>
        </td>
        <!--<td nowrap >Tham gia kh&#225;ng chi&#7871;n</td>
        <td>
            <html:select styleClass="inputbox" name="disability" property="khangChien" style="width:80px">
                <html:option value="0">Kh&#244;ng</html:option>
                <html:option value="1">C&#243;</html:option>
            </html:select>
        </td>-->
    </tr>
            
    <!--<tr height="25">
        <td><bean:message key="disability.form.label.TroCap" bundle="<%=interfaces%>"/>:</td>
        <td colspan="3">
            <html:select styleClass="inputbox" name="disability" property="troCapId" onchange="post('disability',anchor + ':_SELECT_IDTRINHDO');">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:option value="1"><bean:message key="disability.form.label.TroCap.1" bundle="<%=interfaces%>"/></html:option>
                <html:option value="2"><bean:message key="disability.form.label.TroCap.2" bundle="<%=interfaces%>"/></html:option>
                <html:option value="3"><bean:message key="disability.form.label.TroCap.3" bundle="<%=interfaces%>"/></html:option>
            </html:select>
        </td>
    </tr>
    
    <logic:equal name="disability" property="troCapId" value="3">
    <tr height="25">
            <td nowrap><span style="font-weight:bold;font-style:italic;"><bean:message key="disability.form.label.TroCap.other" bundle="<%=interfaces%>" /> :</span></td>
            <td colspan="3"><html:text name="disability" property="troCapKhac" style="width:150px" /></td>
    </tr>		
    </logic:equal>

    <tr height="25">
        <td nowrap><bean:message key="disability.form.label.chuanDoan" bundle="<%=interfaces%>"/>:</td>
        <td align="left" colspan="3" >
            <html:textarea  name="disability" property="chuanDoan"  style="width:500px;height:100px" />
        </td>
    </tr>-->

    

    <tr height="10"><td colspan="4"><hr></hr></td></tr>	

    <tr>
        <td colspan="4">
            <jsp:include page="/disability/chuho/form.jsp" />
        </td>
    </tr>
            
    <tr height="10"><td colspan="4"><hr></hr></td></tr>

    <tr>
        <td colspan="4">
            <jsp:include page="/disability/nguoichamsoc/form.jsp" />
        </td>
    </tr>
    
    <!--<logic:notEqual name="disability" property="id" value="0">
        <tr height="10"><td colspan="4"></td></tr>
        <tr>
            <td>Ng&#224;y c&#7853;p nh&#7853;t</td>
            <td></td>
            <td>Ng&#432;&#7901;i c&#7853;p nh&#7853;t</td>
            <td><html:text name="disability" property="user" value="<%=me.getFullName()%>" /></td>
        </tr>
    </logic:notEqual>-->
            
    <tr>
        <td align="left" colspan="4" style="padding-bottom:5px;">
                <% if(request.getSession().getAttribute("10")!=null){%> 
            <div class="buttom">
                <% if(request.getSession().getAttribute("10.01")!=null){%>
                <logic:equal name="disability" property="id" value="0">
                        <html:button property="_CREATE" styleClass="button" onclick="javascript:if(checkSubmitBKT(this.form)) {  post('disability',anchor + ':_CREATE'); }" >
                                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>
                </logic:equal>
                <%}%>			
                <bean:define id="status" name="disability" property="trangthai" type="Integer" />                
                <logic:notEqual name="disability" property="id" value="0">
                    <% if(request.getSession().getAttribute("10.02")!=null){%>
                    <% if(status!=9){%>
                    <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmitBKT(this.form)) { post('disability',anchor + ':_EDIT'); }"   >                 
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>        
                    <%}}%>
                                    
                    <% if(request.getSession().getAttribute("10.03")!=null){%>
                    <% if(status!=9){%>
                    <html:button property="_DELETE" styleClass="button" onclick="javascript:if(messageDelete())post('disability',anchor + ':_DELETE');"   >
                        <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                    </html:button>
                    <%}}%>	

                    <html:button property="_REPORT" styleClass="button" onclick="post('disability',anchor + ':_REPORT');remove('disability',anchor);"   >
                        <bean:message key="action.report" bundle="<%=interfaces%>"/>
                    </html:button>        
                </logic:notEqual>
            </div>		
        <%}%>
        </td>
    </tr>
    </table>
</div>
</html:form>