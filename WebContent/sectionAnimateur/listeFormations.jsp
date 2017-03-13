<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.model.Formation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../includes/head.jsp"%>
	<%@include file="../includes/sectionAnimateur/importsStyles.jsp" %>	
</head>
<body>
	<%@include file="../includes/menu.jsp" %>
    
    <!-- CONTENU VARIABLE -->
	<div id="wrapper">
	
		<!-- BARRE DE NAVIGATION DE LA SECTION ANIMATEUR -->
		<%@include file="../includes/sectionAnimateur/navigation.jsp" %>	
		
		<div id="page-wrapper">
	    	<div class="row">
	    		<div class="col-lg-12">
	                <h1 class="page-header">Liste des formations</h1>
	            </div>
	            <!-- /.col-lg-12 -->
	        </div>
		
		<!-- LISTE DES FORMATIONS -->
			<div class="container">
		    	<div class="row">
	                <div class="col-lg-11 col-lg-offset-1">
	                    <div class="panel panel-default">
	
	                        <!-- /.panel-heading -->
	                        <a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=ajouter"><button type="button" class="btn btn-outline btn-primary btn-lg btn-block">Ajouter une formation</button></a>
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                                <table class="table table-hover">
	                                    <thead>
	                                        <tr>
	                                            <th>#</th>
	                                            <th>Libelle</th>
	                                            <th>Description</th>
	                                            <th>Compétences</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	
							<%  
							
								List<Formation> formations = (ArrayList<Formation>)request.getAttribute("liste");
							
								// affichage de la liste 
								for (Formation formation : formations) {						
								
							%>
									<tr>		
										<td><%= formation.getIdentifiant() %></td>				
										<td><%= formation.getLibelle() %></td>
										<td><%= formation.getDescription() %></td>
										<td><%= formation.getCompetences() %></td>
										<td><a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=modifier&id=<%= formation.getIdentifiant() %>"><button type="button" class="btn" title="Modifier la formation"><i class="fa fa-edit"></i></button></a></td>
										<td><a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=supprimer&id=<%= formation.getIdentifiant() %>"><button type="button" class="btn" title="Supprimer la formation"><i class="fa fa-trash-o"></i></button></a></td>
									</tr>
												
							<%
								}
							%>
	
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                        <a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=ajouter"><button type="button" class="btn btn-outline btn-primary btn-lg btn-block">Ajouter une formation</button></a>
				
	                    </div>
	                </div>	
				</div>			
			</div>
		</div>
	</div>
	
	<hr>
	
	<%@include file="../includes/sectionAnimateur/importsJs.jsp" %>	
	<%@include file="../includes/footer.jsp" %>
	
</body>
</html>