<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BSearchTinhs">
<div class="bgr3">
    <script language=javascript>
        function getInforSearchByArea(id){
                postAjax('disabilityFuntion','right',anchor + ':_SEARCH_RESULT:id:' + id);
                messageImg('right');
        }
        
        dLeftSearchTinh = new dTree('dLeftSearchTinh');
        dLeftSearchTinh.add(0,-1,'<font style="font-size:11px;"><bean:message key="location" bundle="<%=interfaces%>"/></font>',"#");
        
        <logic:notEmpty name="BSearchTinhs">		
            <logic:iterate id="bean" name="BSearchTinhs" type="com.form.disability.categorys.FTinh">
                    dLeftSearchTinh.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName().replaceAll("--- ","")%>',"javascript:getInforSearchByArea(<%=bean.getId()%>)");
            </logic:iterate> 
        </logic:notEmpty>
                            
        document.write(dLeftSearchTinh);
    </script>
</div>
</logic:present> 
