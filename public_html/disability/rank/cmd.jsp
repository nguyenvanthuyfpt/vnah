<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<div class="bottom">
  <table cellpadding="0" cellspacing="0">
      <tr>
          <td>
               <logic:notEqual name="kpi" property="nktId" value="0">
                <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search">                                 
                            <html:button property="_EDIT" styleClass="button" onclick="submitForm();">
                                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                            </html:button>                                  
                        </span>
                    </span>
                </span>    
              </logic:notEqual>
              
              <logic:notEqual name="kpi" property="plDtlId" value="0">
                <span class="bt_left_Search">
                  <span class="bt_right_Search">
                      <span class="bt_center_Search"> 
                        
                            <bean:define name="phanloai" property="id" id="rankId" type="java.lang.Integer"/>
                            <%String onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_RANK:id:"+rankId+"');";%>
                            <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>">
                                <bean:message key="action.update" bundle="<%=interfaces%>"/>
                            </html:button>                                 
                      </span>
                   </span>
                </span>
              </logic:notEqual>
          </td>
          <td>
              <html:errors property="alert" bundle="<%=interfaces%>"/>
          </td>
      </tr>
  </table>
</div>