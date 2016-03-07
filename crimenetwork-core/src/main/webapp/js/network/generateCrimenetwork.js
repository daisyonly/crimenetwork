/*
 * {
  "nodes" : {
    "S8831" : {
      "姓名" : "陈亚西",
      "籍贯" : "未知",
      "嫌疑人编号" : "R3209820000002003090003",
      "身份证" : "111111111111111111"
    },
    "S8830" : {
      "姓名" : "侯春楼",
      "籍贯" : "未知",
      "嫌疑人编号" : "R3209820000002003090002",
      "身份证" : "111111111111111111"
    },
    "S8833" : {
      "姓名" : "陈亚西",
      "籍贯" : null,
      "嫌疑人编号" : "R3209820020030910164181",
      "身份证" : "111111111111111111"
    },
    "S8832" : {
      "姓名" : "侯春楼",
      "籍贯" : "江苏涟水县",
      "嫌疑人编号" : "R3209820020030910164180",
      "身份证" : "111111111111111111"
    },
    "C21232" : {
      "案件编号" : "A3209820000002003090003",
      "案发地点" : "无"
    }
  },
  "edges" : [ {
    "fromId" : "S8830",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "S8831",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8831",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8832",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8830",
    "attributes" : { }
  }, {
    "fromId" : "S8832",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8833",
    "attributes" : { }
  }, {
    "fromId" : "S8833",
    "endId" : "C21232",
    "attributes" : { }
  } ]
}
 */
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
			if(suspectId!="") id=suspectId;
			else if(caseId!="") id=caseId;
			else if(currencyId!="") id=currencyId;
			var pathLength = $("input[name='path-length']").val();
			var currencySim = $("input[name='currency-sim']").val();
			$.ajax({
				type : "GET",
				url : "search",
				data:{ id:id,
					   pathLength:pathLength,
					   currencySim:currencySim},
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
	var LENGTH_MAIN = 350, LENGTH_SERVER = 150, LENGTH_SUB = 100, WIDTH_SCALE = 2, GREEN = 'green', RED = '#C5000B', ORANGE = 'orange',
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
		var nodeType=rawNodesKeys[i].charAt(0);
		var groupType=null;
		if(nodeType=="S"){
			groupType="suspect";
		}else if(nodeType=="C"){
			groupType="cases";
		}else{
			groupType="jiabi";
		}
		
		var titleString=null;		
		var attributeKeys=Object.keys(curObject);
		for(var j = 0, len = attributeKeys.length; j < len; j++){
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
			size : i*3+10,
			title : titleString
		});	
	}
	
	var rawEdges=rawData.edges;
	for(var i = 0, l = rawEdges.length; i < l; i++){
		var fromId=idMap[rawEdges[i]["fromId"]];
		var endId=idMap[rawEdges[i]["endId"]];
		edges.push({
			from : fromId,
			to : endId,
			arrows:'to',
			arrowStrikethrough:true,
			length : LENGTH_SERVER,
			width : WIDTH_SCALE * 1,
			color : RED
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
			color : GRAY,
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
