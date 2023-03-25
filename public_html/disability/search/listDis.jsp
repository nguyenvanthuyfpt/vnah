<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>


<table width="100%">
<tr>
	<!--<td>Ghi ch&#250;: Nh&#7919;ng tr&#432;&#7901;ng h&#7907;p c&#243; ghi 
		<strong>"C"</strong> l&#224; &#273;&#432;&#7907;c <strong>"Qu&#7843;n l&#253; ca"</strong>.</td>-->
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
        <TH width="5%">#</TH> 
        <TH width="12%"><bean:message key="common.label.regdate" bundle="<%=interfaces%>"/></TH>
        <TH width="15%"><bean:message key="common.label.location" bundle="<%=interfaces%>"/></TH>
        <TH width="12%"><bean:message key="common.label.dis.code" bundle="<%=interfaces%>"/></TH>
        <TH width="12%"><bean:message key="common.label.dis.code.nkt" bundle="<%=interfaces%>"/></TH>
        <TH><bean:message key="common.label.name" bundle="<%=interfaces%>"/></TH>
        <TH width="10%"><bean:message key="common.label.birth" bundle="<%=interfaces%>"/></TH>
        <TH width="8%"><bean:message key="common.label.sex" bundle="<%=interfaces%>"/></TH>
        <!--<TH nowrap><bean:message key="common.label.address" bundle="<%=interfaces%>"/></TH>-->
        <TH width="5%">C</TH>
    </TR>
    
    <logic:present name="BDisabilitys"> 
        <bean:define name="BDisabilitys" id="beans" type="com.form.FBeans" />
        <%  int i = beans.getFirstRecord();%>
        <logic:iterate name="BDisabilitys" id="bean" type="com.form.disability.search.FSearch">                                       
            <!--<tr class="<%=(i%2==0)?"content1":"content"%>">-->
            <tr onclick="post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI:dtlId:<%=bean.getId()%>:locationId:<%=bean.getTinhId()%>:inputType:1:type:1:objId:56');"  
                class="<%=(i%2==0)?"content1":"content"%>" style="<%=(bean.getStatusId()==1)?"font-style:italic;font-weight:bold":""%>">                
                <td align="center"><span class="index"><%=i++%></span></td>
                <td nowrap><bean:write name="bean" property="lastupdate" /></td>
                <td class="left" nowrap><bean:write name="bean" property="tinhName" /></td>
                <td><bean:write name="bean" property="ma" /></td>
                <td><bean:write name="bean" property="maSo" /></td>
                <td class="left" nowrap><bean:write name="bean" property="nkt" /></td>
                <td ><bean:write name="bean" property="ngaySinh"/></td>
                <td  align="left">
                    <logic:equal name="bean" property="sex" value="0" >
                        <bean:message key="common.label.sex.male" bundle="<%=interfaces%>"/>
                    </logic:equal>                    
                    <logic:equal name="bean" property="sex" value="1" >
                        <bean:message key="common.label.sex.female" bundle="<%=interfaces%>"/>
                    </logic:equal>
                </td>
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

  
