#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
﻿<%@   taglib prefix="spring" uri="http://www.springframework.org/tags" 
%><%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" 
%><%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" 
%><%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" 
%><%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" 
%><%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" 
%><%@ taglib prefix="un"     uri="http://jakarta.apache.org/taglibs/unstandard-1.0" 
%><%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"
%><%@ taglib prefix="${prefix}"    uri="/WEB-INF/jstl/${parentArtifactId}.tld"
%><%@ taglib prefix="gen"    uri="/WEB-INF/jstl/genapp.tld"
%><%@ page errorPage="/error.jsp" trimDirectiveWhitespaces="true"%>