#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="${package}.model.fields.FitxerFields"/>
  


        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
        <c:if test="${symbol_dollar}{ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${symbol_dollar}{${prefix}:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,FitxerFields.FITXERID)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,FitxerFields.FITXERID)}</th>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,FitxerFields.NOM)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,FitxerFields.NOM)}</th>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,FitxerFields.DESCRIPCIO)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,FitxerFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,FitxerFields.MIME)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,FitxerFields.MIME)}</th>
        </c:if>
        <c:if test="${symbol_dollar}{!gen:contains(__theFilterForm.hiddenFields,FitxerFields.TAMANY)}">
        <th>${symbol_dollar}{${prefix}:getSortIcons(__theFilterForm,FitxerFields.TAMANY)}</th>
        </c:if>


        <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
        <c:if test="${symbol_dollar}{ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${symbol_dollar}{${prefix}:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

