<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(anchorName){
        post('reportqlc',anchor + ':'+ anchorName);
    }
</script>
<html:form action="reportqlc" method="post">

<div class="padding-content">	
    <ul id="tree">
        <li>
            <div class="bgr7"><a href="javascript:excutePostCategorys('_REPORT_QLC_COLLECT','_QUANLYCA')">
            B&#225;o c&#225;o</a></div>            
        </li>
    </ul>
    
    <br/>
    <table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	B&#225;o c&#225;o c&#244;ng t&#225;c QLTH
            </div>    
        </th>
    </tr>
    
    <tr>
        <td align="left" >
            Ch&#7885;n Tuy&#7871;n :
            <html:select styleClass="inputbox" name="reportqlc" property="id_tinh">
                <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
            </html:select>
        </td>
    </tr>    
    
    <tr>
        <td align="left">
            Th&#225;ng b&#225;o c&#225;o 
            <html:select styleClass="inputbox" name="reportqlc" property="kyBC" >
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
            n&#259;m <html:text name="reportqlc" property="namBC" onblur="isYear(this);" size="4" style="text-align:center;" />
        </td>
    </tr>
    
    <tr>
        <td align="left">
            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_QLC_COLLECT');">
                Xu&#7845;t excel
            </html:button>
        </td>
    </tr>    	
    
    <tr><td height="20px">&nbsp;</td></tr>    
    </table>
</div>
</html:form>  
