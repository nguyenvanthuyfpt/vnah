<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>        
<logic:present name="BBroadcasts" >  
<div class="tblnews" style="width:300px">
 <bean:define name="BBroadcasts" id="beans" type="com.form.FBeans"/>
<%  int i = beans.getFirstRecord();%>
    <logic:iterate name="BBroadcasts" id="bean" type="com.form.broadcast.FBroadcast">
    <div class="b-news">
    <p class="textnone">
        <bean:write name="bean" property="title" /><logic:equal name="bean" property="special" value="1" > &nbsp;<img src="<%=contextPath%>/images/hot.gif" /></logic:equal>
    </p>
        <div class="contentBroadcastList">
        <logic:notEqual name="bean" property="content" value="">
        <bean:define  name="bean" property="content" id="content" />
             <%=content%>
       </logic:notEqual>       
        </div>
        <div align="right" style="font-size:10px;padding-right:5px;"><bean:write name="bean" property="createtime" /></div>
        </div>
    </logic:iterate>            
</div>
</logic:present>
   
   
    
    

  
   