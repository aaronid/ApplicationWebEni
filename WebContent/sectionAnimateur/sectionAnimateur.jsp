<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.model.Formation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../includes/sectionAnimateur/importsStyles.jsp" %>	
</head>
<body>
	<%@include file="../includes/menu.jsp" %>


    <!-- CONTENU VARIABLE -->

	<div id="wrapper">
	
		<!-- BARRE DE NAVIGATION DE LA SECTION ANIMATEUR -->
		<%@include file="../includes/sectionAnimateur/navigation.jsp" %>
	    
	    <!-- ECRAN D'ACCUEIL DE LA SECTION -->
	    <div id="page-wrapper">
	    	<div class="row">
	    		<div class="col-lg-12">
	                <h1 class="page-header">Tableau de bord</h1>
	            </div>
	            <!-- /.col-lg-12 -->
	        </div>
	        	        
		    <div class="container">      
				<div class="row">				
		                <div class="col-lg-3 col-md-6">
		                    <div class="panel panel-primary">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-list fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                    <div class="headers_gestion">Formations</div>
		                                </div>
		                            </div>
		                        </div>
		                        <a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=listeFormationsAnim">
		                            <div class="panel-footer">
		                                <span class="pull-left">Gestion --></span>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
		                        </a>
		                    </div>
		                </div>			

			
		                <div class="col-lg-3 col-md-6">
		                    <div class="panel panel-primary">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-group fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                    <div class="headers_gestion">Stagiaires</div>
		                                </div>
		                            </div>
		                        </div>
		                        <a href="<%=request.getContextPath()%>/ManagerStagiaireServlet?action=listeStagiaires">
		                            <div class="panel-footer">
		                                <span class="pull-left">Gestion --></span>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
		                        </a>
		                    </div>
		                </div>			

			
		                <div class="col-lg-3 col-md-6">
		                    <div class="panel panel-primary">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-unlock-alt fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                    <div class="headers_gestion">Animateurs</div>
		                                </div>
		                            </div>
		                        </div>
		                        <a href="<%=request.getContextPath()%>/ManagerAnimateurServlet?action=listeAnimateurs">
		                            <div class="panel-footer">
		                                <span class="pull-left">Gestion --></span>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
		                        </a>
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