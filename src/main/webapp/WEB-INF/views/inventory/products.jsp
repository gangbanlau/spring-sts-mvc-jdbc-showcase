<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
  <head><title><fmt:message key="products.title"/></title></head>
  <body>
    <h1><fmt:message key="products.heading"/></h1>
    <p><fmt:message key="products.greeting"/> <c:out value="${now}"/></p>
    <h3>Products</h3>
    <c:forEach items="${products}" var="prod">
      <c:out value="${prod.name}"/> <i>$<c:out value="${prod.price}"/></i><br><br>
    </c:forEach>
    <br>
    <a href="<c:url value="/inventory/priceincrease"/>">Increase Prices</a>
    <br>
    <br>
    <a href="<c:url value="/logout"/>">Logout</a>
    <br>    
  </body>
</html>