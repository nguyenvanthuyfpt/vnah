<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="kpi" method="post">
<html:hidden name="kpi" property="nktId"/>
<%	
    String checkBox= "";
    String dangTatIdsTemp= "";
    int nguyenNhanId = 0;
    int total = (Integer)request.getAttribute("total");
%>

<logic:notEmpty name="kpi" property="disDangTatIds">
<bean:define name="kpi" property="disDangTatIds" id="dtId" type="java.lang.String" />
<bean:define name="kpi" property="disNguyenNhanId" id="nnId" type="java.lang.Integer" />    
<%	
    dangTatIdsTemp = dtId;
    nguyenNhanId = nnId;
%>
</logic:notEmpty>
    
    <div>
        <div align="left" class="fullName">
            <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>            
        </div>
        <div style="padding:5px;">
            <table cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
            <tr>
              <logic:present name="BPhanLoais">
                <logic:notEmpty name="BPhanLoais">
                  <logic:iterate id="bean" name="BPhanLoais" indexId="i" type= "com.form.disability.categorys.FDangTat">
                  <%if(bean.getParentID()==0){
                     checkBox = (dangTatIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "checked":"";
                  %>
                      <td align="left" valign="top">
                          <input type="checkbox" name="disPhanLoaiIds" id="disPhanLoaiIds" <%=checkBox%> value="<%=bean.getId()%>" >
                          <strong><%=bean.getName()%></strong>
                      </td>
                  <%}%>
                  </logic:iterate>   
                </logic:notEmpty>
              </logic:present>
            </tr>	
          </table>
        </div>
        
        <table cellpadding="2" width="100%" align="center"
               style="border-clilapse: clilapse" cellspacing="2" border="0">
            <tr>
                <td align="left" width="25%">
                    <%if(total==0){%>
                    <bean:message key="phanloai.list.label.createDate" bundle="<%=interfaces%>"/>:
                    <%}else{%>
                    <bean:message key="phanloai.list.label.createDate.next" bundle="<%=interfaces%>"/>:
                    <%}%>
                </td>
                <td>
                    <input type="text" name="createDate" id="createDate" 
                        onkeypress="return formatDate(event,this);" 
                        onblur="isDate(this);" style="width:80px;" 
                        class="textfield_date"
                        value="<bean:write name="kpi" property="createDate"/>" />						
                    <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;">
                </td>
            </tr>
            
            <!--<tr>
                <td align="left" width="25%">
                    <bean:message key="common.label.re-examination" bundle="<%=interfaces%>"/>:
                </td>
                <td>                    
                     <input type="text" name="disNgayTK" id="disNgayTK"                         
                        class="textfield_date" onblur="isMonth(this)"
                        value="<bean:write name="kpi" property="disNgayTK"/>" />
                </td>
            </tr>
            -->
            <tr>
                <td align="left" width="25%">
                    <bean:message key="common.label.address.exam" bundle="<%=interfaces%>"/>:
                </td>
                <td>
                    <html:select name="kpi" property="disDiaDiem" >
                      <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                      <html:options collection="mapPLoaiDDiem" property="key" labelProperty="value" />
                    </html:select>
                </td>
            </tr>
             
            <!--<tr>
                <td align="left" width="25%">
                    <bean:message key="phanloai.list.label.createDate"
                                  bundle="<%=interfaces%>"/>:
                </td>
                <td>
                    <html:text name="kpi" property="disThoiDiemMac"
                               styleClass="textfield_date"
                               onblur="isNumber(this);" maxlength="4"
                               style="width:80px"
                               onmouseover="Tip(this.value,SHADOW, true, SHADOWWIDTH, 7)"
                               onmouseout="UnTip()"/>
                </td>
            </tr>-->
             
            <tr>
                <td align="left">
                    <bean:message key="phanloai.list.label.reson"  bundle="<%=interfaces%>"/>
                </td>
                <td>
                    <html:textarea name="kpi" property="disChuanDoan" rows="3" cols="55"/>
                </td>
            </tr>
             
            <tr>
                <td align="left" width="120px">
                    <bean:message key="donvi.action.view"
                                  bundle="<%=interfaces%>"/>:
                </td>
                <td align="left">
                    <html:select styleClass="inputbox" name="kpi"
                                 property="disNguyenNhanId"
                                 onchange="postAjax('phanloai','MainCate',anchor + ':_SELECT_PHANLOAI');">
                        <html:option value="0">
                            <bean:message key="combo.luachon"
                                          bundle="<%=interfaces%>"/>
                        </html:option>
                        <logic:present name="BTreeNguyennhans">
                            <html:options collection="BTreeNguyennhans"
                                          property="id" labelProperty="name"/>
                        </logic:present>
                    </html:select>
                </td>
            </tr>
             
            <tr>
                <td align="left" width="120px;">M&#7913;c &#273;&#7897; khuy&#7871;t t&#7853;t:</td>
                <td align="left">
                    <html:select styleClass="inputbox" name="kpi"
                                 property="disMucDoId">
                        <html:option value="0">
                            <bean:message key="combo.luachon"
                                          bundle="<%=interfaces%>"/>
                        </html:option>
                        <logic:present name="BTreeMucdos">
                            <html:options collection="BTreeMucdos" property="id"
                                          labelProperty="name"/>
                        </logic:present>
                    </html:select>
                </td>
            </tr>
        </table>
        <div class="bottom">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                         <logic:notEqual name="kpi" property="nktId" value="0">
                         <%if(request.getSession().getAttribute("10.01")!=null){%>
                          <span class="bt_left_Search">
                            <span class="bt_right_Search">
                                <span class="bt_center_Search">                                 
                                      <html:button property="_EDIT" styleClass="button"
                                                   onclick="postAjax('kpi','MainCate',anchor + ':_INSERT_PHANLOAI');">
                                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                      </html:button>                                  
                                  </span>
                              </span>
                          </span>
                          <%}%>
                        </logic:notEqual>
                        
                        <logic:notEqual name="kpi" property="plDtlId" value="0">
                        <%if(request.getSession().getAttribute("10.02")!=null){%>
                          <span class="bt_left_Search">
                            <span class="bt_right_Search">
                                <span class="bt_center_Search"> 
                                  
                                      <bean:define name="phanloai" property="id" id="phanLoaiId" type="java.lang.Integer"/>
                                      <%String onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_PHANLOAI:id:"+phanLoaiId+"');";%>
                                      <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>">
                                          <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                      </html:button>                                 
                                </span>
                             </span>
                          </span> 
                          <%}%>
                        </logic:notEqual>
                    </td>
                    <td>
                        <html:errors property="alert" bundle="<%=interfaces%>"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</html:form>