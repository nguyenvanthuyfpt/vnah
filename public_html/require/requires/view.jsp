  <%@ include file="/commons/tags.jsp"%>
  <%@ include file="/commons/params.jsp"%>
 <tiles:insert page="/layout/layout.jsp" flush="true">      
      <tiles:put name="leftContent" value="/require/requires/leftRm.jsp" />
      <tiles:put name="content" value="/require/requires/requireList.jsp" />
  </tiles:insert>