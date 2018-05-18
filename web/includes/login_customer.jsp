<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>

<h1>Customer Login Form</h1>
<p>Please enter your email and password to continue.</p>
<form action="${pageContext.request.contextPath}/message" method="post" onSubmit="return login(this);">
    <label>Email</label>
    <input type="text" name="email"><br>
    <label>Password</label>
    <input type="password" name="password"><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login">
    <input type="reset" value="Reset">
</form>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />