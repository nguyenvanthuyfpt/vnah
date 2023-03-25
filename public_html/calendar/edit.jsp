<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
    function addDep(obj){
        var member=document.agenda.departmentIds.value;
        if(member=='') member=',';
        if(obj.checked && member.indexOf(","+ obj.value+ ",")<0){
            member+=obj.value + ',';
        }else if(obj.checked==false){
            member=member.replace(',' + obj.value + ',',',')
        }
        document.agenda.departmentIds.value=member;
    }
    
    function addUserDep(obj){
        var member=document.agenda.userIds.value;
        if(member=='') member=',';
        if(obj.checked && member.indexOf(","+ obj.value+ ",")<0){
            member+=obj.value + ',';
        }else if(obj.checked==false){
            member=member.replace(',' + obj.value + ',',',')
        }
        document.agenda.userIds.value=member;
    }
   </script>

<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<html:form action="agenda"  method="post" target="_parent">
<input type="hidden" name="departmentIds" id="departmentIds" value="" />
<input type="hidden" name="userIds" id="userIds" value="" />
<div id="idEdit">
  <logic:equal name="agenda" property="calendarType" value="1" > 
    <jsp:include page="/calendar/formPublic.jsp" />
  </logic:equal>
  <logic:notEqual name="agenda" property="calendarType" value="1" > 
    <jsp:include page="/calendar/formPrivate.jsp" />
  </logic:notEqual>
</div>
</html:form>