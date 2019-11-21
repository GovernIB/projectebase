#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${symbol_dollar}{fitxerFilterForm.contexte}"/>
  <c:set var="formName" value="fitxer" />
  <c:set var="__theFilterForm" value="${symbol_dollar}{fitxerFilterForm}" />
  <c:if test="${symbol_dollar}{empty fitxerFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="fitxer.fitxer"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty fitxerFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${symbol_dollar}{fitxerFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${symbol_dollar}{empty fitxerFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="fitxer.fitxer"/>
  </c:if>
  <c:if test="${symbol_dollar}{not empty fitxerFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${symbol_dollar}{fitxerFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.fitxer.submit();  
  }
</script>
