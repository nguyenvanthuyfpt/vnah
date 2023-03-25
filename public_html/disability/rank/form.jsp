<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>


<html:form action="kpi" method="POST" >
<html:hidden name="kpi" property="nktId"/>
<%	
	String checkBox="";
	String danhgiaIdsTemp="";
%>

<div align="left" class="fullName">
    <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>            
</div>


<div>
<table width="100%" class="tableForm">

<logic:greaterThan value="0" name="kpi" property="rankId">
<tr>
    <td colspan="2"><b><bean:write name="kpi" property="breadcrumb" /></b></td>
</tr>
</logic:greaterThan>

<tr>
    <td align="left" width="25%">Ng&#224;y &#273;&#225;nh gi&#225;</td>
    <td>
        <input type="text" name="rankCreateDate" id="rankCreateDate" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);" style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="kpi" property="rankCreateDate"/>" />						
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'rankCreateDate','dd/mm/yyyy');return false;">
    </td>
</tr>

<tr>
    <td><bean:message key="common.label.rank.name-parent" bundle="<%=interfaces%>"/></td>
    <td>
         <html:select name="kpi" property="parRankId" onchange="postAjax('kpi','MainCate',anchor + ':_RANK_CHANGE_OPTION:type:1');">
            <logic:present name="BComboRank">
                <html:options collection="BComboRank" property="id" labelProperty="name"/>
            </logic:present>
          </html:select>
    </td>
</tr>

<logic:greaterEqual value="0" name="kpi" property="dtlId">
<tr>
    <td><bean:message key="common.label.rank.score" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select name="kpi" property="rankResult" >
            <html:option value="0">0</html:option>
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
        </html:select>
    </td>
</tr>
</logic:greaterEqual>

<%--<logic:equal value="false" name="kpi" property="hasRank">--%>
<tr>
    <td><bean:message key="common.label.rank.has-support" bundle="<%=interfaces%>"/></td>
    <td>
         <html:select name="kpi" property="rankHasSP" >
            <html:option value="0">Kh&#244;ng</html:option>
            <html:option value="1">C&#243;</html:option>
         </html:select>
    </td>
</tr>
<%--</logic:equal>--%>

<tr>
    <td colspan="2">
        <div class="rank">
          <span>
              <bean:message key="common.label.rank.note" bundle="<%=interfaces%>"/>
          </span>
          <ul>
              <li><bean:message key="common.label.rank.contraint" bundle="<%=interfaces%>"/></li>
              <logic:notEqual name="kpi" property="notifyInit" value="">
                  <li><bean:write name="kpi" property="notifyInit" /></li>
              </logic:notEqual>
              <logic:notEqual name="kpi" property="notifyNext" value="">
                  <li><bean:write name="kpi" property="notifyNext" /></li>
              </logic:notEqual>
              <logic:notEqual name="kpi" property="notifyNumInput" value="">
                  <li><bean:write name="kpi" property="notifyNumInput" /></li>
              </logic:notEqual>
          </ul>
        </div>
    </td>
</tr>
</table>
</div>

<br/>

<logic:notEmpty name="BRankByR_D">
<div style="padding:5px;">
<table class="list-voffice" cellpadding="2" width="100%" align="center" cellspacing="2" border="0">    
  <TR>
	    <TH width="5%">STT</TH>
      <TH width="15%">Ng&#224;y &#273;&#225;nh gi&#225;</TH>
      <TH width="15%">Ng&#432;&#7901;i &#273;&#225;nh gi&#225;</TH>
      <TH><bean:message key="common.label.rank.score" bundle="<%=interfaces%>"/></TH>
      <TH>C&#243; h&#7895; tr&#7907;</TH>
      <TH width="8%">#</TH>
  </TR>

	<logic:present name="BRankByR_D">
  <%  int i =1;%>
      <bean:define name="BRankByR_D" id="beans" type="com.form.FBeans" />
      <logic:notEmpty name="BRankByR_D">
          <logic:iterate id="bean" name="BRankByR_D" type= "com.form.disability.categorys.FRank">
            <tr class="<%=(i%2==0)?"content":"content1"%>">
              <td><%=i++%></td>
              <td><bean:write name="bean" property="createDate"/></td>
              <td><bean:write name="bean" property="userName"/></td>
              <td><bean:write name="bean" property="result"/></td>
              <td>
                  <logic:equal name="bean" property="hasSP" value="1">
                      C&#243;
                  </logic:equal>
                  <logic:equal name="bean" property="hasSP" value="0">
                      Kh&#244;ng
                  </logic:equal>
              </td>
              <td>
                  <%if(request.getSession().getAttribute("10.02")!=null){%>
                  <img style="border:0px" src="<%=contextPath%>/images/editdraft.png" 
                        title="<bean:message key="action.detail" bundle="<%=interfaces%>"/>" 
                        onClick="postAjax('kpi','MainCate',anchor + ':_DETAIL_RANK_DTL:dtlId:<%=bean.getId()%>')">
                  <%}%>      
                  <%if(request.getSession().getAttribute("10.03")!=null){%>
                  <img style="border:0px" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_RANK:dtlId:<%=bean.getId()%>')">
                  <%}%>              
              </td>
            </tr>
          </logic:iterate>
      </logic:notEmpty>
	</logic:present>
</table>
</div>
</logic:notEmpty>


<logic:greaterThan value="0" name="kpi" property="rankId">
<bean:define name="kpi" property="rankId" id="rankId" type="java.lang.Integer"/>
<table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">    
    <tr>
        <td>
            <%if(request.getSession().getAttribute("10.01")!=null){%>
            <span class="bt_left_Search">
                <span class="bt_right_Search">
                    <span class="bt_center_Search">                          
                        <%String ins = "postAjax('kpi','MainCate',anchor + ':_INSERT_RANK:id:"+rankId+"');";%>
                        <html:button property="_EDIT" styleClass="button" onclick="<%=ins%>">
                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>                         
                    </span>
                </span>
            </span>
            <%}%>
        </td>
        
        <logic:greaterThan value="0" name="kpi" property="dtlId">
        <bean:define name="kpi" property="dtlId" id="dtlId" type="java.lang.Integer"/>
        <td>
            <%if(request.getSession().getAttribute("10.02")!=null){%>
            <span class="bt_left_Search">
                <span class="bt_right_Search">
                    <span class="bt_center_Search">
                          <bean:define name="kpi" property="rankId" id="rankId" type="java.lang.Integer"/>
                          <%String upd = "postAjax('kpi','MainCate',anchor + ':_UPDATE_RANK:id:"+rankId+":dtlId:"+dtlId+"');";%>
                          <html:button property="_EDIT" styleClass="button" onclick="<%=upd%>">
                              <bean:message key="action.update" bundle="<%=interfaces%>"/>
                          </html:button>
                    </span>
                </span>
            </span>
            <%}%>
        </td>
        </logic:greaterThan>
    </tr>
</table>
</logic:greaterThan>

</html:form>
