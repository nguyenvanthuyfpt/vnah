<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table class="popupWin" style="border: 1px solid #77BBDD;z-index: 20;" cellpadding="0" cellspacing="0" width="250px">
    <tr><TD background="images/bg_t.gif" align="left" onmousedown="makeObjectToDrag('winPopup')" style="BORDER-BOTTOM: #8CACBB 1px solid;padding-left:4px;cursor:move;">

            <font face="Tahoma" style="color:#FFFFFF;font-weight: bold;">
                <bean:message key="listReport.createName" bundle="<%=interfaces%>"/>
            </font>
        </td>
        <td   background="images/bg_t.gif" valign="middle" align="right"  onmousedown="makeObjectToDrag('winPopup')" style="BORDER-BOTTOM: #8CACBB 1px solid;padding-left:4px;cursor:move;"> 
            <img src="images/close.png" height="18" alt="&#272;&#243;ng" border="0"  style="cursor:pointer" onclick="closeWindow();" >
        </td>
    </TR>
    <tr>
        <td bgcolor="#FFF8E1" colspan="2">
             <html:hidden name="searchdispeople" property="checkEmpAll" /> 
             <html:hidden name="searchdispeople" property="checkEmp" /> 
             <html:hidden name="searchdispeople" property="emps" /> 
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td height="8px"></td>
                </tr>

                <tr>
                    <td id="tdMainName">                     
                          <jsp:include page="/disability/list/edit.jsp"/>
                    </td>    
                </tr>
             
                <tr>
                    <td align="right">                    
                        <table width="100%"  cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td width="5%" height="8px"></td>
                                <td></td>
                                <td width="10%"></td>
                                <td width="30%"></td>                          
                                <td width="6%"></td>                        
                            </tr>
                            <tr>
                                <td  align="center" class="tdheader"> <bean:message key="search.listReport.stt" bundle="<%=interfaces%>"/></td>
                                <td align="center" class="tdheader"> <bean:message key="search.listName" bundle="<%=interfaces%>"/></td>
                                <td align="center" class="tdheader"> <bean:message key="search.listReport.amount" bundle="<%=interfaces%>"/></td>
                                <td  class="tdheader" align="left" ><bean:message key="search.listReport.date" bundle="<%=interfaces%>"/></td>
                                <td class="tdheader"><br></td>
                            </tr>
                        
                            <tr>
                                <td colspan="4" bgcolor="#FFFFFF">
                                <DIV style="OVERFLOW: auto; POSITION: relative; HEIGHT: 205px; WIDTH: 546px">  
                                    <table width="100%" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="5%"></td>
                                            <td></td>
                                            <td width="10%"></td>
                                            <td width="30%"></td>
                                            <td width="6%"></td>
                                        </tr>
                        <%int i=0;%>
                       <logic:iterate name="beans" id="bean" type="com.form.disability.list.FList">  
                       
                                        <tr>
                                           <td class="tdcontent1" align="center" ><%=(++i)%></td>
                                           <td class="tdcontent1" align="center"><bean:write name="bean" property="listName"/></td>
                                           <td class="tdcontent1" align="center"><bean:write name="bean" property="amountEmp"/></td>
                                           <td class="tdcontent1" align="center"><bean:write name="bean" property="dateCreate"/></td>
                                           <td class="tdcontent1" align="center"><input name="listId" type="radio" value="<bean:write name="bean" property="listId"/>" onclick="javascript:postAjax('searchdispeople','tdMainName',anchor + ':_PREPARED_EDIT:listId:<bean:write name="bean" property="listId"/>')">                              
                                           </td>
                                        </tr>
                       </logic:iterate> 
                                </table>
                       </div>
                     </td>
                       </tr>   
                       <tr>
                            <td height="8px" colspan="4"></td>
                        </tr>
                        <tr>
                           <td colspan="3" align="right" valign="middle" style="padding-right:2px;">  
                             <html:errors property="listErrors"  bundle="<%=interfaces%>"/>   
                           </td>
                           <td align="right">
                                <html:button property="_LIST_REPORT" onclick="closeWindow();javascript:excutePostCategorys('_REPORT');" styleClass="button">
                                     <bean:message key="search.listReport.view" bundle="<%=interfaces%>"/>
                                </html:button>
                                <html:button property="_EDIT" onclick="javascript:openWindow('searchdispeople',anchor+':_EDIT');" styleClass="button">
                                     <bean:message key="search.listReport" bundle="<%=interfaces%>"/>
                                </html:button>                          
                           </td>                           
                        </tr>                        
                     </table>
                     
                </td>
             </tr>
             <tr><td height="6px"></td></tr>
         </table>
   </td>
 </tr>     
</table> 
