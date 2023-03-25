<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<div class="content-div">
  <div class="content-calendar-2" align="left">
    <font style="font-size:13px;"><b>
        T&#236;m ki&#7871;m theo Nhu c&#7847;u DVHT
    </b></font>
  </div>
  <%	
    String checkBox      = "";
    String nhucauIdsTemp = "";
  %>
  
  <logic:notEmpty name="support" property="hotroIds">
      <bean:define name="support" property="hotroIds" id="hotroIds" type="java.lang.String" />
      <%			nhucauIdsTemp = hotroIds;		%>
  </logic:notEmpty>
  
  <div style="overflow-x:scroll;height:300px;">
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
                    <% checkBox= (nhucauIdsTemp.indexOf("#"+bean.getId()+"#")>=0)? "checked":""; %>
                    <input type="checkbox" name="nc_phanLoaiIds" id="nc_phanLoaiIds" <%=checkBox%> value="<%=bean.getId()%>" onclick="showCongcu('nhucau_'+this.value,this.checked);">		        
                    <%=bean.getName()%>
                        
                    <logic:notEmpty name="BTemps"> 
                        <div id="nhucau_<%=bean.getId()%>">		                                        
                            <logic:iterate id="beanT" name="BTemps" indexId="i" type= "com.form.disability.categorys.FTinh">
                               <%if(beanT.getLevel()==0){%>
                                  &nbsp;&nbsp;&nbsp;
                               <%}else{%>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <%}%>
                               <input type="checkbox" name="nc_phanLoaiIds" id="nc_phanLoaiIds" <%=checkBox%> value="<%=beanT.getId()%>">
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
  <tr>
      <td><bean:message key="common.label.support.other" bundle="<%=interfaces%>"/> :</td>
      <td><html:text name="timkiem" property="ncauDungCuKhac" style="width:200px" onkeypress="return searchKeyPress(event);"/></td>
  </tr>
  </tr>
  <tr>
      <td colspan="2" align="center">
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
                    <html:button property="_RESET" styleClass="button" onclick="cancelSearch('tdnhucau');">
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
