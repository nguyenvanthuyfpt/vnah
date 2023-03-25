<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>

<table width="100%">
<tr>
	<td>Ghi ch&#250;: Nh&#7919;ng tr&#432;&#7901;ng h&#7907;p c&#243; ghi 
		<strong>"C"</strong> l&#224; &#273;&#432;&#7907;c <strong>"Qu&#7843;n l&#253; ca"</strong>.</td>
	<td>
		<div align="right">
			<logic:equal name="timkiem" property="typeResult" value="0" >
				<% String params = anchor + ":_SEARCH_RESULT" ;%>
					<jsp:include page="/admin/paging.jsp">
						<jsp:param name="BEANS" value="BDisabilitys"/>
						<jsp:param name="FORM" value="searchdispeople"/>
						<jsp:param name="METHOD" value="postAjax"/>
						<jsp:param name="POSITION" value="divSearchResult"/>
						<jsp:param name="PARAMS" value="<%=params%>"/>
					</jsp:include>
			</logic:equal>
				
			<logic:equal name="timkiem" property="typeResult" value="1" >
				<% String params = anchor + ":_SEARCH_RESULT" ;%>
					<jsp:include page="/admin/paging.jsp">
						<jsp:param name="BEANS" value="BDisabilitys"/>
						<jsp:param name="FORM" value="disabilityFuntion"/>
						<jsp:param name="METHOD" value="postAjax"/>
						<jsp:param name="POSITION" value="right"/>
						<jsp:param name="PARAMS" value="<%=params%>"/>
					</jsp:include>
			</logic:equal>
		</div>
	</td>
</tr>
</table>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TR>               
        <TH align="center" width="10px" >#</TH>            
        <TH nowrap><bean:message key="disability.form.label.area" bundle="<%=interfaces%>"/></TH>
        <TH nowrap><bean:message key="disability.form.label.nkt" bundle="<%=interfaces%>"/></TH>
        <TH nowrap><bean:message key="disability.form.label.ngaySinh" bundle="<%=interfaces%>"/></TH>
        <TH nowrap><bean:message key="disability.form.label.cmnd" bundle="<%=interfaces%>"/></TH>
        <TH nowrap><bean:message key="disability.form.label.soNha" bundle="<%=interfaces%>"/></TH>                                            
        <TH nowrap><bean:message key="disability.form.label.sex" bundle="<%=interfaces%>"/></TH>
        <!--<TH nowrap>Tr&#7841;ng th&#225;i</TH>-->
        <TH nowrap align="center" >C</TH>
    </TR>
    
    <logic:present name="BDisabilitys"> 
        <bean:define name="BDisabilitys" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="BDisabilitys" id="bean" type="com.form.disability.search.FSearch">                                       
            <tr class="<%=(i%2==0)?"content1":"content"%>">
                <td align="center"><span class="index"><%=i++%></span></td>
                <td class="left" nowrap><bean:write name="bean" property="tinhName" /></td>
                <td  nowrap align="left">
                    <% if ("9".equals(bean.getTrangthai())){%>
                    <a style="font-weight:bold;"                     
                        href="javascript:view_detail('<%=bean.getId()%>');"
                        onmouseover="Tip('NKT &#273;&#227; b&#7883; x&#243;a.',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                        <bean:write name="bean" property="nkt" />
                    </a>
                    <%}else{%>
                    <a href="javascript:view_detail('<%=bean.getId()%>');"
                        onmouseover="Tip('Xem th&#244;ng tin chi ti&#7871;t NKT',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                        <bean:write name="bean" property="nkt" />
                    </a>
                    <%}%>
                </td>
                <td  align="center"><bean:write name="bean" property="ngaySinh"/></td>
                <td  align="center"><bean:write name="bean" property="cmnd" /></td>
                <td  class="left"><bean:write name="bean" property="soNha" /></td>
                <td  align="center">
                    <logic:equal name="bean" property="sex" value="0" >
                        <bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    <logic:equal name="bean" property="sex" value="1" >
                        <bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/>
                    </logic:equal>
                </td>                
                <!--<td align="center">
                    <logic:equal name="bean" property="trangthai" value="0" >
                        <img style="cursor:pointer;" src="<%=contextPath%>/images/checkboxtree/bg-checkbox.gif" 
                            onmouseover="this.src='<%=contextPath%>/images/checkboxtree/bg-checkbox-checked.gif'"
                            onmouseout="this.src='<%=contextPath%>/images/checkboxtree/bg-checkbox.gif'"
                            title="Ch&#432;a x&#225;c nh&#7853;n" 
                            onClick="javascript:post('dis_detail',anchor + ':_APPROVE:id:<%=bean.getId()%>')">
                    </logic:equal>
                    <logic:equal name="bean" property="trangthai" value="1" >
                        <img style="cursor:pointer;" src="<%=contextPath%>/images/checkboxtree/bg-checkbox-checked.gif" 
                            title="&#272;&#227; x&#225;c nh&#7853;n" 
                            onClick="javascript:post('dis_detail',anchor + ':_DETAIL:id:<%=bean.getId()%>')">
                    </logic:equal>                                    
                    <img style="border:0px" style="cursor:pointer;" src="<%=contextPath%>/images/delete.png" 
                        title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                        onClick="javascript:if(messageDelete())post('searchdispeople',anchor + ':_DELETE_DISABILITY:id:<%=bean.getId()%>')">
                </td>-->
                <td>
                    <logic:equal name="bean" property="ttHoTro" value="1" >C</logic:equal>
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</table>

<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>

<div align="right">
<logic:equal name="timkiem" property="typeResult" value="0" >
    <% String params = anchor + ":_SEARCH_RESULT" ;%>
		<jsp:include page="/admin/paging.jsp">
			<jsp:param name="BEANS" value="BDisabilitys"/>
			<jsp:param name="FORM" value="searchdispeople"/>
			<jsp:param name="METHOD" value="postAjax"/>
			<jsp:param name="POSITION" value="divSearchResult"/>
			<jsp:param name="PARAMS" value="<%=params%>"/>
        </jsp:include>
</logic:equal>
	
<logic:equal name="timkiem" property="typeResult" value="1" >
    <% String params = anchor + ":_SEARCH_RESULT" ;%>
		<jsp:include page="/admin/paging.jsp">
			<jsp:param name="BEANS" value="BDisabilitys"/>
			<jsp:param name="FORM" value="disabilityFuntion"/>
			<jsp:param name="METHOD" value="postAjax"/>
			<jsp:param name="POSITION" value="right"/>
			<jsp:param name="PARAMS" value="<%=params%>"/>
		</jsp:include>
</logic:equal>
</div>

  
