<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<html:select styleClass="combobox_w150" name="timkiem" property="tinhId" 
    onchange="javascript:postAjax('searchdispeople','id_parameter_search',anchor + ':_SELECT_DATATYPE');">    
    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
</html:select>
<span style="color:#005BCC"><bean:write name="timkiem" property="tinhName" /></span> 
