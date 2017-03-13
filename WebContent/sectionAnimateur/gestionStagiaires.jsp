<%@page import="fr.eni.model.Stagiaire"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    
					<!-- EN-TETE FORMULAIRE -->                  
                    <div class="panel-heading">
                       	<h2>
	                    	<c:set var="stagiaire" value="${requestScope['stagiaireAModifier']}" scope="page" />
	                    	
	                    	<c:if test="${empty stagiaire.identifiant}" >Inscrire un stagiaire
	                    		<c:set var="action" value="Inscrire" scope="page"/>
	                    	</c:if>
	                    	<c:if test="${not empty stagiaire.identifiant}" >Modifier la fiche de 
	                    		<c:out value="${stagiaire.nom} ${stagiaire.prenom}"/>
	                    		<c:set var="action" value="Modifier" scope="page"/>
	                    	</c:if>						             	 
                    	</h2>
                    </div>
                    
                    <!-- CORPS FORMULAIRE -->           
                    <form action="<%=request.getContextPath()%>/ManagerStagiaireServlet" method="post">
                        
                        <c:if test="${action == 'Modifier'}">
                        	<input type="hidden" name="action" value="modifier"/>
                        	<input type="hidden" name="id" value="<c:out value="${stagiaire.identifiant}"/>"/>
                        </c:if>
                        <c:if test="${action == 'Inscrire'}">
                        	<input type="hidden" name="action" value="ajouter"/>
                        </c:if>                   	
                        
                        
                                           	
					  	<div class="form-group">					  	
					    	<label for="nom">Nom</label>               	
                    		<input type="text" 
                    		       	class="form-control" 
                    		      	name="nom"
                    		       	required 
                    		       	maxlength="50"
                    		        value="<c:out value="${stagiaire.nom}"/>"
                    		/> <!-- fin input -->
                    	</div>
					  	
					  	<div class="form-group">					  	
					    	<label for="prenom">Prénom</label>               	
                    		<input type="text" 
                    		       	class="form-control" 
                    		      	name="prenom"
                    		       	required 
                    		       	maxlength="50"
                    		       	value="<c:out value="${stagiaire.prenom}"/>"
                    		/><!-- fin input -->
					  	</div>
					  	
					  	<div class="form-group">					  	
					    	<label for="email">Email</label>
                    		<input type="email" 
                    		       	class="form-control" 
                    		      	name="email"
                    		       	required 
                    		       	maxlength="50"
                    		       	value="<c:out value="${stagiaire.email}"/>"
                    		/><!-- fin input -->
					  	</div>
					  	
					  	<!-- COMBO BOX RECUPERANT LE LIBELLE DE CHAQUE FORMATION EN BDD -->					  	
					    <div class="form-group">
					        <label class="col-xs-3 control-label">Formation suivie :</label>
					        <div class="col-xs-9 selectContainer">
					            <select class="form-control" name="formations" required>
									<c:forEach var="formation" items="${requestScope['formations']}">									
										<option 
											selected="<c:if test="${formation.libelle} == ${stagiaire.formation.libelle}">
														selected
													  </c:if>">										
											<c:out value="${formation.libelle}" />
										</option>									
									</c:forEach>
					            </select>
					        </div>
					    </div>
				    				    	
				    	<div class="form-group">					    					              
							<button type="submit" class="btn btn-default btn-lg btn-block">
								<c:out value="${action}" />
							</button>
						</div>
					
					</form>                   
                </div>	
			</div>				
    </div>	
	<hr>
	
	<%@include file="../includes/sectionAnimateur/importsJs.jsp" %>	
	<%@include file="../includes/footer.jsp" %>
	
</body>
</html>