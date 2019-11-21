#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
      <c:if test="${symbol_dollar}{__theFilterForm.visibleMultipleSelection}">
      <th>
         <input type="checkbox" onClick="selectUnselectCheckBoxes(this)" />
      </th>
      </c:if>
