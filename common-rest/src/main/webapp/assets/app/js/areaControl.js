// JavaScript Document
function creat_p(i){ //创建prov option
	var p_select= document.getElementById("prov");
	var p_option=document.createElement("option");
	p_select.appendChild(p_option);
	p_option.text = p[i];
	
}

function creat_c(i,n){ //创建city option
	 var c_select= document.getElementById("city");
	 var c_option=document.createElement("option");
	 c_select.appendChild(c_option);
	 c_option.text = c[i][n];
}

function creat_t(i,n,m){ //创建town option
		var t_select= document.getElementById("town");
		var t_option=document.createElement("option");
		t_select.appendChild(t_option);
		t_option.text = t[i][n][m];	
}

function p_change(){  //#prov改变
	p_text=$("#prov option:selected").text();
	$("#city").empty();	
	$("#town").empty();	
	creat_t(00,00,00);
	for(i in p){
		if(p[i]==p_text){
			var c_select= document.getElementById("city");
			for(n in c[i]){
				var c_option=document.createElement("option");
			   c_select.appendChild(c_option);
			   c_option.text = c[i][n];
			  
			}			
		}
	}
	
}

function c_change(){	//#city改变
	$("#town").empty();
	p_text=$("#prov option:selected").text();
	c_text=$("#city option:selected").text();
	for(i in p){
		if(p[i]==p_text){
			
			for(n in c[i]){  //c[i][n]
				if(c[i][n]==c_text){	
	
					var t_select= document.getElementById("town");
					for(m in t[i][n]){
					var t_option=document.createElement("option");
					t_select.appendChild(t_option);
					t_option.text = t[i][n][m];
					
				    }	
			     }
		    }			
		}
	}	
}





var pname="";
var cname="";
var tname="";
var txt="";

$(document).ready(function() {
    $("#prov").on("change",function(){
		pname=$("#prov option:selected").text();
		if(pname=="省份"){
			txt="";
			$("#area_name").html(txt)
		}
		else{
			txt=pname;
			$("#area_name").html(txt)
		}
	})
}); //获取省地区名


$(document).ready(function() {
    $("#city").on("change",function(){
		pname=$("#prov option:selected").text();
		cname=$("#city option:selected").text();
		if(cname=="地级市"||cname=="市辖区"||cname=="县"||cname=="市"){
			txt=pname;
			$("#area_name").html(txt)
		}
		else{
			txt=pname+"&nbsp;&nbsp;"+cname;
			$("#area_name").html(txt)
		}
	})
}); //获取市地区名


$(document).ready(function() {
    $("#town").on("change",function(){
		pname=$("#prov option:selected").text();
		cname=$("#city option:selected").text();
		tname=$("#town option:selected").text();
		
		if(cname=="市辖区"||cname=="县"||cname=="市"){
			if(tname!="市、县级市"){
				$("#area_name").html(pname+"&nbsp;&nbsp;"+tname);
			}
			else{
				$("#area_name").html(pname);
			}
		}
		else{
			if(tname!="市、县级市"){
				txt=pname+"&nbsp;&nbsp;"+cname+"&nbsp;&nbsp;"+tname;
				$("#area_name").html(txt);
			}
			else{
				$("#area_name").html(pname+cname);
			}
		}
	}); //获取县地区名
})



var i=new Number;
var n=new Number;
var m=new Number;


function areaID(){  //获取areaID
	var p_text=$("#prov option:selected").text();
	var c_text=$("#city option:selected").text();
	var t_text=$("#town option:selected").text();
	for(i in p){ 
	   if(p[i]==p_text){
		   for(n in c[i]){
			  if(c[i][n]==c_text){
				  for(m in t[i][n]){
					if(t[i][n][m]==t_text){
						while(i.length==1){
							i="0"+i
						}
						while(n.length==1){
							n="0"+n
						}
						while(m.length==1){
							m="0"+m;
						}
						if(i=="00"){
							$("#area_id").html("");
						}
						else{
							if(c_text=="地级市"){
								$("#area_id").html(i+"0000")
							}
							else{
								if(c_text=="市辖区"||c_text=="县"||c_text=="市"){
									if(t_text=="市、县级市"){
										$("#area_id").html(i+"0000");
									}
									else{
										$("#area_id").html(i+n+m);
									}
								}
								else{
									if(t_text=="市、县级市"){
										$("#area_id").html(i+n+"00");
									}
									else{
										$("#area_id").html(i+n+m);
									}
								}
							}
						}
					}	  
				 }
			  }
		   }
	   }
	}
	
}  //获取areaID


function chushi(area_code){
if(area_code==""||area_code=="000000"){
				var p_select= document.getElementById("prov");
				for(i in p){ 
				   var p_option=document.createElement("option");
				   p_select.appendChild(p_option);
				   p_option.text = p[i];   
				} //初始化prov
				creat_c(00,00);  //初始化city
				creat_t(00,00,00); //初始化town	
			}
			else{
				
				p_code=parseInt(area_code.substring(0,2));
				c_code=parseInt(area_code.substring(2,4));
				t_code=parseInt(area_code.substring(4,6));
				var p_select= document.getElementById("prov");
				var c_select= document.getElementById("city");
				var t_select= document.getElementById("town");
				
				for(i in p){ 
				   var p_option=document.createElement("option");
				   p_select.appendChild(p_option);
				   p_option.text = p[i];   
				}	//创建prov
				for(n in c[p_code]){ 
				   var c_option=document.createElement("option");
				   c_select.appendChild(c_option);
				   c_option.text = c[p_code][n];   
				}	//创建city
				for(m in t[p_code][c_code]){ 
				   var t_option=document.createElement("option");
				   t_select.appendChild(t_option);
				   t_option.text = t[p_code][c_code][m];   
				}	//创建town			

				
 
				for(var i=0; i<p_select.options.length; i++){  
					if(p_select.options[i].innerHTML == p[p_code]){  
						p_select.options[i].selected = true;  
						break;  
					}  
				} //prov选项
 
				for(var n=0; n<p_select.options.length; n++){  
					if(c_select.options[n].innerHTML == c[p_code][c_code]){  
						c_select.options[n].selected = true;  
						break;  
					}  
				} //city选项

				for(var m=0; m<t_select.options.length; m++){  
					if(t_select.options[m].innerHTML == t[p_code][c_code][t_code]){  
						t_select.options[m].selected = true;  
						break;  
					}  
				} //town选项
				
				
				if(p_code!="00"){
					if(c[p_code][c_code]!="地级市"){  //xxxxxx
						if(c[p_code][c_code]!="市辖区"&&c[p_code][c_code]!="县"&&c[p_code][c_code]!="市"){
							if(t_code!=0){
								$("#area_name").html(p[p_code]+"&nbsp;&nbsp;"+c[p_code][c_code]+"&nbsp;&nbsp;"+t[p_code][c_code][t_code]);
								$("#area_id").html(p_code+c_code+t_code);
							}
							else{  //xxxx00
								$("#area_name").html(p[p_code]);
								$("#area_id").html(area_code);
							}
						}
						else{
							if(t_code!=0){
								$("#area_name").html(p[p_code]+"&nbsp;&nbsp;"+t[p_code][c_code][t_code]);
								$("#area_id").html(area_code);
							}
							else{
								$("#area_name").html(p[p_code]);
								$("#area_id").html(p_code+"0000");
							}
						}
					}
					else{
						$("#area_name").html(p[p_code])
					}
				}
			
				
			}	//else
}

