<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_catalog.jsp" />

<!-- start the middle column -->

<!-- If necessary, this page could be generated from the database. -->

<section>
    <h1>The Book Catalog</h1>

    <h2>Gunilla Persson</h2>
    <p><a href="product/gptg">The Gunilla</a></p>

    <h2 class="top_margin">Stepenie Meyer</h2>
    <p><a href="product/smtc">The Chemist</a></p>
    
    <h2 class="top_margin">Harlan Corben</h2>
    <p><a href="product/hchm">Home</a></p>

    <h2 class="top_margin">Stephen King</h2>
    <p><a href="product/skit">It</a></p>    
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" flush="true" />
<jsp:include page="/includes/footer.jsp" />