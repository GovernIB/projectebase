#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${symbol_dollar}{idiomaFilterForm.contexte}"/>
  <c:set var="formName" value="idioma" />
  <c:set var="__theFilterForm" value="${symbol_dollar}{idiomaFilterForm}" />
  <c:if test="${symbol_dollar}{empty idiomaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="idioma.idioma"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty idiomaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${symbol_dollar}{idiomaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${symbol_dollar}{empty idiomaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="idioma.idioma"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty idiomaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${symbol_dollar}{idiomaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.idioma.submit();  
  }
</script>
