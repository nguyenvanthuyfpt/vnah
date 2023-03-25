<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
  <bean:define name="list" property="radioCheck" id="listId" type="java.lang.Integer"/>
<table class="popupWin" style="border: 1px solid rgb(198,198,198);z-index: 20; background-color:#CCCCCC;"  cellpadding="0" cellspacing="0" width="250px" align="center">
  <tr>
        <td class="tdheader" onmousedown="makeObjectToDrag('winPopup')" style="BORDER-BOTTOM: #e7ebee 1px solid;padding-left:4px;cursor:move;">
           <strong><bean:message key="listReport.list" bundle="<%=interfaces%>"/></strong>
        </td></tr>
     <tr>
 <tr>
   <td>   
   <table class="popupWinInner" width="100%" cellpadding="0" cellspacing="0" border="0">   
   <tr>
   <td>   
    <DIV style="OVERFLOW: auto; POSITION: relative; HEIGHT: 160px; WIDTH: 398px">
                    <table style="padding-left:4px;" align="center" cellpadding="0" cellspacing="0" width="100%" border="0">  
                        <logic:present name="BReports">
                        <%int i=1;%>
                        <logic:iterate name="BReports" id="bean" type="com.form.admin.reportSystem.FReportSystem">                                       
                            <tr>
                                <td align="left"><%=i++%>
                                </td>

                            <td align="left">
                                <a href="javascript:post('disabilityReport', anchor + ':_REPORT:id:<%=bean.getId()%>:listId:<%=listId%>');remove('disabilityReport',anchor);remove('disabilityReport','id');remove('disabilityReport','listId');">
                                    <bean:write name="bean" property="nameOfFileVn" />
                                </a></td>
                            </tr>
                        </logic:iterate>
                        </logic:present>
                    </table>  
        </div> 
      </td>
      </tr>
      <tr>
      <td align="center">
           <html:button property="end" styleClass="button" onclick="closeWindow();">
                      <bean:message key="action.close" bundle="<%=interfaces%>"/>
             </html:button>
      </td>
      </tr>
      <tr>
      <td align="center" height="4px">
          
      </td>
      </tr>
      </table>
   </td>
 </tr>     
</table>
 
