<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BFiles">
<div>
   <ol style="margin:9px;padding:0px;line-height:16px;">
    <logic:iterate name="BFiles" id="beanFiles" type="com.form.template.FTemplate">    
    <li>
    <a  href="javascript:post('template',anchor + ':_DOWNLOAD:id:<bean:write name="beanFiles" property="id"/>');remove('template',anchor);remove('template','id');" >
         <bean:write name="beanFiles" property="name" />
       </a>
       <div align="right" style="font-size:10px;padding-right:5px;"><bean:write name="beanFiles" property="timeCreate" /></div>
    </li>
    </logic:iterate>
    </ol>
</div>
</logic:present>
