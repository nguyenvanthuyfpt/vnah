<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="formEditInf">
<logic:present name="formMyContact" >          
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td>                    
                    <ul class="ulClassDetailMyContact">
                        <li>
                            <bean:message key="mycontact.edit.telephone" bundle="<%=interfaces%>"/>: <bean:write name="formMyContact" property="phone" />
                        </li>
                          <li>
                            <bean:message key="mycontact.edit.icq" bundle="<%=interfaces%>"/> :<bean:write name="formMyContact" property="icq" />
                        </li>
                        <li>
                            <bean:message key="mycontact.edit.address" bundle="<%=interfaces%>"/> :<bean:write name="formMyContact" property="address" />
                        </li>
                        <li>
                            <bean:message key="mycontact.edit.YM" bundle="<%=interfaces%>"/> :<bean:write name="formMyContact" property="ym" />
                        </li>
                        <li>
                            <bean:message key="mycontact.edit.Skype" bundle="<%=interfaces%>"/> :<bean:write name="formMyContact" property="skype" />
                        </li>
                        <li>
                            <bean:message key="mycontact.edit.Gtalk" bundle="<%=interfaces%>"/> :<bean:write name="formMyContact" property="gtalk" />
                        </li>
                     
                        <li >
                           <div style="text-align: right;padding:0px 4px 4px 0px">   
                                  <html:button property="_PREPARED_CREATE" style="width:60px" onclick="if(messageDelete()) {postAjax('formMyContact','tdMycontact',anchor + ':_DELETE_PMYCONTACT');addthis_close();}">
                                            <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                                    </html:button> 
                                <input style="cursor: pointer;width:60px" type="button"  value="S&#7917;a"   onclick="getObj('formEditInf').style.display='block';postAjax('formMyContact','formEditInf',anchor + ':_OPEN_WINDOW:id:<bean:write name="formMyContact" property="id" />');"/>
                            </div>
                        </li>
                    </ul>
                </td>               
            </tr>
        </table>
</logic:present>        
 </div>    
    







