<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<%@include file="includes/head.jsp" %>

</head>
<body>

	<%@include file="includes/menu.jsp" %>

	<!-- IMAGE EN TETE -->
    <header class="business-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="tagline">Ecran de Connection</h1>
                </div>
            </div>
        </div>
    </header>
    
    <hr>

    <!-- CONTENU -->
    <div class="container">
        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
            	<h2>Accès animateur</h2>
                <form action="<%=request.getContextPath()%>/AuthentificationCookieServlet" method="post">
  					<div class="form-group">
    					<label for="email">Email</label>
    					<input type="email" class="form-control" name="email" placeholder="Email" required maxlength="100">
  					</div>
  					<div class="form-group">
    					<label for="mdp">Mot de passe</label>
    					<input type="password" class="form-control" name="mdp" placeholder="Mot de passe" required maxlength="50">
  					</div>
					<div class="form-group">
						<input type="checkbox" name="stayConnected"> Rester connecté
					</div>
  					<button type="submit" class="btn btn-default">Se connecter</button>
				</form>
            </div>
        </div>
	</div>
	
	<%
		if(request.getAttribute("erreur") != null)
		{
	%>
		<div class="messageErreur"><%=request.getAttribute("erreur").toString()%></div>
	<%
		}
	%>
	
   	<hr>
	<%@include file="includes/footer.jsp" %>
	
</body>
</html>