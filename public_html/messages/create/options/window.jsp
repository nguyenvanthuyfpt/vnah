<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<table width="450px" style="border: 1px solid #77BBDD;z-index: 20;background-color:white" cellpadding="0" cellspacing="0" align="center">
<tr>
<td bgcolor="#F0F5E5" valign="top" align="center" onmousedown="makeObjectToDrag('winPopup')">  



    <TABLE cellSpacing=0 cellPadding=0 width="100%" border="0" id="toolbar">  
        <TR >                       
            <TD  colspan="2"  onmousedown="makeObjectToDrag('winPopup')" class="titleOpen" background="<%=contextPath%>/images/bg_t.gif" align="left" style="BORDER-BOTTOM: #8CACBB 1px solid;background-color:#DEE7EC;padding-left:4px">
              sss
            </TD>
             <TD  onmousedown="makeObjectToDrag('winPopup')"  height="22px" background="<%=contextPath%>/images/bg_t.gif" align="right" style="padding-right:1px">
              <img src="<%=contextPath%>/images/close.png" border="0" style="cursor: pointer;" onclick="closeWindow()"  />
           </TD>
        </TR>    
        
        <tr>
            <td colspan="2" bgcolor="#FFFFFF" width="85%">
                    <table>
                         <tr>
                            <td align="left" width="35px">Phong</td>
                            <td align="left">Nhom</td>                            
                        </tr>
                    </table>
            </td>
            <td align="right"  bgcolor="#FFFFFF">
                                 <html:button property="_EDIT" onclick="getDep();closeWindow();" styleClass="button">
                                    Dong y
                                </html:button> 
             </td>
            
        </tr>
            <html:form action="options">  
        <tr>            
            <td class="fontnomal" colspan="3" id="tdAssign" valign="top"  style="BORDER-TOP: #8CACBB 1px solid;">              
            
                 <jsp:include page="/messages/create/options/form.jsp"/>  
                
          
            </td>
        </tr>   
         </html:form>
    </table> 
    
  </td>
  </tr>
  </table>
 
 