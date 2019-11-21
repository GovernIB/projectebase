#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="${package}.model.fields.FitxerFields"/>
  

  <%-- HIDDEN PARAMS: GROUP BY --%>
  <form:hidden path="visibleGroupBy"/>
  <form:hidden id="agruparPerCamp" path="groupBy"/> 
  <form:hidden id="agruparPerValor"  path="groupValue"/>

<%-- AGRUPAR PER - FUNCIONS --%>   
<script type="text/javascript">
  ${symbol_dollar}("body").on("nodeselect.tree.data-api", "[role=leaf]", function (e) {

      var parentstr = "" + e.node.parentage[e.node.parentage.length - 1];
      document.getElementById('agruparPerCamp').value = parentstr;
      document.getElementById('agruparPerValor').value = e.node.value;
      document.fitxer.submit();
  })

  function groupByFieldValue(camp, valor) {
    document.getElementById('agruparPerCamp').value = camp;
    document.getElementById('agruparPerValor').value = valor;
    document.fitxer.submit();
  }

</script>

  <%-- AGRUPAR PER - INICI --%>   

  <c:set var="displayGroupDiv" value="${symbol_dollar}{__theFilterForm.visibleGroupBy?'':'display:none;'}" />  

  <c:if test="${symbol_dollar}{fn:length(groupby_items) > 0}">
 <fmt:message var="buit" key="genapp.notdefined" />
  
  <div id="GroupDiv" class="well" style="${symbol_dollar}{displayGroupDiv} padding: 1px; margin-right: 4px;  float: left; ">
      
      <div class="pull-right" style="padding-left:2px">
            <div class="span10">
               <i title="<fmt:message key="genapp.form.hidegroupby"/>" onclick="document.getElementById('GroupDiv').style.display='none'; document.getElementById('GroupButton').style.display='inline';" class="icon-remove"></i>
            </div>
      </div>


      <ul class="tree" style="margin:3px; padding:0px; float: left; ">

        <li>
          <a href="${symbol_pound}" role="branch" class="tree-toggle" data-toggle="branch" data-value=" "><b><fmt:message key="genapp.form.groupby"/></b></a>
          <ul class="branch in">
              <c:if test="${symbol_dollar}{IE8}">
                <c:set var="linkItem" value="onclick=${symbol_escape}"groupByFieldValue(' ',' ')${symbol_escape}"" />
              </c:if>
              <li><a href="${symbol_pound}" role="leaf" data-value="" ${symbol_dollar}{linkItem} >&raquo; <span style="${symbol_dollar}{(__theFilterForm.groupBy eq null)? "font-weight: bold;" : ""}"><fmt:message key="genapp.form.groupby.noneitem"/></span></a></li>


              <c:forEach  var="groupby_item"  items="${symbol_dollar}{groupby_items}"> 
                <li>
                  <a href="${symbol_pound}" role="branch" class="tree-toggle ${symbol_dollar}{groupby_item.selected? "" : "closed"}" data-toggle="branch" data-value="${symbol_dollar}{groupby_item.value}">
                    <span style="${symbol_dollar}{groupby_item.selected? "font-weight: bold;" : ""}">
                    <c:set var="code" value="${symbol_dollar}{(empty __theFilterForm.labels[groupby_item.field])? groupby_item.codeLabel:__theFilterForm.labels[groupby_item.field]}" />
                        <c:if test="${symbol_dollar}{!fn:startsWith(code,'=')}" >
                        <fmt:message key="${symbol_dollar}{code}">
                              <fmt:param><fmt:message key="${symbol_dollar}{groupby_item.codeParamLabel}"/></fmt:param>
                        </fmt:message>
                        </c:if>
                        <c:if test="${symbol_dollar}{fn:startsWith(code,'=')}" >
                        <c:out value="${symbol_dollar}{fn:substringAfter(code, '=')}" escapeXml="false" />
                        </c:if>
                    </span>
                  </a>
                  <%-- AQUI VANS ELS VALUES --%>
                  <ul class="${symbol_dollar}{(groupby_item.selected || IE8)? "branch in" : "branch"}">
                  <c:forEach  var="groupbyvalue_item"  items="${symbol_dollar}{groupby_item.values}">
                    <li>
                      <c:if test="${symbol_dollar}{IE8}">
                        <c:set var="linkItem" value="onclick=${symbol_escape}"groupByFieldValue('${symbol_dollar}{groupby_item.value}','${symbol_dollar}{groupbyvalue_item.value}')${symbol_escape}"" />
                      </c:if>
                      <a href="${symbol_pound}" role="leaf" data-value="${symbol_dollar}{groupbyvalue_item.value}" ${symbol_dollar}{linkItem} >
                        &raquo; <span style="${symbol_dollar}{groupbyvalue_item.selected? "font-weight: bold;" : ""}" >
                          ${symbol_dollar}{ (empty groupbyvalue_item.codeLabel) ? buit : groupbyvalue_item.codeLabel } (${symbol_dollar}{groupbyvalue_item.count})
                      </span>
                      </a>
                    </li>
                  </c:forEach>
                  </ul>
                </li>
              </c:forEach>

          </ul>
        </li>
      </ul>

  </div>
 </c:if>
 
  <%-- AGRUPAR PER - FINAL --%>

