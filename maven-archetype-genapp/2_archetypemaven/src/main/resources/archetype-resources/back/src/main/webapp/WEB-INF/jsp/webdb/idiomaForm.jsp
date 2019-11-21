#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="idiomaFormTitle.jsp" %>


<form:form modelAttribute="idiomaForm" method="${symbol_dollar}{method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${symbol_dollar}{idiomaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="idiomaFormCorePre.jsp" %>
  <%@include file="idiomaFormCore.jsp" %>

  <%@include file="idiomaFormCorePost.jsp" %>

  <%@include file="idiomaFormButtons.jsp" %>

  <c:if test="${symbol_dollar}{idiomaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/idiomaFormModificable.jsp" %>
  </c:if>

</form:form>


