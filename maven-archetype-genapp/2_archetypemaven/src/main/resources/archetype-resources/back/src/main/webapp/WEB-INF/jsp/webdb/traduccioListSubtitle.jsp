#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${symbol_dollar}{not empty __theFilterForm.subTitleCode}">
<h5 style="line-height: 10px; margin-top: -10px; margin-bottom: 10px;">
<c:set var="subtitleTranslated" value="${symbol_dollar}{fn:startsWith(__theFilterForm.subTitleCode,'=')}" />
<c:if test="${symbol_dollar}{subtitleTranslated}">
   <c:out value="${symbol_dollar}{fn:substringAfter(__theFilterForm.subTitleCode, '=')}" escapeXml="false" />
</c:if>
<c:if test="${symbol_dollar}{not subtitleTranslated}">
  <fmt:message key="${symbol_dollar}{__theFilterForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
