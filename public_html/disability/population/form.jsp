<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String infoActive="";%>

<html:hidden property="level_province" name="population"/>
<bean:define name="population" id="bean" type="com.form.disability.FPopulation" />
<logic:notEmpty name="population" >
<%
    if(bean.getInfoActive()!=null){
        infoActive=bean.getInfoActive();
    }
%>
</logic:notEmpty>

<ul id="tree">
    <li>
        <div class="bgr1">
            <a href="#">
            Nh&#7853;p th&#244;ng tin Tuy&#7871;n Ph&#432;&#7901;ng/X&#227;</a>
        </div>
    </li>
</ul>
<br/>

<div class="content-calendar">
    <div class="content-calendar-2">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td align="left" colspan="4" >
                C&#225;c tuy&#7871;n:
                <html:select styleClass="inputbox" name="population" property="id_tinh" onchange="optionChose(this.selectedIndex,this.value)">
                <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                </html:select>
                <span style="color:#005BCC">(<bean:write name="population" property="tinhName" />)</span>
            </td>
        </tr>
        <tr>
            <td align="left" colspan="4" >
                <bean:message key="disability.population.country.period" bundle="<%=interfaces%>"/> :
                <html:select styleClass="inputbox" name="population" property="period" style="width:50px;text-align:right;">
                    <html:option value="1">1</html:option>
                    <html:option value="2">2</html:option>
                </html:select>
                <bean:message key="disability.population.country.yearOfPeriod" bundle="<%=interfaces%>"/> :
                <html:text name="population" property="yearOfPeriod" onblur="isYear(this);" style="width:40px" />
            </td>
        </tr>

        <tr>
            <th align="left"><bean:message key="disability.population.country.sex.male" bundle="<%=interfaces%>"/><18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>) :
                <html:text name="population" property="maleLessThan18" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
            </th><th align="left"><bean:message key="disability.population.country.sex.male" bundle="<%=interfaces%>"/>>=18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>) :
                <html:text name="population" property="maleMoreThan18" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
            </th><th align="left"><bean:message key="disability.population.country.sex.famale" bundle="<%=interfaces%>"/><18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>) :
                <html:text name="population" property="famaleLessThan18" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
            </th><th align="left"><bean:message key="disability.population.country.sex.famale" bundle="<%=interfaces%>"/>>=18 (<bean:message key="disability.population.country.age" bundle="<%=interfaces%>"/>) :
                <html:text name="population" property="famaleMoreThan18" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
            </th>
        </tr>
        </table>
        
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td colspan="2" align="left">
                <ul class="ulDisability">
                    <li>
                        <html:text name="population" property="number1" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        T&#7893; t&#432; l&#7921;c c&#7911;a NKT &#273;&#432;&#7907;c th&#224;nh l&#7853;p v&#224; ho&#7841;t &#273;&#7897;ng</li>
                    <li>
                        <html:text name="population" property="number2" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        NKT tham gia trong Ban di&#7873;u h&#224;nh  PHCNDVC&#272; c&#7911;a x&#227;</li>
                    <li>
                        <html:text name="population" property="number3" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        M&#7897;t s&#7889; c&#244;ng tr&#236;nh c&#244;ng c&#7897;ng trong x&#227; c&#243; ti&#7871;p c&#7853;n cho NKT</li>
                    <li>
                        <html:text name="population" property="number4" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        &#205;t nh&#7845;t m&#7897;t th&#224;nh vi&#234;n c&#7911;a B&#272;H &#273;&#432;&#7907;c t&#7853;p hu&#7845;n v&#7873; qu&#7843;n l&#253; ct PHCNDVC&#272;</li>
                    <li>
                        <html:text name="population" property="number5" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        L&#7853;p k&#7871; ho&#7841;ch PHCNDVC&#272; h&#224;ng n&#259;m theo chu&#7849;n b&#7897; y t&#7871;</li>
                    <li>
                        <html:text name="population" property="number6" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        T&#7893;ng s&#7889; CTV PHCNDVC&#272;</li>
                    <li>
                        <html:text name="population" property="number7" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV PHCNDVC&#272; l&#224; NKT (%CTV)</li>
                    <li>
                        <html:text name="population" property="number8" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV ch&#432;a &#273;&#432;&#7907;c t&#7853;p hu&#7845;n v&#7873; PHCNDVC&#272;(% CTV)</li>                            
                    <li>
                        <html:text name="population" property="number9" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV &#273;&#432;&#7907;c t&#7853;p hu&#7845;n l&#7841;i c&#417; b&#7843;n v&#7873; PHCNDVC&#272; (% CTV)
                    </li>
                    <li>
                        <html:text name="population" property="number10" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV &#273;&#432;&#7907;c t.hu&#7845;n n&#226;ng cao v&#7873; PHCNDVC&#272; (% CTV)
                    </li>
                    <li>
                        <html:text name="population" property="number11" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV &#273;&#432;&#7907;c t.hu&#7845;n c&#417; b&#7843;n v&#224; n&#226;ng cao v&#7873; PHCNDVC&#272; (% CTV)
                    </li>
                    <li>
                        <html:text name="population" property="number12" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; c&#225;n b&#7897; PHCNDVC&#272; c&#7911;a tr&#7841;m y t&#7871; &#273;&#432;&#7907;c &#273;&#224;o t&#7841;o t&#7841;i tr&#432;&#7901;ng Trung c&#7845;p/Cao &#273;&#7859;ng/&#272;&#7841;i h&#7885;c y
                    </li>
                    <li>
                        <html:text name="population" property="number13" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        S&#7889; CTV PHCNDVC&#272; b&#7887; cu&#7897;c trong k&#7923; b&#225;o c&#225;o (% s&#7889; c&#7897;ng t&#225;c vi&#234;n)
                    </li>
                    <li>
                        <html:text name="population" property="number14" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        Tr&#7841;m y t&#7871; x&#227; &#273;&#7841;t chu&#7849;n qu&#7889;c gia c&#7911;a B&#7897; Y t&#7871; (Bao g&#7891;m c&#7843; PHCN)
                    </li>
                    <li>
                        <html:text name="population" property="number15" onkeypress="return formatInt(event, this);"  onkeyup="return addSeparators(this);" onblur="return isIntComma(this);" style="width:50px;text-align:right;" />
                        Kinh ph&#237; c&#7911;a x&#227; d&#224;nh cho ch&#432;&#417;ng tr&#236;nh
                    </li>
                    <li>&nbsp;</li>
                </ul>
            </td>
        </tr>
       
        <tr>
            <td colspan="2" align="left">            
            <% if(request.getSession().getAttribute("05.01")!=null){%>
                <%if(bean.getLevel_province()==2){%>
                    <html:button property="_CREATE" styleClass="button" onclick="post('population',anchor + ':_CREATE');" >
                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
            <%}}%>
                <logic:notEqual name="population" property="id" value="0">
                <bean:define  name="population" property="id" id="id" type="java.lang.Integer" /> 
                <%String onclick="post('population',anchor + ':_EDIT:id:"+id+"')"; %>
                    <% if(request.getSession().getAttribute("05.02")!=null){%>
                    <html:button property="_EDIT" styleClass="button" onclick="<%=onclick%>">                 
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                     <%}%>  	
                </logic:notEqual>
            </td>
        </tr>
        </table>
    </div>
</div> 
