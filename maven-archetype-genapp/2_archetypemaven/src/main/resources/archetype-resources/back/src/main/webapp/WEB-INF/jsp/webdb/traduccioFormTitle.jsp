#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${symbol_dollar}{fn:startsWith(traduccioForm.titleCode,'=')}">
       <c:out value="${symbol_dollar}{fn:substringAfter(traduccioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${symbol_dollar}{not empty traduccioForm.titleCode}">
    <fmt:message key="${symbol_dollar}{traduccioForm.titleCode}" >
      <fmt:param value="${symbol_dollar}{traduccioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${symbol_dollar}{empty traduccioForm.entityNameCode}">
      <fmt:message var="entityname" key="traduccio.traduccio"/>
    </c:if>
    <c:if test="${symbol_dollar}{not empty traduccioForm.entityNameCode}">
      <fmt:message var="entityname" key="${symbol_dollar}{traduccioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${symbol_dollar}{traduccioForm.nou?'genapp.createtitle':(traduccioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${symbol_dollar}{keytitle}">
      <fmt:param value="${symbol_dollar}{entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${symbol_dollar}{not empty traduccioForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${symbol_dollar}{fn:startsWith(traduccioForm.subTitleCode,'=')}" />
<c:if test="${symbol_dollar}{subtitleTranslated}">
   <c:out value="${symbol_dollar}{fn:substringAfter(traduccioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${symbol_dollar}{not subtitleTranslated}">
  <fmt:message key="${symbol_dollar}{traduccioForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>