<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<section class="cart">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Customer Registration</h1>

<form action="<c:url value='/order/processUser' />" method=post>
    <p id="required">Required <span class="required">*</span></p>
    
    <label>First Name</label>
    <input type="text" name="firstName"  maxlength=20 
               value="${customer.firstName}" required>
    <p class="required">*</p><br>
    
    <label>Last Name</label>
    <input type="text" name="lastName" value="${customer.lastName}" required>
    <p class="required">*</p><br>
    
    <label>Email Address</label>
    <input type="email" name="email" value="${customer.email}" required>
    <p class="required">*</p><br>
    
    <label>Password</label>
    <input type="password" name="password" value="${customer.password}" required>
    <p class="required">*</p><br>
    
    <label>Company</label>
    <input type="text" name="companyName" value="${customer.companyName}">
    <p class="required">&nbsp;</p><br>
    
    <label>Address1</label>
    <input type="text" name="address1" value="${customer.address1}" required> 
    <p class="required">*</p><br>
    
    <label>Address2</label>
    <input type="text" name="address2" value="${customer.address2}">
    <p class="required">&nbsp;</p><br>
    
    <label>City</label>
    <input type="text" name="city" value="${customer.city}" required>
    <p class="required">*</p><br>
    
    <label>State</label>
    <input type="text" name="state" value="${customer.state}" required>
    <p class="required">*</p><br>
    
    <label>Zip Code</label>
    <input type="text" name="zip" value="${customer.zip}" required>
    <p class="required">*</p><br>
    
    <label>Country</label>
    <input type="text" name="country" value="${customer.country}" required> 
    <p class="required">*</p><br>
    
    <label>&nbsp;</label>
    <input type="submit" value="Continue">
</form>
    
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />