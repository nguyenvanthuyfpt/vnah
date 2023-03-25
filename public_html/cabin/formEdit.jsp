<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table class="tableForm" cellpadding="0" align="left" style="border-collapse: collapse" cellspacing="0" border="0" width="100%">    
    <tr>
        <td valign="top">
        <div>
            <table class="tableForm" cellpadding="0" align="left" style="border-collapse: collapse" cellspacing="0" border="0" width="100%">    
                         <tr>
                        <td  class="toolCmd" style="padding-left:10px" colspan="3"  >                
                        <jsp:include page="/cabin/cmd.jsp" />
                        </td>
                    </tr> 
                            <tr>
                            <td valign="top" nowrap ><span id="lableId" ><bean:message key="cabin.recieverEmp.caption" bundle="<%=interfaces%>"/></span></td>
                            <td valign="top" >
                                <select multiple="multiple" name="userIdS" id="userIdS" ondblclick="javascript:removeItem(this,this.form,0)" style="width:250px;height:60px">
                                    <logic:notEmpty name="cabin" property="empsRev" >
                                         <logic:iterate name="cabin" property="empsRev" id="bRecv"  type="com.form.cabin.FCabin"> 
                                                <option value="<bean:write name="bRecv" property="toPertion"/>">
                                                <bean:write name="bRecv" property="userFullName"/></option>
                                        </logic:iterate>
                                    </logic:notEmpty> 
                                </select>
                            </td>
                            <td valign="top" nowrap>
                            </td>
                        </tr>
                       <tr>
                            <td nowrap><bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                            <td align="left" colspan="2" >
                            <html:text name="cabin" property="name" />
                            </td>
                        </tr>  
                         <tr>
                            <td nowrap><bean:message key="title.cabin.label.upFile" bundle="<%=interfaces%>"/></td>
                            <td colspan="2"><html:file name="cabin" property="upFile" style="width:220px;" /></td>
                        </tr>   
                
                  <tr>
                        <td  class="toolCmd" style="padding-left:10px" colspan="3"  >                
                        <jsp:include page="/cabin/cmd.jsp" />
                        </td>
                    </tr> 
            </table>
        </div>    
        </td>
        <td valign="top">
                    <div class="status" id="tdMycontact">
                            <jsp:include page="/cabin/empDepartment.jsp"/>          
                    </div>
        

        </td>
    </tr>
</table>