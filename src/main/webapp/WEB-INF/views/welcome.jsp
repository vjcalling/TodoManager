<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	Welcome ${name}. You are now authenticated.
</div>

<%@ include file="common/footer.jspf"%>


<script>
$(document).ready(function () {
  $(".nav li").removeClass("active"); 
  $('#home').addClass('active');
});
</script>