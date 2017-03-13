<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.model.Formation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <h1 class="tagline">Formations</h1>
                </div>
            </div>
        </div>
    </div>
    
    <hr>

    <!-- CONTENU VARIABLE -->
    <div class="container">
		<div class="row">
	<%  
	
		List<Formation> formations = (ArrayList<Formation>)request.getAttribute("liste");
	
		// affichage de la liste 
		for (Formation formation : formations) {						
		
	%>
			<div class="col-lg-4">
            	<div class="panel panel-primary">
                	<div class="panel-heading">
                        <%= formation.getLibelle() %>
                    </div>
                    <div class="panel-body">
                        <p><%= formation.getDescription() %>
                        <%= formation.getCompetences() %></p>
                    </div>
                    <div class="panel-footer">
                            ------------------
                    </div>
                </div>
          	 </div>
				
						
	<%
		}
	%>
		</div>
    
	</div>
	
   <hr>

	<%@include file="includes/footer.jsp" %>
	
</body>
</html>