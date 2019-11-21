#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TraduccioFields" className="${package}.model.fields.TraduccioFields"/>
  


        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
        <c:if test="${symbol_dollar}{ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${symbol_dollar}{${prefix}:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,TraduccioFields.TRADUCCIOID)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,TraduccioFields.TRADUCCIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
        <c:if test="${symbol_dollar}{ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${symbol_dollar}{${prefix}:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

