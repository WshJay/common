// JavaScript Document

 
$(document).ready(function() {
    $(window).on("load resize",function(){
		w=$("body").width();
		 if(w<=480){
			 $("#preload").attr('href', "css/mobile_area.css");       
		}
		else{
			 $("#preload").attr('href', "css/pc_area.css");            
		}
	 })
});
 
 
