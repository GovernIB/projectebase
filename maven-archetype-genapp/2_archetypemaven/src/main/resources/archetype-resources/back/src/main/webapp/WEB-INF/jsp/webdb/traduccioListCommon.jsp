#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${symbol_dollar}{traduccioFilterForm.contexte}"/>
  <c:set var="formName" value="traduccio" />
  <c:set var="__theFilterForm" value="${symbol_dollar}{traduccioFilterForm}" />
  <c:if test="${symbol_dollar}{empty traduccioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="traduccio.traduccio"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty traduccioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${symbol_dollar}{traduccioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${symbol_dollar}{empty traduccioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="traduccio.traduccio"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty traduccioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${symbol_dollar}{traduccioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.traduccio.submit();  
  }
</script>
