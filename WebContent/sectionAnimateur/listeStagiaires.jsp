<%@page import="fr.eni.model.Stagiaire"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<c:import url="../includes/head.jsp"></c:import>
	<c:import url="../includes/sectionAnimateur/importsStyles.jsp"></c:import>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
    
    <hr>
    
    <!-- CONTENU VARIABLE -->

	<div id="wrapper">
	
		<!-- BARRE DE NAVIGATION DE LA SECTION ANIMATEUR -->	
		<c:import url="../includes/sectionAnimateur/navigation.jsp"></c:import>
		
		<!-- LISTE DES STAGIAIRES -->
		<div class="page-wrapper">
	    	<div class="row">
                <div class="col-lg-8 col-lg-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2>Liste des Stagiaires</h2>
                        </div>
                        <!-- /.panel-heading -->
                        <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/ManagerStagiaireServlet?element=affichageFormulaire&action=ajouter">
                        	<button type="button" class="btn btn-outline btn-primary btn-lg btn-block">Inscrire un stagiaire</button>
                        </a>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>Prénom</th>
                                            <th>Email</th>
                                            <th>Formation suivie</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <c:forEach var="stagiaire" items="${requestScope['liste']}">
                                    
										<tr>
										<td><c:out value="${stagiaire.identifiant}"/></td>
										<td><c:out value="${stagiaire.nom}"/></td>
										<td><c:out value="${stagiaire.prenom}"/></td>
										<td><c:out value="${stagiaire.email}"/></td>
										<td><c:out value="${stagiaire.formation.libelle}"/></td>
										<td>
											<a href="<c:out value="${pageContext.servletContext.contextPath}"/>/ManagerStagiaireServlet?element=affichageFormulaire&action=modifier&id=<c:out value="${stagiaire.identifiant}"/>">
												<button type="button" class="btn" title="Modifier le stagiaire"><i class="fa fa-edit"></i></button>
											</a>
										</td>
										<td>
											<a href="<c:out value="${pageContext.servletContext.contextPath}"/>/ManagerStagiaireServlet?action=supprimer&id=<c:out value="${stagiaire.identifiant}"/>">
												<button type="button" class="btn" title="Modifier le stagiaire"><i class="fa fa-trash"></i></button>
											</a>
										</td>
										</tr>
                                    
                                    </c:forEach>
                                    	
		                            </tbody>
                                </table>
                            </div>
                        </div>
                        <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/ManagerStagiaireServlet?element=affichageFormulaire&action=ajouter">
                        	<button type="button" class="btn btn-outline btn-primary btn-lg btn-block">Inscrire un Stagiaire</button>
                        </a>
                        						
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	
	<c:import url="../includes/sectionAnimateur/importsJs.jsp"></c:import>
	<c:import url="../includes/footer.jsp"></c:import>

</body>
</html>