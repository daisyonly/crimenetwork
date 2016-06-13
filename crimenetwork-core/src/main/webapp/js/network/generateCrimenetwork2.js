$(document).ready(function(){
	
	//var suspectId = $("input[name='suspect']").val();
	//var caseId = $("input[name='cases']").val();
	//var currencyId = $("input[name='currency']").val();
	$("#generate").click(function() {
		var suspectId = $("input[name='suspect']").val();
		var caseId = $("input[name='cases']").val();
		var currencyId = $("input[name='currency']").val();
		var count=0;
		if(suspectId!="") count++;
		if(caseId!="") count++;
		if(currencyId!="") count++;
		if(count>1) alert("只能输入一个查询项");
		if(count==0) alert("请输入一个查询项");
		if(count==1){
			var id=null;
			var pathLength = $("input[name='path-length']").val();
			$.ajax({
				type : "GET",
				url : "searchwithoutrank",
				data:{ suspectId:suspectId,
					   caseId:caseId,
					   currencyId:currencyId,
					   pathLength:pathLength,
					  },
				success : function(data) {
					console.log(data);
					drawNetwork(data);
				}
			});
			
		}	
		
	});
	
});
function drawNetwork(rawData){
	var nodes = null;
	var edges = null;
	var network = null;
	var DIR = './lib/vis/img/network/';
	var LENGTH_MAIN = 350, LENGTH_SERVER = 150, LENGTH_SUB = 100, WIDTH_SCALE = 2, 
	GREEN = 'green', RED = '#C5000B', ORANGE = 'orange',
	//GRAY = '#666666',
	GRAY = 'gray', BLACK = '#2B1B17';
	
	// Create a data table with nodes.
	nodes = [];

	// Create a data table with links.
	edges = [];
	var idMap=new Object();
	var rawNodes=rawData.nodes;
	var rawNodesKeys=Object.keys(rawNodes)
	for(var i = 0, l = rawNodesKeys.length; i < l; i++) {
	    //console.log(list[i]);
		console.log(rawNodes[rawNodesKeys[i]]);
		idMap[rawNodesKeys[i]]=i+1;
		var curObject=rawNodes[rawNodesKeys[i]];
		var name=curObject["姓名"];
		//if(name=="刘德琴") name="李四";
		//if(name=="赵明全") name="王五";
		var curSize=curObject["size"];
		var curSize=25;
		var nodeType=rawNodesKeys[i].charAt(0);
		var groupType=null;
		if(nodeType=="S"){
			groupType="suspect";
		}else if(nodeType=="C"){
			groupType="cases";
		}else{
			groupType="jiabi";
		}
		
		var titleString="";		
		var attributeKeys=Object.keys(curObject);
		for(var j = 0, len = attributeKeys.length; j < len; j++){
			if(attributeKeys[j]=="size") continue;
			if(curObject[attributeKeys[j]]==null)
				curObject[attributeKeys[j]]="暂无";
			//curObject[attributeKeys[j]]="**************";
			if(j==0){
				titleString=titleString+attributeKeys[j]+":"+curObject[attributeKeys[j]];
			}else{
				titleString=titleString+"<br/>"+attributeKeys[j]+":"+curObject[attributeKeys[j]];
			}
		}	
		nodes.push({
			id : i+1,
			label : name,
			group : groupType,
			title : titleString,
			size : curSize
		});	
	}
	
	var rawEdges=rawData.edges;
	for(var i = 0, l = rawEdges.length; i < l; i++){
		var fromId=idMap[rawEdges[i]["fromId"]];
		var endId=idMap[rawEdges[i]["endId"]];
		var curColor=RED;
		var curWidth=WIDTH_SCALE;
		var curDashes=[1,1];
		var fromType = rawEdges[i]["fromId"].charAt(0);
		var endType = rawEdges[i]["endId"].charAt(0);
		if(fromType=="C"&& endType=="J"||fromType=="J"&& endType=="C"){
			curColor=GREEN;
			curDashes=[ 2, 5, 2, 10 ];
		}else if(fromType=="S"&& endType=="S"){
			curColor=GRAY;
			curWidth=WIDTH_SCALE*2;
		}else if(fromType=="J"&& endType=="J"){
			curColor=BLACK;
			curWidth=WIDTH_SCALE*2.5;;
		}
		edges.push({
			from : fromId,
			to : endId,
			arrows:'to',
			arrowStrikethrough:true,
			length : LENGTH_SERVER,
			width : curWidth,
			dashes : curDashes,
			color : curColor
		});
	}

	
	// create a network
	var container = document.getElementById('mynetwork');
	var data = {
		nodes : nodes,
		edges : edges
	};
	var options = {
		nodes : {
			scaling : {
				min : 32,
				max : 32
			}
		},
		edges : {
			smooth : false
		},
		physics : {
			barnesHut : {
				gravitationalConstant : -30000
			},
			stabilization : {
				iterations : 2500
			}
		},
		groups : {
			suspect : {
				shape: 'image', 
				image: DIR + 'Person-icon.png',
				font : {
					size : 25,
				},
				size : 25,				
			},
			cases : {
				shape: 'image', 
				image: DIR + 'case.png',
				font : {
					size : 25,
				},
				size : 25,			
			},
			jiabi : {
				shape: 'image', 
				image: DIR + 'Money-icon.png',
				font : {
					size : 25,
				},
				size : 25,		
			}
		}
	};
	network = new vis.Network(container, data, options);
	
}
