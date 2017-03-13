<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

    <!-- BARRE DE NAVIGATION -->
    <div class="container">
    	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">ENI ecole informatique</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/AccueilServlet">Eni</a>
            </div>
            <!-- MENU -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=listeFormations">Formations</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/AuthentificationCookieServlet">Animateur</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/AuthentificationCookieServlet">Stagiaire</a>
                    </li>
                </ul>
                
            	<!-- FORMULAIRE DE RECHERCHE VIA GOOGLE -->          	
            	<form class="navbar-form navbar-right" action="http://www.google.fr/search" target="_blank">
            		<input type="text" name ="q" class="form-control" placeholder="Google est mon ami" />
            		<input type="submit" class="btn btn-default" value="Rechercher" />
        		</form>
            </div>
	    </nav>
    </div> <!-- Fin div container NAVIGATION -->
