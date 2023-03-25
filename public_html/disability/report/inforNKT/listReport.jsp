<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">    
    function excuteReport(anchorName,funcName){		
        post('reportinfo',anchor + ':'+ anchorName+':func:'+funcName);
    }
</script>
<html:form action="reportinfo" method="post">
<div class="padding-content">	
    <ul id="tree">
        <li>
            <div class="bgr7"><a href="javascript:excutePostCategorys('_MANAGER_INFO')">
                    B&#225;o c&#225;o t&#243;m t&#7855;t ng&#432;&#7901;i khuy&#7871;t t&#7853;t</a></div>
        </li>
</ul>
    
<br/>
    
<table width="100%" class="tableForm" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <th>
            <div class="content-calendar-2" align="left">
            	T&#243;m t&#7855;t NKT theo c&#225;c tuy&#7871;n
            </div>    
        </th>
    </tr>
    <tr>
    <td align="left" >
        Ch&#7885;n Tuy&#7871;n :
        <html:select styleClass="inputbox" name="reportInfo" property="tinhId">
            <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
        </html:select>        
         <logic:equal name="anchor" value="03">
            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_INFOR_NKT','_QUANLYCA');" >Xu&#7845;t excel</html:button>
        </logic:equal>
        <logic:notEqual name="anchor" value="03">
            <html:button property="_CREATE" styleClass="button" onclick="excuteReport('_REPORT_INFOR_NKT','_REPORT_COMMUNE');" >Xu&#7845;t excel</html:button>
        </logic:notEqual>
    </td>
    </tr>
 
     <logic:equal name="anchor" value="03">	
    <tr>
        <td height="30px"><i>(Ph&#226;n t&#237;ch s&#7889; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca)</i></td>
    </tr>
    </logic:equal>
    
    <logic:notEqual name="anchor" value="03">
    <tr>
        <td height="20px">&nbsp;</td></tr>
    </logic:notEqual>
</table>
     
</div>
</html:form>  
