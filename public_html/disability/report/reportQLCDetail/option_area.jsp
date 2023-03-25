<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
Ch&#7885;n Tuy&#7871;n :
<html:select styleClass="inputbox" name="reportqlc" property="id_tinh">
    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
</html:select>
<!--<span style="color:#005BCC">(<bean:write name="reportcommune" property="tinhName" />)</span>-->
 
