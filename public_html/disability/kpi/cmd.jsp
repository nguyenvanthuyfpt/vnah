<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<bean:define name="kpi" property="perId" id="perId" type="java.lang.Integer" />
<script type="text/javascript"> 
   function checkSubmitDis(form){    
        if(form.disName.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            $("#disName").focus();
            return false;
        }
        if(form.disBirth.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            $("#disBirth").focus();
            return false;
        }    
        return true;
    }
    
    function checkSubmitPer(form) {
        if (form.eventId.value=='0') {
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        
        if (form.perName.value=='') {
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
    
    function checkSubmitValue(form) {
        if (form.locationId.value=='0') {
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
    
    function excuteReport(anchorName){		
        post('kpi',anchor + ':'+ anchorName);
        remove('kpi',anchor);
    }
    
    function savePerson(){
        post('kpi',anchor + ':_CREATE:state:0:perId:<%=String.valueOf(perId)%>');
    }
</script>
<logic:present name="kpi">

    <%
        int total = 0;
        try {
            total = (Integer) request.getAttribute("total");
            //out.println(perId);
        } catch (Exception ex) {        
        }
    %>
    <% if(request.getSession().getAttribute("10")!=null){%>
    <table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">    
    <tr>
    <logic:equal name="kpi" property="type" value="2">
       <logic:notEqual value="0" property="eventId" name="kpi">
       <td>
         <span class="bt_left_Search">
            <span class="bt_right_Search">
               <span class="bt_center_Search">
                  <html:button property="_CREATE_PER" styleClass="html:button" onclick="savePerson();">
                     <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                  </html:button></span></span></span>
      </td>
      </logic:notEqual>
      
      <logic:notEqual value="0" property="eventId" name="kpi">
          <logic:greaterThan name="kpi" property="dtlId" value="0">
             <td>
                <span class="bt_left_Search">
                   <span class="bt_right_Search">
                      <span class="bt_center_Search">
                         <html:button property="_EDIT_PER" styleClass="html:button"
                                 onclick="post('kpi',anchor + ':_EDIT:state:0');">
                            <bean:message key="action.update" bundle="<%=interfaces%>"/>
                         </html:button></span></span></span>
             </td>
             <td>
                <span class="bt_left_Search">
                   <span class="bt_right_Search">
                      <span class="bt_center_Search">
                         <html:button property="_DELETE_PER" styleClass="html:button"
                                 onclick="javascript:if(messageDelete()){postAjax('kpi','div_person', anchor + ':_PERSON_DELETE:state:0:type:2');}">
                            <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                         </html:button></span></span></span>
             </td>
          </logic:greaterThan>
      </logic:notEqual>
      
      <td>
            <span class="bt_left_Search">
              <span class="bt_right_Search">
                  <span class="bt_center_Search">
                     <html:button property="_CANCEL" styleClass="button" onclick="post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI');">
                        <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                    </html:button>
                  </span>
              </span>
             </span> 
        </td>
    </logic:equal>
    
    <!-- Hours -->
    <logic:equal name="kpi" property="type" value="3">
        <logic:greaterThan name="kpi" property="dtlId" value="0">
            <td>
                <span class="bt_left_Search">
                      <span class="bt_right_Search">
                          <span class="bt_center_Search">
                               <html:button property="_EDIT" styleClass="button" onclick="post('kpi',anchor + ':_EDIT');">
                                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                </html:button>
                          </span>
                      </span>
                </span>
            </td>
            <td>
                <span class="bt_left_Search">
                    <span class="bt_right_Search">
                  <span class="bt_center_Search">
                     <html:button property="_DELETE_PER" styleClass="html:button"
                             onclick="javascript:if(messageDelete()){postAjax('kpi','div_person', anchor + ':_PERSON_DELETE:state:0');}">
                        <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                     </html:button></span></span></span>
            </td>
        </logic:greaterThan>
    </logic:equal>
    
    <!-- DisabilityPeople  -->
    <logic:equal name="kpi" property="type" value="1">          
          <logic:equal name="kpi" property="nktId" value="0">
          <td>
            <%if(request.getSession().getAttribute("10.01")!=null){%>
             <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmitDis(this.form)) { post('kpi',anchor + ':_CREATE:objId:56:type:1');} ">
                              <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                          </html:button>
                        </span>
                  </span>
            </span>
            <%}%>
          </td>
          </logic:equal>
          
          
          <logic:greaterThan name="kpi" property="nktId" value="0">
          <td>
              <%if(request.getSession().getAttribute("10.02")!=null){%>
              <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">
                             <html:button property="_EDIT" styleClass="button" onclick="post('kpi',anchor + ':_EDIT:objId:56:type:1');">
                                  <bean:message key="action.update" bundle="<%=interfaces%>"/>
                              </html:button>
                        </span>
                    </span>
              </span>
              <%}%>
          </td>
          
          <td>
              <%if(request.getSession().getAttribute("10.03")!=null){%>
              <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">
                            <html:button property="_EDIT" styleClass="button" onclick="javascript:if(messageDelete()){postAjax('kpi','MainCate',anchor + ':_DELETE_DIS:type:1:indId:0');}">
                                <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                            </html:button>
                        </span>
                    </span>
              </span>
              <%}%>
          </td>
          
          <td>
              <span class="bt_left_Search">
                   <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_EXPORT" styleClass="button" onclick="post('kpi',anchor + ':_EXPORT_REPORT');remove('kpi',anchor);">
                              <bean:message key="btn.print.profile" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
              </span>
          </td>
          </logic:greaterThan>  
          
          <td>
              <span class="bt_left_Search">
                <span class="bt_right_Search">
                    <span class="bt_center_Search">
                       <html:button property="_CANCEL" styleClass="button" onclick="post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI:dtlId:0:indId:0');">
                          <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                      </html:button>
                    </span>
                </span>
               </span> 
          </td>
    </logic:equal>
    
    <!-- Value -->
    <logic:equal name="kpi" property="type" value="0">
        <td>
           <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                            <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmitValue(this.form)) { post('kpi',anchor + ':_CREATE');}">
                              <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                            </html:button>
                      </span>
                  </span>
            </span>
        </td>
        
        <logic:greaterThan name="kpi" property="dtlId" value="0">
        <td>
            <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                           <html:button property="_EDIT" styleClass="button" onclick="post('kpi',anchor + ':_EDIT');">
                                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                            </html:button>
                      </span>
                  </span>
            </span>
        </td>
        
        <td>
            <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_EDIT" styleClass="button" onclick="javascript:if(messageDelete()){postAjax('kpi','div_value', anchor + ':_VALUE_DELETE');}">
                              <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
            </span> 
        </td>
        </logic:greaterThan>
        
         <td>
            <span class="bt_left_Search">
              <span class="bt_right_Search">
                  <span class="bt_center_Search">
                     <html:button property="_CANCEL" styleClass="button" onclick="post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI');">
                        <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                    </html:button>
                  </span>
              </span>
             </span> 
        </td>
        
    </logic:equal>        
       
          
          <!--
          <%if(total>0){%>
          <td>
              <span class="bt_left_Search">
                   <span class="bt_right_Search">
                      <span class="bt_center_Search">
                          <html:button property="_EXPORT" styleClass="button" onclick="javascript:excuteReport('_EXPORT_DATA');">
                              <bean:message key="btn.export-data" bundle="<%=interfaces%>"/>
                          </html:button>
                      </span>
                  </span>
              </span>
          </td>
          <%}%>
          -->
    </tr>
    </table>
    <%}%>
</logic:present>    
