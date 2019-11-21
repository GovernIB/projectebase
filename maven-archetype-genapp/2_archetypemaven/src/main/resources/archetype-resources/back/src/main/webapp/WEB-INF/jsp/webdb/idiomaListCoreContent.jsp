#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IdiomaFields" className="${package}.model.fields.IdiomaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}" >
        <c:if test="${symbol_dollar}{ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${symbol_dollar}{not empty __entry.value.valueMap }">
               <c:out escapeXml="${symbol_dollar}{__entry.value.escapeXml}" value="${symbol_dollar}{__entry.value.valueMap[idioma.idiomaID]}" />
             </c:if>
             <c:if test="${symbol_dollar}{not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${symbol_dollar}{pageScope}" />
               <c:set var="__trosos" value="${symbol_dollar}{fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${symbol_dollar}{__trosos}">
                  <c:set var="__tmp" value="${symbol_dollar}{__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${symbol_dollar}{__entry.value.escapeXml}" value="${symbol_dollar}{__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.IDIOMAID)}">
          <td>
          ${symbol_dollar}{idioma.idiomaID}
          </td>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.NOM)}">
          <td>
          ${symbol_dollar}{idioma.nom}
          </td>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.SUPORTAT)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${symbol_dollar}{idioma.suportat?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.ORDRE)}">
          <td>
          ${symbol_dollar}{idioma.ordre}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}" >
        <c:if test="${symbol_dollar}{ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${symbol_dollar}{not empty __entry.value.valueMap }">
               <c:out escapeXml="${symbol_dollar}{__entry.value.escapeXml}" value="${symbol_dollar}{__entry.value.valueMap[idioma.idiomaID]}" />
             </c:if>
             <c:if test="${symbol_dollar}{not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${symbol_dollar}{pageScope}" />
               <c:set var="__trosos" value="${symbol_dollar}{fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${symbol_dollar}{__trosos}">
                  <c:set var="__tmp" value="${symbol_dollar}{__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${symbol_dollar}{__entry.value.escapeXml}" value="${symbol_dollar}{__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


