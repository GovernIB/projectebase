#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
  <div class="navbar-form pull-right">
    <c:if test="${symbol_dollar}{__theForm.saveButtonVisible}">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="genapp.save"/>">
    </c:if>
    <c:if test="${symbol_dollar}{__theForm.cancelButtonVisible}">
    <input type="button" class="btn" onclick="goTo('<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{__theForm.idioma.idiomaID}/cancel"/>')" value="<fmt:message key="genapp.cancel"/>">
    </c:if>
    <c:if test="${symbol_dollar}{!__theForm.nou && __theForm.deleteButtonVisible}">
    <button type="button" class="btn btn-danger" onclick="openModal('<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{__theForm.idioma.idiomaID}/delete"/>','show');"><fmt:message key="genapp.delete"/></button>
    </c:if>
    <c:set var="bracket" value="{0}"/>
    <c:forEach var="button" items="${symbol_dollar}{__theForm.additionalButtons}">
    <c:if test="${symbol_dollar}{!__theForm.nou || (-1 == fn:indexOf(button.link,bracket))}">
    <c:set var="pk" value="${symbol_dollar}{__theForm.idioma.idiomaID}"/>
    <c:set var="thelink" value="${symbol_dollar}{fn:replace(button.link,bracket, pk)}" />
    <c:set var="thehref" value="${symbol_pound}"/>
    <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
     <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
     <c:url var="thelink" value=""/>
    </c:if>
    <a class="btn ${symbol_dollar}{button.type}" 
       href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}" style="${symbol_dollar}{(empty button.type)? '' : 'color: white;'}"  >
       <i class="${symbol_dollar}{button.icon}"></i><fmt:message key="${symbol_dollar}{button.codeText}"/>
    </a>
    </c:if>
    </c:forEach>
  </div>
