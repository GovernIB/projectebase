#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="fitxerFormTitle.jsp" %>


<form:form modelAttribute="fitxerForm" method="${symbol_dollar}{method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${symbol_dollar}{fitxerForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="fitxerFormCorePre.jsp" %>
  <%@include file="fitxerFormCore.jsp" %>

  <%@include file="fitxerFormCorePost.jsp" %>

  <%@include file="fitxerFormButtons.jsp" %>

  <c:if test="${symbol_dollar}{fitxerForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/fitxerFormModificable.jsp" %>
  </c:if>

</form:form>


