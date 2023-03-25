<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 

<table width="100%">
<logic:equal name="reportInfo" property="periodType" value="0" >
<tr>
    <td align="left" width="15%"><bean:message key="common.label.month" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select styleClass="inputbox" name="reportInfo" property="monthReport">
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
            <html:option value="5">5</html:option>
            <html:option value="6">6</html:option>
            <html:option value="7">7</html:option>
            <html:option value="8">8</html:option>
            <html:option value="9">9</html:option>
            <html:option value="10">10</html:option>
            <html:option value="11">11</html:option>
            <html:option value="12">12</html:option>
        </html:select>
         <html:select styleClass="inputbox" name="reportInfo" property="yearReport" >                          
              <html:option value="2016">2016</html:option>
              <html:option value="2017">2017</html:option>
              <html:option value="2018">2018</html:option>
              <html:option value="2019">2019</html:option>
              <html:option value="2020">2020</html:option>
               <html:option value="2021">2021</html:option>
                <html:option value="2022">2022</html:option>
                <html:option value="2023">2023</html:option>
                <html:option value="2024">2024</html:option>
                <html:option value="2025">2025</html:option>
          </html:select>
    </td>
</tr>
</logic:equal>

<logic:equal name="reportInfo" property="periodType" value="1" >
<tr>
    <td align="left" width="15%"><bean:message key="common.label.quarter" bundle="<%=interfaces%>"/></td>
    <td>
        <html:select styleClass="inputbox" name="reportInfo" property="quarterReport">
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
        </html:select>
         <html:select styleClass="inputbox" name="reportInfo" property="yearReport" >                          
              <html:option value="2016">2016</html:option>
              <html:option value="2017">2017</html:option>
              <html:option value="2018">2018</html:option>
              <html:option value="2019">2019</html:option>
              <html:option value="2020">2020</html:option>
              <html:option value="2021">2021</html:option>
              <html:option value="2022">2022</html:option>
          </html:select>
    </td>
</tr>
</logic:equal>

<logic:equal name="reportInfo" property="periodType" value="2" >
<tr>
    <td align="left" width="15%"><bean:message key="common.label.year" bundle="<%=interfaces%>"/></td>
    <td>
         <html:select styleClass="inputbox" name="reportInfo" property="yearReport" >                          
              <html:option value="2016">2016</html:option>
              <html:option value="2017">2017</html:option>
              <html:option value="2018">2018</html:option>
              <html:option value="2019">2019</html:option>
              <html:option value="2020">2020</html:option>
              <html:option value="2021">2021</html:option>
              <html:option value="2022">2022</html:option>
          </html:select>
    </td>
</tr>
</logic:equal>

<logic:equal name="reportInfo" property="periodType" value="3" >
<tr>
    <td align="left" width="15%"><bean:message key="common.label.from-to" bundle="<%=interfaces%>"/></td>
    <td>
         <html:select styleClass="inputbox" name="reportInfo" property="fromMonth">
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
            <html:option value="5">5</html:option>
            <html:option value="6">6</html:option>
            <html:option value="7">7</html:option>
            <html:option value="8">8</html:option>
            <html:option value="9">9</html:option>
            <html:option value="10">10</html:option>
            <html:option value="11">11</html:option>
            <html:option value="12">12</html:option>
        </html:select>
        <html:select styleClass="inputbox" name="reportInfo" property="fromYear" >                          
              <html:option value="2016">2016</html:option>
              <html:option value="2017">2017</html:option>
              <html:option value="2018">2018</html:option>
              <html:option value="2019">2019</html:option>
              <html:option value="2020">2020</html:option>
              <html:option value="2021">2021</html:option>
              <html:option value="2022">2022</html:option>
        </html:select>
        <bean:message key="common.label.to" bundle="<%=interfaces%>"/>
        <html:select styleClass="inputbox" name="reportInfo" property="toMonth">
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
            <html:option value="4">4</html:option>
            <html:option value="5">5</html:option>
            <html:option value="6">6</html:option>
            <html:option value="7">7</html:option>
            <html:option value="8">8</html:option>
            <html:option value="9">9</html:option>
            <html:option value="10">10</html:option>
            <html:option value="11">11</html:option>
            <html:option value="12">12</html:option>
        </html:select>
         <html:select styleClass="inputbox" name="reportInfo" property="toYear" >                          
              <html:option value="2016">2016</html:option>
              <html:option value="2017">2017</html:option>
              <html:option value="2018">2018</html:option>
              <html:option value="2019">2019</html:option>
              <html:option value="2020">2020</html:option>
              <html:option value="2021">2021</html:option>
              <html:option value="2022">2022</html:option>
          </html:select>
    </td>
</tr>
</logic:equal>
</table>