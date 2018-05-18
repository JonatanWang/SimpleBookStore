<%@page import="book.business.Customer"%>
<jsp:include page="/includes/header_logout.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>

<h1>Log in</h1>
<div class="div3">
   
   <% 
       // get the info 
       String info = (String) request.getAttribute("info");
       // if info is not null, display the info
       if (info != null) {
           out.println(info);
       }
       // get the customer
       Customer customer = (Customer) session.getAttribute("customer");
       // check if customer is logged in
       if (customer != null) {
    %>
       <table align="center" width="350" border="1" height="200" bordercolor="#E8F4CC">
            <tr>
                <td align="center" colspan="2">
                    <span style="font-weight: bold;font-size: 18px;">
                        <%=customer.getFirstName() %></span>
			successfully logged in!
		</td>
            </tr>
            
            <tr>
		<td align="right">Last Name: </td>
		<td>
                    <%=customer.getLastName() %>
		</td>
            </tr>
            
            <tr>
		<td align="right">Email: </td>
		<td>
                    <%=customer.getEmail() %>
		</td>
            </tr>
           
            <tr>
		<td align="right">Address: </td>
		<td>
                    <%=customer.getAddress1() %>
		</td>
            </tr>
            
            <tr>
		<td align="right">City: </td>
		<td>
                    <%=customer.getCity() %>
		</td>
            </tr>
            
            <tr>
		<td align="right">Zip: </td>
		<td>
                    <%=customer.getZip() %>
		</td>
            </tr>
            
            <tr>
		<td align="right">Country: </td>
		<td>
                    <%=customer.getCountry() %>
		</td>
            </tr>
            
	</table>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout" id="button"/>
        </form>
        <%								
	}else{
            out.println("<br>Sorry, failed to log in! Please check the email and password.");
	}
	%>
</div>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />