<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>	
<script type="text/javascript">
    function getSearchPanel(obj) {
         $(".khung-div-search").show();
        if (trim(getObj('td' + obj.value).innerHTML) == '') {
            messageImg('td' + obj.value, '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
            postAjax('searchPanel', 'td' + obj.value, anchor + ':_PANEL:panel:' + obj.name);
        }    
        hideshow('td' + obj.value, obj.checked);
    }
</script>

<html:hidden name="support" property="idNkt" styleId="idNkt" />
<bean:define id="strStatusId" name="support" property="statusId" type="java.lang.Integer" />
<%
    String checkBox="";
    String subCheck="";
    String hotroIdsTemp="";
    String supportSelTemp = "";
    String strCreateDate = "";
    int supportStt = 0;
    int supportNguonId = 0;
    int hotroId = 0;    
    int dtlId = 0;
    
    int statusId = strStatusId;
    java.util.Map<String, String> map_hotro_kn_chitra = (java.util.Map<String, String>) request.getAttribute("map_hotro_kn_chitra");
    java.util.Map<String, String> map_hotro_the_bhyt = (java.util.Map<String, String>) request.getAttribute("map_hotro_the_bhyt");
    java.util.Map<String, String> map_hotro_sd_the = (java.util.Map<String, String>) request.getAttribute("map_hotro_sd_the");
    java.util.Map<String, String> map_hotro_sd_the_phcn = (java.util.Map<String, String>) request.getAttribute("map_hotro_sd_the_phcn");
%>
<logic:notEmpty name="support" property="hotroIds">
<bean:define name="support" property="hotroIds" id="hotroIds" type="java.lang.String" />	    
<%	
    hotroIdsTemp = hotroIds;
%>
</logic:notEmpty>

<logic:notEmpty name="support" property="supportSel">
<bean:define name="support" property="supportSel" id="supportSel" type="java.lang.String" />
<%	
    supportSelTemp = supportSel;
%>
</logic:notEmpty>

<logic:notEqual value="0" name="support" property="idNkt">
    <bean:define id="nktId" name="support" property="idNkt" type="java.lang.Integer" />
    <bean:define id="createDate" name="support" property="dateCreate" type="java.lang.String" />
    <bean:define id="sttSP" name="support" property="stt" type="java.lang.Integer" />
    <bean:define id="sttNguon" name="support" property="nguonHoTroId" type="java.lang.Integer" />
    <%  
        dtlId = nktId;
        strCreateDate = createDate;
        supportStt = sttSP;
        supportNguonId = sttNguon;
    %>
</logic:notEqual>

<div>
    <div align="left" class="fullName">
        <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>
    </div>  
    
    <div style="overflow-x:scroll;height:400px;">
		<table cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
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
				    <div><strong><%=bean.getName().replaceAll("--- ","")%></strong></div>
				    <table border="0" width="100%">			
				<%}else if(bean.getLevel()==1){%>

				<%
            String SQL_HOTRO_LEVEL_1="SELECT hotro_id, parent_id, " +  ((statusId==1) ? "name_htro":"name") + " FROM DR_HOTRO WHERE parent_id = ? order by order_by";
            com.form.FBeans beans = new com.form.FBeans();
            beans = new com.bo.tree.BTreeView().getTree(bean.getId(),false,SQL_HOTRO_LEVEL_1,"","");
            request.setAttribute("BTreeHotros",beans);
				%>
				    <tr>
				    	<td>
				    	<%	
                  checkBox = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "checked" : "";	%>				       
			            <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                      <td colspan="2">
                        <%
                            String display = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "":"style='display:none;'";
                            String style = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "style='font-style:italic;font-weight:bold;'":"";
                        %>
                        <input type="checkbox" name="supportIds" <%=checkBox%> onclick="showCongcu(this.value,this.checked);" value="<%=bean.getId()%>" />
                        <%                       
                            String hrefPopup = "javascript:openWindow('kpi',anchor +':_PREPARED_DGIA:dtlId:"+dtlId+":supportId:"+bean.getId()+":dateCreate:"+strCreateDate+"');remove('kpi',anchor);";
                        %>
                          <span <%=style%> >
                              <logic:equal name="support" property="statusId" value="0">
                                    <%=bean.getName().replaceAll("--- ","")%>
                               </logic:equal>   
                               <logic:equal name="support" property="statusId" value="1">
                                    <%if(hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0){%>
                                        <a href="<%=hrefPopup%>" > <%=bean.getName().replaceAll("--- ","")%></a>
                                    <%}else{%>
                                        <%=bean.getName().replaceAll("--- ","")%>
                                    <%}%>
                               </logic:equal>
                          </span>
                          <%
                              String displaySub = (supportSelTemp.indexOf("#"+bean.getId()+"#")>=0) ? "":"style='display:none;'";                              
                          %>
                          <div id="<%=bean.getId()%>" <%=displaySub%>>
                               <logic:iterate id="subBean" name="BTreeHotros" indexId="i" type= "com.form.disability.categorys.FTinh">
                               <%if(subBean.getLevel()==0){%>
                                  &nbsp;&nbsp;&nbsp;
                               <%}else{%>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <%}%>
                               <input type="checkbox" name="supportIds" <%=(hotroIdsTemp.indexOf("#"+subBean.getId()+"#")>=0) ? "checked" : ""%> <%=subCheck%> value="<%=subBean.getId()%>" />
                               &nbsp;<i><%=subBean.getName().replaceAll("--- ","")%></i><br/>
                               <%if(bean.getId()==13 && subBean.getId()==61){%>
                                  <!-- Nhap DCTG Khac  -->
                                  &nbsp;&nbsp;&nbsp;Kh&#225;c &nbsp;<html:text name="support" property="phcnKhac" style="width:150px" /><br/>
                               <%}%>
                               </logic:iterate>
                          </div>
                      </td>
                  </tr>
            </table>
            
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

<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<tr height="25">
    <td width="30%">
        <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>:
    </td>
    <td colspan="3">
        <input type="text" name="createDate" id="createDate" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);" style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="createDate"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;">
    </td>    
</tr>

<logic:equal name="support" property="hasVisit" value="1">
<tr height="25">
    <td colspan="2">         
        <img src="<%=contextPath%>/images/geography.png" />
        <bean:message key="common.label.geography" bundle="<%=interfaces%>"/>:
        <i><bean:message key="common.label.latitude" bundle="<%=interfaces%>"/> 
        <bean:write name="support" property="latitude"/></i>
        <i><bean:message key="common.label.longitude" bundle="<%=interfaces%>"/>
        <bean:write name="support" property="longitude"/></i>
    </td>
    <td colspan="2">
        <img src="<%=contextPath%>/images/location.png" />
        <bean:message key="common.label.homevisit" bundle="<%=interfaces%>"/>:
        <bean:write name="support" property="location"/>
    </td>
</tr>
<tr height="25">
    <td colspan="2">        
        <bean:message key="common.label.time.start" bundle="<%=interfaces%>"/>:        
        <bean:write name="support" property="startAt"/>
    </td>
    <td colspan="2">        
        <bean:message key="common.label.time.end" bundle="<%=interfaces%>"/>:
        <bean:write name="support" property="endAt"/>
    </td>
</tr>
</logic:equal>

<logic:equal name="support" property="statusId" value="1">
<tr>
    <td><bean:message key="hotro.list.label.formDate" bundle="<%=interfaces%>"/>:</td>
    <td colspan="3">
        <input type="text" 
            name="dateForm" id="dateForm" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);" style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="dateForm"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateForm','dd/mm/yyyy');return false;">
      
        <bean:message key="hotro.list.label.toDate" bundle="<%=interfaces%>"/>:
        <input type="text" name="dateTo" id="dateTo" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);"  style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="dateTo"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateTo','dd/mm/yyyy');return false;">
    </td>
</tr>
</logic:equal>

<tr height="25">
    <td><bean:message key="hotro.list.label.kn.chitra" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="knChiTra">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_kn_chitra" property="key" labelProperty="value" />
        </html:select>    	
    </td>
    <td><bean:message key="hotro.list.label.the.bhyt" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="theBhyte">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_the_bhyt" property="key" labelProperty="value" />
        </html:select>    	
    </td>
</tr>

<tr height="25">
    <td width="35%"><bean:message key="hotro.list.label.sd.the" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="sdThe" styleClass="combobox_w100">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_sd_the" property="key" labelProperty="value" />
        </html:select>    	
    </td>
    <td><bean:message key="hotro.list.label.sd.the.phcn" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="sdThePhcn" styleClass="combobox_w100">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_sd_the_phcn" property="key" labelProperty="value" />
        </html:select>    	
    </td>
</tr>

<tr>
    <td colspan="4">
        <table width="100%">
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.mtieu.gdinh" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="mtieuGdinh" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.mtieu.dtri" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="mtieuDtri" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.vltl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctVltl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.hdtl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctHdtl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.antl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctAntl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.gddb" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctGddb" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.csgn" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctCsgn" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%">             
                D&#7909;ng c&#7909; tr&#7907; gi&#250;p (M&#244; t&#7843;)
            </td>
            <td colspan="3">
                  <!-- Mo-Ta-Chi-Tiet DCTG -->
                  <html:textarea name="support" property="dungcuKhac" rows="3" cols="55"/>
            </td>
        </tr>
        
        <logic:equal name="support" property="statusId" value="1">
        <tr>
            <td>C&#7909; th&#7875; v&#7873; h&#7895; tr&#7907;</td>
            <td colspan="3">
                <html:textarea name="support" property="reson" rows="3" cols="55"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="hotro.list.label.nguonhotro" bundle="<%=interfaces%>"/></td>
            <td>            
                 <html:select styleClass="inputbox" name="support" property="nguonHoTroId">
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BNguonHoTros">
                      <html:options collection="BNguonHoTros" property="id" labelProperty="name"/>
                    </logic:present>
                 </html:select>
            </td>
            <logic:equal name="support" property="nguonHoTroId" value="8" >
             <td><bean:message key="hotro.form.label.stt" bundle="<%=interfaces%>"/></td>
             <td><bean:write name="support" property="stt" /></td>
            </logic:equal>
            
            <logic:notEqual name="support" property="nguonHoTroId" value="8" >
            <td colspan="2">&nbsp;</td>
            </logic:notEqual>
        </tr>        
        <tr>
            <td><bean:message key="hotro.list.label.nguonhotroId.detail" bundle="<%=interfaces%>"/></td>
            <td colspan="3"><html:textarea name="support" property="nguonhotro" rows="3" cols="55"/></td>
        </tr>
        
        <tr>
            <td align="left" width="25%"><bean:message key="common.label.re-examination" bundle="<%=interfaces%>"/>:</td>
             <td colspan="3">
                 <input type="text" name="disNgayTK" id="disNgayTK"                         
                    class="textfield_date" onblur="isMonth(this)"
                    value="<bean:write name="kpi" property="disNgayTK"/>" />
            </td>
        </tr>
        
        <tr>
            <td align="left" width="25%"><bean:message key="common.label.address.support.exam" bundle="<%=interfaces%>"/>:</td>
            <td>
                <html:select name="kpi" property="disDiaDiem" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:options collection="mapPLoaiDDiem" property="key" labelProperty="value" />
                </html:select>
            </td>       
            <td align="left" width="25%"><bean:message key="common.label.object.support" bundle="<%=interfaces%>"/>:</td>
            <td>
                <html:select name="kpi" property="disDoiTuong">
                    <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="1"><bean:message key="kpi.dis.support.vnah" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="2"><bean:message key="kpi.dis.support.province" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="3"><bean:message key="kpi.dis.support.district" bundle="<%=interfaces%>"/></html:option> 
                    <html:option value="4"><bean:message key="kpi.dis.support.ward" bundle="<%=interfaces%>"/></html:option>
                </html:select>
            </td>
        </tr>
        </logic:equal>
        
        <tr>
            <td width="25%"><bean:message key="common.label.support.createdby.name" bundle="<%=interfaces%>" /></td> 
            <td><html:text name="support" property="nguoiTHTen" style="width:120px;" /></td>
            <td width="25%"><bean:message key="common.label.support.createdby.position" bundle="<%=interfaces%>" /></td> 
            <td>
                <html:select name="kpi" property="nguoiTHCv">
                    <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="1"><bean:message key="common.label.support.createdby.1" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="2"><bean:message key="common.label.support.createdby.2" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="3"><bean:message key="common.label.support.createdby.3" bundle="<%=interfaces%>"/></html:option> 
                    <html:option value="4"><bean:message key="common.label.support.createdby.4" bundle="<%=interfaces%>"/></html:option>
                </html:select>
            </td>
        </tr>
        </table>
    </td>
</tr>    
</table>
</div>

<div>
    <html:errors property="alert" bundle="<%=interfaces%>" />
</div>
	
<div class="bottom">
    <logic:notEqual name="support" property="idNkt" value="0">
          <logic:notEqual name="support" property="mode" value="UPDATE" >
              <%if(request.getSession().getAttribute("10.01")!=null){%>
              <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_CREATE" styleClass="button" onclick="postAjax('kpi','MainCate',anchor + ':_INSERT_SUPPORT');">
                              <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
               </span>
              <%}%>
           </logic:notEqual>
           
           <logic:equal name="support" property="mode" value="UPDATE" >
                <%if(request.getSession().getAttribute("10.02")!=null){%>
                <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">                             
                            <%
                                String onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_SUPPORT:stt:"+supportStt+"');";
                            %>
                            <html:button property="_CREATE" styleClass="button" onclick="<%=onclick%>">
                                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                            </html:button>                         
                        </span>
                    </span>
                 </span>
                  <%}%>
                  <%if(request.getSession().getAttribute("10.03")!=null){%>
                  <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">                             
                            <%
                                String onclickDelete="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_SUPPORT:nktId:"+ dtlId + ":dateCreate:"+ strCreateDate +":stt:"+supportStt+":nguonId:"+supportNguonId+"');";                                
                            %>
                            <html:button property="_CREATE" styleClass="button" onclick="<%=onclickDelete%>">
                                <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                            </html:button>                         
                        </span>
                    </span>
                 </span>
                 <%}%>
           </logic:equal>
     </logic:notEqual>
         
 </div>