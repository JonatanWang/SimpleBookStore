<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<!-- Import the core JSTL library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- start the middle column -->

<section>

  <h1>Join our email list</h1>

  <p>Before you can download a sample chapter, 
  you must subscribe with us by entering your name and email 
  address below.</p>
  <p>And we will send you announcements about new releases and special offers.</p>

  <!-- Use the JSTL url tag to encode the URL -->
  <form action="<c:url value='/catalog/product/${product.code}/listen/register'/>" method="post">
    <label>Email</label>
    <input type="email" name="email" required><br>
    <label>First Name</label>
    <input type="text" name="firstName" required><br>
    <label>Last Name</label>
    <input type="text" name="lastName" required><br>        
    <label>&nbsp;</label>
    <input type="submit" value="Join Now" id="submit">
    <input type="reset" value="Reset" id="button">
  </form>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_buttons.jsp" />
<jsp:include page="/includes/footer.jsp" />