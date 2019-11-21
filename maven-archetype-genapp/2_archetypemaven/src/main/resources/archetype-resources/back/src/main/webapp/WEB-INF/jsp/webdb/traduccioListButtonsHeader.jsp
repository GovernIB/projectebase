#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
          <c:if test="${symbol_dollar}{__theFilterForm.editButtonVisible || __theFilterForm.deleteButtonVisible || not empty __theFilterForm.additionalButtonsForEachItem || not empty __theFilterForm.additionalButtonsByPK}">
          <th><fmt:message key="genapp.actions" /></th>
          </c:if>
