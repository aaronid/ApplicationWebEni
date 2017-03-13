<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="includes/head.jsp" %>
</head>
<body>
	<%@include file="includes/menu.jsp" %>

	<!-- IMAGE EN TETE -->
    <div class="business-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="tagline">Accueil</h1>
                </div>
            </div>
        </div>
    </div>

	<hr>

    <!-- CONTENU VARIABLE -->
    <div class="container">
       
		<div class="row">
			<img class="img-circle img-responsive img-center" src="images/logo_eni.jpg" alt="">
		</div>


        <div class="row">
            <div class="col-sm-8">
                <h2>Que pouvez-vous faire ?</h2>
                <p>Accédez à toutes les fonctionnalités qui vous sont autorisées en vous rendant sur 
				votre rubrique d'accès (animateur ou stagiaire)</p>
                <p>
                    <a class="btn btn-default btn-lg" href="<%=request.getContextPath()%>/ManagerFormationServlet?action=listeFormations" title="Liste des formations">Formations &raquo;</a>
                    <a class="btn btn-default btn-lg" href="<%=request.getContextPath()%>/AuthentificationCookieServlet" title="acces animateur">Animateur &raquo;</a>
                    <a class="btn btn-default btn-lg" href="<%=request.getContextPath()%>/AuthentificationCookieServlet" title="acces stagiaire">Stagiaire &raquo;</a>
                </p>
            </div>
            <div class="col-sm-4">
                <h2>Contactez-nous</h2>
                <address>
                    <strong>Eni - Nantes</strong>
                    <br>ZAC du Moulin Neuf - 2 B, rue Benjamin Franklin
                    <br>BP 80009 - 44801 Saint-Herblain
                    <br>
                </address>
                <address>
                    <abbr title="Phone">Tel:</abbr>02 28 03 17 28
                    <br>
                    <abbr title="Email">Email:</abbr> <a href="mailto:#">contact@eni-ecole.fr</a>
                </address>
            </div>            
        </div> 
        </div>   
    <hr>

	<%@include file="includes/footer.jsp" %>
</body>
</html>