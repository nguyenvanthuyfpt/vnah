<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
  <bean:define name="BList" property="radioCheck" id="listId" type="java.lang.Integer"/>
<table class="tablecontent" id="table6" style="border: 1px solid #77BBDD;z-index: 20;" cellpadding="0" cellspacing="0" width="400px" align="center">
  <tr>
        <TH class="tdheader">
           <strong>B&#225;o c&#225;o DS CBCNV qu&#7843;n l&#253; &#7913;ng d&#7909;ng tin h&#7885;c</strong>
        </TH></tr>
 <tr>
   <td>   
   <table class="popupWinInner" bgcolor="Silver" style="border-collapse: collapse" height="100px" width="100%" cellpadding="0" cellspacing="0" border="0">   
                        <tr>
                          <td ><bean:message key="empapps.apps" bundle="<%=interfaces%>"/></td>
                          <td >
                            <html:select property="idEmployee" name="BList" style="width:150px" styleClass="fieldSelect">
                                 <html:option value="0">L&#7921;a ch&#7885;n</html:option>
                                 <html:options collection="BApps" property="id" labelProperty="name"/>
                            </html:select>   
                          </td>
                        </tr>   
<BR>
                        <tr>
                           <td  align="center" colspan="2">
                           <a href="javascript:post('list',anchor + ':_LIST_REPORT:listCode:APPS:listId:<%=listId%>');remove('reportList',anchor);">OK</a>
                           <a href="javascript:closeWindow();"><bean:message key="quatrinh.button.ketthuc" bundle="<%=interfaces%>"/></a>
                           </td>
                        </tr>                        
      </table>
   </td>
 </tr>     
</table>
 
