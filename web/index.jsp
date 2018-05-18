<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->

<section>
    <h1 align="center">Welcome to CDOFF!</h1>
    <img src="<c:url value='/images/bookstore_front.gif' />" width="550" 
         alt="Book Store Fasade Picture">
    <p>Thanks for visiting us, the smallest web shop in Nordic!</p>

    <p>If you have any questions, please do not hesitate to contact us!</p>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />