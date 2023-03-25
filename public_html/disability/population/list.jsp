<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div class="content-calendar">
<table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TBODY>
        <TR>               
            <TH   width="10px" nowrap align="center">#</TH>            
            <TH nowrap ><bean:message key="disability.population.country.dateUpdate" bundle="<%=interfaces%>"/></TH>
            <TH nowrap ><bean:message key="disability.population.country.period" bundle="<%=interfaces%>"/></TH>
            <TH nowrap >
                <bean:message key="disability.population.country.sex.male" bundle="<%=interfaces%>"/>
                <18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>)
            </TH>
            <TH nowrap >
                <bean:message key="disability.population.country.sex.male" bundle="<%=interfaces%>"/>
                >=18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>)
            </TH>
            <TH nowrap >
                <bean:message key="disability.population.country.sex.famale" bundle="<%=interfaces%>"/>
                <18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>)
            </TH>
            <TH nowrap >
                <bean:message key="disability.population.country.sex.famale" bundle="<%=interfaces%>"/>
                >=18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>)
            </TH>
            <TH nowrap >#</TH>
        </TR>
        <logic:present name="BPopulations"> 
        <bean:define name="BPopulations" id="beans" type="com.form.FBeans" />
            <%  int i =1;%>
            <logic:iterate name="BPopulations" id="bean" type="com.form.disability.FPopulation">                                       
                <tr style="cursor:pointer" onclick="post('population',anchor + ':_DETAIL:id:<%=bean.getId()%>')"  class="<%=(i%2==0)?"content1":"content"%>">
                    <td  nowrap align="center"><span class="index"><%=i++%></span></td>
                    <td  nowrap align="center"><bean:write name="bean" property="dateEdit" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="period" />/<bean:write name="bean" property="yearOfPeriod" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="maleLessThan18" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="maleMoreThan18" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="famaleLessThan18" /></td>
                    <td  nowrap align="center"><bean:write name="bean" property="famaleMoreThan18" /></td>
                    <td><img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('population',anchor + ':_DELETE:id:<%=bean.getId()%>')"></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>
</div> 
