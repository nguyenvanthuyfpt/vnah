<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BPortletClose">
    <logic:notEmpty name="BPortletClose">
                <select style="width:80px;margin-left:10px;" name="menuId" onchange="javascript:if(this.selectedIndex>0) {eval(this.value);this.remove(this.selectedIndex);if(this.length==1)this.style.display='none';}">
                <option value="0">--M&#7903;--</option>
                    <logic:iterate name="BPortletClose" id="bean" type="com.form.main.FMain">
                    <option value="postAjax('addPortlet','<%=bean.getMenuId()%>',anchor + ':_OPEN_PORTLET:menuId:<%=bean.getMenuId()%>')" ><%=bean.getName()%></option>
                    </logic:iterate>
                </select>
    </logic:notEmpty>
    </logic:present>