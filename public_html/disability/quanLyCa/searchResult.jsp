<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<%
    String total = (String)request.getAttribute("total");
%>
	
<div class="content-calendar">      
    <div class="content-calendar-2" >
        <a href="javascript:getSearchForm()">
            <bean:message key="disability.search.form.tkt" bundle="<%=interfaces%>"/>
        </a>
        | 
        <a href="javascript:openWindow('searchdispeople',anchor +':_PREPARED');">
            B&#225;o c&#225;o
        </a>
        |
        <a href="#">T&#7893;ng s&#7889; <%=total%> k&#7871;t qu&#7843;</a> 
    </div>
<div>

<bean:define name="timkiem" property="kyBC" id="kybcT" type="java.lang.Integer" /> 
<bean:define name="timkiem" property="namBC" id="nambcT" type="java.lang.Integer"/> 

<div align="right">
    <logic:equal name="timkiem" property="typeResult" value="0" >
        <% String params = anchor + ":_QUANLYCA_RESULT" ;%>
            <jsp:include page="/admin/paging.jsp">
                    <jsp:param name="BEANS" value="BDisabilitys"/>
                    <jsp:param name="FORM" value="searchdispeople"/>
                    <jsp:param name="METHOD" value="postAjax"/>
                    <jsp:param name="POSITION" value="divSearchResult"/>
                    <jsp:param name="PARAMS" value="<%=params%>"/>
            </jsp:include>
    </logic:equal>
</div>

<br/>

<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
	<TBODY>	
	
	<TR>               
	    <TH width="10px" nowrap align="center">#</TH>            
	    <TH nowrap ><bean:message key="disability.form.label.area" bundle="<%=interfaces%>"/></TH>
            <TH nowrap >S&#7889; th&#7913; t&#7921; NKT </TH>
	    <TH nowrap ><bean:message key="disability.form.label.nkt" bundle="<%=interfaces%>"/></TH>
	    <TH nowrap ><bean:message key="disability.form.label.ngaySinh" bundle="<%=interfaces%>"/></TH>
	    <TH nowrap ><bean:message key="disability.form.label.cmnd" bundle="<%=interfaces%>"/></TH>
	    <TH nowrap ><bean:message key="disability.form.label.sex" bundle="<%=interfaces%>"/></TH>
		<TH nowrap align="center">C</TH>
	</TR>
		
	<logic:present name="BDisabilitys"> 
		<bean:define name="BDisabilitys" id="beans" type="com.form.FBeans" />
		<%  int i = beans.getFirstRecord();%>
		<logic:iterate name="BDisabilitys" id="bean" type="com.form.disability.search.FSearch">                                       
	        <tr class="<%=(i%2==0)?"content1":"content"%>">
		        <td  nowrap align="center"><span class="index"><%=i++%></span></td>
		        <td  nowrap class="left"><bean:write name="bean" property="tinhName" /></td>
                        <td  nowrap class="center"><bean:write name="bean" property="ma" /></td>
		        <td  nowrap class="left">
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
		        <td  nowrap align="center"><bean:write name="bean" property="ngaySinh" /></td>
		        <td  nowrap align="center"><bean:write name="bean" property="cmnd" /></td>
		        <td  nowrap align="center">
			        <logic:equal name="bean" property="sex" value="0" >
			            <bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/>
			        </logic:equal>
			        <logic:equal name="bean" property="sex" value="1" >
			            <bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/>
			        </logic:equal>
		        </td>
		         <td>
                	<logic:equal name="bean" property="ttHoTro" value="1" >
                		C
                	</logic:equal>
                </td>	
	        </tr>
		</logic:iterate>
	</logic:present>
</tbody>
</table>

<div align="right">
	<logic:equal name="timkiem" property="typeResult" value="0" >
	    <% String params = anchor + ":_QUANLYCA_RESULT" ;%>
		<jsp:include page="/admin/paging.jsp">
			<jsp:param name="BEANS" value="BDisabilitys"/>
			<jsp:param name="FORM" value="searchdispeople"/>
			<jsp:param name="METHOD" value="postAjax"/>
			<jsp:param name="POSITION" value="divSearchResult"/>
			<jsp:param name="PARAMS" value="<%=params%>"/>
		</jsp:include>
	</logic:equal>
</div>

</div>
</div> 
 
