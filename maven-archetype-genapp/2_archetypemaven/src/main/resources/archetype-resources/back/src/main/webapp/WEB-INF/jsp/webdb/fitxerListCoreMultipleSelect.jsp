#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${symbol_dollar}{__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${symbol_dollar}{fitxer.fitxerID}"/>
       &nbsp;
      </td>
      </c:if>

