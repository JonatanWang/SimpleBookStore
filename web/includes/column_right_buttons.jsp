    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside id="sidebarB">
        <form method="post" action="<c:url value='/order/addItem'/>">
            <input type="hidden" name="productCode" value="${product.code}">
            <input type="image" src="<c:url value='/images/add_to_cart.gif'/>" 
                   width="170" height="55" alt="Add to Cart">
        </form>
        <a href="<c:url value='/catalog/product/${product.code}/read' />">
            <img src="<c:url value='/images/sample_chapter_button_210x110.jpg'/>" 
                 width="150" alt="Read Sample Chapter" class="top_margin">
        </a>
    </aside>