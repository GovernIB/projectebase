#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="traduccioFormTitle.jsp" %>


<form:form modelAttribute="traduccioForm" method="${symbol_dollar}{method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${symbol_dollar}{traduccioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="traduccioFormCorePre.jsp" %>
  <%@include file="traduccioFormCore.jsp" %>

  <%@include file="traduccioFormCorePost.jsp" %>

  <%@include file="traduccioFormButtons.jsp" %>

  <c:if test="${symbol_dollar}{traduccioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/traduccioFormModificable.jsp" %>
  </c:if>

</form:form>

