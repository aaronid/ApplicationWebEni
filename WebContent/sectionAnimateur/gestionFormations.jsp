<%@page import="fr.eni.model.Formation"%>
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
    
    <hr>
    
    <!-- CONTENU VARIABLE -->

	<div id="wrapper">
	
		<!-- BARRE DE NAVIGATION DE LA SECTION ANIMATEUR -->
		<%@include file="../includes/sectionAnimateur/navigation.jsp" %>	
	
        <!-- UPDATE OU CREATE DES FORMATIONS -->
	    	<div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                
                <%
                	String action = "";
                	if("ajouter".equals(request.getAttribute("action"))){
                		action = "Ajouter";
                	}else if("modifier".equals(request.getAttribute("action"))){
                		action = "Modifier";
                	}
                	
                	// si l'attribut formation de la requête n'est pas null et
                	// l'action est "modifier", on peut renseigner les champs du formulaire
                	Formation fAModifier = new Formation();
                	if("Modifier".equals(action) && request.getAttribute("formation")!=null){
                		fAModifier = (Formation)request.getAttribute("formation");               		                		            		            
                	}
                	                                          
                %>
                               
                    <div class="panel-heading">
                    	<h2><%=action%> une formation</h2>
                    </div>
                               
                    <form action="<%=request.getContextPath()%>/ManagerFormationServlet" method="post">
                        
                        <%if("modifier".equals(action)){ %>           	
                    	<input type="hidden" name="id" value="<%=fAModifier.getIdentifiant()%>"/>
                    	<%} %>
                    	
					  	<div class="form-group">					  	
					    	<label for="libelle">Libelle</label>               	
                    		<input type="text" 
                    		       class="form-control" 
                    		       name="libelle"
                    		       <%if(fAModifier.getLibelle() != null || "".equals(fAModifier.getLibelle())){ %>
                    		       value="<%=fAModifier.getLibelle()%>"
                    		       <%} %>
                    		       required 
                    		       maxlength="50"/>
					  	</div>
					  	
						<div class="form-group">
							<label for="description">Description</label>
	                    	<textarea
	                    		   class="form-control" 
	                    		   name="description"
	                    		   rows="3">
	                    		   <%if(fAModifier.getDescription() != null || "".equals(fAModifier.getDescription())){ %>
                    		       <%=fAModifier.getDescription()%>"
                    		       <%} %>
	                    	</textarea>
	                    </div>
	                      
						<div class="form-group">
							<label for="competences">Competences</label>
	                    	<textarea
	                    	       class="form-control" 
	                    	       name="competences"
	                    	       rows="3">
	                    	       <%if(fAModifier.getCompetences() != null || "".equals(fAModifier.getCompetences())){ %>
                    		       <%=fAModifier.getCompetences()%>"
                    		       <%} %>
	                    	</textarea>
	                    </div>
	                      
						<button type="submit" class="btn btn-default"><%=action%></button>
					
					</form>                   
                </div>	
			</div>	
    </div>	
	<hr>
	
	<%@include file="../includes/sectionAnimateur/importsJs.jsp" %>	
	<%@include file="../includes/footer.jsp" %>
	
</body>
</html>



