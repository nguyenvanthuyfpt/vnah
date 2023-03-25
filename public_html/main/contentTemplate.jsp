<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>        
 <ol class="oltenplate">
    <logic:iterate name="BTemplates" id="bean" type="com.form.template.FTemplate">
     <li class="lifontTemplate"><bean:write name="bean" property="code" />  -  
     <a  href="javascript:post('template',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('template',anchor);remove('template','id');" >
     <bean:write name="bean" property="name" />
     </a>
      <logic:equal name="bean" property="versionId" value="1">
        <input type="button" style="cursor:pointer;width:15px" value="+" onclick="javascript:addthis_open(this,'<bean:message key="doc.version.caption" bundle="<%=interfaces%>"/>','','', '');postAjax('template','at_share',anchor + ':_SAVE_FALSE:type:1:code:<bean:write name="bean" property="code"/>');"   />
    </logic:equal>
     <logic:equal name="bean" property="hostNew" value="1">
    <img  src="<%=contextPath%>/images/hot.gif" />
    </logic:equal>
     </li>      
  </logic:iterate>                      
  </ol>

   
   
    
    

  
   