<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
	function checkSubmitDonVi(form){
	    if(form.name.value=='' || form.address.value=='' || form.phone.value=='' ){
	        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
	        return false;
	    }
	    return true;
	}
</script>
	
<div class="content-calendar-2">
<div style="height:20px">&nbsp;</div>	
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td align="left" nowrap>C&#225;c Tuy&#7871;n </td>
        <td align="left" colspan="3">
            <html:select name="dr_unit" property="tinhId" onchange="post('dr_unit',anchor + ':_SELECT_IDTINH');">                               
                <logic:present name="BTreeTinhs">
                    <html:option value="0">-----<bean:message key="action.select" bundle="<%=interfaces%>"/>-------</html:option>
                    <html:options collection="BTreeTinhs" property="id"  labelProperty="name" />
                </logic:present>
            </html:select>
            <logic:notEmpty name="dr_unit" property="tinhName">
                <span style="color:#005BCC">(<bean:write name="dr_unit" property="tinhName" />)</span>
            </logic:notEmpty>
        </td>
    </tr>

    <tr>
        <td align="left" width="100px" nowrap>L&#297;nh v&#7921;c </td>
        <td align="left" colspan="3">
            <html:select name="dr_unit" property="id_type" onchange="post('dr_unit',anchor + ':_SELECT_IDTINH');">
                <logic:present name="BCategoryUnits">
                    <html:option value="0">-----<bean:message key="action.select" bundle="<%=interfaces%>"/>-------</html:option>
                    <html:options collection="BCategoryUnits" property="id_type" labelProperty="name_type" />
                </logic:present>
            </html:select>
        </td>     
    </tr>
    <tr>
        <td align="left" width="100px"><bean:message key="disability.unit.label.name" bundle="<%=interfaces%>" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left" colspan="3"><html:text name="dr_unit" property="name" style="width:500px;" /></td>
    </tr>
    <tr>
        <td align="left" width="100px"><bean:message key="disability.unit.label.address" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
        <td align="left" colspan="3"><html:text name="dr_unit" property="address" style="width:500px;"/></td>
    </tr>
    <tr>
        <td align="left" width="100px"><bean:message key="disability.unit.label.phone" bundle="<%=interfaces%>" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td align="left"><html:text name="dr_unit" property="phone" style="width:200px;"/></td>
        <td align="left" width="100px"><bean:message key="disability.unit.label.fax" bundle="<%=interfaces%>"/>
        <td align="left"><html:text name="dr_unit" property="fax" style="width:200px;"/></td>
   </tr>
   
    <tr>
        <td align="left" width="100px"><bean:message key="disability.unit.label.email" bundle="<%=interfaces%>"  />
        <td align="left"><html:text name="dr_unit" property="email" style="width:200px;"/></td>
        <td align="left" width="100px"><bean:message key="disability.unit.label.nguoilienhe" bundle="<%=interfaces%>"  /></td>
        <td align="left"><html:text name="dr_unit" property="nguoilienhe" style="width:200px;"/></td>
    </tr>
   
    <tr>
        <td colspan="4" align="left">
            <html:button property="_CREATE" styleClass="button" onclick="javascript:if(checkSubmitDonVi(this.form)) { post('dr_unit',anchor + ':_CREATE');}" >
                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
            </html:button>
                
            <logic:notEqual name="dr_unit" property="id" value="0">
                <bean:define  name="dr_unit" property="id" id="id" type="java.lang.Integer" /> 
                    <%String onclick="javascript:if(checkSubmitDonVi(this.form)) { post('dr_unit',anchor + ':_EDIT:id:"+id+"');}"; %>
                <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>"   >                 
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                </html:button>        
            </logic:notEqual>
            
            <html:button property="_EXCEL" styleClass="button" onclick="post('dr_unit',anchor + ':_EXCEL');remove('dr_unit',anchor)" >
                    <bean:message key="report.list.caption" bundle="<%=interfaces%>"/>
            </html:button>
        </td>
    </tr>
    </table>   
</div>