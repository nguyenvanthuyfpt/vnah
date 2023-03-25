<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="#"><bean:message key="kpi.title.caption" bundle="<%=interfaces%>"/></a></div>
        </li>	
    </ul>
</div>

<br/>	
<html:form action="object" method="post">
    <html:hidden name="object" property="id"/>    
    <div class="content-calendar">
        <div class="padding-content">    
            <logic:present name="object">
                <div style="padding:5px;">
                    <b><bean:write name="object" property="code" /></b>&nbsp;<bean:write name="object" property="name" />
                </div>                
                <br/>
                <div>
                    <bean:message key="common.label.year" bundle="<%=interfaces%>"/>
                    <html:select styleClass="inputbox" name="object" property="year" onchange="javascript:postAjax('object','id_obj_ind',anchor + ':_CHANGE_YEAR');">
                        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2016">2016</html:option>
                        <html:option value="2017">2017</html:option>
                        <html:option value="2018">2018</html:option>
                        <html:option value="2019">2019</html:option>
                        <html:option value="2020">2020</html:option>
                        <html:option value="2021">2021</html:option>
                        <html:option value="2022">2022</html:option>
                    </html:select>
                </div>
                <br/>
                
                <div id="id_obj_ind">
                    <jsp:include page="/disability/categorys/object/listInd.jsp"/>
                </div>
                  
                <br/>
                <table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr valign="center" align="middle">
                    <td>
                        <span class="bt_left_Search">
                            <span class="bt_right_Search">
                                <span class="bt_center_Search">
                                   <html:button property="_SAVE_IND" styleClass="button" onclick="post('object',anchor + ':_SAVE_IND');">
                                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                    </html:button> 
                                </span>
                            </span>
                        </span>
                    </td>
                    <td>
                        <span class="bt_left_Search">
                            <span class="bt_right_Search">
                                <span class="bt_center_Search">
                                   <html:button property="_DELETE_IND" styleClass="button" onclick="post('object',anchor + ':_DELETE_IND');">
                                      <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                                  </html:button>   
                                </span>
                            </span>
                        </span>
                    </td>
                    <td>  
                        <span class="bt_left_Search">
                            <span class="bt_right_Search">
                                <span class="bt_center_Search">
                                   <html:button property="_CANCEL_PER" styleClass="button" onclick="post('object',anchor + ':_CANCEL_IND');">
                                        <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                                    </html:button>   
                                </span>
                            </span>
                        </span>
                    </td>
                </tr>
                </table>
                <br/> 
            </logic:present>
      </div>
   </div>  
</html:form>   
