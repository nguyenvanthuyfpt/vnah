<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<logic:equal name="docAssign" property="checkSelectRecv" value="1">
<div class="ct-celendar">
<table width="100%" cellpadding="0" cellspacing="0" class="adminformEdit">  
      <tr>
           <td valign="top"  nowrap >                
                <Strong><bean:message key="doc.officer.caption" bundle="<%=interfaces%>"/>:</strong>
                   <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="app.remove.cation" bundle="<%=interfaces%>"/>" onClick="javascript:removeItem(document.docAssignRecv.usersId,document.docAssignRecv,0)">
                <ul style="margin-left:2px;" class="ulClassDoc">
        <%boolean check=true;%>
        <logic:present name="BTransfer">
        <logic:iterate name="BTransfer" id="bean" type="com.form.admin.doc.category.transfer.FTransfer"> 
                    <li>
                    <%if(check){ check=false;%>
                     <input type="radio"  name="views"  value='<bean:write name="bean" property="id" />' checked>
                    <%}else{%>
                     <input type="radio"  name="views"  value='<bean:write name="bean" property="id" />'>
                    <%}%>
                    <bean:write name="bean" property="name" />
                    
                    </li>
         </logic:iterate>
         </logic:present>
                </ul>
           </td>        
           <td valign="top" >                
                <html:select multiple="multiple" name="docAssign" property="usersId"  styleId="usersId" style="width:210px;height:170px"> 
                </html:select>            
           </td>   
          
    </tr> 
   
    <tr>
        <td></td>
        <td>
                     <html:button styleClass="button" property="_CREATE" onclick="javascript:this.disabled=true;submitAssign();" >
                               <bean:message key="doc.assign.cmd.caption" bundle="<%=interfaces%>"/>                                                                                    
                     </html:button> 
        </td>
    </tr>
    <tr>
        <td nowrap align="left" colspan="2">
            <jsp:include page="/admin/alert.jsp" />
        </td>
    </tr>      
</table>
</div>
</logic:equal>
        

            
            
  
 
 